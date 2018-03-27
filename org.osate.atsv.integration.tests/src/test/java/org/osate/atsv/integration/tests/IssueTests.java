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
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.ChoicePointModel.SubcomponentChoice;

public class IssueTests extends IntegrationTestBase {

	@Test
	public void Issue0001Test() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.flow-latency", null, true, "",
				Collections.singletonMap("scs.exampleFlow", new VarDescriptor("60.0")),
				Collections.singleton(new SubcomponentChoice("scs.mdev", "SimpleComponentChoice::MidProcess1",
						ATSVVariableType.STRING)));

	}

	@Test
	public void Issue0002Test() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.property-totals", null, true, "",
				Stream.of(new Object[] { "Price", new VarDescriptor("1500.0") },
						new Object[] { "Weight", new VarDescriptor("8.41") })
				.collect(Collectors.toMap(s -> (String) s[0], s -> (VarDescriptor) s[1])),
				Collections.singleton(new SubcomponentChoice("scs.mdev", "SimpleComponentChoice::MidProcess1",
						ATSVVariableType.STRING)));
	}
}
