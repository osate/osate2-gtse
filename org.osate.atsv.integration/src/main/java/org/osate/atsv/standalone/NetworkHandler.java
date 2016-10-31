package org.osate.atsv.standalone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * A simple standalone client designed to be connected to an ATSV-Integration enabled version of OSATE. Much of the
 * socket code is adapted from http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html 
 * 
 * @author Sam Procter
 *
 */
public class NetworkHandler {
	public static void main(String[] args) {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		try (Socket socket = new Socket(host, port)) {
			initializeProtocol(socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initializeProtocol(Socket socket) throws IOException {
		String inp;
		int i = 0;
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer.println("org.osate.atsv.integration.flow-latency:CarSystem:MyCar_CruiseControlTasks_Instance");
		while ((inp = reader.readLine()) != null) {
			i++;
			System.out.println("Got '" + inp + "' from the socket!");
			writer.println("Hello from ATSV!");
			if (i == 5) {
				break;
			}
		}
	}
}
