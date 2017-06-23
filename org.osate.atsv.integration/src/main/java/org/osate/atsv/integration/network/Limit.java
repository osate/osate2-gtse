package org.osate.atsv.integration.network;

import java.io.Serializable;

/**
 * This class describes the user-specifiable limit / threshold which
 * makes a model invalid if exceeded. For example, if a user doesn't
 * want any systems where the costs exceeds 1,000,000, that request
 * would be handled with this class.    
 * 
 * @author Sam
 */
public class Limit implements Serializable {

	/**
	 * Default serial version 
	 */
	private static final long serialVersionUID = 1L;

	private RelationalOperator op;
	private float limit;

	private enum RelationalOperator {
		GT, GTE, EQ, NEQ, LT, LTE;
	}

	public Limit(String Op, String limit) {
		this.op = getOp(Op);
		this.limit = Float.valueOf(limit);
	}

	public boolean checkLimit(String val) throws Exception {
		float actual = Float.valueOf(val);
		if (op == RelationalOperator.GT) {
			return actual > limit;
		} else if (op == RelationalOperator.GTE) {
			return actual >= limit;
		} else if (op == RelationalOperator.EQ) {
			return actual == limit;
		} else if (op == RelationalOperator.NEQ) {
			return actual != limit;
		} else if (op == RelationalOperator.LT) {
			return actual < limit;
		} else if (op == RelationalOperator.LTE) {
			return actual <= limit;
		} else {
			// This should be impossible.
			throw new Exception("Corrupt relational operator!");
		}
	}

	private RelationalOperator getOp(String op) {
		return RelationalOperator.valueOf(op.toUpperCase());
	}

}
