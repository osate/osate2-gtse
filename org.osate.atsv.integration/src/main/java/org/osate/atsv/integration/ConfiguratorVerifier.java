/*******************************************************************************
 * OSATE2-GTSE
 *
 * Copyright 2017 Carnegie Mellon University. All Rights Reserved.
 *
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * Released under an Eclipse Public License - v1.0-style license, please see
 * license.txt or contact permission@sei.cmu.edu for full terms.
 *
 * DM17-0002
 *******************************************************************************/
package org.osate.atsv.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osate.atsv.integration.EngineConfigModel.ConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.ImpliesConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.SetRestrictionConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.SetRestrictionDependentVariableModel;
import org.osate.atsv.integration.EngineConfigModel.SimpleConfiguratorModel;
import org.osate.atsv.integration.exception.ConfiguratorRepresentationException;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;
import org.osate.atsv.integration.exception.UnsupportedFeatureException;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.GateTranslator;

public class ConfiguratorVerifier {

	/**
	 * Mapping from configurator var names -> configurator Id's
	 */
	private Map<String, Integer> configuratorIdMap = new HashMap<>();

	/**
	 * Mapping from constant used in configurators -> Const object
	 */
	private Map<String, Const> constMap = new LinkedHashMap<>();

	/**
	 * Counter variable used in converting configurators to equality logic
	 */
	private int configuratorId = 1;

	/**
	 * Cache used for memoizing the equality substitution's recurrence relation
	 */
	private Map<String, BinaryExp> memoP = new HashMap<>();

	/**
	 * Set of variables used in the equality substitution algorithm.
	 */
	private List<List<Var>> P_A;

	/**
	 * Counter variable for vars used in the equality substitution algorithm
	 */
	private int eqsId = 1;

	/**
	 * Validates (ie, ensures the satisfiability of) the list of configurators
	 *
	 * @param configurators The constraints to check the consistency of
	 * @throws ConfiguratorRepresentationException An internal error that signifies a problem in
	 * converting the given configurators to a sat-solver-checkable format
	 * @throws UnsatisfiableConstraint Signifies the given constraints are unsatisfiable
	 * @throws UnsupportedFeatureException Signifies an unhandleable constraint was used
	 */
	public static void validate(List<ConfiguratorModel> configurators, Collection<TypeRestriction> typeRestrictions)
			throws ConfiguratorRepresentationException, UnsatisfiableConstraint, UnsupportedFeatureException {
		ConfiguratorVerifier cnfc = new ConfiguratorVerifier();

		// Phase 1: Configurators -> Equality Logic
		List<BinaryExp> conjEqs = cnfc.getEqualityLogic(configurators);

		// Phase 2: Add type restrictions
		conjEqs.addAll(cnfc.processTypeRestrictions(typeRestrictions));

		// Phase 3: Equality logic (with constants) -> Equality logic (without constants)
		conjEqs.addAll(cnfc.removeConstants());

		// Phase 4: Equality Logic -> Propositional Logic
		BinaryExp top = cnfc.equalitySubstitution(conjEqs);

		// Phase 5: Propositional Logic -> CNF
		ISolver solver = SolverFactory.newLight();
		GateTranslator gt = new GateTranslator(solver);
		try {
			// Phase 6: Require the whole expression to be true and check its satisfiability
			gt.gateTrue(top.getCNFVar(gt).id);
			if (!solver.isSatisfiable()) {
				throw new UnsatisfiableConstraint("The specified configurators are unsatisfiable");
			}
		} catch (TimeoutException e) {
			// This really shouldn't happen, we don't specify a timeout
			e.printStackTrace();
		} catch (ContradictionException e) {
			throw new UnsatisfiableConstraint("The specified configurators are unsatisfiable");
		}
	}

