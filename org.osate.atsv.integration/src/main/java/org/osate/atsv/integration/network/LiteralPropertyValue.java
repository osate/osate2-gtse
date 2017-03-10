package org.osate.atsv.integration.network;

import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class LiteralPropertyValue extends PropertyValue {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public LiteralPropertyValue(String componentPath, String propertyName, String value, ATSVVariableType type) {
		super(componentPath, propertyName, value, type);
	}
}
