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
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Property;
import org.osate.aadl2.PrototypeBinding;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.util.InstanceUtil.InstantiatedClassifier;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.AadlConstants;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.aadl2.modelsupport.errorreporting.MarkerAnalysisErrorReporter;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.atsv.integration.ChoicePointModel.ChoicePointSpecification;
import org.osate.atsv.integration.ChoicePointModel.ReferencePropertyValue;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

public class CustomInstantiator extends InstantiateModel {

	/**
	 * InstancePath -> Spec
	 */
	private Map<String, ChoicePointSpecification> choicepoints;

	/**
	 * InstancePath -> referenced element
	 */
	private Map<String, ComponentInstance> referencedInstances;

	/**
	 * InstancePath to elements used in reference properties
	 */
	private Set<String> referencePaths;

	public CustomInstantiator(IProgressMonitor pm, AnalysisErrorReporterManager errMgr,
			Map<String, ChoicePointSpecification> choicepoints) {
		super(pm, errMgr);
		this.choicepoints = choicepoints;
		this.referencePaths = choicepoints.values().stream().filter(ReferencePropertyValue.class::isInstance)
				.map(ChoicePointSpecification::getValueAsString).collect(Collectors.toSet());
		this.referencedInstances = new HashMap<>();
	}

	/**
	 * Copied (!) from org.osate.aadl2.instantiation.InstantiateModel#buildInstanceModelFile(), since you can't 
	 * override static methods in java.
	 */
	public static SystemInstance myBuildInstanceModelFile(final ComponentImplementation ci,
			Map<String, ChoicePointSpecification> choicepoints) throws Exception {
		URI instanceURI = OsateResourceUtil.getInstanceModelURI(ci);
		Resource aadlResource = OsateResourceUtil.getEmptyAaxl2Resource(instanceURI);

		final InstantiateModel instantiateModel = new CustomInstantiator(new NullProgressMonitor(),
				new AnalysisErrorReporterManager(
						new MarkerAnalysisErrorReporter.Factory(AadlConstants.INSTANTIATION_OBJECT_MARKER)),
				choicepoints);
		SystemInstance root = instantiateModel.createSystemInstance(ci, aadlResource);
		return root;
	}

	@Override
	protected void getUsedPropertyDefinitions(SystemInstance root) throws InterruptedException {
		// Copied from org.osate.aadl2.instantiation.InstantiateModel, see explanation in there...
		EList<Property> propertyDefinitionList = getAllUsedPropertyDefinitions(root);
		CustomCacheContainedPropertyAssociationsSwitch ccpas = new CustomCacheContainedPropertyAssociationsSwitch(
				classifierCache, scProps, monitor, errManager);

		// Rather than work as the instantiator is running, custom properties are added from the top level once the other
		// properties have been set. So, we create a set of the relevant choicepoint specifications and use only those
		ccpas.addChoicePointSpecifications(choicepoints.values().stream().filter(ChoicePointSpecification::isProperty)
				.collect(Collectors.toSet()));
		ccpas.setReferencedInstances(referencedInstances);
		ccpas.processPostOrderAll(root);
		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}

		final CustomCachePropertyAssociationsSwitch cpas = new CustomCachePropertyAssociationsSwitch(monitor,
				errManager, propertyDefinitionList, classifierCache, scProps, mode2som);
		cpas.processPreOrderAll(root);
		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}
	}

	@Override
	protected ComponentType getComponentType(ComponentInstance ci) {
		String path = ci.getComponentInstancePath();
		// If there's a reference to this node, store a reference
		if (referencePaths.contains(path)) {
			referencedInstances.put(path, ci);
		}

		// If we need to swap out this node, we do that here
		if (choicepoints.containsKey(path)) {
			ChoicePointSpecification cps = choicepoints.get(ci.getComponentInstancePath());
			Classifier cl = EMFIndexRetrieval.getClassifierInWorkspace(ci, cps.getValueAsString());
			if (cl instanceof Classifier) {
				// See public static InstantiatedClassifier getInstantiatedClassifier(InstanceObject iobj, int index,
				// in InstanceUtil.java

				Subcomponent sub = ci.getSubcomponent();
				EList<PrototypeBinding> prototypeBindings = sub.getOwnedPrototypeBindings();
				InstantiatedClassifier ic = new InstantiatedClassifier(cl, prototypeBindings);

				classifierCache.put(ci, ic);

				// It's unclear if changing the type of an instantiated object should
				// be allowed -- if it should be, you can use the following line (and
				// modify the accompanying if statement above) though this will break
				// swapping of system subcomponents.
//				return (ComponentType) cl;
			}
		}
		return super.getComponentType(ci);
	}

}
