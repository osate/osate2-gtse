package org.osate.atsv.integration.EngineConfigModel;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;

public class SetRestrictionConfiguratorModel extends ConfiguratorModel {

	@XmlElement(name = "VariableValue")
	private String varVal1;

	SetRestrictionDependentVariableModel var2;

	private boolean isMembership;

	public SetRestrictionConfiguratorModel(String varName1, String varVal1, String varName2, Collection<String> varVals2,
			boolean isMembership) {
		super(varName1, varName2);
		this.varVal1 = varVal1;
		var2 = new SetRestrictionDependentVariableModel(varName2, varVals2);
		this.isMembership = isMembership;
	}

	public boolean isMembership() {
		return isMembership;
	}

	public boolean isExclusion() {
		return !isMembership;
	}

	@Override
	@XmlAnyElement
	public String getVarName2() {
		// TODO Auto-generated method stub
		return null;
	}

}
