package org.osate.atsv.integration.flowlatency;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.analysis.flows.actions.CheckFlowLatency;
import org.osate.analysis.flows.model.LatencyReport;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.network.Response;

public class FlowLatency extends AbstractAnalysis{

	@Override
	public Response runAnalysis(SystemInstance instance, SystemOperationMode som,
			AnalysisErrorReporterManager errMgr, IProgressMonitor progressMonitor) {
		CheckFlowLatency checker = new CheckFlowLatency();
		Response ret = new Response();
		LatencyReport report = checker.invokeAndGetReport(progressMonitor, errMgr, instance, som);
		ret.addVariable("Report Name", report.getName());		
		return ret;
	}
}
