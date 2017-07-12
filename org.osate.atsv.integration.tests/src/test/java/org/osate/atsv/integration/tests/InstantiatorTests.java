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
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.InstanceReferenceValue;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.ChoicePointModel.ChoicePointSpecification;
import org.osate.atsv.integration.ChoicePointModel.LiteralPropertyValue;
import org.osate.atsv.integration.ChoicePointModel.PropertyValue;
import org.osate.atsv.integration.ChoicePointModel.ReferencePropertyValue;
import org.osate.atsv.integration.ChoicePointModel.SubcomponentChoice;
import org.osate.atsv.integration.instantiator.CustomInstantiator;
import org.osate.atsv.integration.tests.xtexthelpers.OsateTest;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.GetProperties;

public class InstantiatorTests extends OsateTest {

	private static String pkgText;

	@BeforeClass
	public static void preSetUp() throws IOException {
		pkgText = new String(Files.readAllBytes(Paths.get("src/test/resources/IntegrationTestsModel.aadl")));
	}

	@SuppressWarnings("unchecked")
	@Before
	public void customSetup() throws UnknownHostException, IOException {
		this.setUp();
		// I'm not sure this line can be written without warnings
		createFiles(Pair.<String, String> of("IntegrationTestsModel.aadl", pkgText));
	}

	private SystemInstance getComponentInstance(String packageName, String implName,
			Set<ChoicePointSpecification> choicepoints) throws Exception {
		AadlPackage pkg = EMFIndexRetrieval.getPackageInWorkspace(packageName, OsateResourceUtil.createResourceSet());
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
				// The classifier doesn't get updated, even though the instantiated
				// element does, so we can't rely on it for testing:
				// assertEquals("MidProcess1", innerCI.getClassifier().getName());
				assertEquals(5000, GetProperties.getLatencyinMicroSec(innerCI.getFlowSpecifications().get(0)), .0001);
				return;
			}
		}
		fail("Couldn't find scs.mdev component");
	}

	@Test
	public void testRangedFloatPropertyValueSwapInstance() throws Exception {
		propSwapHelper("scs.mdev", "SEI::RAMActual", 37.8, 500.0, LiteralPropertyValue.class);
	}

	@Test
	public void testRangedIntPropertyValueSwapInstance() throws Exception {
		propSwapHelper("scs.edev", "SEI::SecurityLevel", 42L, 5L, LiteralPropertyValue.class);
	}

	@Test
	public void testStringPropertyValueSwapInstance() throws Exception {
		propSwapHelper("scs.processor2", "SEI::Platform", "Maingear", "Falcon Northwest", LiteralPropertyValue.class);
	}

	@Test
	public void testReferencePropertyValueSwapInstance() throws Exception {
		propSwapHelper("scs.mdev", "Deployment_Properties::Actual_Processor_Binding", "scs.processor2",
				"scs.processor1", ReferencePropertyValue.class);
	}

	@Override
	public String getProjectName() {
		return "ATSVCustomInstantiatorTestProject";
	}

	private void propSwapHelper(String path, String prop, Object newValue, Object oldValue,
			Class<? extends PropertyValue> cpsClazz) throws Exception {
		ATSVVariableType type;
		Class<? extends PropertyExpression> propValClazz;
		if (newValue instanceof Double) {
			type = ATSVVariableType.FLOAT;
			propValClazz = RealLiteral.class;
		} else if (newValue instanceof Long) {
			type = ATSVVariableType.INTEGER;
			propValClazz = IntegerLiteral.class;
		} else if (newValue instanceof String) {
			type = ATSVVariableType.STRING;
			if (cpsClazz.equals(ReferencePropertyValue.class)) {
				propValClazz = InstanceReferenceValue.class;
			} else {
				propValClazz = StringLiteral.class;
			}
		} else {
			throw new Exception("Can't figure out the type of " + newValue);
		}

		SystemInstance swappedSI = getComponentInstance(PluginTest.PACKAGE_NAME, PluginTest.COMPONENT_NAME,
				Collections.singleton(cpsClazz
						.getDeclaredConstructor(String.class, String.class, String.class, ATSVVariableType.class)
						.newInstance(path, prop, String.valueOf(newValue), type)));
		propCheckHelper(path, prop, newValue, propValClazz, swappedSI);

		SystemInstance emptySI = getComponentInstance(PluginTest.PACKAGE_NAME, PluginTest.COMPONENT_NAME,
				Collections.emptySet());
		propCheckHelper(path, prop, oldValue, propValClazz, emptySI);
	}

	private void propCheckHelper(String path, String prop, Object value,
			Class<? extends PropertyExpression> propValClazz, SystemInstance swappedSI) throws Exception {
		Iterator<ComponentInstance> cisIter = swappedSI.getAllComponentInstances().iterator();
		Iterator<String> pathIter = Arrays.asList(path.split("\\.")).iterator();
		ComponentInstance ci = null;
		String pathSegment = pathIter.next();
		while (cisIter.hasNext()) {
			ci = cisIter.next();
			if (ci.getName().equalsIgnoreCase(pathSegment)) {
				if (pathIter.hasNext()) {
					pathSegment = pathIter.next();
					cisIter = ci.getAllComponentInstances().iterator();
				} else {
					for (PropertyExpression pe : ci.getPropertyValues(prop.split("\\:\\:")[0],
							prop.split("\\:\\:")[1])) {
						if (propValClazz.isInstance(pe)) {
							if (value instanceof Double) {
								assertEquals((Double) value, (Double) propValClazz.getMethod("getValue").invoke(pe),
										.0001);
							} else if (pe instanceof InstanceReferenceValue) {
								assertEquals(value, ((InstanceReferenceValue) pe).getReferencedInstanceObject()
										.getComponentInstancePath());
							} else {
								assertEquals(value, propValClazz.getMethod("getValue").invoke(pe));
							}
							return;
						}
					}
				}
			}
		}
		fail("Couldn't find the " + prop + " property");
	}
}
