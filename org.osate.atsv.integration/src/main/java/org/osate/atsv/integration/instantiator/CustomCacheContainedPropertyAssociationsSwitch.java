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
package org.osate.atsv.integration.instantiator;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.util.InstanceUtil.InstantiatedClassifier;
import org.osate.aadl2.instantiation.CacheContainedPropertyAssociationsSwitch;
import org.osate.aadl2.instantiation.SCProperties;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;

public class CustomCacheContainedPropertyAssociationsSwitch extends CacheContainedPropertyAssociationsSwitch {

	protected CustomCacheContainedPropertyAssociationsSwitch(
			HashMap<InstanceObject, InstantiatedClassifier> classifierCache, SCProperties scProps, IProgressMonitor pm,
			AnalysisErrorReporterManager errManager) {
		super(classifierCache, scProps, pm, errManager);
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void initSwitches() {
		instanceSwitch = new myNewInstanceSwitch();
	}

	protected class myNewInstanceSwitch extends myInstanceSwitch {
		@Override
		public String caseSystemInstance(final SystemInstance si) {
			if (monitor.isCanceled()) {
				cancelTraversal();
				return DONE;
			}
			monitor.subTask("Caching system instance contained property associations");
			processContainedPropertyAssociations(si, si, si.getComponentImplementation().getAllPropertyAssociations());
			// TODO: Insert hooks here
			// Modify ci here using choicepoints map

			return DONE;
		}
	}
}
