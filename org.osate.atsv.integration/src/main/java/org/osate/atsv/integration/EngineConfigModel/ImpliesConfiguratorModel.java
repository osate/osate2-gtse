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
package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlElement;

/**
 * This models an individual implies (requires or forbids relationship between variables)
 * configurator -- essentially a constraint on the inputs ATSV will generate
 */
public class ImpliesConfiguratorModel extends ConfiguratorModel {

	private boolean isRequires;

	@XmlElement(name = "VariableValue")
	private String varVal1;

	@XmlElement(name = "DependentVariableValue")
	private String varVal2;

	public ImpliesConfiguratorModel(String varName1, String varVal1, String varName2, String varVal2,
			boolean isRequires) {
		super(varName1, varName2);
		this.varVal1 = varVal1;
		this.varVal2 = varVal2;
		this.isRequires = isRequires;
	}

	@Override
	@XmlElement(name = "DependentVariable")
	public String getVarName2() {
		return this.varName2;
	}

	public String getVarVal1() {
		return varVal1;
	}

	public void setVarVal1(String varVal1) {
		this.varVal1 = varVal1;
	}

	public String getVarVal2() {
		return varVal2;
	}

	public void setVarVal2(String varVal2) {
		this.varVal2 = varVal2;
	}

	public boolean isRequires() {
		return isRequires;
	}

	public boolean isForbids() {
		return !isRequires;
	}
}
