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
