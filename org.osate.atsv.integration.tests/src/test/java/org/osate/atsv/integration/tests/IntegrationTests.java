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
		testHelper(fakePluginID, "No extension with id '" + fakePluginID + "' found!", false, Collections.emptyMap(),
				Collections.emptySet());
	}

	@Test
	public void flowLatencyTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.flow-latency", null, true,
				Collections.singletonMap("exampleFlow", new VarDescriptor("25.0")), Collections.emptySet());
	}

	@Test
	public void portConsistencyTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.port-consistency", null, false, Collections.emptyMap(),
				Collections.emptySet());
	}

	@Test
	public void powerConsumptionTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.power-consumption", null, true,
				Collections.singletonMap("Grid", new VarDescriptor("20.0")), Collections.emptySet());
	}

	@Test
	public void unhandledFaultsTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.unhandled-faults", null, false, Collections.emptyMap(),
				Collections.emptySet());
	}

	@Test
	public void priceAndWeightTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.property-totals", null, true,
				Stream.of(new Object[] { "Price", new VarDescriptor("1750.63") },
						new Object[] { "Weight", new VarDescriptor("8.41") })
						.collect(Collectors.toMap(s -> (String) s[0], s -> (VarDescriptor) s[1])),
				Collections.emptySet());
	}

	@Test
	public void limitTest() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.property-totals", null, false,
				Collections.singletonMap("Price", new VarDescriptor("1750.63", "lt", "1500.0")),
				Collections.emptySet());
	}
}
