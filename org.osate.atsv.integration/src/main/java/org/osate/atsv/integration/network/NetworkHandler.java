package org.osate.atsv.integration.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.osate.atsv.integration.AnalysisDelegator;

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
		Request req;
		Response res;
		try (ServerSocket serverSocket = new ServerSocket(portNum); // bind to port
				Socket clientSocket = serverSocket.accept(); // wait for ATSV
				ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());) {
			AnalysisDelegator delegator = new AnalysisDelegator();
			while ((req = (Request) input.readObject()) != null) {
				res = delegator.invoke(req);
				output.writeObject(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
