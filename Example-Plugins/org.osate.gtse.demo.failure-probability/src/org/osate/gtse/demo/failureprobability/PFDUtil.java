/**
 * Copyright (c) 2004-2020 Carnegie Mellon University and others. (see Contributors file).
 * All Rights Reserved.
 *
 * NO WARRANTY. ALL MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE
 * OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT
 * MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * SPDX-License-Identifier: EPL-2.0
 *
 * Created, in part, with funding and support from the United States Government. (see Acknowledgments file).
 *
 * This program includes and/or can make use of certain third party source code, object code, documentation and other
 * files ("Third Party Software"). The Third Party Software that is used by this program is dependent upon your system
 * configuration. By using this program, You agree to comply with any and all relevant Third Party Software terms and
 * conditions contained in any such Third Party Software or separate license file distributed with such Third Party
 * Software. The parties who own the Third Party Software ("Third Party Licensors") are intended third party benefici-
 * aries to this license with respect to the terms applicable to their Third Party Software. Third Party Software li-
 * censes only apply to the Third Party Software and not any other portion of this program or this program as a whole.
 */
package org.osate.gtse.demo.failureprobability;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.univariate.BrentOptimizer;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.errormodel.FaultTree.Event;
import org.osate.aadl2.errormodel.FaultTree.FaultTree;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.properties.InvalidModelException;
import org.osate.xtext.aadl2.properties.util.GetProperties;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

/**
 * This class calculates the conservative posterior mean of a system's
 * probability of failure on demand (pfd) and conservative probability that the
 * system is perfect (p_0(n)). It is an implementation of formulas 12 and 13
 * from Bishop, Bloomfield, Littlewood, Povyakalo, and Wright's 2011 IEEE TSE
 * paper "Toward a Formalism for Conservative Claims about the Dependability of
 * Software-Based Systems" https://dx.doi.org/10.1109/TSE.2010.67
 */
class PFDUtil {

	static void fillProbabilities(FaultTree ftaModel) {
		for (Event event : ftaModel.getEvents()) {
			EObject element = event.getRelatedEMV2Object();
			if (element instanceof NamedElement) {
				fillProbability(event);
			}
		}

	}

	private static void fillProbability(Event event) {
		InstanceObject io = (InstanceObject) event.getRelatedInstanceObject();
		int successes = 0;
		double estimate = 0.0;
		double doubt = 0.0;
		double perfect = 0.0;
		try {
			Property p = GetProperties.lookupPropertyDefinition(io, "PFD_Properties", "Successful_Tests");
			successes = (int) PropertyUtils.getIntegerValue(io, p, 0);
			p = GetProperties.lookupPropertyDefinition(io, "PFD_Properties", "Estimated_PFD");
			estimate = PropertyUtils.getRealValue(io, p, 0.0);
			p = GetProperties.lookupPropertyDefinition(io, "PFD_Properties", "Assessor_Doubt");
			doubt = PropertyUtils.getRealValue(io, p, 0.0);
			p = GetProperties.lookupPropertyDefinition(io, "PFD_Properties", "Estimated_Perfect");
			perfect = PropertyUtils.getRealValue(io, p, 0.0);
		} catch (InvalidModelException e) {
		}
		double pfd = worstCasePfdAndP_0n(successes, doubt, estimate, perfect);
		event.setAssignedProbability(new BigDecimal(pfd, MathContext.UNLIMITED));
	}

	/**
	 * Calculates the conservative posterior mean of a component's probability
	 * of failure on demand and conservative probability that the system is
	 * perfect
	 *
	 * @param n The number of successful tests the component has gone through
	 * @param x The assessor's doubt about the estimated pfd
	 * @param y The system's estimated probability of failure on a given demand
	 * @param alpha The estimated probability that the system is perfect
	 * @return
	 */
	private static double worstCasePfdAndP_0n(int n, double x, double y, double alpha) {
		BrentOptimizer bop = new BrentOptimizer(2 * FastMath.ulp(1d), Double.MIN_VALUE);
		UnivariateObjectiveFunction uof = new UnivariateObjectiveFunction(new PosteriorMeanFunc(n, x, y, alpha));
		SearchInterval si = new SearchInterval(0.0, 1.0);
		UnivariatePointValuePair result = bop.optimize(uof, MaxEval.unlimited(), si, GoalType.MINIMIZE);

		double mean = 1 - result.getValue();
		double p_On = conservativePosteriorProbWithConfidence(n, x, y, alpha, result.getPoint());
		return mean;
	}

