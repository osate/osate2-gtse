package org.osate.atsv.integration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.atsv.integration.network.Response;

public abstract class AbstractAnalysis {

	private final IProgressMonitor NULL_PROGRESS_MONITOR = new NullProgressMonitor();

	abstract public void runAnalysis(SystemInstance instance, SystemOperationMode som,
			AnalysisErrorReporterManager errMgr, IProgressMonitor progressMonitor, Response resp);

	final public void runAnalysis(SystemInstance instance, SystemOperationMode som, Response resp) {
		runAnalysis(instance, som, AnalysisErrorReporterManager.NULL_ERROR_MANANGER, NULL_PROGRESS_MONITOR, resp);
	}

	final public void runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			Response resp) {
		runAnalysis(instance, som, errMgr, NULL_PROGRESS_MONITOR, resp);
	}

	final public void runAnalysis(SystemInstance instance, SystemOperationMode som, IProgressMonitor progressMonitor,
			Response resp) {
		runAnalysis(instance, som, AnalysisErrorReporterManager.NULL_ERROR_MANANGER, progressMonitor, resp);
	}
}
