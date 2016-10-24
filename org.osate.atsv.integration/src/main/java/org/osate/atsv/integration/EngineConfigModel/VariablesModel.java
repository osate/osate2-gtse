package org.osate.atsv.integration.EngineConfigModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class VariablesModel {

	@XmlJavaTypeAdapter(VariableModelAdapter.class)
	@XmlAnyElement
	private List<VariableModel> variables = new ArrayList<>();
	
	public VariablesModel(){
		variables.add(new VariableModel("deltaT", true, false, false, ATSVVariableType.REAL, "Unused", new ValuesModel("A", "B")));
		variables.add(new VariableModel("dv1", true, false, true, ATSVVariableType.REAL, "5.0", new UniformDistributionModel((float)0.0, (float)2.0)));
	}

}
