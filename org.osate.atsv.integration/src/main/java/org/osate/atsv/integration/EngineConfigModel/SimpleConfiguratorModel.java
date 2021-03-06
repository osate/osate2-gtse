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

import org.osate.atsv.integration.annotation.StringConfiguratorHack;

/**
 * This class models the basic (equal and unique) configurators.
 *
 * These restrictions take the following forms:
 *
 *   Equality -- Choosing one value means another choicepoint has to have the same value
 *     X = 7 → Y = 7
 *     X = 3 → Y = 3.
 *   Uniqueness -- Choosing one value means another choicepoint can't take that same value
 *     X = 7 → Y ≠ 7
 *     X = 3 → Y ≠ 3
 *
 * The exclusions serialize to this xml:
 *   <Equal>
 *     <Variable>e</Variable>
 *     <Variable>f</Variable>
 *   </Equal>
 *   <Unique>
 *     <Variable>c</Variable>
 *     <Variable>d</Variable>
 *   </Unique>
 *
 * @author Sam
 */
public class SimpleConfiguratorModel extends ConfiguratorModel {
	private boolean isEquality;

	/**
	 *
	 * @param varName1 The name of the left hand side variable
	 * @param varName2 The name of the right hand side variable
	 * @param isEquality True if this is an equality configurator, false if it's uniqueness
	 */
	public SimpleConfiguratorModel(String varName1, String varName2, boolean isEquality) {
		super(varName1, varName2);
		this.isEquality = isEquality;
	}

	public boolean isEquality() {
		return isEquality;
	}

	public boolean isUnique() {
		return !isEquality;
	}

	@Override
	@XmlElement(name = "Variable")
	public String getVarName2() {
		return this.varName2;
	}

	@Override
	@StringConfiguratorHack
	public void convertToSafeVal(ExplorationEngineModel eem) {
		// SimpleConfigurators are always safe, so no conversion is needed
	}
}