	/**
	 * This informs the SAT solver of the type restrictions on choicepoints
	 *
	 * @param typeRestrictions A collection of type restrictions
	 * @return BinaryExpressions encoding those type restrictions
	 */
	private Collection<BinaryExp> processTypeRestrictions(Collection<TypeRestriction> typeRestrictions) {
		// We encode each individual type restriction as a disjunction with as many terms are there
		// specified elements in the type

		// One disjunction
		Collection<BinaryExp> ors;

		// The collection of disjunctions
		Collection<BinaryExp> ret = new HashSet<>();
		BinaryExp bexp, oldBexp;
		for (TypeRestriction tr : typeRestrictions) {
			ors = new HashSet<>();

			// Get an equality for each particular element, ie, X = 5
			for (String value : tr.getAllowedValues()) {
				bexp = new BinaryExp();
				bexp.left = new Var(getId(tr.getVarName()));
				bexp.op = Op.EQ;
				bexp.right = getConst(value);
				ors.add(bexp);
			}
			Iterator<BinaryExp> orsIter = ors.iterator();
			bexp = new BinaryExp();
			if (ors.size() == 1) {
				ret.add(ors.iterator().next());
				continue;
			}

			// Connect the individual equalities together with disjunctions, ie, (x = 5) ∨ (x = 6)
			for (int i = 0; i < ors.size() - 2; i++) {
				oldBexp = bexp;
				bexp = new BinaryExp();
				oldBexp.left = orsIter.next();
				oldBexp.op = Op.OR;
				oldBexp.right = bexp;
			}
			bexp.left = orsIter.next();
			bexp.op = Op.OR;
			bexp.right = orsIter.next();

			// Add the large disjunction to the list of type restrictions
			ret.add(bexp);
		}
		return ret;
	}


	/**
	 * Transforms the supplied configurators into equality logic (ie, X = Y, Y != Z)
	 *
	 * @param configurators The set of configurators
	 * @return A list of binary expressions (which should be interpreted as being joined
	 * via AND's)
	 * @throws UnsupportedFeatureException Thrown if an unknown configurator type is encountered
	 */
	private List<BinaryExp> getEqualityLogic(List<ConfiguratorModel> configurators) throws UnsupportedFeatureException {
		List<BinaryExp> ret = new LinkedList<>();
		for (ConfiguratorModel cm : configurators) {
			BinaryExp bexp;
			if(cm instanceof SimpleConfiguratorModel) {
				bexp = getEqualityLogicSimple((SimpleConfiguratorModel) cm);
			} else if (cm instanceof ImpliesConfiguratorModel) {
				bexp = getEqualityLogicImplication((ImpliesConfiguratorModel) cm);
			} else if (cm instanceof SetRestrictionConfiguratorModel) {
				bexp = getEqualityLogicSetRestriction((SetRestrictionConfiguratorModel) cm);
			} else {
				throw new UnsupportedFeatureException("Unknown configurator model type");
			}
			ret.add(bexp);
		}
		return ret;
	}

	/**
	 * This, along with {@link #getConst(String)} implements of Algorithm 4.1.1 'Remove-Constants'
	 * from Kroening and Strichman's "Decision Procedures: An Algorithmic Point of View" (Second Edition)
	 * This method handles step 3.
	 *
	 * See https://doi.org/10.1007/978-3-662-50497-0
	 *
	 * Briefly, this method takes the fresh variables introduced in place of constants and creates new
	 * inequalities to ensure that they are not set equal to each other by the SAT solver
	 *
	 * @return The set of new constraints ensuring that the constants are not equal to one another
	 */
	private Collection<BinaryExp> removeConstants() {

		// Bail out immediately if we don't use multiple constants
		if (constMap.size() < 2) {
			return Collections.emptySet();
		}

		Set<BinaryExp> ret = new HashSet<>();
		Iterator<String> outer = constMap.keySet().iterator();
		Iterator<String> inner;
		BinaryExp bexp;

		// We advance the outer iterator at the bottom of the loop, so we prime it here
		String outerStr = outer.next();

		while (outer.hasNext()) {
			inner = constMap.keySet().iterator();

			// We advance the inner iterator until it's equal to the outer one
			while (!inner.next().equals(outerStr)) {
			}
			do {
				bexp = new BinaryExp();
				bexp.left = constMap.get(outerStr);
				bexp.right = constMap.get(inner.next());
				bexp.op = Op.NEQ;
				ret.add(bexp);
			} while (inner.hasNext());
			outerStr = outer.next();
		}
		return ret;
	}

	/**
	 * Encodes statements of the form "x=y" and "x≠y"
	 *
	 * @param scm The configurator model
	 * @return A statement in equality logic
	 */
	private BinaryExp getEqualityLogicSimple(SimpleConfiguratorModel scm) {
		BinaryExp bexp = new BinaryExp();
		bexp.left = new Var(getId(scm.getVarName1()));
		bexp.right = new Var(getId(scm.getVarName2()));
		bexp.op = scm.isEquality() ? Op.EQ : Op.NEQ;
		return bexp;
	}

