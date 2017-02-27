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

import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
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
import org.osate.atsv.integration.network.ChoicePointSpecification;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

public class CustomInstantiator extends InstantiateModel {

	/**
	 * InstancePath -> Spec
	 */
	private Map<String, ChoicePointSpecification> choicepoints;

	public CustomInstantiator(IProgressMonitor pm, AnalysisErrorReporterManager errMgr,
			Map<String, ChoicePointSpecification> choicepoints) {
		super(pm, errMgr);
		this.choicepoints = choicepoints;
	}

	/**
	 * Copied (!) from org.osate.aadl2.instantiation.InstantiateModel#buildInstanceModelFile(), since you can't 
	 * override static methods in java.
	 */
	public static SystemInstance myBuildInstanceModelFile(final ComponentImplementation ci,
			Map<String, ChoicePointSpecification> choicepoints) throws Exception {
		ComponentImplementation ici = ci;
		EObject eobj = OsateResourceUtil.loadElementIntoResourceSet(ci);
		if (eobj instanceof ComponentImplementation) {
			ici = (ComponentImplementation) eobj;
		}
		URI instanceURI = OsateResourceUtil.getInstanceModelURI(ici);
		Resource aadlResource = OsateResourceUtil.getEmptyAaxl2Resource(instanceURI);

		final InstantiateModel instantiateModel = new CustomInstantiator(new NullProgressMonitor(),
				new AnalysisErrorReporterManager(
						new MarkerAnalysisErrorReporter.Factory(AadlConstants.INSTANTIATION_OBJECT_MARKER)),
				choicepoints);
		SystemInstance root = instantiateModel.createSystemInstance(ici, aadlResource);
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

	/*- 
	/**
	 * TODO: Lutz says this is "suspicious" and may break in the future.
	
	@Override
	protected void addUsedPropertyDefinitions(Element root, List<Property> result) {
		if (!(root instanceof NamedElement) || root == null) {
			super.addUsedPropertyDefinitions(root, result);
			return;
		}
		if (choicepoints.get(((NamedElement) root).getQualifiedName()) != null) {
			TreeIterator<Element> it = EcoreUtil.getAllContents(Collections.singleton(root));
			while (it.hasNext()) {
				EObject ao = it.next();
				if (ao instanceof PropertyAssociation) {
					Property pd = ((PropertyAssociation) ao).getProperty();
					ChoicePointSpecification cps = choicepoints.get(((NamedElement) root).getQualifiedName())
							.get(pd.getQualifiedName());
					if (cps != null) {
						if (cps.getType() == ATSVVariableType.FLOAT
								|| cps.getType() == ATSVVariableType.DISCRETE_FLOAT) {
							// TODO: Can ownedvalues be > 1?
							((RealLiteralImpl) ((PropertyAssociation) ao).getOwnedValues().get(0).getOwnedValue())
									.setValue(cps.getValueAsFloat());
						} else if (cps.getType() == ATSVVariableType.INTEGER) {
							((IntegerLiteralImpl) ((PropertyAssociation) ao).getOwnedValues().get(0).getOwnedValue())
									.setValue(cps.getValueAsInt());
						} else {
							// TODO: Throw an error? we don't support string property swaps...
						}
						result.add(pd);
					} else {
						if (pd != null) {
							result.add(pd);
						}
					}
				}
			}
		}
		super.addUsedPropertyDefinitions(root, result);
	}*/

	@Override
	protected ComponentType getComponentType(ComponentInstance ci) {
		if (choicepoints.containsKey(ci.getComponentInstancePath())) {
			ChoicePointSpecification cps = choicepoints.get(ci.getComponentInstancePath());
			Classifier cl = EMFIndexRetrieval.getClassifierInWorkspace(// ci.eResource().getResourceSet(),
					cps.getValueAsString());
			if (cl instanceof ComponentType) {
				// See public static InstantiatedClassifier getInstantiatedClassifier(InstanceObject iobj, int index,
				// in InstanceUtil.java

				Subcomponent sub = ci.getSubcomponent();
				ComponentClassifier classifier = (ComponentClassifier) cl;
				EList<PrototypeBinding> prototypeBindings = sub.getOwnedPrototypeBindings();
				InstantiatedClassifier ic = new InstantiatedClassifier(classifier, prototypeBindings);

				classifierCache.put(ci, ic);
				return (ComponentType) cl;
			}
		}
		return super.getComponentType(ci);
	}

}
