package org.osate.gtse.demo.failureprobability;

import java.text.DecimalFormat;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.univariate.BrentOptimizer;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.network.Response;

/**
 * This class calculates the conservative posterior mean of a system's
 * probability of failure on demand (pfd) and conservative probability that the
 * system is perfect (p_0(n)). It is an implementation of formulas 12 and 13
 * from Bishop, Bloomfield, Littlewood, Povyakalo, and Wright's 2011 IEEE TSE
 * paper "Toward a Formalism for Conservative Claims about the Dependability of
 * Software-Based Systems" https://dx.doi.org/10.1109/TSE.2010.67
 *
 * @author sprocter
 *
 */
public class FailureProbability extends AbstractAnalysis {

	@Override
	public void runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			IProgressMonitor progressMonitor, Response resp) {

		// TODO: Get these values from properties in the model
		// TODO: Aggregate these values from components from across the model
		worstCasePfdAndP_0n(100, .01, .0005, .9);
	}

	/**
	 * Test-driver / figure generation method.
	 * @param args empty
	 */
	public static void main(String[] args) {
		FailureProbability me = new FailureProbability();
		DecimalFormat formatter = new DecimalFormat("#0.0000000000000");

		double x = .1;
		double a = .5;
		int min = 0;
		int max = 10000000;

		System.out.println(min + " " + formatter.format(me.worstCasePfdAndP_0n(min, x, .0005, a)));
		for (int scaleFactor = min + 1; scaleFactor < max; scaleFactor *= 10) {
			for (int i = 1; i < 10; i++) {
				System.out.println(String.valueOf(i * scaleFactor) + " "
						+ formatter.format(me.worstCasePfdAndP_0n(i * scaleFactor, x, .0005, a)));
			}
		}
		System.out.println(max + " " + formatter.format(me.worstCasePfdAndP_0n(max, x, .0005, a)));

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
	 */
	private double worstCasePfdAndP_0n(int n, double x, double y, double alpha) {
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
	private double conservativePosteriorProbWithConfidence(int n, double x, double y, double alpha, double z) {
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
	private double posteriorMean(double z, int n, double x, double y, double alpha) {
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
	private class PosteriorMeanFunc implements UnivariateFunction {
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
}
