package org.osate.atsv.integration.tests;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.osate.atsv.integration.ChoicePointModel.SubcomponentChoice;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class IssueTests extends IntegrationTestBase {

	@Test
	public void Issue0001Test() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.flow-latency", null, true,
				Collections.singletonMap("exampleFlow", new VarDescriptor("25.0")),
				Collections.singleton(new SubcomponentChoice("scs.mdev", "SimpleComponentChoice::MidProcess1",
						ATSVVariableType.STRING)));

	}

	@Test
	public void Issue0002Test() throws IOException, ClassNotFoundException {
		testHelper("org.osate.atsv.integration.property-totals", null, true,
				Stream.of(new Object[] { "Price", new VarDescriptor("1500.0") },
						new Object[] { "Weight", new VarDescriptor("8.41") })
						.collect(Collectors.toMap(s -> (String) s[0], s -> (VarDescriptor) s[1])),
				Collections.singleton(new SubcomponentChoice("scs.mdev", "SimpleComponentChoice::MidProcess1",
						ATSVVariableType.STRING)));
	}
}
