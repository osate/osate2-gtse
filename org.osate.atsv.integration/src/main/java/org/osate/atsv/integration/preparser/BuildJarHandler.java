package org.osate.atsv.integration.preparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.SystemUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.integration.EngineConfigGenerator;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;

public class BuildJarHandler extends AbstractHandler {

	private Map<String, String> startingInputs = new HashMap<>();
	private Map<String, String> startingOutputs = new HashMap<>();
	private Properties prop = null;
	private EngineConfigGenerator ecf = new EngineConfigGenerator();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		initializeProperties();
		parseProperties();
		generateEngineConfig();
		generateInputFile();
		generateOutputFile();
		generateRunScript();
		// generateParserJar();
		// generateIntegrationJar();
		return null;
	}

	private void addGeneratedChoicePoint(String title, ATSVVariableType type, String defaultVal, ValuesModel values) {
		ecf.addVariable(title, false, true, type, defaultVal, values);
		startingInputs.put(title, defaultVal);
	}

	private void addOutputVariable(String title, ATSVVariableType type, String defaultVal) {
		ecf.addVariable(title, false, false, type, defaultVal);
		startingOutputs.put(title, defaultVal);
	}

	private Properties initializeProperties() {
		// See / from: http://stackoverflow.com/a/6895220
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IFile propFile = null;
		if (window != null) {
			IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
			Object firstElement = selection.getFirstElement();
			if (firstElement instanceof IAdaptable) {
				propFile = ((IFile) firstElement).getAdapter(IFile.class);
			}
		}

		if (propFile == null) {
			return null;
		}

		// Assume choicepoint definitions are named the same as the package they specify
		prop = new Properties();
		IPath propFilePath = propFile.getRawLocation();
		try {
			prop.load(new FileInputStream(propFilePath.toFile()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return prop;
	}

	private void generateOutputFile() {
		try (PrintWriter out = new PrintWriter(
				Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY) + File.separator
						+ "output.txt")) {
			for (Map.Entry<String, String> output : startingOutputs.entrySet()) {
				out.println(output.getKey() + "," + output.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void generateInputFile() {
		try (PrintWriter out = new PrintWriter(
				Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY) + File.separator
						+ "input.txt")) {
			for (Map.Entry<String, String> input : startingInputs.entrySet()) {
				out.println(input.getKey() + "," + input.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void generateEngineConfig() {
		try (PrintWriter out = new PrintWriter(
				Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY) + File.separator
						+ "ATSVConfig.ecf")) {
			out.println(ecf.getXML());
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void generateRunScript() {
		try (PrintWriter out = new PrintWriter(
				Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY) + File.separator
						+ (SystemUtils.IS_OS_WINDOWS ? "run.bat" : "run.sh"))) {
			if (!SystemUtils.IS_OS_WINDOWS) {
				out.println("#!/bin/sh");
			}
			out.println("java -classpath . ATSVIntegrationJar");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void parseProperties() {
		for (String propName : prop.stringPropertyNames()) {
			String[] propNames = propName.split("-");
			if (propNames[0].equalsIgnoreCase("Analyses")) {
				// TODO: Handle analysis specification
			} else if (propNames[0].equalsIgnoreCase("SubcompChoice")) {
				String[] options = prop.getProperty(propName).split(",");
				addGeneratedChoicePoint(propNames[1] + "-" + propNames[2], ATSVVariableType.STRING, options[0],
						new ValuesModel(options));
			} else if (propNames[0].equalsIgnoreCase("Output")) {
				ATSVVariableType type = getTypeFromString(prop.getProperty(propName));
				addOutputVariable(propNames[1], type, getDefaultFromType(type));
			}
		}
	}

	private String getDefaultFromType(ATSVVariableType type) {
		if (type == ATSVVariableType.STRING) {
			return "UNSET_STRING";
		} else if (type == ATSVVariableType.FLOAT) {
			return String.valueOf((float) 0);
		} else if (type == ATSVVariableType.INTEGER) {
			return String.valueOf(0);
		}
		return null;
	}

	private ATSVVariableType getTypeFromString(String property) {
		if (property.equalsIgnoreCase("float")) {
			return ATSVVariableType.FLOAT;
		} else if (property.equalsIgnoreCase("int")) {
			return ATSVVariableType.INTEGER;
		} else if (property.equalsIgnoreCase("string")) {
			return ATSVVariableType.STRING;
		}
		return null;
	}

}
