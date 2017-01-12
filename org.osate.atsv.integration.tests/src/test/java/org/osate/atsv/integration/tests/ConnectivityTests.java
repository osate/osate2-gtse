package org.osate.atsv.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.integration.network.Request;
import org.osate.atsv.integration.network.Response;
import org.osate.core.test.OsateTest;

public class ConnectivityTests extends OsateTest {

	private static Socket socket;
	private static ObjectOutputStream outStream;
	private static ObjectInputStream inStream;

	@BeforeClass
	public static void preSetUp() throws IOException {
	}

	@AfterClass
	public static void afterCleanUp() throws IOException {
		socket.close();
	}

	@SuppressWarnings("unchecked")
	@Before
	public void customSetup() throws UnknownHostException, IOException {
		this.setUp();
		// I'm not sure this line can be written without warnings
		createFiles(Pair.<String, String> of("pullprotocols.aadl", pkgText));
		socket = new Socket("localhost",
				Activator.getDefault().getPreferenceStore().getDefaultInt(Activator.ATSV_INTEGRATION_PORT));
		outStream = new ObjectOutputStream(socket.getOutputStream());
		inStream = new ObjectInputStream(socket.getInputStream());
	}

	@After
	public void customCleanUp() throws IOException {
		this.cleanUp();
		socket.close();
	}

	@Test
	public void badPluginIdTest() throws IOException, ClassNotFoundException {
		Request r = new Request();
		final String fakePluginID = "this.isnt.a.real.plugin.id";
		r.setPluginId(fakePluginID);
		r.setPackageName("none");
		r.setComponentImplementationName("none");
		outStream.writeObject(r);
		outStream.flush();
		Response res = (Response) inStream.readObject();
		assertTrue(res.hasException());
		assertEquals("No extension with id '" + fakePluginID + "' found!", res.getException().getMessage());
		assertTrue(res.getVariables().isEmpty());
	}

	/*-
	 * 
	 * Disabled 10.11.16 -- I can't get maven tycho to run this test successfully, even though it
	 * runs just fine in eclipse.
	*/
	@Test
	public void osateConnectivityTest() throws IOException, ClassNotFoundException {
		Request r = new Request();
		r.setPluginId("org.osate.atsv.integration.flow-latency");
		r.setPackageName("PullProtocols");
		r.setComponentImplementationName("stub.i");
		outStream.writeObject(r);
		outStream.flush();
		Response res = (Response) inStream.readObject();
		assertFalse(res.hasException());
		assertTrue(res.getVariables().containsKey("XferOnly"));
		assertEquals("504.0", res.getVariables().get("XferOnly"));
	}

	@Override
	public String getProjectName() {
		return "ATSVConnectivityTestProject";
	}

