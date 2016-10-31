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

	public NetworkHandler(int portNum) {
		this.portNum = portNum;
	}

	public void run() {
		String inp;
		try (ServerSocket serverSocket = new ServerSocket(portNum); // bind to port
				Socket clientSocket = serverSocket.accept(); // wait for ATSV
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			AnalysisDelegator delegator = new AnalysisDelegator();
			while ((inp = reader.readLine()) != null) {
				String s[] = inp.split(":"); // 0: id of analyzer plugin
											 // 1: Package name
											 // 2: Component instance name
											 // 3: System operation mode name
				if(s.length == 3){
					delegator.invoke(s[0], s[1], s[2], null);
				} else if (s.length == 4){
					delegator.invoke(s[0], s[1], s[2], s[3]);
				} else {
					// TODO: Handle this
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
