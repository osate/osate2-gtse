package org.osate.atsv.integration.tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.standalone.NetworkHandler;
import org.osate.core.test.OsateTest;

public class ConnectivityTests extends OsateTest {

	// This is used to capture system.out from the hosted methods / classes
	// See http://stackoverflow.com/a/1119559
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static PrintStream sysOut = System.out;
	private static Socket socket;

	@BeforeClass
	public static void preSetUp() throws IOException {
		System.setOut(new PrintStream(outContent));
		socket = new Socket("localhost",
				Activator.getDefault().getPreferenceStore().getDefaultInt(Activator.ATSV_INTEGRATION_PORT));
	}

	@AfterClass
	public static void afterCleanUp() throws IOException {
		System.setOut(sysOut);
		socket.close();
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();
		outContent.reset();
	}

	@Override
	@After
	public void cleanUp() {
		super.cleanUp();
	}

	@Test
	public void osateConnectivityTest() throws IOException {
		String inp;
		int i = 0;
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer.println("Hello from JUnit!");
		while ((inp = reader.readLine()) != null) {
			i++;
			assertEquals("Hello from OSATE!", inp);
			writer.println("Hello from JUnit!");
			if (i == 5) {
				break;
			}
		}
	}

	@Test
	public void atsvConnectivityTest() throws IOException {
		int portNum = 4445;
		Thread mockOSATEThread = new Thread(new mockOSATE(portNum));
		mockOSATEThread.start();
		Socket clientSocket = new Socket("localhost", portNum);
		NetworkHandler.initializeProtocol(clientSocket);
		String expected = "Got 'Hello from I-can't-believe-it's-not-OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from I-can't-believe-it's-not-OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from I-can't-believe-it's-not-OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from I-can't-believe-it's-not-OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from I-can't-believe-it's-not-OSATE!' from the socket!" + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}

	@Test
	public void interConnectivityTest() throws IOException {
		NetworkHandler.initializeProtocol(socket);
		String expected = "Got 'Hello from ATSV!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from ATSV!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from ATSV!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from ATSV!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from ATSV!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from OSATE!' from the socket!" + System.lineSeparator()
				+ "Got 'Hello from ATSV!' from the socket!" + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}

	@Override
	public String getProjectName() {
		return "ATSVConnectivityTestProject";
	}

	private class mockOSATE implements Runnable {

		private int portNum;

		public mockOSATE(int portNum) {
			this.portNum = portNum;
		}

		@Override
		public void run() {
			try (ServerSocket serverSocket = new ServerSocket(portNum);
					Socket clientSocket = serverSocket.accept(); // wait for ATSV
					PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
				while (reader.readLine() != null) {
					writer.println("Hello from I-can't-believe-it's-not-OSATE!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
