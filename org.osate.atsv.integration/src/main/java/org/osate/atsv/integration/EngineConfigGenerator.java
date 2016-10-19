package org.osate.atsv.integration;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.osate.atsv.integration.EngineConfigModel.ExplorationEngineModel;

public class EngineConfigGenerator {

	private Marshaller marshal;
	private ExplorationEngineModel cfg;

	public EngineConfigGenerator() {
		try {
			JAXBContext context = JAXBContext.newInstance(ExplorationEngineModel.class);
			marshal = context.createMarshaller();
			marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			cfg = new ExplorationEngineModel();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getFormattedXML() throws JAXBException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		marshal.marshal(cfg, stream);
		return stream.toString();
	}
}