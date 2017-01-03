package org.osate.atsv.integration.network;

import java.io.Serializable;

import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class ChoicePointSpecification implements Serializable {

	/**
	 * Default serial version 
	 */
	private static final long serialVersionUID = 1L;
	protected String componentName;
	protected String choicepointName;
	protected String value;
	protected ATSVVariableType type;

	public ChoicePointSpecification(String componentName, String choicepointName, String value, ATSVVariableType type) {
		this.componentName = componentName;
		this.choicepointName = choicepointName;
		this.value = value;
		this.type = type;
	}

	public float getValueAsFloat() {
		if (type != ATSVVariableType.FLOAT) {
			return Float.NaN;
		}
		return Float.valueOf(value);
	}

	public int getValueAsInt() {
		if (type != ATSVVariableType.INTEGER) {
			return Integer.MIN_VALUE; // TODO: Is there a better default value we can use?
		}
		return Integer.valueOf(value);
	}

	public String getValueAsString() {
		if (type != ATSVVariableType.STRING) {
			return null;
		}
		return value;
	}

	public String getChoicepointName() {
		return choicepointName;
	}

	public String getComponentName() {
		return componentName;
	}

	public ATSVVariableType getType() {
		return type;
	}
}