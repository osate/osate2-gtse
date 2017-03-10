package org.osate.atsv.integration.network;

import org.osate.aadl2.PropertyExpression;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.exception.UnhandledVariableTypeException;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

public class LiteralPropertyValue extends PropertyValue {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public LiteralPropertyValue(String componentPath, String propertyName, String value, ATSVVariableType type) {
		super(componentPath, propertyName, value, type);
	}

	@Override
	public PropertyExpression getValue() throws UnhandledVariableTypeException {
		if (type == ATSVVariableType.FLOAT || type == ATSVVariableType.DISCRETE_FLOAT) {
			return PropertyUtils.createRealValue(getValueAsFloat());
		} else if (type == ATSVVariableType.INTEGER) {
			return PropertyUtils.createIntegerValue(getValueAsInt());
		} else if (type == ATSVVariableType.STRING) {
			return PropertyUtils.createStringValue(getValueAsString());
		}
		throw new UnhandledVariableTypeException("Can't handle " + type + " as a literal!");
	}
}
