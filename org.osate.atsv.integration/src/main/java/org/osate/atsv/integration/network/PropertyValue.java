package org.osate.atsv.integration.network;

import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class PropertyValue extends ChoicePointSpecification {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	protected String propertyName;

	public PropertyValue(String componentPath, String propertyName, String value, ATSVVariableType type) {
		super(componentPath, value, type);
		this.propertyName = propertyName;
	}

	public String getRawValue() {
		return value;
	}

	public String getPropertyName() {
		return propertyName;
	}

}