package org.osate.atsv.integration.preparser;

import org.osate.aadl2.util.Aadl2Switch;
import org.osate.atsv.integration.EngineConfigGenerator;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class PreparseSwitch extends Aadl2Switch<String> {

	private void addGeneratedChoicePoint(String title, ATSVVariableType type, String value) {
		EngineConfigGenerator.getInstance().addVariable(title, false, true, type, value);
	}
}