	/**
	 * Encodes statements of the form "x=7 → y=9" and "x=7 → y≠9"
	 *
	 * @param scm The configurator model
	 * @return A statement in equality logic
	 */
	private BinaryExp getEqualityLogicImplication(ImpliesConfiguratorModel icm) {
		BinaryExp bexp = new BinaryExp();
		BinaryExp bexpl = new BinaryExp();
		bexp.left = bexpl;
		BinaryExp bexpr = new BinaryExp();
		bexp.right = bexpr;

		bexp.op = Op.IMPLY;

		bexpl.left = new Var(getId(icm.getVarName1()));
		bexpl.right = getConst(icm.getVarVal1());
		bexpl.op = Op.EQ;

		bexpr.left = new Var(getId(icm.getVarName2()));
		bexpr.right = getConst(icm.getVarVal2());
		bexpr.op = icm.isRequires() ? Op.EQ : Op.NEQ;

		return bexp;
	}

	/**
	 * Encodes statements of the form "x=7 → y∈{1,3,9}" and "x=7 → y∉{1,3,9}"
	 *
	 * @param scm The configurator model
	 * @return A statement in equality logic
	 */
	private BinaryExp getEqualityLogicSetRestriction(SetRestrictionConfiguratorModel srcm) {
		// Top level binary exp -- the one with the implication
		BinaryExp bexp = new BinaryExp();

		// Left hand side -- the equality we're testing for
		BinaryExp bexpl = new BinaryExp();
		bexp.left = bexpl;

		bexp.op = Op.IMPLY;

		bexpl.left = new Var(getId(srcm.getVarName1()));
		bexpl.right = getConst(srcm.getVarVal1());
		bexpl.op = Op.EQ;

		SetRestrictionDependentVariableModel depVarMdl = srcm.getDependentVariable();
		Collection<String> depVarVals = depVarMdl.getValues();
		Iterator<String> depVarIter = depVarVals.iterator();
		BinaryExp currentBexp = new BinaryExp();
		bexp.right = currentBexp;
		BinaryExp currentEqBexp = new BinaryExp();
		BinaryExp oldBexp;
		Var depVar = new Var(getId(depVarMdl.getVarName()));

		// Short circuit if the set is small
		if (depVarVals.size() == 0) {
			bexp.left = getConst("placeholder");
			bexp.right = getConst("placeholder");
			if (srcm.isMembership()) {
				bexp.op = Op.NEQ; // Trivially false -- impossible to be a member of an empty set
			} else {
				bexp.op = Op.EQ; // Trivially true -- impossible not to be a member of an empty set
			}
			return bexp;
		} else if (depVarVals.size() == 1) {
			BinaryExp bexpr = new BinaryExp();
			bexpr.left = depVar;
			bexpr.right = new Var(getId(depVarIter.next()));
			if (srcm.isMembership()) {
				bexpr.op = Op.EQ;
			} else {
				bexpr.op = Op.NEQ;
			}
			bexp.right = bexpr;
			return bexp;
		}

		// If we're testing for membership, we encode it as a disjunction of equalities
		// ie: x=7 → y∈{1,3,9} ⇒ (x=7)→((y=1)∨(y=3)∨(y=9))
		// Alternatively, exclusion is encoded as a conjunction of inequalities
		// ie: x=7 → y∉{1,3,9} ⇒ (x=7)→((y≠1)∧(y≠3)∧(y≠9))
		Op curOp = srcm.isMembership() ? Op.OR : Op.AND;
		Op eqOp = srcm.isMembership() ? Op.EQ : Op.NEQ;

		/*- Our encoding of ((y=1)∨(y=3)∨(y=9)) is a tree of the form:
		 *   ∨
		 *  / \
		 * y=1 ∨
		 *    / \
		 *  y=3 y=9
		 *
		 * This loop builds all but the right/bottom-most two leaves
		 */
		for (int i = 0; i < depVarVals.size() - 2; i++) {
			currentEqBexp.left = depVar;
			currentEqBexp.op = eqOp;
			currentEqBexp.right = getConst(depVarIter.next());

			currentBexp.left = currentEqBexp;
			currentEqBexp = new BinaryExp();
			currentBexp.op = curOp;
			oldBexp = currentBexp;
			currentBexp = new BinaryExp();
			oldBexp.right = currentBexp;
		}

		// We build the right/bottom-most two leaves here
		currentEqBexp.left = depVar;
		currentEqBexp.op = eqOp;
		currentEqBexp.right = getConst(depVarIter.next());
		currentBexp.left = currentEqBexp;
		currentBexp.op = curOp;
		currentEqBexp = new BinaryExp();
		currentEqBexp.left = depVar;
		currentEqBexp.op = eqOp;
		currentEqBexp.right = getConst(depVarIter.next());
		currentBexp.right = currentEqBexp;

		return bexp;
	}

