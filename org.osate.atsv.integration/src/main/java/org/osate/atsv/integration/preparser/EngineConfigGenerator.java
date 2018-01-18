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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.SystemUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osate.atsv.integration.Activator;
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
import org.osgi.framework.Bundle;

/**
 * This is the main API for specifying ATSV engine configurations by adding variables and constraints.
 * @author sam
 *
 */
public final class EngineConfigGenerator {

	/**
	 * These are values needed by OSATE that shouldn't be encoded as ATSV properties, eg:
	 * <ul>
	 *  <li>The package name</li>
	 *  <li>The component implementation to instantiate</li>
	 *  <li>Which analysis plugins to use</li>
	 *  <li>The type of each choicepoint (but not their values -- that comes from ATSV via input.txt)</li>
	 *  <li>The names of expected variables if they have limits specified </li>
	 * </ul>
	 */
	private Properties osateProps = null;

	/**
	 * The directory all the generated files will go
	 */
	private String targetDirStr = Activator.getDefault().getPreferenceStore().getString(Activator.ATSV_FILES_DIRECTORY)
			+ File.separator;

	private Marshaller marshal;
	private JAXBElement<ExplorationEngineModel> cfg;
	private ExplorationEngineModel eem;
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

			eem = new ExplorationEngineModel();
			cfg = new JAXBElement<ExplorationEngineModel>(new QName("ExplorationEngineModel"),
					ExplorationEngineModel.class, eem);
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

//	/**
//	 * Add an input variable to the engine configuration.
//	 *
//	 * @param title The name of this variable
//	 * @param sampled Whether or not this variable is sampled
//	 * @param type The type of this variable
//	 */
//	public void addInputVariable(String title, boolean sampled, ATSVVariableType type) {
//		String value = ATSVVariableType.getDefaultFromType(type);
//		VariableModel vm = new VariableModel(title, sampled, true, type, value);
//		eem.addVariable(vm);
//	}

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
		eem.addVariable(vm);
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
		eem.addVariable(vm);
		eem.addTypeRestriction(title, values);
		startingInputs.addVar(title, type, values.getDefault());
		addChoicepointToOsateProps(title, type);
	}

	private void addChoicepointToOsateProps(String title, ATSVVariableType type) {
		String propName = null;
		if(type == ATSVVariableType.STRING) {
			propName = "SubcompChoice-" + title;
		} else if (type == ATSVVariableType.FLOAT || type == ATSVVariableType.INTEGER) {
			propName = "LitPropertyValue-" + title;
		} else if (type == ATSVVariableType.REFERENCE) {
			propName = "RefPropertyValue-" + title;
		} else if (type == ATSVVariableType.LIST) {
			//ListRefPropertyValue
			//ListLitPropertyValue
			try {
				throw new UnsupportedFeatureException("List properties aren't implemented yet");
			} catch (UnsupportedFeatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		osateProps.setProperty(propName, "(Key value is unused)");
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
		eem.addVariable(vm);
		startingInputs.addVar(title, type, distribution.getDefault());
		addChoicepointToOsateProps(title, type);
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
		eem.renderConfigurator();
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
		eem.addConfigurator(new SimpleConfiguratorModel(varName1, varName2, true));
	}

	/**
	 * Add a requirement that two variables must never have equal values
	 *
	 * @param varName1
	 * @param varName2
	 */
	public void addUniquenessConstraint(String varName1, String varName2) {
		eem.addConfigurator(new SimpleConfiguratorModel(varName1, varName2, false));
	}

	public void addRequiresConstraint(String varName1, String varVal1, String varName2, String varVal2) {
		eem.addConfigurator(new ImpliesConfiguratorModel(varName1, varVal1, varName2, varVal2, true));
	}

	public void addForbidsConstraint(String varName1, String varVal1, String varName2, String varVal2) {
		eem.addConfigurator(new ImpliesConfiguratorModel(varName1, varVal1, varName2, varVal2, false));
	}

	public void addMembershipConstraint(String varName1, String varVal1, String varName2, Collection<String> varVals2) {
		eem.addConfigurator(new SetRestrictionConfiguratorModel(varName1, varVal1, varName2, varVals2, true));
	}

	public void addExclusionConstraint(String varName1, String varVal1, String varName2, Collection<String> varVals2) {
		eem.addConfigurator(new SetRestrictionConfiguratorModel(varName1, varVal1, varName2, varVals2, false));
	}

	public void execute() {
		initializeDirectory();
		generateEngineConfig();
		generateInputFile();
		generateOutputFile();
		generateLimits();
		generateRunScripts();
		generateRequestProperties();
		copyJars();
		setPermissions();
	}

	private void initializeDirectory() {
		new File(targetDirStr).mkdirs();
	}

	public void initializeFields() {
		osateProps = new Properties();
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

	/**
	 * Sets the package and component implementation (ie, root of the instantiation)
	 *
	 * @param qualifiedName The package-qualified name of the root component
	 */
	public void setPackageAndComponentName(String qualifiedName) {
		int lastSep = qualifiedName.lastIndexOf("::");
		String packageName = qualifiedName.substring(0, lastSep);
		String compImplName = qualifiedName.substring(lastSep + 2);
		osateProps.setProperty("packageName", packageName);
		osateProps.setProperty("componentImplementationName", compImplName);
	}

	private void generateLimits() {
		Map<String, Limit> limits = getLimits();
		for (String varName : limits.keySet()) {
			Limit limit = limits.get(varName);
			osateProps.setProperty("Limit-" + varName, limit.getOpStr() + "-" + limit.getLimitStr());
		}
	}

	private void generateOutputFile() {
		try {
			getStartingOutputs().writeToFile(targetDirStr + "output.xml");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private void generateInputFile() {
		try {
			getStartingInputs().writeToFile(targetDirStr + "input.xml");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private void generateEngineConfig() {
		try (PrintWriter out = new PrintWriter(targetDirStr + "ATSVConfig.ecf")) {
			out.println(getXML());
		} catch (JAXBException | FileNotFoundException | UnsatisfiableConstraint | ConfiguratorRepresentationException
				| UnsupportedFeatureException e) {
			e.printStackTrace();
		}
	}

	private void generateRunScripts() {
		try (PrintWriter runGTSEConnector = new PrintWriter(
				targetDirStr + (SystemUtils.IS_OS_WINDOWS ? "run.bat" : "run.sh"))) {
			if (!SystemUtils.IS_OS_WINDOWS) {
				runGTSEConnector.println("#!/bin/sh");
				runGTSEConnector.println("java -classpath . -jar connector.jar "
						+ Activator.getDefault().getPreferenceStore().getInt(Activator.ATSV_INTEGRATION_PORT));
			} else {
				/*
				 * Running ATSV on windows enables 3D plots, but also requires extra care:
				 * The included version of Java -- which has the following version info -- must be used
				 *
				 * java version "1.7.0"
				 * Java(TM) SE Runtime Environment (build 1.7.0-b147)
				 * Java HotSpot(TM) Client VM (build 21.0-b17, mixed mode)
				 *
				 * So, since a lot of GTSE code requires 1.8 for lambdas and whatnot, we actually have to run
				 * our two JVMs with different major versions :\
				 *
				 * This requires modifying runATSV.bat to cache the user's path (we also remove memory limits and
				 * the loading of the car test data that's included with ATSV)
				 *
				 */
				PrintWriter runATSVJar = new PrintWriter(targetDirStr + "runATSV.bat");
				runATSVJar.println("set ORIG_PATH=%PATH%");
				runATSVJar.println("set PATH=jre/bin;jnilib");
				runATSVJar.println("java -Dsun.java2d.ddoffscreen=false -Dsun.java2d.gdiblit=false -jar dist/atsv.jar");
				runATSVJar.close();

				/*
				 * Then in the connector-launching batch file we use the original path to get Java 8+
				 */
				runGTSEConnector.println("set PATH=%ORIG_PATH%");
				runGTSEConnector.println("java -classpath . -jar connector.jar 4444");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addAnalyses(String analysisIDs) {
		osateProps.setProperty("pluginIds", analysisIDs);
	}
}