package org.osate.atsv.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.osate.atsv.integration.EngineConfigModel.ConfiguratorModel;
import org.osate.atsv.integration.exception.ConfiguratorRepresentationException;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;
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
	 * Counter variable used in converting configurators to equality logic
	 */
	private int configuratorId = 1;

	/**
	 * Cache used for memoizing the equality substitution's recurrence relation
	 */
	private Map<String, BinaryExp> memoP = new HashMap<>();

	/**
	 * Set of variables used in the equality substitution algorithm
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
	 */
	public static void validate(List<ConfiguratorModel> configurators)
			throws ConfiguratorRepresentationException, UnsatisfiableConstraint {
		ConfiguratorVerifier cnfc = new ConfiguratorVerifier();

		// Phase 1: Configurators -> Equality Logic
		List<BinaryExp> conjEqs = cnfc.getEqualityLogic(configurators);

		// Phase 2: Equality Logic -> Propositional Logic
		BinaryExp top = cnfc.equalitySubstitution(conjEqs);

		// Phase 3: Propositional Logic -> CNF
		ISolver solver = SolverFactory.newLight();
		GateTranslator gt = new GateTranslator(solver);
		try {
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
	 * Transforms the supplied configurators into equality logic (ie, X = Y, Y != Z)
	 * 
	 * @param configurators The set of configurators
	 * @return A list of binary expressions (which should be interpreted as being joined
	 * via AND's)
	 */
	private List<BinaryExp> getEqualityLogic(List<ConfiguratorModel> configurators) {
		List<BinaryExp> ret = new LinkedList<>();
		for (ConfiguratorModel cm : configurators) {
			BinaryExp bexp = new BinaryExp();
			bexp.left = new Var(getId(cm.getVarName1()));
			bexp.right = new Var(getId(cm.getVarName2()));
			bexp.op = cm.isEquality() ? Op.EQ : Op.NEQ;
			ret.add(bexp);
		}
		return ret;
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
			trivialBexp.op = Op.AND;
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
		int i = ((Var) bexp.left).id;
		int j = ((Var) bexp.right).id;
		if (i < j) {
			return getP(1, i, j, bexp.leftSign, bexp.rightSign);
		} else if (j > i) {
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
		for (int m = 1; m <= configuratorId - 2; m++) {
			ArrayList<Var> row = new ArrayList<>();
			row.add(null); // and another placeholder here.
			P_A.add(row);
			for (int n = 1; n <= configuratorId - 1; n++) {
				row.add(n, new Var(eqsId++));
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
	 * Operators for the expressions. Somewhat absuively, includes operators for both equality and
	 * propositional logic
	 */
	private enum Op {
		NEQ, EQ, AND, OR
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
				if (!leftSign && left instanceof Var) {
					leftId = leftVar.getNot().id;
				} else {
					leftId = leftVar.id;
				}
				if (!rightSign && right instanceof Var) {
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
			String signStr = positive ? "'" : "'~";
			return signStr + String.valueOf((char) (id + 96)) + "'";
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
}