	/**
	 * This builds an equisatisfiable (highly unbalanced) parse tree of propositional logic from
	 * the given list of equality statements.
	 *
	 * @param conjEqs A list of equality statements, ie, X = Y, Y = Z.
	 * @return A tree of propositional statements (ie X AND Y OR Z) that is equisatisfiable to the
	 * supplied equality statements
	 * @throws UnsatisfiableConstraint If the set of equality statements cannot be satisfied.
	 */
	private BinaryExp equalitySubstitution(List<BinaryExp> conjEqs) throws UnsatisfiableConstraint {

		// First, we check if we can short-circuit the full algorithm
		if (conjEqs.size() == 1) {
			// Only one equation, which is guaranteed to be unsatisfiable if it's X != X.
			BinaryExp eq = conjEqs.get(0);
			if (((((Var) eq.left).id) == ((Var) eq.right).id) && (eq.op == Op.NEQ)) {
				throw new UnsatisfiableConstraint("A variable has been required to not equal itself");
			}
			// Otherwise it's guaranteed to be satisfiable (either X = Y, X = X, or X != Y)
			// so we generate a trivial expression and send that to sat4j.
			BinaryExp trivialBexp = new BinaryExp();
			trivialBexp.left = new Var(eqsId++);
			trivialBexp.right = new Var(eqsId++);
			if (eq.op == Op.EQ || eq.op == Op.NEQ) {
				trivialBexp.op = Op.AND;
			} else {
				trivialBexp.op = eq.op;
			}
			return trivialBexp;
		}

		initP_A();
		BinaryExp topBexp = new BinaryExp(); // This is the top-level bexp that will be returned
		BinaryExp currentBexp = topBexp; // The current Bexp -- left half is the current term, right half is a pointer to the rest of the tree
		BinaryExp formerBexp = null;
		Iterator<BinaryExp> eqsIter = conjEqs.iterator();
		BinaryExp bexp = eqsIter.next();

		// We'll always enter the loop once, since we checked previously for the case where we have one
		// equation
		while (eqsIter.hasNext()) {
			currentBexp.op = Op.AND;
			currentBexp.left = eqs(bexp);
			currentBexp.leftSign = bexp.op == Op.NEQ ? false : true;

			formerBexp = currentBexp;
			currentBexp.right = new BinaryExp();
			currentBexp = (BinaryExp) currentBexp.right;
			bexp = eqsIter.next();
		}
		formerBexp.right = eqs(bexp);
		formerBexp.rightSign = bexp.op == Op.NEQ ? false : true;
		return topBexp;
	}

	/**
	 * This, along with {@link #getP(int, int, int, boolean, boolean)} actually implements the
	 * recurrence relation from <a href="https://doi.org/10.1016/S1571-0661(04)80661-3" target="_top">this paper</a>
	 * by Zantema and Groote.
	 *
	 * @param bexp The binary expression in equality logic (ie, its operator is Eq or Neq)
	 * @return A variable or (propositional, ie, its operators are And / Or) binary expression that is
	 * equisatisfiable with bexp.
	 */
	private Node eqs(BinaryExp bexp) {
		if (bexp.op == Op.OR || bexp.op == Op.AND || bexp.op == Op.IMPLY) {
			BinaryExp newBexp = new BinaryExp();
			newBexp.left = eqs((BinaryExp) bexp.left);
			newBexp.right = eqs((BinaryExp) bexp.right);
			newBexp.op = bexp.op;
			return newBexp;
		}

		int i = ((Var) bexp.left).id;
		int j = ((Var) bexp.right).id;
		if (i < j) {
			return getP(1, i, j, bexp.leftSign, bexp.rightSign);
		} else if (j < i) {
			return getP(1, j, i, bexp.leftSign, bexp.rightSign);
		} else {
			// Trivial case
			return new Var(eqsId++);

		}
	}

