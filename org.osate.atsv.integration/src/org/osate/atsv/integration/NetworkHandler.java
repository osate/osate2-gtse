package org.osate.atsv.integration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * A network adapter to enable OSATE to talk to an external jvm over a user-specified port. Much of the
 * socket code is adapted from http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html 
 * 
 * @author Sam Procter
 *
 */
public class NetworkHandler implements Runnable {

	private int portNum;
	int i = 0;

	public NetworkHandler(int portNum) {
		this.portNum = portNum;
	}

	public void run() {
		String inp = "nothing :(";
		try (ServerSocket serverSocket = new ServerSocket(portNum); // bind to port
				Socket clientSocket = serverSocket.accept(); // wait for ATSV
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			while ((inp = reader.readLine()) != null) {
				i++;
				System.out.println("Got '" + inp + "' from the socket!");
				writer.println("Hello from OSATE!");
				if (i == 5) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

}
