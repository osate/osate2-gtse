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
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable {

	/**
	 * Default serial version 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A mapping from output variable name to value
	 */
	private Map<String, String> variables = new HashMap<>();

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

	public Response() {
		addVariable("ValidModel", "1.0");
	}

	public void setException(Exception e) {
		markInvalid();
		exception = e;
	}

	public boolean hasException() {
		return exception != null;
	}

	public Exception getException() {
		return exception;
	}

	public Response(Map<String, String> vars) {
		variables.putAll(vars);
	}

	public void addVariable(String varName, String value) {
		variables.put(varName, value);
	}

	public Map<String, String> getVariables() {
		return variables;
	}

	public void markInvalid() {
		addVariable("ValidModel", "0.0");
		valid = false;
	}

	public boolean isValidModel() {
		return valid;
	}
}
