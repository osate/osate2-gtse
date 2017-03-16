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
package org.osate.atsv.integration.EngineConfigModel;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.SystemUtils;
import org.osate.atsv.integration.Activator;

public class ExplorationEngineModel {

	/**
	 * This is the directory we'll be dropping the files needed to run ATSV into
	 */
	private final String baseDir = Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY)
			+ File.separator;

	/**
	 * This is a mode setting ATSV to get its data from an executable (as opposed to a database, spreadsheet, etc)
	 */
	@XmlElement
	private final String analysisCode = "CommandLineProblem";

	/**
	 * This (despite its name) is a file path to the script that runs the analysis
	 */
	@XmlElement
	private final String configurationFile = baseDir + (SystemUtils.IS_OS_WINDOWS ? "run.bat" : "run.sh");

	/**
	 * A file path to the jar that handles parsing
	 */
	@XmlElement
	private final String parser = baseDir + "parser.jar";

	/**
	 * File path to the file that ATSV writes inputs to 
	 */
	@XmlElement
	private final String inputFile = baseDir + "input.txt";

	/**
	 * File path to the file that ATSV writes outputs to
	 */
	@XmlElement
	private final String outputFile = baseDir + "output.txt";

	/**
	 * This isn't used, but if the element isn't in the file, ATSV can't parse it
	 */
	@XmlElement
	private final String runCodeParameter = "";

	/**
	 * This isn't used, but if the element isn't in the file, ATSV can't parse it
	 */
	@XmlElement
	private final String analysisFile = "";

	/**
	 * The list of input variable names
	 */
	@XmlElement
	private final InputTokensModel inputTokens = new InputTokensModel();

	/**
	 * Output tokens are only used for excel analyses, so this is unused for our purposes.
	 */
	@XmlElement
	private final String outputTokens = "";

	/**
	 * The variables used in the engine model
	 */
	@XmlElement
	private final VariablesModel variables = new VariablesModel();

	/**
	 * This isn't used, but if the element isn't in the file, ATSV can't parse it
	 */
	@XmlElement
	private final String excelMacro = "";

	/**
	 * The number of times to run the analysis
	 */
	@XmlElement
	private final String sampleCount = Activator.getDefault().getPreferenceStore()
			.getString(Activator.ATSV_SAMPLE_COUNT);

	/**
	 * I'm not sure what this does, but it seems safe to set it to the same value as the sample count
	 */
	@XmlElement
	private final String updateATSVInterval = sampleCount;

	/**
	 * This is an XML string that can define "must be equal" or "must be unique" relationships between variables
	 * 
	 * That is, it is an XML string (which will be re-xml encoded when the full model is serialized)
	 * Its value is set by the {@link #renderConfigurator() renderConfigurator} method, which has to be called
	 * after all the configurators have been added (typically right before the model is generated.
	 */
	@XmlElement
	private String configurator = null;
	private ConfiguratorsModel cm = new ConfiguratorsModel();

	/**
	 * Any additional entries for the PATH environment variable can be 
	 */
	@XmlElement
	private final String userDefinedPath = "";

	/**
	 * Add a variable to the internal list of variables.
	 * 
	 * This is not designed to be called by clients, @see org.osate.atsv.integration.EngineConfigGenerator#addVariable()
	 * @param vm The variable model to add
	 */
	public void addVariable(VariableModel vm) {
		if (vm.isInput())
			inputTokens.addTokenModel(new InputTokenModel(vm.getTitle()));
		variables.addVariable(vm);
	}

	/**
	 * Empty the list of variables
	 */
	public void clearTokensAndVariables() {
		inputTokens.clear();
		variables.clear();
	}

	public void addConfigurator(ConfiguratorModel configuratorModel) {
		cm.addConfigurator(configuratorModel);
	}

	public void renderConfigurator() throws JAXBException {
		if (cm.isEmpty()) {
			configurator = "";
			return;
		}
		JAXBContext context = JAXBContext.newInstance(ConfiguratorsModel.class, ConfiguratorModel.class);
		ConfiguratorModelAdapter configuratorAdapter = new ConfiguratorModelAdapter();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Marshaller marshal = context.createMarshaller();
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
		marshal.setProperty(Marshaller.JAXB_FRAGMENT, true);
		marshal.setAdapter(configuratorAdapter);
		JAXBElement<ConfiguratorsModel> configuratorXML = new JAXBElement<ConfiguratorsModel>(new QName("Configurator"),
				ConfiguratorsModel.class, cm);
		marshal.marshal(configuratorXML, stream);
		configurator = stream.toString();
	}
}
