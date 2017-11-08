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
package org.osate.atsv.integration.powerconsumption;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.analysis.flows.reporting.model.Line;
import org.osate.analysis.flows.reporting.model.Report;
import org.osate.analysis.flows.reporting.model.ReportedCell;
import org.osate.analysis.flows.reporting.model.Section;
import org.osate.analysis.resource.budgets.handlers.DoPowerAnalysis;
import org.osate.atsv.integration.AbstractAnalysis;
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.network.Response;

public class PowerConsumption extends AbstractAnalysis {

	@Override
	public void runAnalysis(SystemInstance instance, SystemOperationMode som, AnalysisErrorReporterManager errMgr,
			IProgressMonitor progressMonitor, Response resp) {
		DoPowerAnalysis checker = new DoPowerAnalysis();
		Report report = checker.invokeAndGetReport(progressMonitor, instance);
		populateVariables(report, resp);
	}

	private void populateVariables(Report report, Response ret) {
		String varName;
		for (Section section : report.getSections()) {
			varName = "";
			for (Line line : section.getLines()) {
				for (ReportedCell cell : line.getContent()) {
					if (cell.getMessage().startsWith("Computing Electrical Power for ")) {
						// Grab everything after the prefix
						varName = cell.getMessage().substring(31);
					}
					if (cell.getMessage().startsWith("Budget: ")) {
						ret.addVariable(varName, ATSVVariableType.FLOAT, scaleToWatts(cell.getMessage()));
					}
				}
			}
		}
	}

	private String scaleToWatts(String msg) {
		String unscaledStr = msg.substring(8, msg.lastIndexOf(" "));
		String units = msg.substring(msg.lastIndexOf(" ") + 1);
		double unscaledDbl = Double.parseDouble(unscaledStr);
		double wattsVal = 0.0;
		if (units.equalsIgnoreCase("mW")) {
			wattsVal = unscaledDbl / 1000;
		} else if (units.equalsIgnoreCase("W")) {
			wattsVal = unscaledDbl;
		} else if (units.equalsIgnoreCase("kW")) {
			wattsVal = unscaledDbl * 1000;
		}
		return String.valueOf(wattsVal);
	}
}
