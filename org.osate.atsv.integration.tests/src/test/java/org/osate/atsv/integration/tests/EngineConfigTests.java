package org.osate.atsv.integration.tests;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.osate.atsv.integration.EngineConfigGenerator;
import org.osate.atsv.integration.EngineConfigModel.UniformDistributionModel;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class EngineConfigTests {

	private String noVarXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode><configurationFile>/home/sam/git/gtse/org.osate.atsv.integration.tests/ATSVConfig.ecf</configurationFile><parser>/home/sam/git/gtse/org.osate.atsv.integration.tests/parser.jar</parser><inputFile>/home/sam/git/gtse/org.osate.atsv.integration.tests/input.txt</inputFile><outputFile>/home/sam/git/gtse/org.osate.atsv.integration.tests/output.txt</outputFile><runCodeParameter></runCodeParameter><analysisFile></analysisFile><inputTokens/><outputTokens></outputTokens><variables/><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><configurator></configurator><userDefinedPath></userDefinedPath></ExplorationEngineModel>";
	private String exVarXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode><configurationFile>/home/sam/git/gtse/org.osate.atsv.integration.tests/ATSVConfig.ecf</configurationFile><parser>/home/sam/git/gtse/org.osate.atsv.integration.tests/parser.jar</parser><inputFile>/home/sam/git/gtse/org.osate.atsv.integration.tests/input.txt</inputFile><outputFile>/home/sam/git/gtse/org.osate.atsv.integration.tests/output.txt</outputFile><runCodeParameter></runCodeParameter><analysisFile></analysisFile><inputTokens><var0 name=\"dv1\" token=\"\"/><var1 name=\"phideg\" token=\"\"/><var2 name=\"intTest\" token=\"\"/><var3 name=\"strTest\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"dv1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"0\" value=\"0.95151705\" preference=\"0\"><distribution distType=\"uniform\" min=\"0.0\" max=\"2.0\"/></varName0><varName1 title=\"phideg\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"0\" value=\"3.5089884\" preference=\"0\"><distribution distType=\"uniform\" min=\"-5.0\" max=\"5.0\"/></varName1><varName2 title=\"intTest\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"4\" value=\"13\" preference=\"0\"><values val8=\"20\" val7=\"19\" val6=\"18\" val5=\"17\" val4=\"16\" val3=\"15\" val2=\"14\" val1=\"13\" val0=\"12\"/></varName2><varName3 title=\"strTest\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"A\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName3><varName4 title=\"dv2\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/><varName5 title=\"dvTotal\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/><varName6 title=\"deltaT\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><configurator></configurator><userDefinedPath></userDefinedPath></ExplorationEngineModel>";

	@Test
	public void noVariables() throws JAXBException {
		EngineConfigGenerator ecf = new EngineConfigGenerator();
		assertEquals(noVarXML, ecf.getXML());
	}
	
	@Test
	public void exampleVariables() throws JAXBException {
		EngineConfigGenerator ecf = new EngineConfigGenerator();
		ecf.addVariable("dv1", true, false, true, ATSVVariableType.FLOAT, "0.95151705", new UniformDistributionModel((float)0.0, (float)2.0));
		ecf.addVariable("phideg", true, false, true, ATSVVariableType.FLOAT, "3.5089884", new UniformDistributionModel((float)-5.0, (float)5.0));
		ecf.addVariable("intTest", true, false, true, ATSVVariableType.INTEGER, "13", new ValuesModel("12", "13", "14", "15", "16", "17", "18", "19", "20"));
		ecf.addVariable("strTest", true, false, true, ATSVVariableType.STRING, "A", new ValuesModel("A", "B"));
		ecf.addVariable("dv2", true, false, false, ATSVVariableType.FLOAT, null);
		ecf.addVariable("dvTotal", true, false, false, ATSVVariableType.FLOAT, null);
		ecf.addVariable("deltaT", true, false, false, ATSVVariableType.FLOAT, null);
		assertEquals(exVarXML, ecf.getXML());
	}
}
