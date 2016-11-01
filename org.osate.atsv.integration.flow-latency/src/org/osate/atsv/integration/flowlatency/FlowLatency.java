package org.osate.atsv.integration.flowlatency;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.analysis.flows.actions.CheckFlowLatency;
import org.osate.analysis.flows.model.LatencyReport;
import org.osate.atsv.integration.AbstractAnalysis;

public class FlowLatency extends AbstractAnalysis{

	@Override
	public Map<String, String> runAnalysis(SystemInstance instance, SystemOperationMode som,
			AnalysisErrorReporterManager errMgr, IProgressMonitor progressMonitor) {
		CheckFlowLatency checker = new CheckFlowLatency();
		LatencyReport report = checker.invokeAndGetReport(progressMonitor, errMgr, instance, som);
		Map<String, String> ret = new HashMap<>();
		ret.put("Name", report.getName());
		return ret;
	}
}
