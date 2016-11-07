package org.osate.atsv.standalone;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map.Entry;

import org.osate.atsv.integration.network.Request;
import org.osate.atsv.integration.network.Response;

/**
 * 
 * A simple standalone client designed to be connected to an ATSV-Integration enabled version of OSATE.
 * 
 * @author Sam Procter
 *
 */
public class NetworkHandler {
	private static ObjectOutputStream outStream;
	private static ObjectInputStream inStream;

	public static void main(String[] args) {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		try (Socket socket = new Socket(host, port); PrintWriter out = new PrintWriter("output.txt")) {
			initializeSender(socket);
			initializeListener(socket); // We could initialize a listener per request, to parallelize things
			sendRequest(getMockRequest());
			Response r;
			r = (Response) inStream.readObject();
			if (r.hasException()) {
				System.err.println("An exception occurred.");
				System.err.println(r.getException().getMessage());
				r.getException().printStackTrace(System.err);
			} else {
				for (Entry<String, String> e : r.getVariables().entrySet()) {
					out.println(e.getKey() + "," + e.getValue());
				}
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Request getMockRequest() {
		Request r = new Request();
		r.setPluginId("org.osate.atsv.integration.flow-latency");
		r.setPackageName("CarSystem");
		r.setComponentImplementationName("MyCar.CruiseControlTasks");
		return r;
	}

	private static void initializeListener(Socket socket) throws IOException {
		inStream = new ObjectInputStream(socket.getInputStream());
	}

	private static void initializeSender(Socket socket) throws IOException {
		outStream = new ObjectOutputStream(socket.getOutputStream());
	}

	public static void sendRequest(Request r) throws IOException {
		outStream.writeObject(r);
		outStream.flush();
	}
}
