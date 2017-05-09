package org.osate.atsv.integration.unhandledfaults;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.Element;
import org.osate.aadl2.errormodel.analysis.actions.UnhandledFaultsAction;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.network.Response;

public class UnhandledFaults extends AbstractAnalysis {

	private MyUnhandledFaultsAction ufa;
	private Response resp;

	public UnhandledFaults() {
		ufa = new MyUnhandledFaultsAction();
	}

	@Override
	public void runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			IProgressMonitor progressMonitor, Response resp) {
		this.resp = resp;
		ufa.doAaxlAction(progressMonitor, instance);
	}

	private class MyUnhandledFaultsAction extends UnhandledFaultsAction {
		@Override
		public void error(Element obj, String msg) {
			resp.markInvalid();
		}
	}

}
