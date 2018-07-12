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
package org.osate.atsv.standalone;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.ChoicePointModel.ListPropertyValue;
import org.osate.atsv.integration.ChoicePointModel.LiteralPropertyValue;
import org.osate.atsv.integration.ChoicePointModel.ReferencePropertyValue;
import org.osate.atsv.integration.ChoicePointModel.SubcomponentChoice;
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
		int port = Integer.parseInt(args[0]);
		try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), port)) {
			if (!((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().isLoopbackAddress()) {
				throw new Exception("Remote connections are not allowed.");
			}
			initializeSender(socket);
			initializeListener(socket); // We could initialize a listener per request, to parallelize things
			sendRequest(getRequest(parseInput()));
			Response r = (Response) inStream.readObject();
			if (r.hasException()) {
				System.err.println("An exception occurred.");
				System.err.println(r.getException().getMessage());
				r.getException().printStackTrace(System.err);
			} else {
				ATSVVarCollection avc = new ATSVVarCollection();
				for (ATSVVar v : r.getVariables().getVars().values()) {
					// Variable types aren't actually used for output, so we use a dummy value here
					avc.addVar(v.getName(), v.getType(), v.getVal());
				}
				avc.writeToFile(System.getProperty("user.dir") + File.separator + "output.xml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, String> parseInput() throws JAXBException {
		Map<String, String> inputMap = new HashMap<>();
		ATSVVarCollection avc = new ATSVVarCollection();
		avc.loadFromFile(System.getProperty("user.dir") + File.separator + "input.xml");
		for (ATSVVar var : avc.getVars().values()) {
			inputMap.put(var.getName(), var.getVal());
		}
		return inputMap;
	}

	private static Request getRequest(Map<String, String> inputMap) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "request.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Request r = new Request();
		r.setPluginIds(prop.getProperty("pluginIds").split(","));
		r.setPackageName(prop.getProperty("packageName"));
		r.setComponentImplementationName(prop.getProperty("componentImplementationName"));
		for (String propName : prop.stringPropertyNames()) {
			String[] propNames = propName.split("-");
			if (propNames[0].equalsIgnoreCase("SubcompChoice")) {
				r.addChoicePoint(
						new SubcomponentChoice(propNames[1], inputMap.get(propNames[1]), ATSVVariableType.STRING));
			} else if (propNames[0].equalsIgnoreCase("LitPropertyValue")) {
				r.addChoicePoint(new LiteralPropertyValue(propNames[1], propNames[2],
						inputMap.get(propNames[1] + "-" + propNames[2]), ATSVVariableType.getTypeByName(propNames[3])));
			} else if (propNames[0].equalsIgnoreCase("RefPropertyValue")) {
				r.addChoicePoint(new ReferencePropertyValue(propNames[1], propNames[2],
						// TODO: Change to ATSVVariableType.REFERENCE?
						inputMap.get(propNames[1] + "-" + propNames[2]), ATSVVariableType.STRING));
			} else if (propNames[0].equalsIgnoreCase("ListRefPropertyValue")) {
				r.addChoicePoint(new ListPropertyValue(propNames[1], propNames[2],
						inputMap.get(propNames[1] + "-" + propNames[2]), ATSVVariableType.REFERENCE));
			} else if (propNames[0].equalsIgnoreCase("ListLitPropertyValue")) {
				r.addChoicePoint(new ListPropertyValue(propNames[1], propNames[2],
						inputMap.get(propNames[1] + "-" + propNames[2]), ATSVVariableType.getTypeByName(propNames[3])));
			} else if (propNames[0].equalsIgnoreCase("Limit")) {
				String[] limitArr = prop.getProperty(propName).split("-");
				r.addLimit(propNames[1], limitArr[0], limitArr[1]);
			} else if (propNames[0].equalsIgnoreCase("ConfigCacheHack")) {
				r.addConfiguratorCacheEntry(propNames[1], Float.valueOf(propNames[2]), prop.getProperty(propName));
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
