package org.osate.atsv.integration;

import java.util.Collection;

import org.osate.atsv.integration.EngineConfigModel.ValuesModel;

public class TypeRestriction {

	private String varName;

	private Collection<String> allowedValues;

	public TypeRestriction(String varName, ValuesModel allowedValues) {
		this.varName = varName;
		this.allowedValues = allowedValues.getValueSet();
	}

	public String getVarName() {
		return varName;
	}

	public Collection<String> getAllowedValues() {
		return allowedValues;
	}
}
