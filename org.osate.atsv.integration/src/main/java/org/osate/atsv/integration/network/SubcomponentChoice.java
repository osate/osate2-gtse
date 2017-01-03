package org.osate.atsv.integration.network;

import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class SubcomponentChoice extends ChoicePointSpecification {

	/**
	 * Default serial version 
	 */
	private static final long serialVersionUID = 1L;

	public SubcomponentChoice(String componentName, String choicepointName, String value, ATSVVariableType type) {
		super(componentName, choicepointName, value, type);
	}

}