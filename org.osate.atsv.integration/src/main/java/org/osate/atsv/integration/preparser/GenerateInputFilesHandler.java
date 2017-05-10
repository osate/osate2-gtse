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
package org.osate.atsv.integration.preparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.SystemUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.osate.atsv.integration.Activator;
import org.osate.atsv.integration.EngineConfigGenerator;
import org.osate.atsv.integration.EngineConfigModel.DistributionModel;
import org.osate.atsv.integration.EngineConfigModel.UniformDistributionModel;
import org.osate.atsv.integration.EngineConfigModel.ValuesModel;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.exception.ConfiguratorRepresentationException;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;
import org.osgi.framework.Bundle;

public class GenerateInputFilesHandler extends AbstractHandler {

	/**
	 * Initial values for input.txt, which will be overwritten by ATSV
	 */
	private Map<String, String> startingInputs = null;

	/**
	 * Initial values for input.txt, which will be overwritten by ATSV
	 */
	private Map<String, String> startingOutputs = null;

	/**
	 * These are specified by the user, currently as a properties file but eventually as a custom
	 * choicepoint DSL
	 */
	private Properties userProps = null;

	/**
	 * These are values needed by OSATE that shouldn't be encoded as ATSV properties, eg:
	 * <ul>
	 *  <li>The package name</li>
	 *  <li>The component implementation to instantiate</li>
	 *  <li>Which analysis plugins to use</li>
	 *  <li>The type of each choicepoint (but not their values -- that comes from ATSV via input.txt)</li>
	 * </ul>  
	 */
	private Properties osateProps = null;

	/**
	 * The engine config spec that will get seralized to XML and then read in by ATSV
	 */
	private EngineConfigGenerator ecf;