	/**
	 * Adds the specified propositional statement to the solver, using Sat4J's implementation
	 * of the Tseitin algorithm. The statement takes the form:
	 * <pre>freshId = Op(ids[0], ids[1], ...)</pre>
	 *
	 * @see <a href="https://en.wikipedia.org/wiki/Tseytin_transformation" target="_top">Wikipedia's explanation</a>
	 * @see <a href="https://www.cs.cmu.edu/~ggordon/780-fall07/lectures/05-logic+planning.pdf" target="_top">Geoff Gordon's notes</a>
	 *
	 * Note that as Tseitin's algorithm is commonly used in planning logic gates, the solver
	 * subclass we're using is called "GateTranslator"
	 *
	 * @param gt A reference to the solver we're using.
	 * @param op The operator for the statement, either AND or OR
	 * @param freshId The id of the fresh variable used to represent this clause.
	 * @param ids The ids of the variables that make up the statement
	 * @throws ConfiguratorRepresentationException Thrown if an operator other than AND or OR is used
	 * @throws ContradictionException Thrown if the statement is a contradiction
	 */
	private void getCNFRep(GateTranslator gt, Op op, int freshId, int... ids)
			throws ConfiguratorRepresentationException, ContradictionException {
		if (op == Op.AND) {
			gt.and(freshId, new VecInt(ids));
		} else if (op == Op.OR) {
			gt.or(freshId, new VecInt(ids));
		} else if (op == Op.IMPLY) {
			if (ids.length > 2) {
				throw new ConfiguratorRepresentationException("Implications with > 2 operands aren't allowed");
			}
			// p→q is equivalent to ¬(p∧¬q) which by De Morgan's law is equivalent to ¬p∨q
			ids[0] = -ids[0];
			gt.or(freshId, new VecInt(ids));
		} else {
			throw new ConfiguratorRepresentationException("Invalid operator passed to CNF converter");
		}
	}

	/**
	 * Initializes the set of variables used in the equality substitution
	 */
	private void initP_A() {
		P_A = new ArrayList<>();
		P_A.add(null); // The equality substitution algorithm is 1-indexed instead of 0-indexed,
		// so we insert a placeholder here
		for (int i = 1; i <= configuratorId - 2; i++) {
			ArrayList<Var> row = new ArrayList<>();
			row.add(null); // and another placeholder here.
			P_A.add(row);
			for (int j = 1; j <= i; j++) {
				row.add(null); // We only want values where i < j, so we put placeholders where
				// that's not the case
			}
			for (int j = i + 1; j <= configuratorId - 1; j++) {
				row.add(j, new Var(eqsId++));
			}
		}
	}

	/**
	 * This, along with {@link #eqs(BinaryExp)} actually implements the recurrence relation from
	 * <a href="https://doi.org/10.1016/S1571-0661(04)80661-3" target="_top">this paper</a> by Zantema and Groote.
	 * See the paper for the meaning of the indices k, i and j.	 *
	 *
	 * @param k
	 * @param i
	 * @param j
	 * @param leftSign If the left operand should be positive (true) or negated (false)
	 * @param rightSign If the right operand should be positive (true) or negated (false)
	 * @return A variable or (propositional, ie, its operators are And / Or) binary expression that is
	 * equisatisfiable with bexp.
	 */
	private Node getP(int k, int i, int j, boolean leftSign, boolean rightSign) {
		if (k == i) {
			return P_A.get(i).get(j);
		}
		String keyName = String.valueOf(k) + "-" + String.valueOf(i) + "-" + String.valueOf(j);
		if (memoP.containsKey(keyName)) {
			return memoP.get(keyName);
		}
		BinaryExp ret = new BinaryExp();

		BinaryExp subLeft = new BinaryExp();
		ret.left = subLeft;
		subLeft.left = P_A.get(k).get(i);
		subLeft.leftSign = leftSign;
		subLeft.op = Op.AND;
		subLeft.right = P_A.get(k).get(j);
		subLeft.rightSign = leftSign;

		ret.op = Op.OR;

		TernaryExp subRight = new TernaryExp();
		ret.right = subRight;
		subRight.left = P_A.get(k).get(i);
		subRight.leftSign = !rightSign;
		subRight.op = Op.AND;
		subRight.mid = P_A.get(k).get(j);
		subRight.midSign = !rightSign;
		subRight.right = getP(k + 1, i, j, rightSign, rightSign);

		memoP.put(keyName, ret);

		return ret;
	}

	/**
	 * Gets the id associated with a given variable name. Maintains and uses the {@link #configuratorIdMap}.
	 * @param varName The variable name to look up
	 * @return The variable's id
	 */
	private int getId(String varName) {
		if (configuratorIdMap.containsKey(varName)) {
			return configuratorIdMap.get(varName);
		} else {
			configuratorIdMap.put(varName, configuratorId);
			return configuratorId++;
		}
	}

