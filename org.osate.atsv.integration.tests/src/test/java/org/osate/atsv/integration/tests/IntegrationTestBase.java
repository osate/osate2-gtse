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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.integration.ChoicePointModel.ChoicePointSpecification;
import org.osate.atsv.integration.network.Request;
import org.osate.atsv.integration.network.Response;
import org.osate.atsv.integration.tests.xtexthelpers.OsateTest;

public class IntegrationTestBase extends OsateTest {

	private static Socket socket;
	private static ObjectOutputStream outStream;
	private static ObjectInputStream inStream;
	private static String pkgText;

	@BeforeClass
	public static void preSetUp() throws IOException {
		pkgText = new String(Files.readAllBytes(Paths.get("src/test/resources/IntegrationTestsModel.aadl")));
	}

	@AfterClass
	public static void afterCleanUp() throws IOException {
		socket.close();
	}

	@SuppressWarnings("unchecked")
	@Before
	public void customSetup() throws UnknownHostException, IOException {
		this.setUp();
		// I'm not sure this line can be written without warnings
		createFiles(Pair.<String, String> of("IntegrationTestsModel.aadl", pkgText));
		socket = new Socket(InetAddress.getLoopbackAddress(),
				Activator.getDefault().getPreferenceStore().getDefaultInt(Activator.ATSV_INTEGRATION_PORT));
		outStream = new ObjectOutputStream(socket.getOutputStream());
		inStream = new ObjectInputStream(socket.getInputStream());
	}

	@After
	public void customCleanUp() throws IOException {
		this.cleanUp();
		socket.close();
	}

	/**
	 * Helps run the tests by thoroughly inspecting the response returned by OSATE
	 *
	 * @param pluginId The id of the plugin to run, eg, 'org.osate.atsv.integration.property-totals'
	 * @param expectedException Either null, if no exception is expected, or the message that should be returned
	 * @param expectValid True if a valid model is expected, false otherwise
	 * @param expectedVars A map of variable name to variable value. Each pair will be checked.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected void testHelper(String pluginId, String expectedException, boolean expectValid,
			String expectedInvalidReasons, Map<String, VarDescriptor> expectedVars,
			Set<ChoicePointSpecification> CPSpecs) throws IOException, ClassNotFoundException {
		Request r = new Request();
		r.addPluginId(pluginId);
		for (ChoicePointSpecification cps : CPSpecs) {
			r.addChoicePoint(cps);
		}
		r.setPackageName(PluginTest.PACKAGE_NAME);
		for (String varName : expectedVars.keySet()) {
			VarDescriptor desc = expectedVars.get(varName);
			if (!(desc.op == null || desc.limit == null)) {
				r.addLimit(varName, expectedVars.get(varName).op, expectedVars.get(varName).limit);
			}
		}
		r.setComponentImplementationName(PluginTest.COMPONENT_NAME);
		outStream.writeObject(r);
		outStream.flush();
		Response res = (Response) inStream.readObject();
		if (expectedException == null) {
			if (res.hasException()) {
				res.getException().printStackTrace();
			}
			assertFalse("The model shouldn't have an exception, but it does", res.hasException());
		} else {
			assertTrue("The model should have an exception, but it doesn't have one", res.hasException());
			assertEquals(expectedException, res.getException().getMessage());
		}
		assertTrue("The ValidModel value isn't set", res.getVariables().getVars().containsKey("ValidModel"));
		assertTrue("The InvalidReason value isn't set", res.getVariables().getVars().containsKey("InvalidReason"));
		assertEquals("Wrong InvalidReason", expectedInvalidReasons,
				res.getVariables().getVars().get("InvalidReason").getVal());
		if (expectValid) {
			assertTrue("The model is marked invalid, but it shouldn't be", res.isValidModel());
			assertEquals("Wrong ValidModel score", "1.0", res.getVariables().getVars().get("ValidModel").getVal());
		} else {
			assertFalse("The model is marked valid, but it shouldn't be", res.isValidModel());
			assertEquals("Wrong ValidModel score", "0.0", res.getVariables().getVars().get("ValidModel").getVal());
		}
		expectedVars.forEach((name, value) -> {
			assertTrue(name + " is not set", res.getVariables().getVars().containsKey(name));
			assertEquals("Incorrect value for " + name + ".", value.expectedValue,
					res.getVariables().getVars().get(name).getVal());
		});
	}

	@Override
	public String getProjectName() {
		return "ATSVConnectivityTestProject";
	}

	protected class VarDescriptor {
		String expectedValue = null;
		String op = null;
		String limit = null;

		public VarDescriptor(String expectedValue, String op, String limit) {
			this.expectedValue = expectedValue;
			this.op = op;
			this.limit = limit;
		}

		public VarDescriptor(String expectedValue) {
			this.expectedValue = expectedValue;
		}
	}
}
