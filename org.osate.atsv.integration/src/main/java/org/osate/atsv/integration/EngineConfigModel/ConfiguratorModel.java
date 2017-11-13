package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Configurators restrict the values of the values that ATSV chooses based on other selected
 * values. That is, these allow dynamic relationships between variable values to be enforced,
 * unlike type restrictions (which are known statically).
 *
 * @author Sam
 *
 */
public abstract class ConfiguratorModel {

	@XmlElement(name = "Variable")
	private String varName1;

	@XmlTransient
	protected String varName2;

	private int id1;

	private int id2;

	protected ConfiguratorModel(String varName1, String varName2) {
		this.varName1 = varName1;
		this.varName2 = varName2;
	}

	public String getVarName1() {
		return varName1;
	}

	public abstract String getVarName2();

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