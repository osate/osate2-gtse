package org.osate.atsv.integration;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.osate.atsv.integration.EngineConfigModel.ExplorationEngineModel;
import org.osate.atsv.integration.EngineConfigModel.InputTokenAdapter;
import org.osate.atsv.integration.EngineConfigModel.InputTokenModel;

public class EngineConfigGenerator {

	private Marshaller marshal;
	private JAXBElement<ExplorationEngineModel> cfg;
	private ExplorationEngineModel ecf;

	public EngineConfigGenerator() {
		try {
			JAXBContext context = JAXBContext.newInstance(ExplorationEngineModel.class, InputTokenModel.class);
			InputTokenAdapter inputAdapter = new InputTokenAdapter();
			marshal = context.createMarshaller();
			// ATSV actually cannot parse formatted output :(
			marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			marshal.setAdapter(inputAdapter);
			ecf = new ExplorationEngineModel();
			cfg = new JAXBElement<ExplorationEngineModel>(new QName("ExplorationEngineModel"), ExplorationEngineModel.class, ecf);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	public String getXML() throws JAXBException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		marshal.marshal(cfg, stream);
		return stream.toString();
	}
}