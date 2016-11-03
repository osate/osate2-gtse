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
	
	public Response() {
	}

	public void setException(Exception e){
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
	
	public Map<String, String> getVariables(){
		return variables;
	}
}
