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
import javax.xml.bind.annotation.XmlType;

/**
 * This class models the implication (requires and forbids) configurators.
 *
 * These restrictions take the following forms:
 *
 *   Requires -- Choosing one value means another choicepoint has to have a specific value that is not equal to the first value
 *     X = 7 → Y = 9
 *     X = 2 → Y = 4
 *   Forbids -- Choosing one value means another choicepoint can't take a specific value that is not equal to the first value
 *     X = 7 → Y ≠ 9
 *     X = 2 → Y ≠ 4
 *
 * The exclusions serialize to this xml:
 *
 *   <Requires>
 *     <Variable>a</Variable>
 *     <VariableValue>2</VariableValue>
 *     <DependentVariable>b</DependentVariable>
 *     <DependentVariableValue>4</DependentVariableValue>
 *   </Requires>
 *   <Forbids>
 *     <Variable>a</Variable>
 *     <VariableValue>3</VariableValue>
 *     <DependentVariable>b</DependentVariable>
 *     <DependentVariableValue>4</DependentVariableValue>
 *   </Forbids>
 *
 * @author Sam
 */
@XmlType(propOrder = { "varVal1", "varName2", "varVal2" })
public class ImpliesConfiguratorModel extends ConfiguratorModel {

	private boolean isRequires;

	@XmlElement(name = "VariableValue")
	private String varVal1;

	@XmlElement(name = "DependentVariableValue")
	private String varVal2;

	/**
	 * @param varName1 Name of the variable on the left hand side of the restriction
	 * @param varVal1 The value on the left hand side -- ie the value that triggers the configurator
	 * @param varName2 Name of the variable on the right hand side of the restriction
	 * @param varVals2 The value that the second (RHS) variable is required / forbidden to take
	 * @param isMembership True for a requires configurator, false for forbids
	 */
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

	public void setVarVal1(String varVal1) {
		this.varVal1 = varVal1;
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
