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
package org.osate.atsv.integration.tests;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class IntegrationTests extends IntegrationTestBase {

	@Test
	public void badPluginIdTest() throws IOException, ClassNotFoundException {
		final String fakePluginID = "this.isnt.a.real.plugin.id";
		testHelper(fakePluginID, "No extension with id '" + fakePluginID + "' found!", false,
				"Encountered exception: No extension with id '" + fakePluginID + "' found!", Collections.emptyMap(),
				Collections.emptySet());
	}

	@Test
	public void flowLatencyTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.flow-latency", null, true, "",
				Collections.singletonMap("scs.exampleFlow", new VarDescriptor("160.0")), Collections.emptySet());
	}

	@Test
	public void portConsistencyTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.port-consistency", null, false,
				"Port Consistency Analysis -- In mdev.output -> edev.input: Source rate unit PerSecond and destination rate unit PerDispatch differ;\nPort Consistency Analysis -- In mdev.output -> edev.input: Maximum source data rate 50.0 is greater than maximum destination data rate 1.0",
				Collections.emptyMap(), Collections.emptySet());
	}

	@Test
	public void powerConsumptionTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.power-consumption", null, true, "",
				Collections.singletonMap("Grid", new VarDescriptor("19.0")), Collections.emptySet());
	}

	@Test
	public void unhandledFaultsTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.unhandled-faults", null, false,
				"Unhandled Faults Analysis -- EPSU.Supplier -> Grid.Supplier: Outgoing propagation  Supplier{ValueError} has error types not handled by incoming propagation Supplier{SequenceValueError};\nUnhandled Faults Analysis -- Outgoing propagation  Supplier{ValueError} has error types not handled by incoming propagation Supplier{SequenceValueError}",
				Collections.emptyMap(), Collections.emptySet());
	}

	@Test
	public void priceAndWeightTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.property-totals", null, true, "",
				Stream.of(new Object[] { "Price", new VarDescriptor("1750.63") },
						new Object[] { "Weight", new VarDescriptor("8.41") })
				.collect(Collectors.toMap(s -> (String) s[0], s -> (VarDescriptor) s[1])),
				Collections.emptySet());
	}

	@Test
	public void limitTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.property-totals", null, false,
				"Limit Violation -- Price (1750.63) must be less than 1500.0 but isn't.",
				Collections.singletonMap("Price", new VarDescriptor("1750.63", "lt", "1500.0")),
				Collections.emptySet());
	}
}
