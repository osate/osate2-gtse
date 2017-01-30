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

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;

import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.atsv.integration.CustomInstantiator;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
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
		return CustomInstantiator.myBuildInstanceModelFile(impl, choicepoints);
	}

	@Test
	public void testSubcomponentSwapInstance() throws Exception {
		SystemInstance si = getComponentInstance(PluginTest.PACKAGE_NAME, PluginTest.COMPONENT_NAME,
				Collections.singleton(new SubcomponentChoice("scs", "mdev", "SimpleComponentChoice::MidProcess1",
						ATSVVariableType.STRING)));
		assertNotNull(si);
		// TODO: Once it's possible to identify the classifier that was instantiated for a particular
		// subcomponent, do that here to make sure the swapped instantiation worked correctly
		// si.getComponentInstances().get(2).getComponentInstances().get(1)
	}

	@Test
	public void testPropertyValueSwapInstance() throws Exception {
		SystemInstance si = getComponentInstance(PluginTest.PACKAGE_NAME, PluginTest.COMPONENT_NAME,
				Collections.singleton(new PropertyValue("SimpleComponentChoice::StartProcess", "SEI::PowerBudget",
						String.valueOf(4.2), ATSVVariableType.FLOAT)));
		assertNotNull(si);
		// TODO: Once it's possible to identify the classifier that was instantiated for a particular
		// subcomponent, do that here to make sure the swapped instantiation worked correctly
		// si.getComponentInstances().get(2).getComponentInstances().get(1)
	}

	@Override
	public String getProjectName() {
		return "ATSVCustomInstantiatorTestProject";
	}
}