	/**
	 * Combines the inputs into "the expert's posterior probability that the
	 * system is fault free"
	 *
	 * @param n The number of successful tests the component has gone through
	 * @param x The assessor's doubt about the estimated pfd
	 * @param y The system's estimated probability of failure on a given demand
	 * @param alpha The estimated probability that the system is perfect
	 * @param z Where the probability mass of the assessor's doubt is concentrated
	 * @return Posterior probability the system is perfect
	 */
	private static double conservativePosteriorProbWithConfidence(int n, double x, double y, double alpha, double z) {
		double top_term_1 = alpha;
		double bot_term_1 = FastMath.pow((1 - y), n) * (1 - x - alpha);
		double bot_term_2 = FastMath.pow((1 - z), n) * x;
		double bot_term_3 = alpha;

		double ret_val = top_term_1 / (bot_term_1 + bot_term_2 + bot_term_3);

		return ret_val;
	}

	/**
	 * Combines the inputs into the "most pessimistic improper density [function]"
	 *
	 * @param z Where the probability mass of the assessor's doubt is concentrated
	 * @param n The number of successful tests the component has gone through
	 * @param x The assessor's doubt about the estimated pfd
	 * @param y The system's estimated probability of failure on a given demand
	 * @param alpha The estimated probability that the system is perfect
	 * @return Worst-case pfd
	 */
	private static double posteriorMean(double z, int n, double x, double y, double alpha) {
		double one_y_n = FastMath.pow((1 - y), n) * (1 - x - alpha);
		double one_z_n_x = FastMath.pow((1 - z), n) * x;
		double top_term_1 = y * one_y_n;
		double top_term_2 = z * one_z_n_x;
		double bot_term_1 = one_y_n;
		double bot_term_2 = one_z_n_x;
		double bot_term_3 = alpha;
		double ret_val = (top_term_1 + top_term_2) / (bot_term_1 + bot_term_2 + bot_term_3);

		return 1 - ret_val;
	}

	/**
	 * This internal class is used by the {@link BrentOptimizer} to find the function's
	 * minimum
	 *
	 * @author sprocter
	 */
	private static class PosteriorMeanFunc implements UnivariateFunction {
		/**
		 * The number of successful tests the component has gone through
		 */
		private int n;

		/**
		 * The assessor's doubt about the estimated pfd
		 */
		private double x;

		/**
		 * The system's estimated probability of failure on a given demand
		 */
		private double y;

		/**
		 * The estimated probability that the system is perfect
		 */
		private double alpha;

		/**
		 * Constructor, simply initializes fields
		 * @param n The number of successful tests the component has gone through
		 * @param x The assessor's doubt about the estimated pfd
		 * @param y The system's estimated probability of failure on a given demand
		 * @param alpha The estimated probability that the system is perfect
		 */
		public PosteriorMeanFunc(int n, double x, double y, double alpha) {
			this.n = n;
			this.x = x;
			this.y = y;
			this.alpha = alpha;
		}

		@Override
		public double value(double z) {
			return posteriorMean(z, n, x, y, alpha);
		}
	}

	/**
	 * Test-driver / figure generation method.
	 * @param args empty
	 */
	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("#0.0000000000000");

		double x = .1;
		double a = .5;
		int min = 0;
		int max = 10000000;

		System.out.println(min + " " + formatter.format(worstCasePfdAndP_0n(min, x, .0005, a)));
		for (int scaleFactor = min + 1; scaleFactor < max; scaleFactor *= 10) {
			for (int i = 1; i < 10; i++) {
				System.out.println(String.valueOf(i * scaleFactor) + " "
						+ formatter.format(worstCasePfdAndP_0n(i * scaleFactor, x, .0005, a)));
			}
		}
		System.out.println(max + " " + formatter.format(worstCasePfdAndP_0n(max, x, .0005, a)));

	}

}
