package org.osate.atsv.integration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.SafeRunner;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.atsv.integration.exception.AnalysisPluginException;
import org.osate.atsv.integration.network.ChoicePointSpecification;
import org.osate.atsv.integration.network.Request;
import org.osate.atsv.integration.network.Response;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

public class AnalysisDelegator {

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

	private class AnalysisRunner implements ISafeRunnable {
		private Response response;
		private String packageName;
		private String implName;
		private String modeName;
		private Set<ChoicePointSpecification> choices;
		private SystemInstance instance;
		private Set<IExtension> exts;

		public AnalysisRunner(Request req) throws AnalysisPluginException {
			this.exts = resolveExtensions(req.getPluginIds());
			this.packageName = req.getPackageName();
			this.implName = req.getComponentImplementationName();
			this.modeName = req.getOperationModeName();
			this.choices = req.getChoicepoints();
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
			instance = instantiateClassifier(packageName, implName, choices);
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
		}

		public Response getResponse() {
			return response;
		}
	}

	private SystemInstance instantiateClassifier(String packageName, String implName,
			Set<ChoicePointSpecification> choices) throws Exception {
		AadlPackage pkg = EMFIndexRetrieval.getPackageInWorkspace(packageName);

		ComponentImplementation impl = (ComponentImplementation) pkg.getPublicSection().getOwnedClassifiers().stream()
				.filter(sysImpl -> sysImpl.getName().equals(implName)).findFirst().get();

		return CustomInstantiator.myBuildInstanceModelFile(impl, choices);
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
}
