package org.osate.atsv.standalone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.network.Request;
import org.osate.atsv.integration.network.Response;
import org.osate.atsv.integration.network.SubcomponentChoice;

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
			sendRequest(getRequest(parseInput()));
			Response r = (Response) inStream.readObject();
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

	private static Map<String, String> parseInput() {
		Map<String, String> inputMap = null;
		Path path = Paths.get(System.getProperty("user.dir") + File.separator + "input.txt");
		try (Stream<String> lines = Files.lines(path)) {
			inputMap = lines.map(line -> line.split(",")).collect(Collectors.toMap(k -> k[0], k -> k[1]));
		} catch (FileNotFoundException e) {
			System.err.println("Can't find input.txt!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputMap;
	}

	private static Request getRequest(Map<String, String> inputMap) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "request.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Request r = new Request();
		r.setPluginId(prop.getProperty("pluginId"));
		r.setPackageName(prop.getProperty("packageName"));
		r.setComponentImplementationName(prop.getProperty("componentImplementationName"));
		for (String propName : prop.stringPropertyNames()) {
			String[] propNames = propName.split("-");
			if (propNames[0].equalsIgnoreCase("SubcompChoice")) {
				r.addChoicePoint(new SubcomponentChoice(propNames[1], propNames[2],
						inputMap.get(propNames[1] + "-" + propNames[2]), ATSVVariableType.STRING));
			}
		}
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
