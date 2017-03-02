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

import static java.util.function.Function.identity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.instantiator.CustomInstantiator;
import org.osate.atsv.integration.network.ChoicePointSpecification;
import org.osate.atsv.integration.network.PropertyValue;
import org.osate.atsv.integration.network.SubcomponentChoice;
import org.osate.core.test.OsateTest;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

public class InstantiatorTests extends OsateTest {

	private static String pkgText;

	@BeforeClass
	public static void preSetUp() throws IOException {
		pkgText = new String(Files.readAllBytes(Paths.get("src/test/resources/ConnectivityTestModel.aadl")));
	}

	@SuppressWarnings("unchecked")
	@Before
	public void customSetup() throws UnknownHostException, IOException {
		this.setUp();
		// I'm not sure this line can be written without warnings
		createFiles(Pair.<String, String> of("ConnectivityTestModel.aadl", pkgText));
	}

	private SystemInstance getComponentInstance(String packageName, String implName,
			Set<ChoicePointSpecification> choicepoints) throws Exception {
		AadlPackage pkg = EMFIndexRetrieval.getPackageInWorkspace(packageName);
		ComponentImplementation impl = (ComponentImplementation) pkg.getPublicSection().getOwnedClassifiers().stream()
				.filter(sysImpl -> sysImpl.getName().equals(implName)).findFirst().get();
		return CustomInstantiator.myBuildInstanceModelFile(impl, getChoicePointMap(choicepoints));
	}

	private Map<String, ChoicePointSpecification> getChoicePointMap(Set<ChoicePointSpecification> choicepoints) {
		return Collections.unmodifiableMap(choicepoints.stream()
				.collect(Collectors.toMap(ChoicePointSpecification::getComponentPath, identity())));
	}

	@Test
	public void testSubcomponentSwapInstance() throws Exception {
		SystemInstance si = getComponentInstance(PluginTest.PACKAGE_NAME, PluginTest.COMPONENT_NAME,
				Collections.singleton(new SubcomponentChoice("scs.mdev", "SimpleComponentChoice::MidProcess1",
						ATSVVariableType.STRING)));

		for (ComponentInstance outerCI : si.getAllComponentInstances()) {
			if (!outerCI.getName().equalsIgnoreCase("scs")) {
				continue;
			}
			for (ComponentInstance innerCI : outerCI.getAllComponentInstances()) {
				if (!innerCI.getName().equalsIgnoreCase("mdev")) {
					continue;
				}
				assertEquals("MidProcess2", innerCI.getClassifier().getName());
				return;
			}
		}
		fail("Couldn't find scs.mdev component");
	}

	@Test
	public void testPropertyValueSwapInstance() throws Exception {
		SystemInstance si = getComponentInstance(PluginTest.PACKAGE_NAME, PluginTest.COMPONENT_NAME,
				Collections.singleton(new PropertyValue("scs.sdev.power", "SEI::PowerBudget", String.valueOf(4.2),
						ATSVVariableType.FLOAT)));
		for (ComponentInstance outerCI : si.getAllComponentInstances()) {
			if (!outerCI.getName().equalsIgnoreCase("scs")) {
				continue;
			}
			for (ComponentInstance innerCI : outerCI.getAllComponentInstances()) {
				if (!innerCI.getName().equalsIgnoreCase("sdev")) {
					continue;
				}
				for (FeatureInstance fi : innerCI.getAllFeatureInstances()) {
					if (!fi.getName().equalsIgnoreCase("power")) {
						continue;
					}
					for (PropertyExpression pe : fi.getPropertyValues("SEI", "PowerBudget")) {
						if (pe instanceof RealLiteral) {
							assertEquals(4.2, ((RealLiteral) pe).getValue(), .0001);
							return;
						}
					}
				}
			}
		}
		fail("Couldn't find the SEI::PowerBudget Property");
	}

//	@Test
//	public void testNestedSystemPropertyValueSwap() throws Exception {
//		// TODO: Since we key off of system instances, it seems important to make sure we can
//		// handle nested ones.
//		fail("Not yet implemented");
//	}

	@Override
	public String getProjectName() {
		return "ATSVCustomInstantiatorTestProject";
	}
}
