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

	@Override
	public void run() {
		Request req;
		Response res;
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(portNum); // bind to port
					Socket clientSocket = serverSocket.accept(); // wait for ATSV
					ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());) {
				AnalysisDelegator delegator = new AnalysisDelegator();
				req = (Request) input.readObject();
				res = delegator.invoke(req);
				output.writeObject(res);
				output.flush();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
