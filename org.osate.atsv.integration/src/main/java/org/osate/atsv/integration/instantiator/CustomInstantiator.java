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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Connection;
import org.osate.aadl2.Feature;
import org.osate.aadl2.Property;
import org.osate.aadl2.PrototypeBinding;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.util.InstanceUtil;
import org.osate.aadl2.instance.util.InstanceUtil.InstantiatedClassifier;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.AadlConstants;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.aadl2.modelsupport.errorreporting.MarkerAnalysisErrorReporter;
import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
import org.osate.atsv.integration.ChoicePointModel.ChoicePointSpecification;
import org.osate.atsv.integration.ChoicePointModel.ReferencePropertyValue;
import org.osate.atsv.integration.ChoicePointModel.SubcomponentChoice;
import org.osate.atsv.integration.exception.UnsupportedFeatureException;

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

	private Map<String, Classifier> originalClassifiers;

	public CustomInstantiator(IProgressMonitor pm, AnalysisErrorReporterManager errMgr,
			Map<String, ChoicePointSpecification> choicepoints) {
		super(pm, errMgr);
		this.choicepoints = choicepoints;
		this.referencePaths = choicepoints.values().stream().filter(ReferencePropertyValue.class::isInstance)
				.map(ChoicePointSpecification::getValueAsString).collect(Collectors.toSet());
		this.referencedInstances = new HashMap<>();
		this.originalClassifiers = new HashMap<>();
	}

	public static SystemInstance myBuildInstanceModelFile(final ComponentImplementation ci,
			Map<String, ChoicePointSpecification> choicepoints) throws Exception {
		URI instanceURI = InstantiateModel.getInstanceModelURI(ci);
		ResourceSet rs = new ResourceSetImpl();
		Resource aadlResource = rs.createResource(instanceURI);

		final InstantiateModel instantiateModel = new CustomInstantiator(new NullProgressMonitor(),
				new AnalysisErrorReporterManager(
						new MarkerAnalysisErrorReporter.Factory(AadlConstants.INSTANTIATION_OBJECT_MARKER)),
				choicepoints);
		SystemInstance root = instantiateModel.createSystemInstanceInt(
				(ComponentImplementation) rs.getEObject(EcoreUtil.getURI(ci), true), aadlResource, false);
		return root;
	}

	@Override
	protected void cacheProperties(ComponentInstance root) throws InterruptedException {
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
	protected InstantiatedClassifier getInstantiatedClassifier(InstanceObject iobj, int index,
			HashMap<InstanceObject, InstantiatedClassifier> classifierCache) {
		if (choicepoints.containsKey(iobj.getComponentInstancePath())
				&& choicepoints.get(iobj.getComponentInstancePath()) instanceof SubcomponentChoice) {
			try {
				return myGetInstantiatedClassifier(iobj, classifierCache);
			} catch (UnsupportedFeatureException e) {
				// TODO Handle this in some way that interacts with the user
				e.printStackTrace();
			}
		}

		return super.getInstantiatedClassifier(iobj, index, classifierCache);
	}

	/**
	 * Copied from {@link org.osate.aadl2.instance.util.InstanceUtil.getInstantiatedClassifier(InstanceObject, int, HashMap<InstanceObject, InstantiatedClassifier>)}
	 *
	 * @param iobj The instance object we're trying to resolve
	 * @param classifierCache The current cache of objects
	 * @return The instantiated classifier
	 * @throws UnsupportedFeatureException If swapping an unsupported AADL construct is attempted
	 */
	private InstantiatedClassifier myGetInstantiatedClassifier(InstanceObject iobj,
			HashMap<InstanceObject, InstantiatedClassifier> classifierCache) throws UnsupportedFeatureException {
		InstantiatedClassifier ic = null;
		if (classifierCache != null) {
			ic = classifierCache.get(iobj);
		}
		if (ic != null) {
			return ic;
		}
		if (iobj instanceof SystemInstance) {
			ic = new InstantiatedClassifier(((SystemInstance) iobj).getComponentImplementation(), null);
		}
		if (ic == null) {
			Classifier classifier = null;
			EList<PrototypeBinding> prototypeBindings = null;
			if (iobj instanceof ComponentInstance) {
				Subcomponent sub = ((ComponentInstance) iobj).getSubcomponent();
				originalClassifiers.put(iobj.getComponentInstancePath(), sub.getClassifier());
				ChoicePointSpecification cps = choicepoints.get(iobj.getComponentInstancePath());
				classifier = Aadl2GlobalScopeUtil.get(iobj, Aadl2Package.eINSTANCE.getClassifier(),
						cps.getValueAsString());
				prototypeBindings = sub.getOwnedPrototypeBindings();
			} else if (iobj instanceof FeatureInstance) {
				throw new UnsupportedFeatureException("Swapping features is not implemented");
			} else {
				return null;
			}
			if (classifier != null) {
				ic = new InstantiatedClassifier(classifier, prototypeBindings);
			}

			// no classifier => try prototype
			if (ic == null) {
				throw new UnsupportedFeatureException(
						"Swapping components requires classifiers, swaps using prototypes aren't supported");
			}
		}

		if (classifierCache != null && ic != null) {
			classifierCache.put(iobj, ic);
		}

		return ic;
	}

	@Override
	protected ComponentType getComponentType(ComponentInstance ci) {
		String path = ci.getComponentInstancePath();
		// If there's a reference to this node, store a reference
		if (referencePaths.contains(path)) {
			referencedInstances.put(path, ci);
		}
		// If this component has been swapped, we need to fix connections that start in it
		if (choicepoints.containsKey(path)) {
			ComponentImplementation compImpl = InstanceUtil
					.getComponentImplementation(ci.getContainingComponentInstance(), 0, classifierCache);
			for (Connection conn : compImpl.getAllConnections()) {
				if (conn.getSource().getConnectionEnd().getContainingClassifier()
						.equals(originalClassifiers.get(path))) {
					String origName = conn.getSource().getConnectionEnd().getFullName();
					for (Feature feat : ci.getClassifier().getAllFeatures()) {
						if (feat.getFullName().equals(origName)) {
							conn.getSource().setConnectionEnd(feat);
						}
					}
				}
				// Do destinations matter here?
			}

		}
		return super.getComponentType(ci);
	}
}
