package org.osate.atsv.integration;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.osate.atsv.integration.EngineConfigModel.DistributionModel;
import org.osate.atsv.integration.EngineConfigModel.ExplorationEngineModel;
import org.osate.atsv.integration.EngineConfigModel.InputTokenAdapter;
import org.osate.atsv.integration.EngineConfigModel.InputTokenModel;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.EngineConfigModel.VariableModelAdapter;

public class EngineConfigGenerator {

	private Marshaller marshal;
	private JAXBElement<ExplorationEngineModel> cfg;
	private ExplorationEngineModel ecf;

	public EngineConfigGenerator() {
		try {
			JAXBContext context = JAXBContext.newInstance(ExplorationEngineModel.class, InputTokenModel.class,
					VariableModel.class);
			InputTokenAdapter inputAdapter = new InputTokenAdapter();
			VariableModelAdapter variableAdapter = new VariableModelAdapter();

			marshal = context.createMarshaller();
			// ATSV actually cannot parse formatted output :(
			marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			marshal.setAdapter(inputAdapter);
			marshal.setAdapter(variableAdapter);

			ecf = new ExplorationEngineModel();
			cfg = new JAXBElement<ExplorationEngineModel>(new QName("ExplorationEngineModel"),
					ExplorationEngineModel.class, ecf);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Add a variable to the engine configuration. The value parameter is ignored if isInput is false.
	 * 
	 * @param title The name of this variable
	 * @param capture Whether or not this variable is captured
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The initial value of this variable
	 */
	public void addVariable(String title, boolean capture, boolean sampled, boolean isInput, ATSVVariableType type,
			String value) {
		VariableModel vm = new VariableModel(title, capture, sampled, isInput, type, value);
		ecf.addVariable(vm);
	}

	/**
	 * Add a variable with an enumerated list of values to the engine configuration. The value parameter is ignored if isInput is false.
	 * 
	 * @param title The name of this variable
	 * @param capture Whether or not this variable is captured
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The initial value of this variable
	 * @param values The values this variable can take 
	 */
	public void addVariable(String title, boolean capture, boolean sampled, boolean isInput, ATSVVariableType type,
			String value, ValuesModel values) {
		VariableModel vm = new VariableModel(title, capture, sampled, isInput, type, value, values);
		ecf.addVariable(vm);
	}

	/**
	 * Add a variable with a distribution to the engine configuration. The value parameter is ignored if isInput is false.
	 * 
	 * @param title The name of this variable
	 * @param capture Whether or not this variable is captured
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The initial value of this variable
	 * @param distribution The distribution that the data this variable tracks fit 
	 */
	public void addVariable(String title, boolean capture, boolean sampled, boolean isInput, ATSVVariableType type,
			String value, DistributionModel distribution) {
		VariableModel vm = new VariableModel(title, capture, sampled, isInput, type, value, distribution);
		ecf.addVariable(vm);
	}

	public String getXML() throws JAXBException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		marshal.marshal(cfg, stream);
		return stream.toString();
	}
}