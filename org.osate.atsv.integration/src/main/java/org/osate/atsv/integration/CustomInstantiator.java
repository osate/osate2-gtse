package org.osate.atsv.integration;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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

	private Map<String, ChoicePointSpecification> choicepoints;

	public CustomInstantiator(IProgressMonitor pm, AnalysisErrorReporterManager errMgr,
			Set<ChoicePointSpecification> choicepoints) {
		super(pm, errMgr);
		this.choicepoints = choicepoints.stream().collect(
				Collectors.toMap((e -> e.getComponentName() + "-" + e.getChoicepointName()), Function.identity()));
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

	/*-
	 * 
	 * There is a getClassifier or getComponentClassifier (or something) as part of the 
	 * ComponentInstance class. That is implemented as getSubcomponent().getClassifier(ish).
	 * 
	 * That should be overriden -- it'll pull the classifier from the declarative model.
	 * 
	 * The fix:
	 * The instance model metamodel should be modified s/t each component instance has a reference
	 * to the instantiated classifier. That is not just the subcomponent, which it has now,
	 * but the classifier as well.
	 * 
	 * There is a classifier cache that is used during instantiation that tracks this information.
	 * 
	 * The other option is to modify this cache with my replacement information.
	 */

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
			ChoicePointSpecification cps = choicepoints.get(parentName + "-" + ci.getName());
			if (cps != null) {
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