	/**
	 * The directory all the generated files will go
	 */
	private String targetDirStr = Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY)
			+ File.separator;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		initializeFields();
		initializeProperties();
		parseProperties();
		generateEngineConfig();
		generateInputFile();
		generateOutputFile();
		generateRunScript();
		generateRequestProperties();
		copyJars();
		setPermissions();
		return null;
	}

	private void initializeFields() {
		startingInputs = new HashMap<>();
		startingOutputs = new HashMap<>();
		userProps = null;
		osateProps = new Properties();
		ecf = new EngineConfigGenerator();
	}

	private void setPermissions() {
		Set<PosixFilePermission> perms = new HashSet<>();
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE); // Required to allow overwriting on subsequent runs
		perms.add(PosixFilePermission.OWNER_EXECUTE);

		java.nio.file.Path parseJarPath = FileSystems.getDefault().getPath(targetDirStr + "parser.jar");
		java.nio.file.Path connectJarPath = FileSystems.getDefault().getPath(targetDirStr + "connector.jar");
		java.nio.file.Path runScriptPath = FileSystems.getDefault()
				.getPath(targetDirStr + (SystemUtils.IS_OS_WINDOWS ? "run.bat" : "run.sh"));

		try {
			java.nio.file.Files.setPosixFilePermissions(parseJarPath, perms);
			java.nio.file.Files.setPosixFilePermissions(connectJarPath, perms);
			java.nio.file.Files.setPosixFilePermissions(runScriptPath, perms);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateRequestProperties() {
		try (FileOutputStream out = new FileOutputStream(targetDirStr + "request.properties")) {
			osateProps.store(out,
					"NO USER MODIFIABLE CONTENTS\n"
							+ "#Auto-generated properties for the ATSV-OSATE connection (connector.jar)\n"
							+ "#These properties encode information needed by OSATE that ATSV can't use");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void copyJars() {
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		Path srcParsePath = new Path("src/main/resources/parser.jar");
		java.nio.file.Path dstParsePath = FileSystems.getDefault().getPath(targetDirStr + "parser.jar");
		Path srcConnectPath = new Path("src/main/resources/connector.jar");
		java.nio.file.Path dstConnectPath = FileSystems.getDefault().getPath(targetDirStr + "connector.jar");
		try (InputStream parseIS = FileLocator.openStream(bundle, srcParsePath, false);
				InputStream connectIS = FileLocator.openStream(bundle, srcConnectPath, false)) {
			if (java.nio.file.Files.exists(dstParsePath)) {
				java.nio.file.Files.delete(dstParsePath);
			}
			if (java.nio.file.Files.exists(dstConnectPath)) {
				java.nio.file.Files.delete(dstConnectPath);
			}
			java.nio.file.Files.copy(parseIS, dstParsePath);
			java.nio.file.Files.copy(connectIS, dstConnectPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addGeneratedChoicePoint(String title, ATSVVariableType type, String defaultVal, ValuesModel values) {
		ecf.addVariable(title, false, true, type, defaultVal, values);
		startingInputs.put(title, defaultVal);
	}

	private void addGeneratedChoicePoint(String title, ATSVVariableType type, String defaultVal,
			DistributionModel dist) {
		ecf.addVariable(title, false, true, type, defaultVal, dist);
		startingInputs.put(title, defaultVal);
	}

	private void addOutputVariables(String titles, ATSVVariableType type, String defaultVal) {
		for (String title : titles.split(",")) {
			ecf.addVariable(title, false, false, type, defaultVal);
			startingOutputs.put(title, defaultVal);
		}
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

		osateProps.setProperty("packageName", propFile.getName().substring(0,
				propFile.getName().length() - propFile.getFileExtension().length() - 1));

		// Assume choicepoint definitions are named the same as the package they specify
		userProps = new Properties();
		IPath propFilePath = propFile.getRawLocation();
		try {
			userProps.load(new FileInputStream(propFilePath.toFile()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return userProps;
	}

	private void generateOutputFile() {
		try (PrintWriter out = new PrintWriter(targetDirStr + "output.txt")) {
			for (Map.Entry<String, String> output : startingOutputs.entrySet()) {
				out.println(output.getKey() + "," + output.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void generateInputFile() {
		try (PrintWriter out = new PrintWriter(targetDirStr + "input.txt")) {
			for (Map.Entry<String, String> input : startingInputs.entrySet()) {
				out.println(input.getKey() + "," + input.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void generateEngineConfig() {
		try (PrintWriter out = new PrintWriter(targetDirStr + "ATSVConfig.ecf")) {
			out.println(ecf.getXML());
		} catch (JAXBException | FileNotFoundException | UnsatisfiableConstraint
				| ConfiguratorRepresentationException e) {
			e.printStackTrace();
		}
	}

	private void generateRunScript() {
		try (PrintWriter out = new PrintWriter(targetDirStr + (SystemUtils.IS_OS_WINDOWS ? "run.bat" : "run.sh"))) {
			if (!SystemUtils.IS_OS_WINDOWS) {
				out.println("#!/bin/sh");
			}
			out.println("java -classpath . -jar connector.jar localhost "
					+ Activator.getDefault().getPreferenceStore().getInt(Activator.ATSV_INTEGRATION_PORT));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void parseProperties() {
		for (String propName : userProps.stringPropertyNames()) {
			String[] propNames = propName.split("-");
			if (propNames[0].equalsIgnoreCase("Analyses")) {
				osateProps.setProperty("pluginIds", userProps.getProperty(propName));
			} else if (propNames[0].equalsIgnoreCase("SubcompChoice")) {
				String[] options = userProps.getProperty(propName).split(",");
				addGeneratedChoicePoint(propNames[1], ATSVVariableType.STRING, options[0], new ValuesModel(options));
				// Pass the choicepoint definition through to the properties used to build the request...
				osateProps.setProperty(propName, "(Key value is unused for subcomponent values)");
			} else if (propNames[0].equalsIgnoreCase("PropertyValue")) {
				handlePropValSpec(propName);
			} else if (propNames[0].equalsIgnoreCase("Output")) {
				ATSVVariableType type = ATSVVariableType.getTypeByName(propNames[1]);
				addOutputVariables(userProps.getProperty(propName), type, ATSVVariableType.getDefaultFromType(type));
			} else if (propNames[0].equalsIgnoreCase("componentImplementationName")) {
				// Just pass this straight through as-is
				osateProps.setProperty(propName, userProps.getProperty(propName));
			}
		}
	}

	private void handlePropValSpec(String propName) {
		String[] propNames = propName.split("-");
		String[] range = userProps.getProperty(propName).split(",");
		ATSVVariableType type = ATSVVariableType.getTypeByName(propNames[3]);
		if (type == ATSVVariableType.FLOAT) {
			addGeneratedChoicePoint(propNames[1] + "-" + propNames[2], type, ATSVVariableType.getDefaultFromType(type),
					new UniformDistributionModel(Float.valueOf(range[0]), Float.valueOf(range[1])));
		} else if (type == ATSVVariableType.INTEGER) {
			addGeneratedChoicePoint(propNames[1] + "-" + propNames[2], type, ATSVVariableType.getDefaultFromType(type),
					new UniformDistributionModel(Integer.valueOf(range[0]), Integer.valueOf(range[1])));
		} else if (type == ATSVVariableType.STRING) {
			addGeneratedChoicePoint(propNames[1] + "-" + propNames[2], type, ATSVVariableType.getDefaultFromType(type),
					new ValuesModel(range));
		} else if (type == null && propNames[3].equals("reference")) {
			addGeneratedChoicePoint(propNames[1] + "-" + propNames[2], ATSVVariableType.STRING,
					ATSVVariableType.getDefaultFromType(ATSVVariableType.STRING), new ValuesModel(range));
		}
		osateProps.setProperty(propName, "(Key value is unused for property values)");
	}
}