	/**
	 * This, along with {@link #removeConstants()} implements of Algorithm 4.1.1 'Remove-Constants'
	 * from Kroening and Strichman's "Decision Procedures: An Algorithmic Point of View" (Second Edition)
	 * This method handles step 2.
	 *
	 * See https://doi.org/10.1007/978-3-662-50497-0
	 *
	 * Briefly, this method takes constants and replaces them with fresh variables. It maintains and uses
	 * {@link #constMap} to ensure the same fresh variable is used if a constant value shows up in multiple
	 * places
	 *
	 * @return A fresh variable representing this constant.
	 */
	private Const getConst(String constStr) {
		if (!constMap.containsKey(constStr)) {
			constMap.put(constStr, new Const(configuratorId++));
		}
		return constMap.get(constStr);
	}

	/**
	 * Operators for the expressions. Somewhat absuively, includes operators for both equality and
	 * propositional logic
	 */
	private enum Op {
		NEQ, EQ, AND, OR, IMPLY
	};

	private interface Node {
		Var getCNFVar(GateTranslator gt) throws ConfiguratorRepresentationException, ContradictionException;

		String toString(boolean positive);
	}

	private abstract class Exp implements Node {
		Node left;
		boolean leftSign = true;// False if negated, true if not-negated (default)
		Node right;
		boolean rightSign = true;
		Op op;
	}

	private class TernaryExp extends Exp {
		Node mid;
		boolean midSign = true;
		private Var freshVar;

		@Override
		public String toString() {
			return toString(true);
		}

		@Override
		public String toString(boolean positive) {
			String signStr = positive ? "" : "~";
			return signStr + op.toString() + "(" + getLeftAsBexp().toString(true) + "," + right.toString(rightSign)
			+ ")";
		}

		public BinaryExp getLeftAsBexp() {
			BinaryExp bexp = new BinaryExp();

			bexp.left = left;
			bexp.leftSign = leftSign;
			bexp.op = op;
			bexp.right = mid;
			bexp.rightSign = midSign;

			return bexp;
		}

		@Override
		public Var getCNFVar(GateTranslator gt) throws ConfiguratorRepresentationException, ContradictionException {
			if (freshVar == null) {
				freshVar = new Var(eqsId++);
				Var leftVar = left.getCNFVar(gt);
				Var midVar = mid.getCNFVar(gt);
				Var rightVar = right.getCNFVar(gt);
				int leftId, midId, rightId;
				if (!leftSign && left instanceof Var) {
					leftId = leftVar.getNot().id;
				} else {
					leftId = leftVar.id;
				}
				if (!midSign && mid instanceof Var) {
					midId = midVar.getNot().id;
				} else {
					midId = midVar.id;
				}
				if (!rightSign && right instanceof Var) {
					rightId = rightVar.getNot().id;
				} else {
					rightId = rightVar.id;
				}
				getCNFRep(gt, op, freshVar.id, leftId, midId, rightId);
			}
			return freshVar;
		}
	}

	private class BinaryExp extends Exp {
		private Var freshVar = null;

		@Override
		public String toString() {
			return toString(true);
		}

		@Override
		public String toString(boolean positive) {
			String signStr = positive ? "" : "~";
			return signStr + op.name() + "(" + left.toString(leftSign) + "," + right.toString(rightSign) + ")";
		}

		@Override
		public Var getCNFVar(GateTranslator gt) throws ConfiguratorRepresentationException, ContradictionException {
			if (freshVar == null) {
				freshVar = new Var(eqsId++);
				Var leftVar = left.getCNFVar(gt);
				Var rightVar = right.getCNFVar(gt);
				int leftId, rightId;
				if (!leftSign) {
					leftId = leftVar.getNot().id;
				} else {
					leftId = leftVar.id;
				}
				if (!rightSign) {
					rightId = rightVar.getNot().id;
				} else {
					rightId = rightVar.id;
				}
				getCNFRep(gt, op, freshVar.id, leftId, rightId);
			}
			return freshVar;
		}
	}

	private class Var implements Node {
		int id = -1;
		private Var notVar = null;

		@Override
		public String toString() {
			return toString(true);
		}

		@Override
		public String toString(boolean positive) {
			String signStr = positive ? "" : "~";
			return signStr + id + "";
		}

		public Var(int id) {
			this.id = id;
		}

		@Override
		public Var getCNFVar(GateTranslator gt) {
			return this;
		}

		public Var getNot() {
			if (notVar == null) {
				notVar = new Var(-id);
			}
			return notVar;
		}
	}

	private class Const extends Var {
		public Const(int id) {
			super(id);
			// TODO Auto-generated constructor stub
		}
	}
}
