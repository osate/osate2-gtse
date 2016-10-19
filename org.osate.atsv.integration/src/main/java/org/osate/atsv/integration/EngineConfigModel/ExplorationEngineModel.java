package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ExplorationEngineModel")
public class ExplorationEngineModel {
	
	private String analysisCode = "CommandLineProblem";

	public String getAnalysisCode() {
		return analysisCode;
	}

	public void setAnalysisCode(String analysisCode) {
		this.analysisCode = analysisCode;
	}

}
