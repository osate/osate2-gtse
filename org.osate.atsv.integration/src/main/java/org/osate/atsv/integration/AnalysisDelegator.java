package org.osate.atsv.integration;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.SafeRunner;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

public class AnalysisDelegator {

	private final String EXTENSION_POINT_ID = "org.osate.atsv.integration";
	private IExtensionRegistry registry = RegistryFactory.getRegistry();
	
	/*
	 * Code adapted from http://www.vogella.com/tutorials/EclipseExtensionPoint/article.html
	 */

	public void invoke(String extensionId, String packageName, String instanceName, String modeName) {
		IExtension ext = registry.getExtension(extensionId);
		if (!ext.isValid()) {
			// TODO: Handle this
		}
		if (ext.getExtensionPointUniqueIdentifier() != EXTENSION_POINT_ID) {
			// TODO: Handle this
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(Throwable e) {
				// TODO: Handle this
				System.out.println("Exception in analysis");
			}

			@Override
			public void run() throws Exception {
				SystemInstance instance = getSystemInstanceFromName(packageName, instanceName);
				((AbstractAnalysis) ext).runAnalysis(instance, getSystemModeFromName(instance, modeName));
			}
		};
		SafeRunner.run(runnable);
	}

	private SystemInstance getSystemInstanceFromName(String packageName, String systemInstanceName) {
		AadlPackage pkg = EMFIndexRetrieval.getPackageInWorkspace(packageName);
		SystemInstance sysInst = (SystemInstance) pkg.getPublicSection().getOwnedClassifiers().stream()
				.filter(sysImpl -> sysImpl.getName().equals(systemInstanceName)).findFirst().get();
		return sysInst;
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
