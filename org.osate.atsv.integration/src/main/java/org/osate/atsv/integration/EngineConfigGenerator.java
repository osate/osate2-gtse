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
package org.osate.atsv.integration;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.osate.atsv.integration.EngineConfigModel.ConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.DistributionModel;
import org.osate.atsv.integration.EngineConfigModel.ExplorationEngineModel;
import org.osate.atsv.integration.EngineConfigModel.InputTokenAdapter;
import org.osate.atsv.integration.EngineConfigModel.InputTokenModel;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.EngineConfigModel.VariableModelAdapter;
import org.osate.atsv.integration.exception.ConfiguratorRepresentationException;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;

public final class EngineConfigGenerator {

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
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The initial value of this variable
	 */
	public void addVariable(String title, boolean sampled, boolean isInput, ATSVVariableType type, String value) {
		if (value == null) {
			value = ATSVVariableType.getDefaultFromType(type);
		}
		VariableModel vm = new VariableModel(title, sampled, isInput, type, value);
		ecf.addVariable(vm);
	}

	/**
	 * Add a variable with an enumerated list of values to the engine configuration. The value parameter is ignored if isInput is false.
	 * 
	 * @param title The name of this variable
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The initial value of this variable
	 * @param values The values this variable can take 
	 */
	public void addVariable(String title, boolean sampled, boolean isInput, ATSVVariableType type, String value,
			ValuesModel values) {
		if (value == null || value.equals("")) {
			value = values.getDefault();
		}
		VariableModel vm = new VariableModel(title, sampled, isInput, type, value, values);
		ecf.addVariable(vm);
	}

	/**
	 * Add a variable with a distribution to the engine configuration. The value parameter is ignored if isInput is false.
	 * 
	 * @param title The name of this variable
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The initial value of this variable
	 * @param distribution The distribution that the data this variable tracks fit 
	 */
	public void addVariable(String title, boolean sampled, boolean isInput, ATSVVariableType type, String value,
			DistributionModel distribution) {
		if (value == null || value.equals("")) {
			value = distribution.getDefault();
		}
		VariableModel vm = new VariableModel(title, sampled, isInput, type, value, distribution);
		ecf.addVariable(vm);
	}

	public String getXML() throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		// The configurators have to be double-encoded, so we call that rendering here
		ecf.renderConfigurator();
		// And here we render the entire specification
		marshal.marshal(cfg, stream);
		return stream.toString();
	}

	/**
	 * Add a requirement that two variables must always have equal values
	 * 
	 * @param varName1
	 * @param varName2
	 */
	public void addEqualityConstraint(String varName1, String varName2) {
		ecf.addConfigurator(new ConfiguratorModel(varName1, varName2, true));
	}

	/**
	 * Add a requirement that two variables must never have equal values
	 * 
	 * @param varName1
	 * @param varName2
	 */
	public void addUniquenessConstraint(String varName1, String varName2) {
		ecf.addConfigurator(new ConfiguratorModel(varName1, varName2, false));
	}
}