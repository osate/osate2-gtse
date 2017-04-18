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
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.integration.network.Request;
import org.osate.atsv.integration.network.Response;
import org.osate.atsv.integration.tests.xtexthelpers.OsateTest;

public class IntegrationTests extends OsateTest {

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
		socket = new Socket("localhost",
				Activator.getDefault().getPreferenceStore().getDefaultInt(Activator.ATSV_INTEGRATION_PORT));
		outStream = new ObjectOutputStream(socket.getOutputStream());
		inStream = new ObjectInputStream(socket.getInputStream());
	}

	@After
	public void customCleanUp() throws IOException {
		this.cleanUp();
		socket.close();
	}

	@Test
	public void badPluginIdTest() throws IOException, ClassNotFoundException {
		Request r = new Request();
		final String fakePluginID = "this.isnt.a.real.plugin.id";
		r.addPluginId(fakePluginID);
		r.setPackageName(PluginTest.PACKAGE_NAME);
		r.setComponentImplementationName("none");
		outStream.writeObject(r);
		outStream.flush();
		Response res = (Response) inStream.readObject();
		assertTrue(res.hasException());
		assertEquals("No extension with id '" + fakePluginID + "' found!", res.getException().getMessage());
		assertEquals("0.0", res.getVariables().get("ValidModel"));
	}

	@Test
	public void flowLatencyTest() throws IOException, ClassNotFoundException {
		Request r = new Request();
		r.addPluginId("org.osate.atsv.integration.flow-latency");
		r.setPackageName(PluginTest.PACKAGE_NAME);
		r.setComponentImplementationName(PluginTest.COMPONENT_NAME);
		outStream.writeObject(r);
		outStream.flush();
		Response res = (Response) inStream.readObject();
		assertFalse(res.hasException());
		assertTrue(res.getVariables().containsKey("exampleFlow"));
		assertEquals("25.0", res.getVariables().get("exampleFlow"));
		assertEquals("1.0", res.getVariables().get("ValidModel"));
	}

	@Test
	public void portConsistencyTest() throws IOException, ClassNotFoundException {
		Request r = new Request();
		r.addPluginId("org.osate.atsv.integration.port-consistency");
		r.setPackageName(PluginTest.PACKAGE_NAME);
		r.setComponentImplementationName(PluginTest.COMPONENT_NAME);
		outStream.writeObject(r);
		outStream.flush();
		Response res = (Response) inStream.readObject();
		assertFalse("Model incorrectly marked valid!", res.isValidModel());
		assertFalse("Model incorrectly threw an exception!", res.hasException());
		assertEquals("0.0", res.getVariables().get("ValidModel"));
	}

	@Test
	public void powerConsumptionTest() throws IOException, ClassNotFoundException {
		Request r = new Request();
		r.addPluginId("org.osate.atsv.integration.power-consumption");
		r.setPackageName(PluginTest.PACKAGE_NAME);
		r.setComponentImplementationName(PluginTest.COMPONENT_NAME);
		outStream.writeObject(r);
		outStream.flush();
		Response res = (Response) inStream.readObject();
		assertFalse(res.hasException());
		assertTrue(res.getVariables().containsKey("Grid"));
		assertEquals("20.0", res.getVariables().get("Grid"));
		assertEquals("1.0", res.getVariables().get("ValidModel"));
	}

	@Override
	public String getProjectName() {
		return "ATSVConnectivityTestProject";
	}
}
