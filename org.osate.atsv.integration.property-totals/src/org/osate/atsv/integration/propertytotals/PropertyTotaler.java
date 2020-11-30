/*******************************************************************************
 * OSATE2-GTSE
 *
 * Copyright 2017 Carnegie Mellon University. All Rights Reserved.
 *
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * Released under an Eclipse Public License - v1.0-style license, please see
 * license.txt or contact permission@sei.cmu.edu for full terms.
 *
 * DM17-0002
 *******************************************************************************/
package org.osate.atsv.integration.propertytotals;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.analysis.architecture.PropertyTotals;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.network.Response;
import org.osate.result.RealValue;
import org.osate.ui.handlers.AaxlReadOnlyHandlerAsJob;
import org.osate.ui.handlers.AbstractAaxlHandler;

public class PropertyTotaler extends AbstractAnalysis {

	private AbstractAaxlHandler aaa = new MyDoPropertyTotals();

	@Override
	public void runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			IProgressMonitor progressMonitor, Response resp) {
		PropertyTotals pt = new PropertyTotals(progressMonitor, aaa);
		resp.addVariable("Weight", ATSVVariableType.FLOAT,
				String.valueOf(((RealValue) PropertyTotals.invoke(instance).getResults().get(0).getValues().get(0))
						.getValue()));
		resp.addVariable("Price", ATSVVariableType.FLOAT, String.valueOf(pt.getPrice(instance)));
	}

	private class MyDoPropertyTotals extends AaxlReadOnlyHandlerAsJob {

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
