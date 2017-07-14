/*******************************************************************************
 * OSATE2-GTSE
 *
 * Copyright 2017 Carnegie Mellon University. All Rights Reserved.
 *
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * Released under an Eclipse Public License - v1.0-style license, please see
 * license.txt or contact permission@sei.cmu.edu for full terms. 
 *
 * DM17-0002
 *******************************************************************************/
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
