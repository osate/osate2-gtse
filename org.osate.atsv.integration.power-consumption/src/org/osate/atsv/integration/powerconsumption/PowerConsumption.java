package org.osate.atsv.integration.powerconsumption;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.analysis.flows.reporting.model.Line;
import org.osate.analysis.flows.reporting.model.Report;
import org.osate.analysis.flows.reporting.model.ReportedCell;
import org.osate.analysis.flows.reporting.model.Section;
import org.osate.analysis.resource.budgets.actions.DoPowerAnalysis;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.network.Response;

public class PowerConsumption extends AbstractAnalysis {

	@Override
	public Response runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			IProgressMonitor progressMonitor) {
		DoPowerAnalysis checker = new DoPowerAnalysis();
		Response ret = new Response();
		Report report = checker.invokeAndGetReport(progressMonitor, instance);
		populateVariables(report, ret);
		return ret;
	}

	private void populateVariables(Report report, Response ret) {
		for (Section section : report.getSections()) {
			for (Line line : section.getLines()) {
				for (ReportedCell cell : line.getContent()) {
					cell.getMessage();// do things
				}
			}
		}
	}
}
