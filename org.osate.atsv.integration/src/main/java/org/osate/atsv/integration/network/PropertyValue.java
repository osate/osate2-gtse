package org.osate.atsv.integration.network;

import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class PropertyValue extends ChoicePointSpecification {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public PropertyValue(String componentName, String itemName, String value, ATSVVariableType type) {
		super(componentName, itemName, value, type);
	}
}
