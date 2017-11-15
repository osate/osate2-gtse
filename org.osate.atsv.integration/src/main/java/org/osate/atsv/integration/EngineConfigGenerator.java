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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.EngineConfigModel.DistributionModel;
import org.osate.atsv.integration.EngineConfigModel.ExplorationEngineModel;
import org.osate.atsv.integration.EngineConfigModel.ImpliesConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.InputTokenAdapter;
import org.osate.atsv.integration.EngineConfigModel.InputTokenModel;
import org.osate.atsv.integration.EngineConfigModel.SetRestrictionConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.SimpleConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModelAdapter;
import org.osate.atsv.integration.exception.ConfiguratorRepresentationException;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;
import org.osate.atsv.integration.exception.UnsupportedFeatureException;
import org.osate.atsv.integration.network.Limit;
import org.osate.atsv.standalone.ATSVVarCollection;

/**
 * This is the main API for specifying ATSV engine configurations by adding variables and constraints.
 * @author sam
 *
 */
public final class EngineConfigGenerator {

	private Marshaller marshal;
	private JAXBElement<ExplorationEngineModel> cfg;
	private ExplorationEngineModel ecf;
	private Map<String, Limit> limits = new HashMap<>();
	private ATSVVarCollection startingInputs = new ATSVVarCollection();
	private ATSVVarCollection startingOutputs = new ATSVVarCollection();

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

	public ATSVVarCollection getStartingInputs() {
		return startingInputs;
	}

	public ATSVVarCollection getStartingOutputs() {
		return startingOutputs;
	}

	/**
	 * Add an input variable to the engine configuration.
	 *
	 * @param title The name of this variable
	 * @param sampled Whether or not this variable is sampled
	 * @param type The type of this variable
	 */
	public void addInputVariable(String title, boolean sampled, ATSVVariableType type) {
		String value = ATSVVariableType.getDefaultFromType(type);
		VariableModel vm = new VariableModel(title, sampled, true, type, value);
		ecf.addVariable(vm);
	}

	/**
	 * Add an expected output variable to the engine configuration.
	 *
	 * @param title The name of this variable
	 * @param type The type of this variable
	 * @param limit A limit for this variable, or null if none
	 */
	public void addOutputVariable(String title, ATSVVariableType type, Limit limit) {
		String value = ATSVVariableType.getDefaultFromType(type);
		VariableModel vm = new VariableModel(title, false, false, type, value);
		ecf.addVariable(vm);
		if (limit != null) {
			limits.put(title, limit);
		}
		startingOutputs.addVar(title, type, value);
	}

	/**
	 * Define a choicepoint.
	 *
	 * @param title The name of this choicepoint
	 * @param type The type of this choicepoint
	 * @param values The values this choicepoint can take
	 */
	public void addChoicePointDefinition(String title, ATSVVariableType type, ValuesModel values) {
		String value = values.getDefault();
		VariableModel vm = new VariableModel(title, false, true, type, value, values);
		ecf.addVariable(vm);
		startingInputs.addVar(title, type, values.getDefault());
	}

	/**
	 * Define a choicepoint.
	 *
	 * @param title The name of this choicepoint
	 * @param type The type of this choicepoint
	 * @param distribution The distribution of this choicepoint's values
	 */
	public void addChoicePointDefinition(String title, ATSVVariableType type, DistributionModel distribution) {
		String value = distribution.getDefault();
		VariableModel vm = new VariableModel(title, false, true, type, value, distribution);
		ecf.addVariable(vm);
		startingInputs.addVar(title, type, distribution.getDefault());
	}

	/**
	 * Renders the engine configuration to an ATSV-compatible .ecf file
	 * @return XML suitable for feeding into ATSV as an engine configuration
	 * @throws JAXBException
	 * @throws UnsatisfiableConstraint
	 * @throws ConfiguratorRepresentationException
	 * @throws UnsupportedFeatureException
	 */
	public String getXML() throws JAXBException, UnsatisfiableConstraint, ConfiguratorRepresentationException,
			UnsupportedFeatureException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		// The configurators have to be double-encoded, so we call that rendering here
		ecf.renderConfigurator();
		// And here we render the entire specification
		marshal.marshal(cfg, stream);
		return stream.toString();
	}

	/**
	 * Gets a mapping of variable names to their limits
	 * @return
	 */
	public Map<String, Limit> getLimits() {
		return limits;
	}

	/**
	 * Add a requirement that two variables must always have equal values
	 *
	 * @param varName1
	 * @param varName2
	 */
	public void addEqualityConstraint(String varName1, String varName2) {
		ecf.addConfigurator(new SimpleConfiguratorModel(varName1, varName2, true));
	}

	/**
	 * Add a requirement that two variables must never have equal values
	 *
	 * @param varName1
	 * @param varName2
	 */
	public void addUniquenessConstraint(String varName1, String varName2) {
		ecf.addConfigurator(new SimpleConfiguratorModel(varName1, varName2, false));
	}

	public void addRequiresConstraint(String varName1, String varVal1, String varName2, String varVal2) {
		ecf.addConfigurator(new ImpliesConfiguratorModel(varName1, varVal1, varName2, varVal2, true));
	}

	public void addForbidsConstraint(String varName1, String varVal1, String varName2, String varVal2) {
		ecf.addConfigurator(new ImpliesConfiguratorModel(varName1, varVal1, varName2, varVal2, false));
	}

	public void addMembershipConstraint(String varName1, String varVal1, String varName2, Collection<String> varVals2) {
		ecf.addConfigurator(new SetRestrictionConfiguratorModel(varName1, varVal1, varName2, varVals2, true));
	}

	public void addExclusionConstraint(String varName1, String varVal1, String varName2, Collection<String> varVals2) {
		ecf.addConfigurator(new SetRestrictionConfiguratorModel(varName1, varVal1, varName2, varVals2, false));
	}
}