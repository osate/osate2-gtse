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
 * This models an individual simple (equality or inequality between variables) configurator --
 * essentially a constraint on the inputs ATSV will generate
 */
public class SimpleConfiguratorModel extends ConfiguratorModel {
	private boolean isEquality;

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
}
