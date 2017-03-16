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
package org.osate.atsv.integration.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.integration.EngineConfigGenerator;
import org.osate.atsv.integration.EngineConfigModel.UniformDistributionModel;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class EngineConfigTests {

	private String baseDir = Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY)
			+ File.separator;

	@Test
	public void noVariables() throws JAXBException {
		EngineConfigGenerator ecf = new EngineConfigGenerator();
		String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode><configurationFile>"
				+ baseDir + "run.sh</configurationFile><parser>" + baseDir + "parser.jar</parser><inputFile>" + baseDir
				+ "input.txt</inputFile><outputFile>" + baseDir
				+ "output.txt</outputFile><runCodeParameter></runCodeParameter><analysisFile></analysisFile><inputTokens/><outputTokens></outputTokens><variables/><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><configurator></configurator><userDefinedPath></userDefinedPath></ExplorationEngineModel>";
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorEquality() throws JAXBException {
		EngineConfigGenerator ecf = new EngineConfigGenerator();
		String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode><configurationFile>"
				+ baseDir + "run.sh</configurationFile><parser>" + baseDir + "parser.jar</parser><inputFile>" + baseDir
				+ "input.txt</inputFile><outputFile>" + baseDir
				+ "output.txt</outputFile><runCodeParameter></runCodeParameter><analysisFile></analysisFile><inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"A\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"A\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><configurator>&lt;Configurator&gt;&lt;Equal&gt;&lt;var&gt;str1&lt;/var&gt;&lt;var&gt;str2&lt;/var&gt;&lt;/Equal&gt;&lt;/Configurator&gt;</configurator><userDefinedPath></userDefinedPath></ExplorationEngineModel>";
		ecf.addVariable("str1", false, true, ATSVVariableType.STRING, "A", new ValuesModel("A", "B"));
		ecf.addVariable("str2", false, true, ATSVVariableType.STRING, "A", new ValuesModel("A", "B"));
		ecf.addEqualityConstraint("str1", "str2");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorUniqueness() throws JAXBException {
		EngineConfigGenerator ecf = new EngineConfigGenerator();
		String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode><configurationFile>"
				+ baseDir + "run.sh</configurationFile><parser>" + baseDir + "parser.jar</parser><inputFile>" + baseDir
				+ "input.txt</inputFile><outputFile>" + baseDir
				+ "output.txt</outputFile><runCodeParameter></runCodeParameter><analysisFile></analysisFile><inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"A\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"A\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><configurator>&lt;Configurator&gt;&lt;Unique&gt;&lt;var&gt;str1&lt;/var&gt;&lt;var&gt;str2&lt;/var&gt;&lt;/Unique&gt;&lt;/Configurator&gt;</configurator><userDefinedPath></userDefinedPath></ExplorationEngineModel>";
		ecf.addVariable("str1", false, true, ATSVVariableType.STRING, "A", new ValuesModel("A", "B"));
		ecf.addVariable("str2", false, true, ATSVVariableType.STRING, "A", new ValuesModel("A", "B"));
		ecf.addUniquenessConstraint("str1", "str2");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void exampleVariables() throws JAXBException {
		EngineConfigGenerator ecf = new EngineConfigGenerator();
		String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode><configurationFile>"
				+ baseDir + "run.sh</configurationFile><parser>" + baseDir + "parser.jar</parser><inputFile>" + baseDir
				+ "input.txt</inputFile><outputFile>" + baseDir
				+ "output.txt</outputFile><runCodeParameter></runCodeParameter><analysisFile></analysisFile><inputTokens><var0 name=\"dv1\" token=\"\"/><var1 name=\"phideg\" token=\"\"/><var2 name=\"intTest\" token=\"\"/><var3 name=\"strTest\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"dv1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"0\" value=\"0.95151705\" preference=\"0\"><distribution distType=\"uniform\" min=\"0.0\" max=\"2.0\"/></varName0><varName1 title=\"phideg\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"0\" value=\"3.5089884\" preference=\"0\"><distribution distType=\"uniform\" min=\"-5.0\" max=\"5.0\"/></varName1><varName2 title=\"intTest\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"4\" value=\"13\" preference=\"0\"><values val8=\"20\" val7=\"19\" val6=\"18\" val5=\"17\" val4=\"16\" val3=\"15\" val2=\"14\" val1=\"13\" val0=\"12\"/></varName2><varName3 title=\"strTest\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"A\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName3><varName4 title=\"dv2\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/><varName5 title=\"dvTotal\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/><varName6 title=\"deltaT\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><configurator></configurator><userDefinedPath></userDefinedPath></ExplorationEngineModel>";
		ecf.addVariable("dv1", false, true, ATSVVariableType.FLOAT, "0.95151705",
				new UniformDistributionModel((float) 0.0, (float) 2.0));
		ecf.addVariable("phideg", false, true, ATSVVariableType.FLOAT, "3.5089884",
				new UniformDistributionModel((float) -5.0, (float) 5.0));
		ecf.addVariable("intTest", false, true, ATSVVariableType.INTEGER, "13",
				new ValuesModel("12", "13", "14", "15", "16", "17", "18", "19", "20"));
		ecf.addVariable("strTest", false, true, ATSVVariableType.STRING, "A", new ValuesModel("A", "B"));
		ecf.addVariable("dv2", false, false, ATSVVariableType.FLOAT, null);
		ecf.addVariable("dvTotal", false, false, ATSVVariableType.FLOAT, null);
		ecf.addVariable("deltaT", false, false, ATSVVariableType.FLOAT, null);
		assertEquals(expectedXML, ecf.getXML());
	}
}
