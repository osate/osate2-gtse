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

import static java.util.function.Function.identity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.util.Aadl2Util;
import org.osate.atsv.integration.ChoicePointModel.ChoicePointSpecification;
import org.osate.atsv.integration.annotation.StringConfiguratorHack;
import org.osate.atsv.integration.exception.AnalysisPluginException;
import org.osate.atsv.integration.instantiator.CustomInstantiator;
import org.osate.atsv.integration.network.Limit;
import org.osate.atsv.integration.network.Request;
import org.osate.atsv.integration.network.Response;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class AnalysisDelegator {

	@Inject
	private ResourceDescriptionsProvider rdp;

	private final String EXTENSION_POINT_ID = "org.osate.atsv.integration";

	public Response invoke(Request req) {
		AnalysisRunner runnable = null;
		try {
			runnable = new AnalysisRunner(req);
		} catch (AnalysisPluginException ape) {
			Response exceptionResponse = new Response();
			exceptionResponse.setException(ape);
			return exceptionResponse;
		}
		SafeRunner.run(runnable);
		return runnable.getResponse();
	}

	public AnalysisDelegator() {
		Injector injector = IResourceServiceProvider.Registry.INSTANCE
				.getResourceServiceProvider(URI.createFileURI("dummy.aadl")).get(Injector.class);
		injector.injectMembers(this);
	}

	private class AnalysisRunner implements ISafeRunnable {
		private Response response;
		private String packageName;
		private String implName;
		private String modeName;
		private Set<ChoicePointSpecification> choicepoints;
		private Map<String, Limit> limits;
		private SystemInstance instance;
		private Set<IExtension> exts;

		public AnalysisRunner(Request req) throws AnalysisPluginException {
			this.exts = resolveExtensions(req.getPluginIds());
			this.packageName = req.getPackageName();
			this.implName = req.getComponentImplementationName();
			this.modeName = req.getOperationModeName();
			this.choicepoints = req.getChoicepoints();
			unCacheChoicepoints(req.getConfiguratorCache());
			this.limits = req.getLimits();
		}

		/**
		 * This undoes the conversion of string values to floats required by the {@link StringConfiguratorHack}.
		 *
		 * @param configuratorCache The cache, which was loaded from the request.properties file
		 */
		@StringConfiguratorHack
		private void unCacheChoicepoints(Map<String, Map<Float, String>> configuratorCache) {
			for (ChoicePointSpecification cps : choicepoints) {
				if (configuratorCache.containsKey(cps.getComponentPath())) {
					Map<Float, String> cache = configuratorCache.get(cps.getComponentPath());
					float cacheKey = Float.valueOf(cps.getValueAsString());
					cps.setValue(cache.get(cacheKey));
				}
			}
		}

		private Set<IExtension> resolveExtensions(Collection<String> pluginIds) throws AnalysisPluginException {
			exts = new HashSet<>();
			for (String pluginId : pluginIds) {
				pluginId = pluginId.trim();
				IExtension ext = RegistryFactory.getRegistry().getExtension(pluginId);
				if (ext == null) {
					throw new AnalysisPluginException("No extension with id '" + pluginId + "' found!");
				}
				if (!ext.isValid()) {
					throw new AnalysisPluginException("The extension with id '" + pluginId + "' isn't valid!");
				}
				if (!ext.getExtensionPointUniqueIdentifier().equals(EXTENSION_POINT_ID)) {
					throw new AnalysisPluginException("The extension with id '" + pluginId + "' extends "
							+ ext.getExtensionPointUniqueIdentifier() + " when it should extend " + EXTENSION_POINT_ID
							+ "!");
				}
				exts.add(ext);
			}
			return exts;
		}

		@Override
		public void handleException(Throwable e) {
			if (e instanceof Exception) {
				response = new Response();
				response.setException((Exception) e);
			} else {
				System.err.println("Java error in analysis!");
			}
		}

		@Override
		public void run() throws Exception {
			Map<String, ChoicePointSpecification> choicepointMap = mappifyChoicePoints();
			instance = instantiateClassifier(packageName, implName, choicepointMap);
			response = new Response();
			AbstractAnalysis analyzer = null; // TODO: Provide default implementation that gives a useful error if the analyzer can't be found
			for (IExtension ext : exts) {
				for (IConfigurationElement cfgElem : ext.getConfigurationElements()) {
					if (cfgElem.getName().equals("Analysis")) {
						analyzer = (AbstractAnalysis) cfgElem.createExecutableExtension("AnalyzerClass");
						analyzer.runAnalysis(instance, getSystemModeFromName(instance, modeName), response);
					}
				}
			}
			for (String limitVar : limits.keySet()) {
				if (response.getVariables().getVars().containsKey(limitVar)) {
					String reason = limits.get(limitVar).checkLimit(limitVar,
							Float.valueOf(response.getVariables().getVars().get(limitVar).getVal()));
					if (reason.length() > 0) {
						response.markInvalid("Limit Violation -- " + reason);
						break;
					}
				}
			}
		}

		private Map<String, ChoicePointSpecification> mappifyChoicePoints() {
			// This method got hacked a little bit because name conflicts would mean that
			// you can't have two choicepoints with the same path, even if one is a property.
			// We don't actually use key values for non-property-choicepoints, though, so
			// this method keeps the paths for non-properties, and gives properties random keys.
			Map<String, ChoicePointSpecification> ret = new HashMap<>();
			Map<String, ChoicePointSpecification> noProps = Collections.unmodifiableMap(choicepoints.stream()
					.filter(c -> !c.isProperty())
					.collect(Collectors.toMap(ChoicePointSpecification::getComponentPath, identity())));
			Map<String, ChoicePointSpecification> props = Collections.unmodifiableMap(choicepoints.stream()
					.filter(c -> c.isProperty())
					.collect(Collectors.toMap(c -> java.util.UUID.randomUUID().toString(), identity())));
			ret.putAll(noProps);
			ret.putAll(props);
			return ret;
		}

		public Response getResponse() {
			return response;
		}
	}

	private SystemInstance instantiateClassifier(String packageName, String implName,
			Map<String, ChoicePointSpecification> choicepoints) throws Exception {

		AadlPackage pkg = getPackageInWorkspace(packageName, OsateResourceUtil.createResourceSet());

		ComponentImplementation impl = (ComponentImplementation) pkg.getPublicSection().getOwnedClassifiers().stream()
				.filter(sysImpl -> sysImpl.getName().equals(implName)).findFirst().get();

		return CustomInstantiator.myBuildInstanceModelFile(impl, choicepoints);
	}

	private SystemOperationMode getSystemModeFromName(SystemInstance instance, String modeName) {
		if (modeName == null) {
			if (instance.getCurrentSystemOperationMode() == null) {
				return instance.getInitialSystemOperationMode();
			} else {
				return instance.getCurrentSystemOperationMode();
			}
		} else {
			SystemOperationMode som = instance.getSystemOperationModes().stream()
					.filter(mode -> mode.getName().equals(modeName)).findFirst().get();
			return som;
		}
	}

	/**
	* get package in workspace by looking it up in EMF index
	* @param pname String package name
	* @param resoruceSet the resource in which the models are expected
	* @return AADL package
	*/
	private AadlPackage getPackageInWorkspace(String pname, ResourceSet resourceSet) {
		IResourceDescriptions rds = rdp.getResourceDescriptions(resourceSet);
		Iterable<IEObjectDescription> packagedlist = rds
				.getExportedObjectsByType(Aadl2Package.eINSTANCE.getAadlPackage());
		for (IEObjectDescription eod : packagedlist) {
			if (eod.getName().toString("::").equalsIgnoreCase(pname)) {
				EObject res = eod.getEObjectOrProxy();
				res = EcoreUtil.resolve(res, resourceSet);
				if (!Aadl2Util.isNull(res)) {
					return (AadlPackage) res;
				}
			}
		}
		return null;
	}
}
