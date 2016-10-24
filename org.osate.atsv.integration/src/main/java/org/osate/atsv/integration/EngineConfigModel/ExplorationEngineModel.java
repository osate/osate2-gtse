package org.osate.atsv.integration.EngineConfigModel;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;

import org.osate.atsv.integration.Activator;

public class ExplorationEngineModel {

	/**
	 * This is the directory we'll be dropping the files needed to run ATSV into
	 */
	private String baseDir;

	/**
	 * This is a mode setting ATSV to get its data from an executable (as opposed to a database, spreadsheet, etc)
	 */
	@XmlElement
	private final String analysisCode = "CommandLineProblem";

	/**
	 * This (despite its name) is a file path to the script that runs the analysis
	 */
	@XmlElement
	private final String configurationFile;

	/**
	 * A file path to the jar that handles parsing
	 */
	@XmlElement
	private final String parser;

	/**
	 * File path to the file that ATSV writes inputs to 
	 */
	@XmlElement
	private final String inputFile;

	/**
	 * File path to the file that ATSV writes outputs to
	 */
	@XmlElement
	private final String outputFile;

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
	 * 
	 */
	@XmlElement
	private final VariablesModel variables = new VariablesModel();
	
	public ExplorationEngineModel() {
		baseDir = Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY)
				+ File.separator;

		configurationFile = baseDir + "ATSVConfig.ecf";
		parser = baseDir + "parser.jar";
		inputFile = baseDir + "input.txt";
		outputFile = baseDir + "output.txt";
	}
}
