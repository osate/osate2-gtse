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
