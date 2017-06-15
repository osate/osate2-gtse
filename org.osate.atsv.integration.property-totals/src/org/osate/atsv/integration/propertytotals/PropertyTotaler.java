package org.osate.atsv.integration.propertytotals;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.analysis.architecture.PropertyTotals;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.network.Response;
import org.osate.ui.actions.AaxlReadOnlyActionAsJob;
import org.osate.ui.actions.AbstractAaxlAction;

public class PropertyTotaler extends AbstractAnalysis {

	private AbstractAaxlAction aaa = new MyDoPropertyTotals();

	@Override
	public void runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			IProgressMonitor progressMonitor, Response resp) {
		PropertyTotals pt = new PropertyTotals(progressMonitor, aaa);
		resp.addVariable("Weight", String.valueOf(pt.getWeight(instance)));
		resp.addVariable("Price", String.valueOf(pt.getPrice(instance)));
	}

	private class MyDoPropertyTotals extends AaxlReadOnlyActionAsJob {

		@Override
		protected String getActionName() {
			return "ATSV Property Totaler";
		}

		public MyDoPropertyTotals() {
			errManager = AnalysisErrorReporterManager.NULL_ERROR_MANANGER;
		}

		@Override
		protected void doAaxlAction(IProgressMonitor monitor, Element root) {
			// Nothing to do here, this plugin doesn't use this method
		}

		@Override
		protected boolean suppressErrorMessages() {
			return true;
		}

	}

}
