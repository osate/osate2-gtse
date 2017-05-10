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
import javax.xml.bind.annotation.XmlTransient;

/**
 * This models an individual "configurator" essentially a constraint on the inputs ATSV will generate
 */
public class ConfiguratorModel {
	@XmlElement(name = "var")
	private String varName1;

	@XmlElement(name = "var")
	private String varName2;

	private int id1, id2;

	private boolean isEquality;

	public ConfiguratorModel(String varName1, String varName2, boolean isEquality) {
		this.varName1 = varName1;
		this.varName2 = varName2;
		this.isEquality = isEquality;
	}

	public boolean isEquality() {
		return isEquality;
	}

	public boolean isUnique() {
		return !isEquality;
	}

	public String getVarName1() {
		return varName1;
	}

	public String getVarName2() {
		return varName2;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	@XmlTransient
	public int getId1() {
		return id1;
	}

	@XmlTransient
	public int getId2() {
		return id2;
	}
}