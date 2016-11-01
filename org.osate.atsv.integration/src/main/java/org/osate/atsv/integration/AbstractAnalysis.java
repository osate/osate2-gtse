package org.osate.atsv.integration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.atsv.integration.network.Response;

public abstract class AbstractAnalysis {

	private final IProgressMonitor NULL_PROGRESS_MONITOR = new NullProgressMonitor();

	abstract public Response runAnalysis(SystemInstance instance, SystemOperationMode som,
			AnalysisErrorReporterManager errMgr, IProgressMonitor progressMonitor);

	final public Response runAnalysis(SystemInstance instance, SystemOperationMode som) {
		return runAnalysis(instance, som, AnalysisErrorReporterManager.NULL_ERROR_MANANGER, NULL_PROGRESS_MONITOR);
	}

	final public Response runAnalysis(SystemInstance instance, SystemOperationMode som,
			AnalysisErrorReporterManager errMgr) {
		return runAnalysis(instance, som, errMgr, NULL_PROGRESS_MONITOR);
	}

	final public Response runAnalysis(SystemInstance instance, SystemOperationMode som,
			IProgressMonitor progressMonitor) {
		return runAnalysis(instance, som, AnalysisErrorReporterManager.NULL_ERROR_MANANGER, progressMonitor);
	}
}
