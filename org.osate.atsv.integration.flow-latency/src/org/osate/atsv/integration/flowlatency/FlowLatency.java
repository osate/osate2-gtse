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
package org.osate.atsv.integration.flowlatency;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.analysis.flows.FlowLatencyAnalysisSwitch;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.network.Response;
import org.osate.result.AnalysisResult;
import org.osate.result.RealValue;
import org.osate.result.Result;

public class FlowLatency extends AbstractAnalysis {

	@Override
	public void runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			IProgressMonitor progressMonitor, Response resp) {
		FlowLatencyAnalysisSwitch flas = new FlowLatencyAnalysisSwitch(progressMonitor, instance);
		AnalysisResult result = flas.invokeAndGetResult(instance, som);
		populateVariables(result, resp);
	}

	private void populateVariables(AnalysisResult result, Response ret) {
		for (Result flowResult : result.getResults()) {
			ret.addVariable(result.getAnalysis(), ATSVVariableType.FLOAT,
					Double.toString(((RealValue) flowResult.getValues().get(2)).getValue()));
		}
	}
}
