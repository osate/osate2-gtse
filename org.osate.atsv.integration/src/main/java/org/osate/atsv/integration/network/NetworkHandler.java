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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

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
	private StringBuilder stringbuilder;
	private int runCount = 1;
	private Instant previousResponseTime = Instant.EPOCH;

	public NetworkHandler(int portNum) {
		this.portNum = portNum;
	}

	@Override
	public void run() {
		Request req;
		Response res;
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(portNum, 50, InetAddress.getLoopbackAddress()); // bind to port
					Socket clientSocket = serverSocket.accept(); // wait for ATSV
					ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());) {
				if (!((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress().isLoopbackAddress()) {
					throw new Exception("Remote connections are not allowed.");
				}
				Timestamp timestamp = new Timestamp(previousResponseTime);
				timestamp.setRequestReceived(Instant.now());
				AnalysisDelegator delegator = new AnalysisDelegator(timestamp);
				req = (Request) input.readObject();
				timestamp.setUnmarshallingComplete(Instant.now());
				res = delegator.invoke(req);
				output.writeObject(res);
				timestamp.setMarshallingComplete(Instant.now());
				output.flush();
				previousResponseTime = Instant.now();
				timestamp.setResponseSent(previousResponseTime);
				printTimestampCSV(timestamp);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private void printTimestampCSV(Timestamp timestamp) {
		// Run #, ATSV Time, OSATE init time, unmarshalling time, plugin load time, instantiation time, analysis time, marshalling time, transmit time, osate
		// time
		stringbuilder = new StringBuilder();
		stringbuilder.append(runCount++).append(",");
		stringbuilder.append(Duration.between(timestamp.getPrevResponseSent(), timestamp.getRequestReceived()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getRequestReceived(), timestamp.getOsateInitialized()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getOsateInitialized(), timestamp.getUnmarshallingComplete()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getUnmarshallingComplete(), timestamp.getPluginsLoaded()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getPluginsLoaded(), timestamp.getModelInstantiated()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getModelInstantiated(), timestamp.getAnalysesRun()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getAnalysesRun(), timestamp.getMarshallingComplete()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getMarshallingComplete(), timestamp.getResponseSent()))
				.append(",");
		stringbuilder.append(Duration.between(timestamp.getRequestReceived(), timestamp.getResponseSent())).append(",")
				.append("\n");
		System.out.print(stringbuilder);
	}

	private void printTimestampHuman(Timestamp timestamp) {
		stringbuilder = new StringBuilder();
		stringbuilder.append("Run #").append(runCount++).append(":\n");
		stringbuilder.append("\tATSV time: ")
				.append(Duration.between(timestamp.getPrevResponseSent(), timestamp.getRequestReceived())).append("\n");
		stringbuilder.append("\tOSATE Init time: ")
				.append(Duration.between(timestamp.getRequestReceived(), timestamp.getOsateInitialized())).append("\n");
		stringbuilder.append("\tUnmarshalling time: ")
				.append(Duration.between(timestamp.getOsateInitialized(), timestamp.getUnmarshallingComplete()))
				.append("\n");
		stringbuilder.append("\tPlugin Load time: ")
				.append(Duration.between(timestamp.getUnmarshallingComplete(), timestamp.getPluginsLoaded()))
				.append("\n");
		stringbuilder.append("\tInstantiation time: ")
				.append(Duration.between(timestamp.getPluginsLoaded(), timestamp.getModelInstantiated())).append("\n");
		stringbuilder.append("\tAnalysis time: ")
				.append(Duration.between(timestamp.getModelInstantiated(), timestamp.getAnalysesRun())).append("\n");
		stringbuilder.append("\tMarshalling time: ")
				.append(Duration.between(timestamp.getAnalysesRun(), timestamp.getMarshallingComplete())).append("\n");
		stringbuilder.append("\tTransmit time: ")
				.append(Duration.between(timestamp.getMarshallingComplete(), timestamp.getResponseSent()))
				.append("\n");
		stringbuilder.append("Total OSATE time: ")
				.append(Duration.between(timestamp.getRequestReceived(), timestamp.getResponseSent())).append("\n")
				.append("=============================").append("\n");
		System.out.print(stringbuilder);
	}
}
