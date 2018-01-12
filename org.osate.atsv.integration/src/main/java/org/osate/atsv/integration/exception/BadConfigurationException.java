package org.osate.atsv.integration.exception;

public class BadConfigurationException extends Exception {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public BadConfigurationException(String description) {
		super(description);
	}
}
