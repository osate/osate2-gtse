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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.Property;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.ModeInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.instance.util.InstanceUtil.InstantiatedClassifier;
import org.osate.aadl2.instantiation.CachePropertyAssociationsSwitch;
import org.osate.aadl2.instantiation.SCProperties;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;

public class CustomCachePropertyAssociationsSwitch extends CachePropertyAssociationsSwitch {

	protected CustomCachePropertyAssociationsSwitch(IProgressMonitor pm, AnalysisErrorReporterManager errManager,
			List<Property> filter, HashMap<InstanceObject, InstantiatedClassifier> classifierCache,
			SCProperties scProps, HashMap<ModeInstance, List<SystemOperationMode>> mode2som) {
		super(pm, errManager, filter, classifierCache, scProps, mode2som);
		// This is currently a placeholder that just calls the non-custom switch. There are two reasons it was created:
		// 1. To enable future property-instantiation techniques that *don't* use contained property associations
		// 2. To not require modification of the getUsedPropertyDefinitions method, which currently calls both
		// contained and non-contained property association switches
	}

}
