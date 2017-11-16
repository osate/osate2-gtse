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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.integration.EngineConfigGenerator;
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.EngineConfigModel.UniformDistributionModel;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.exception.ConfiguratorRepresentationException;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;
import org.osate.atsv.integration.exception.UnsupportedFeatureException;

public class EngineConfigTests {

	private String baseDir = Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY)
			+ File.separator;

	private final String ECF_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode><configurationFile>"
			+ baseDir + "run.sh</configurationFile><parser>" + baseDir + "parser.jar</parser><inputFile>" + baseDir
			+ "input.xml</inputFile><outputFile>" + baseDir
			+ "output.xml</outputFile><runCodeParameter></runCodeParameter><analysisFile></analysisFile>";

	private final String ECF_SUFFIX = "</Configurator><userDefinedPath></userDefinedPath></ExplorationEngineModel>";

	private EngineConfigGenerator ecf;

	@Before
	public void startUp() {
		ecf = new EngineConfigGenerator();
	}

	@After
	public void tearDown() {
		ecf = null;
	}

	@Test
	public void noVariables() throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
	UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens/><outputTokens></outputTokens><variables/><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>"
				+ ECF_SUFFIX;
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorEquality()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Equal&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;Variable&gt;str2&lt;/Variable&gt;&lt;/Equal&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addEqualityConstraint("str1", "str2");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorUniqueness()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Unique&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;Variable&gt;str2&lt;/Variable&gt;&lt;/Unique&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addUniquenessConstraint("str1", "str2");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorRequires()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"C\" preference=\"0\"><values val2=\"C\" val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"C\" preference=\"0\"><values val2=\"C\" val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Requires&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;VariableValue&gt;A&lt;/VariableValue&gt;&lt;DependentVariable&gt;str2&lt;/DependentVariable&gt;&lt;DependentVariableValue&gt;C&lt;/DependentVariableValue&gt;&lt;/Requires&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B", "C"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B", "C"));
		ecf.addRequiresConstraint("str1", "A", "str2", "C");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorForbids()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"C\" preference=\"0\"><values val2=\"C\" val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"C\" preference=\"0\"><values val2=\"C\" val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Forbids&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;VariableValue&gt;A&lt;/VariableValue&gt;&lt;DependentVariable&gt;str2&lt;/DependentVariable&gt;&lt;DependentVariableValue&gt;C&lt;/DependentVariableValue&gt;&lt;/Forbids&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B", "C"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B", "C"));
		ecf.addForbidsConstraint("str1", "A", "str2", "C");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorMembership()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"C\" preference=\"0\"><values val2=\"C\" val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"D\" preference=\"0\"><values val3=\"D\" val2=\"C\" val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Membership&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;VariableValue&gt;A&lt;/VariableValue&gt;&lt;DependentVariable v0=\"C\" v1=\"D\"&gt;str2&lt;/DependentVariable&gt;&lt;/Membership&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B", "C"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B", "C", "D"));
		Set<String> ASet = Stream.of("C", "D").collect(Collectors.toSet());
		ecf.addMembershipConstraint("str1", "A", "str2", ASet);
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorExclusion()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"C\" preference=\"0\"><values val2=\"C\" val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"D\" preference=\"0\"><values val3=\"D\" val2=\"C\" val1=\"B\" val0=\"A\"/></varName1></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Exclusion&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;VariableValue&gt;A&lt;/VariableValue&gt;&lt;DependentVariable v0=\"C\" v1=\"D\"&gt;str2&lt;/DependentVariable&gt;&lt;/Exclusion&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B", "C"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B", "C", "D"));
		Set<String> ASet = Stream.of("C", "D").collect(Collectors.toSet());
		ecf.addExclusionConstraint("str1", "A", "str2", ASet);
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void configuratorMultiple()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/><var2 name=\"str3\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName1><varName2 title=\"str3\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName2></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Equal&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;Variable&gt;str2&lt;/Variable&gt;&lt;/Equal&gt;&lt;Equal&gt;&lt;Variable&gt;str2&lt;/Variable&gt;&lt;Variable&gt;str3&lt;/Variable&gt;&lt;/Equal&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str3", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addEqualityConstraint("str1", "str2");
		ecf.addEqualityConstraint("str2", "str3");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test(expected = UnsatisfiableConstraint.class)
	public void impossibleSimpleConfigurator()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str3", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addEqualityConstraint("str1", "str2");
		ecf.addEqualityConstraint("str2", "str3");
		ecf.addUniquenessConstraint("str1", "str3");

		// This should throw the exception
		ecf.getXML();
	}

	@Test(expected = UnsatisfiableConstraint.class)
	public void impossibleImpliesConfigurator()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B", "C", "D"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B", "C", "D"));
		ecf.addChoicePointDefinition("str3", ATSVVariableType.STRING, new ValuesModel("A"));
		ecf.addChoicePointDefinition("str4", ATSVVariableType.STRING, new ValuesModel("D"));
		ecf.addRequiresConstraint("str1", "A", "str2", "C");
		ecf.addEqualityConstraint("str1", "str3");
		ecf.addEqualityConstraint("str2", "str4");

		// This should throw the exception
		ecf.getXML();
	}

	@Test
	public void possibleConfigurator()
			throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"str1\" token=\"\"/><var1 name=\"str2\" token=\"\"/><var2 name=\"str3\" token=\"\"/><var3 name=\"str4\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"str1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName0><varName1 title=\"str2\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName1><varName2 title=\"str3\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName2><varName3 title=\"str4\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName3></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>&lt;Configurator&gt;&lt;Equal&gt;&lt;Variable&gt;str1&lt;/Variable&gt;&lt;Variable&gt;str2&lt;/Variable&gt;&lt;/Equal&gt;&lt;Equal&gt;&lt;Variable&gt;str3&lt;/Variable&gt;&lt;Variable&gt;str4&lt;/Variable&gt;&lt;/Equal&gt;&lt;Unique&gt;&lt;Variable&gt;str2&lt;/Variable&gt;&lt;Variable&gt;str3&lt;/Variable&gt;&lt;/Unique&gt;&lt;/Configurator&gt;"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("str1", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str2", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str3", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addChoicePointDefinition("str4", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addEqualityConstraint("str1", "str2");
		ecf.addEqualityConstraint("str3", "str4");
		ecf.addUniquenessConstraint("str2", "str3");
		assertEquals(expectedXML, ecf.getXML());
	}

	@Test
	public void exampleVariables() throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
	UnsupportedFeatureException {
		String expectedXML = ECF_PREFIX
				+ "<inputTokens><var0 name=\"dv1\" token=\"\"/><var1 name=\"phideg\" token=\"\"/><var2 name=\"intTest\" token=\"\"/><var3 name=\"strTest\" token=\"\"/></inputTokens><outputTokens></outputTokens><variables><varName0 title=\"dv1\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"0\" value=\"0.0\" preference=\"0\"><distribution distType=\"uniform\" min=\"0.0\" max=\"2.0\"/></varName0><varName1 title=\"phideg\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"0\" value=\"-5.0\" preference=\"0\"><distribution distType=\"uniform\" min=\"-5.0\" max=\"5.0\"/></varName1><varName2 title=\"intTest\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"4\" value=\"20\" preference=\"0\"><values val8=\"20\" val7=\"19\" val6=\"18\" val5=\"17\" val4=\"16\" val3=\"15\" val2=\"14\" val1=\"13\" val0=\"12\"/></varName2><varName3 title=\"strTest\" capture=\"true\" sampled=\"false\" ioValue=\"0\" type=\"2\" value=\"B\" preference=\"0\"><values val1=\"B\" val0=\"A\"/></varName3><varName4 title=\"dv2\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/><varName5 title=\"dvTotal\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/><varName6 title=\"deltaT\" capture=\"true\" sampled=\"false\" ioValue=\"1\" type=\"0\" preference=\"0\"/></variables><excelMacro></excelMacro><sampleCount>25</sampleCount><updateATSVInterval>25</updateATSVInterval><Configurator>"
				+ ECF_SUFFIX;
		ecf.addChoicePointDefinition("dv1", ATSVVariableType.FLOAT,
				new UniformDistributionModel((float) 0.0, (float) 2.0));
		ecf.addChoicePointDefinition("phideg", ATSVVariableType.FLOAT,
				new UniformDistributionModel((float) -5.0, (float) 5.0));
		ecf.addChoicePointDefinition("intTest", ATSVVariableType.INTEGER,
				new ValuesModel("12", "13", "14", "15", "16", "17", "18", "19", "20"));
		ecf.addChoicePointDefinition("strTest", ATSVVariableType.STRING, new ValuesModel("A", "B"));
		ecf.addOutputVariable("dv2", ATSVVariableType.FLOAT, null);
		ecf.addOutputVariable("dvTotal", ATSVVariableType.FLOAT, null);
		ecf.addOutputVariable("deltaT", ATSVVariableType.FLOAT, null);
		assertEquals(expectedXML, ecf.getXML());
	}
}
