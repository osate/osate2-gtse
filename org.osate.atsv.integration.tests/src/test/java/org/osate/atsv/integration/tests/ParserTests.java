package org.osate.atsv.integration.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.standalone.ATSVVar;
import org.osate.atsv.standalone.ATSVVarCollection;

public class ParserTests {

	/**
	 * One variable of float type
	 */
	private final static String EXPECTED_BASIC_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
			+ System.lineSeparator() + "<vars>" + System.lineSeparator()
			+ "    <var name=\"Test\" type=\"FLOAT\">42.0</var>" + System.lineSeparator() + "</vars>"
			+ System.lineSeparator();

	/**
	 * Multiple variables, multiple types
	 */
	private final static String EXPECTED_COMPLEX_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
			+ System.lineSeparator() + "<vars>" + System.lineSeparator()
			+ "    <var name=\"1Float\" type=\"FLOAT\">42.0</var>" + System.lineSeparator()
			+ "    <var name=\"2Int\" type=\"INTEGER\">365</var>" + System.lineSeparator()
			+ "    <var name=\"3String\" type=\"STRING\">Hello!</var>" + System.lineSeparator() + "</vars>"
			+ System.lineSeparator();

	private final static String XML_FILE_PATH_FIRST = "src";
	private final static String[] BASIC_XML_FILE_PATH_REST = { "test", "resources", "BasicInOut.xml" };
	private final static String[] COMPLEX_XML_FILE_PATH_REST = { "test", "resources", "ComplexInOut.xml" };

	private Path basicXMLFilePath = FileSystems.getDefault().getPath(XML_FILE_PATH_FIRST, BASIC_XML_FILE_PATH_REST);
	private Path complexXMLFilePath = FileSystems.getDefault().getPath(XML_FILE_PATH_FIRST, COMPLEX_XML_FILE_PATH_REST);
	private ATSVVarCollection basicAVC;
	private ATSVVarCollection complexAVC;

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	@Before
	public void preSetUp() throws JAXBException {
		basicAVC = new ATSVVarCollection();
		basicAVC.addVar("Test", ATSVVariableType.FLOAT, "42.0");
		complexAVC = new ATSVVarCollection();
		complexAVC.addVar("1Float", ATSVVariableType.FLOAT, "42.0");
		complexAVC.addVar("2Int", ATSVVariableType.INTEGER, "365");
		complexAVC.addVar("3String", ATSVVariableType.STRING, "Hello!");

		JAXBContext context = JAXBContext.newInstance(ATSVVarCollection.class);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		unmarshaller = context.createUnmarshaller();
	}

	@Test
	public void basicInputTest() throws JAXBException {
		StringWriter writer = new StringWriter();
		marshaller.marshal(basicAVC, writer);
		assertEquals(EXPECTED_BASIC_XML, writer.toString());
	}

	@Test
	public void ComplexInputTest() throws JAXBException {
		StringWriter writer = new StringWriter();
		marshaller.marshal(complexAVC, writer);
		assertEquals(EXPECTED_COMPLEX_XML, writer.toString());
	}

	@Test
	public void basicInputFileTest() throws JAXBException {
		ATSVVarCollection localAVC = new ATSVVarCollection();
		localAVC.loadFromFile(basicXMLFilePath.toFile());
		varCollectionTestHelper(basicAVC, localAVC);
	}

	@Test
	public void complexInputFileTest() throws JAXBException {
		ATSVVarCollection localAVC = new ATSVVarCollection();
		localAVC.loadFromFile(complexXMLFilePath.toFile());
		varCollectionTestHelper(complexAVC, localAVC);
	}

	@Test
	public void basicOutputTest() throws JAXBException {
		StringReader reader = new StringReader(EXPECTED_BASIC_XML);
		varCollectionTestHelper(basicAVC, (ATSVVarCollection) unmarshaller.unmarshal(reader));
	}

	@Test
	public void complexOutputTest() throws JAXBException {
		StringReader reader = new StringReader(EXPECTED_COMPLEX_XML);
		varCollectionTestHelper(complexAVC, (ATSVVarCollection) unmarshaller.unmarshal(reader));
	}

	@Test
	public void basicOutputFileTest() throws JAXBException, IOException {
		Path tmpPath = Files.createTempFile("GTSEParserTests", null);
		basicAVC.writeToFile(tmpPath.toFile());
		List<String> tempContents = Files.readAllLines(tmpPath);
		List<String> avcContents = Files.readAllLines(basicXMLFilePath);
		Iterator<String> avcIter = avcContents.iterator();
		for (String line : tempContents) {
			assertEquals(avcIter.next(), line);
		}
	}

	@Test
	public void complexOutputFileTest() throws JAXBException, IOException {
		Path tmpPath = Files.createTempFile("GTSEParserTests", null);
		complexAVC.writeToFile(tmpPath.toFile());
		List<String> tempContents = Files.readAllLines(tmpPath);
		List<String> avcContents = Files.readAllLines(complexXMLFilePath);
		Iterator<String> avcIter = avcContents.iterator();
		for (String line : tempContents) {
			assertEquals(avcIter.next(), line);
		}
	}

	private void varCollectionTestHelper(ATSVVarCollection expectedAVC, ATSVVarCollection actualAVC) {
		assertEquals(expectedAVC.getVars().size(), actualAVC.getVars().size());
		ATSVVar actualVar = actualAVC.getVars().iterator().next();
		ATSVVar expectedVar = expectedAVC.getVars().iterator().next();
		assertEquals(expectedVar.getName(), actualVar.getName());
		assertEquals(expectedVar.getType(), actualVar.getType());
		assertEquals(expectedVar.getVal(), actualVar.getVal());
	}
}
