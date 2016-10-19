package org.osate.atsv.integration.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isSimilarTo;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.osate.atsv.integration.EngineConfigGenerator;

public class EngineConfigTests {

	EngineConfigGenerator ecf = new EngineConfigGenerator();

	@Test
	public void test() throws JAXBException {
		String controlXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ExplorationEngineModel><analysisCode>CommandLineProblem</analysisCode></ExplorationEngineModel>";
		assertThat(ecf.getFormattedXML(), isSimilarTo(controlXML).normalizeWhitespace());
	}
}
