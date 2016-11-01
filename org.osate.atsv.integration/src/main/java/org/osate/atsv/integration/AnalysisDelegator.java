package org.osate.atsv.integration;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.SafeRunner;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

public class AnalysisDelegator {

	private final String EXTENSION_POINT_ID = "org.osate.atsv.integration";
	private IExtensionRegistry registry = RegistryFactory.getRegistry();

	/*
	 * Code adapted from http://www.vogella.com/tutorials/EclipseExtensionPoint/article.html
	 */

	public Map<String, String> invoke(String extensionId, String packageName, String implName, String modeName) {
		IExtension ext = registry.getExtension(extensionId);
		if (!ext.isValid()) {
			// TODO: Handle this
		}
		if (ext.getExtensionPointUniqueIdentifier() != EXTENSION_POINT_ID) {
			// TODO: Handle this
		}
		AnalysisRunner runnable = new AnalysisRunner(ext, packageName, implName, modeName);
		SafeRunner.run(runnable);
		return runnable.m;
	}

	private class AnalysisRunner implements ISafeRunnable {
		public Map<String, String> m;
		private IExtension ext;
		private String packageName;
		private String implName;
		private String modeName;

		public AnalysisRunner(IExtension ext, String packageName, String implName, String modeName) {
			this.ext = ext;
			this.packageName = packageName;
			this.implName = implName;
			this.modeName = modeName;
		}

		@Override
		public void handleException(Throwable e) {
			// TODO: Handle this
			System.out.println("Exception in analysis");
		}

		@Override
		public void run() throws Exception {
			SystemInstance instance = instantiateClassifier(packageName, implName);
			AbstractAnalysis analyzer = null; // TODO: Provide default implementation that gives a useful error if the analyzer can't be found
			for (IConfigurationElement cfgElem : ext.getConfigurationElements()) {
				if (cfgElem.getName().equals("Analysis")) {
					try {
						analyzer = (AbstractAnalysis) cfgElem.createExecutableExtension("AnalyzerClass");
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			m = analyzer.runAnalysis(instance, getSystemModeFromName(instance, modeName));
		}
	}

	private SystemInstance instantiateClassifier(String packageName, String implName) throws Exception {
		AadlPackage pkg = EMFIndexRetrieval.getPackageInWorkspace(packageName);

		ComponentImplementation impl = (ComponentImplementation) pkg.getPublicSection().getOwnedClassifiers().stream()
				.filter(sysImpl -> sysImpl.getName().equals(implName)).findFirst().get();

		return InstantiateModel.buildInstanceModelFile(impl);
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
