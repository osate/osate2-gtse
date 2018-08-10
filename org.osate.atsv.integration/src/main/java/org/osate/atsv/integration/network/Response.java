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
package org.osate.atsv.integration.network;

import java.io.Serializable;
import java.util.Map;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.standalone.ATSVVar;
import org.osate.atsv.standalone.ATSVVarCollection;

public class Response implements Serializable {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The variables produced by running the analyses
	 */
	private ATSVVarCollection variables = new ATSVVarCollection();

	/**
	 * The root exception, if something went wrong
	 */
	private Exception exception = null;

	/**
	 * This is marked as false if either
	 * 1. An "all or nothing" analysis finds a problem with instance
	 * model created by the request (ie, it's unschedulable, or has
	 * inconsistent connections), or
	 * 2. Instantiating / analyzing it generates an exception
	 */
	private boolean valid = true;

	/**
	 * Pointer to the set of variables in the response. Not meant to be shared / used
	 * outside this class.
	 */
	private Map<String, ATSVVar> innerMap = null;;

	public Response() {
		addVariable("ValidModel", ATSVVariableType.FLOAT, "1.0");
		addVariable("InvalidReason", ATSVVariableType.STRING, "(None)");
	}

	public void setException(Exception e) {
		markInvalid("Encountered exception: " + e.getMessage());
		exception = e;
	}

	public boolean hasException() {
		return exception != null;
	}

	public Exception getException() {
		return exception;
	}

//	public Response(Map<String, String> vars) {
//		variables.putAll(vars);
//	}

	public void addVariable(String varName, ATSVVariableType type, String value) {
		variables.addVar(varName, type, value);
	}

	public ATSVVarCollection getVariables() {
		return variables;
	}

	public void markInvalid(String reason) {
		if (innerMap == null) {
			innerMap = variables.getVars();
		}
		addVariable("ValidModel", ATSVVariableType.FLOAT, "0.0");
		String existingReasons = "";
		if (!innerMap.get("InvalidReason").getVal().equals("(None)")) {
			existingReasons = innerMap.get("InvalidReason").getVal() + ";\n";
		}
		addVariable("InvalidReason", ATSVVariableType.STRING, existingReasons + reason);
		valid = false;
	}

	public boolean isValidModel() {
		return valid;
	}
}
