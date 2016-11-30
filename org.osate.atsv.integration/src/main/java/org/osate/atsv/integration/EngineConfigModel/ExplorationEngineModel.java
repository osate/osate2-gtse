package org.osate.atsv.integration.EngineConfigModel;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;

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
	private final String configurationFile = baseDir + "ATSVConfig.ecf";

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
	private final String sampleCount = Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_SAMPLE_COUNT);
	
	/**
	 * I'm not sure what this does, but it seems safe to set it to the same value as the sample count
	 */
	@XmlElement
	private final String updateATSVInterval = sampleCount;
	
	/**
	 * This is an XML string that can define "must be equal" or "must be unique" relationships between variables
	 */
	@XmlElement
	private final String configurator = "";
	
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
	public void addVariable(VariableModel vm){
		if(vm.isInput())
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
}
