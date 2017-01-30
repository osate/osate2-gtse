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

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
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
	 * Container -> Element that is to be replaced -> Spec
	 */
	private Map<String, Map<String, ChoicePointSpecification>> choicepoints;

	public CustomInstantiator(IProgressMonitor pm, AnalysisErrorReporterManager errMgr,
			Set<ChoicePointSpecification> choicepoints) {
		super(pm, errMgr);
		this.choicepoints = choicepoints.stream().collect(groupingBy(ChoicePointSpecification::getComponentName,
				toMap(ChoicePointSpecification::getItemName, identity())));
	}

	/**
	 * Copied (!) from org.osate.aadl2.instantiation.InstantiateModel#buildInstanceModelFile(), since you can't 
	 * override static methods in java.
	 */
	public static SystemInstance myBuildInstanceModelFile(final ComponentImplementation ci,
			Set<ChoicePointSpecification> choicepoints) throws Exception {
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
	protected void addUsedPropertyDefinitions(Element root, List<Property> result) {
		if (!(root instanceof NamedElement) || root == null) {
			super.addUsedPropertyDefinitions(root, result);
			return;
		}
		System.out.println(((NamedElement) root).getQualifiedName());
		if (choicepoints.get(((NamedElement) root).getQualifiedName()) != null) {
			TreeIterator<Element> it = EcoreUtil.getAllContents(Collections.singleton(root));
			while (it.hasNext()) {
				EObject ao = it.next();
				if (ao instanceof PropertyAssociation) {
					Property pd = ((PropertyAssociation) ao).getProperty();
					if (choicepoints.get(((NamedElement) root).getQualifiedName()).get(pd.getQualifiedName()) != null) {
						result.add(pd); // TODO: Start here, swap tha value
					} else {
						if (pd != null) {
							result.add(pd);
						}
					}
				}
			}
		}

//		ChoicePointSpecification cps = choicepoints.get(parentName + "-" + ((NamedElement)root).getName());
		super.addUsedPropertyDefinitions(root, result);
	}

	@Override
	protected ComponentType getComponentType(ComponentInstance ci) {
		if (ci.getContainingComponentInstance() != null) {
			// trim off the last 9 characters, which are "_Instance"
			// Top-level is system instance, everything else is a component instance
			String parentName = null;
			if (ci.getContainingComponentInstance() instanceof SystemInstance) {
				parentName = ci.getContainingComponentInstance().getName()
						.substring(0, ci.getContainingComponentInstance().getName().length() - 9).replace('_', '.');
			} else {
				parentName = ci.getContainingComponentInstance().getName().replace('_', '.');
			}

			if (choicepoints.get(parentName) != null && choicepoints.get(parentName).get(ci.getName()) != null) {
				ChoicePointSpecification cps = choicepoints.get(parentName).get(ci.getName());
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
		}
		return super.getComponentType(ci);
	}

}