	private static String pkgText = "\n" + "package PullProtocols\n" + "public\n" + "\n"
			+ "virtual bus DCFMInputPullProtocol\n" + "properties\n" + "Transmission_Type => pull;\n"
			+ "Latency => 300 ms .. 300 ms ; -- should be allowed on virtual bus\n"
			+ "-- Implemented_As => classifier (PullDCFMInputDataset.CrossPartition);\n"
			+ "-- implemented_as wants a system implementation or an abstract implementation\n"
			+ "-- On the other hand latency analysis currently assumes that threads sit inside processes for one way of recognizing partition boundaries.\n"
			+ "end DCFMInputPullProtocol;\n" + "\n" + "process PullDCFMInputDataset\n" + "features\n"
			+ "SenderData: in data port ;\n" + "ReceiverData: out data port ;\n" + "flows\n"
			+ "Xfer: flow path SenderData -> ReceiverData;\n" + "end PullDCFMInputDataset; \n" + "\n"
			+ "process implementation PullDCFMInputDataset.CrossPartition\n" + "subcomponents\n"
			+ " sender: thread PullDCFMDataSetSender;\n" + " requestor: thread PullDCFMDataSetRequestor;\n"
			+ " connections\n" + " 	incoming: port SenderData -> sender.SenderData;\n"
			+ " 	outgoing: port requestor.ReceiverData -> ReceiverData;\n"
			+ " 	STRequest: port requestor.SourceTracksRequest -> sender.SourceTracksRequest ;--{Timing => Immediate;};\n"
			+ " 	STReply: port sender.SendSourceTrackSet -> requestor.ReceivedSourceTrackSet\n"
			+ " 		;--{Timing => Delayed;};\n"
			+ " 	CTRequest: port requestor.CorrelatedTracksRequest -> sender.CorrelatedTracksRequest;--{Timing => Immediate;};\n"
			+ " 	CTReply: port sender.SendCorrelatedTrackSet -> requestor.ReceivedCorrelatedTrackSet;-- {Timing => Delayed;};\n"
			+ " 	APRequest: port requestor.OwnAircraftPositionRequest -> sender.OwnAircraftPositionRequest;--{Timing => Immediate;};\n"
			+ " 	APReply: port sender.SendOwnAircraftPosition -> requestor.ReceivedOwnAircraftPosition;-- {Timing => Delayed;};\n"
			+ " flows\n"
			+ " -- flow to measure latency of protocol. The result is to be reflected in the latency property of the virtual bus it implements\n"
			+ " 	XferOnly: end to end flow sender.SourceTrackFlow -> STReply -> requestor.SourceTrackReceivedFlow -> CTRequest ->\n"
			+ " 		sender.CorrelatedFlow -> CTReply -> requestor.CorrelatedTrackReceivedFlow -> \n"
			+ " 		APRequest -> sender.AircraftPositionFlow -> APReply -> requestor.AircraftPositionReceivedFlow;\n"
			+ "-- 		{Latency => 10 ms .. 10 ms;};\n"
			+ " -- flow to be used when abstract implementation is used in the transfer instead of a protocol binding \n"
			+ " 	Xfer: flow path SenderData -> incoming -> sender.SenderDataSetFLow -> STReply -> requestor.SourceTrackReceivedFlow -> CTRequest ->\n"
			+ " 		sender.CorrelatedFlow -> CTReply -> requestor.CorrelatedTrackReceivedFlow -> APRequest -> sender.AircraftPositionFlow -> APReply -> requestor.ReceivedDataSetFlow\n"
			+ " 		-> outgoing -> ReceiverData;\n" + " 	properties\n"
			+ " 	-- alternating immediate/delayed emulates partition slots and communication within a frame.\n"
			+ " 	-- making all delayed emulates frame delayed cross partition communication.\n"
			+ " 	-- we could also indicates partitions by tagging the process as SEI::isPartition and a SEI::Partition_Latency\n"
			+ " 	-- Alternatively we can use virtual processor and the ARINC653 properties or the period on the virtual processor\n"
			+ " 		Timing => Immediate applies to STRequest,CTRequest,APRequest;\n"
			+ " 		Timing => Delayed applies to STReply,CTReply,APReply;\n"
			+ " 		Latency => 300 ms .. 300 ms applies to XferOnly;\n"
			+ " 		Period => 100 ms applies to sender, requestor;\n"
			+ "-- 		Deadline => 1 ms applies to sender, requestor;\n"
			+ " 		Dispatch_Protocol => Periodic applies to sender, requestor;\n"
			+ "end PullDCFMInputDataset.CrossPartition;\n" + "\n" + "system stub\n" + "end stub;\n" + "\n"
			+ "system implementation stub.i\n" + "subcomponents\n"
			+ " prot: process PullDCFMInputDataset.CrossPartition;\n" + "end stub.i;\n" + "\n"
			+ "thread PullDCFMDataSetSender\n" + "features\n" + "-- data to be transferred\n"
			+ "SenderData: in data port  ;\n" + "-- protocol interaction ports\n"
			+ "SourceTracksRequest: in data port;\n" + "SendSourceTrackSet: out data port ;\n"
			+ "CorrelatedTracksRequest: in data port;\n" + "SendCorrelatedTrackSet: out data port ;\n"
			+ "OwnAircraftPositionRequest: in data port;\n" + "SendOwnAircraftPosition: out data port  ;\n" + "flows\n"
			+ "	SenderDataSetFLow: flow path SenderData -> SendSourceTrackSet;\n"
			+ "	SourceTrackFlow: flow source SendSourceTrackSet;\n"
			+ "	SourceTrackFlowpath: flow path SourceTracksRequest->SendSourceTrackSet;\n"
			+ "	CorrelatedFlow: flow path CorrelatedTracksRequest -> SendCorrelatedTrackSet;\n"
			+ "	AircraftPositionFlow: flow path OwnAircraftPositionRequest -> SendOwnAircraftPosition;\n"
			+ "properties\n" + "	Dispatch_Protocol => Periodic;\n"
			+ "	Latency => 1ms..1ms applies to SourceTrackFlowpath, CorrelatedFlow, AircraftPositionFlow;\n"
			+ "end PullDCFMDataSetSender;\n" + "\n" + "thread PullDCFMDataSetRequestor\n" + "features\n"
			+ "-- data being transferred\n" + "ReceiverData: out data port  ;\n" + "-- protocol interaction ports\n"
			+ "SourceTracksRequest: out data port;\n" + "ReceivedSourceTrackSet: in data port ;\n"
			+ "CorrelatedTracksRequest: out data port;\n" + "ReceivedCorrelatedTrackSet: in data port ;\n"
			+ "OwnAircraftPositionRequest: out data port;\n" + "ReceivedOwnAircraftPosition: in data port  ;\n"
			+ "flows\n" + "	SourceTrackRequestFlow: flow source SourceTracksRequest;\n"
			+ "	SourceTrackReceivedFlow: flow path ReceivedSourceTrackSet -> CorrelatedTracksRequest;\n"
			+ "	CorrelatedTrackReceivedFlow: flow path ReceivedCorrelatedTrackSet -> OwnAircraftPositionRequest;\n"
			+ "	AircraftPositionReceivedFlow: flow sink ReceivedOwnAircraftPosition ;\n"
			+ "	ReceivedDataSetFlow: flow path ReceivedOwnAircraftPosition -> ReceiverData;\n" + "properties\n"
			+ "	Dispatch_Protocol => Periodic;\n"
			+ "	Latency => 1ms..1ms applies to SourceTrackReceivedFlow, CorrelatedTrackReceivedFlow;\n"
			+ "	Latency => 0ms..0ms applies to SourceTracksRequest, AircraftPositionReceivedFlow;\n"
			+ "	end PullDCFMDataSetRequestor;\n" + "\n" + "end PullProtocols;\n" + "\n";
}
