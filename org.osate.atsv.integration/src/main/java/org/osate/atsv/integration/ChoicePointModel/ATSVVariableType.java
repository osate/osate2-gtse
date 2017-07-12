package org.osate.atsv.integration.ChoicePointModel;

public enum ATSVVariableType {
	FLOAT(0), DISCRETE_FLOAT(1), STRING(2), INTEGER(4);

	private int typeVal;

	ATSVVariableType(int typeVal) {
		this.typeVal = typeVal;
	}

	public int getTypeVal() {
		return typeVal;
	}

	public static ATSVVariableType getTypeByName(String typename) {
		if (typename.equalsIgnoreCase("float")) {
			return ATSVVariableType.FLOAT;
		} else if (typename.equalsIgnoreCase("int") || typename.equalsIgnoreCase("integer")) {
			return ATSVVariableType.INTEGER;
		} else if (typename.equalsIgnoreCase("string")) {
			return ATSVVariableType.STRING;
		} else if (typename.equalsIgnoreCase("discretefloat")) {
			return ATSVVariableType.DISCRETE_FLOAT;
		}
		return null;
	}

	public static String getDefaultFromType(ATSVVariableType type) {
		if (type == ATSVVariableType.STRING) {
			return "UNSET_STRING";
		} else if (type == ATSVVariableType.FLOAT) {
			return String.valueOf((float) 0);
		} else if (type == ATSVVariableType.INTEGER) {
			return String.valueOf(0);
		}
		return null;
	}
}
