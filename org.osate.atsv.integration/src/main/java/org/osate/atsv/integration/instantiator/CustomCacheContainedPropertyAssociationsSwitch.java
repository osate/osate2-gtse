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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.osate.aadl2.ContainmentPathElement;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.InstanceFactory;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.InstanceReferenceValue;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.util.InstanceUtil.InstantiatedClassifier;
import org.osate.aadl2.instantiation.CacheContainedPropertyAssociationsSwitch;
import org.osate.aadl2.instantiation.SCProperties;
import org.osate.aadl2.modelsupport.errorreporting.AnalysisErrorReporterManager;
import org.osate.atsv.integration.ChoicePointModel.ChoicePointSpecification;
import org.osate.atsv.integration.ChoicePointModel.LiteralPropertyValue;
import org.osate.atsv.integration.ChoicePointModel.PropertyValue;
import org.osate.atsv.integration.ChoicePointModel.ReferencePropertyValue;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.exception.BadPathException;
import org.osate.atsv.integration.exception.UnhandledVariableTypeException;
import org.osate.xtext.aadl2.properties.util.GetProperties;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

public class CustomCacheContainedPropertyAssociationsSwitch extends CacheContainedPropertyAssociationsSwitch {

	private Set<ChoicePointSpecification> choicepoints;
	private Map<String, ComponentInstance> referencedInstances;

	protected CustomCacheContainedPropertyAssociationsSwitch(
			HashMap<InstanceObject, InstantiatedClassifier> classifierCache, SCProperties scProps, IProgressMonitor pm,
			AnalysisErrorReporterManager errManager) {
		super(classifierCache, scProps, pm, errManager);
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

			BasicEList<PropertyAssociation> pas = new BasicEList<>();
			pas.addAll(si.getComponentImplementation().getAllPropertyAssociations());
			try {
				pas.addAll(
						buildPAList(processChoicepoints(si), new ArrayList<>(choicepoints.size()), new LinkedList<>()));
			} catch (BadPathException | UnhandledVariableTypeException e) {
				e.printStackTrace();
			}

			processContainedPropertyAssociations(si, si, pas);

			return DONE;
		}
	}

	private List<PropertyAssociation> buildPAList(Node n, List<PropertyAssociation> pas,
			LinkedList<NamedElement> ancestry) {

		if (!n.propName.isEmpty()) {
			Iterator<String> setIter = n.propSet.iterator();
			Iterator<String> nameIter = n.propName.iterator();
			Iterator<PropertyExpression> valIter = n.vals.iterator();

			while (setIter.hasNext()) {
				// 1. Get a reference to the named property
				Property p = GetProperties.lookupPropertyDefinition(n.ele, setIter.next(), nameIter.next());

				// 2. Create the property association
				PropertyAssociation pa = n.ele.createOwnedPropertyAssociation();
				pa.setProperty(p);

				// 3. Set the value
				pa.createOwnedValue().setOwnedValue(valIter.next());

				// 4. Set the Applies-To
				ContainmentPathElement cpe = pa.createAppliesTo().createPath();
				for (Iterator<NamedElement> iter = ancestry.iterator(); iter.hasNext();) {
					cpe.setNamedElement(iter.next());
					if (iter.hasNext()) {
						cpe = cpe.createPath();
					}
				}

				// 5. Add the PropertyAssociation to the list so it can be returned
				pas.add(pa);
			}
		}

		for (Node child : n.children.values()) {
			ancestry.addLast(child.ele);
			buildPAList(child, pas, ancestry);
			ancestry.removeLast();
		}

		return pas;
	}

	public void addChoicePointSpecifications(Set<ChoicePointSpecification> choicepoints) {
		this.choicepoints = choicepoints;
	}

	private Node processChoicepoints(SystemInstance si) throws BadPathException, UnhandledVariableTypeException {
		Node root = new Node(si);
		PropertyValue pv;
		for (ChoicePointSpecification cps : choicepoints) {
			if (!cps.isProperty()) {
				continue;
			}
			pv = (PropertyValue) cps;
			processChoicepoint(root, pv, "");
		}
		return root;
	}

	private void processChoicepoint(Node current, PropertyValue pv, String path)
			throws BadPathException, UnhandledVariableTypeException {
		String[] pathArr = pv.getComponentPath().split("\\.");
		int depth = 0;
		while (depth <= pathArr.length) {
			if (path.equals(pv.getComponentPath())) {
				// Found it~
				current.propSet.add(pv.getPropertyName().split("::")[0]);
				current.propName.add(pv.getPropertyName().split("::")[1]);
				current.vals.add(getPropValue(pv));
				return;
			}
			String nextName = pathArr[depth++];
			if (!(current.ele instanceof ComponentInstance)) {
				throw new BadPathException("All non-terminal elements of paths must be components, but got "
						+ current.ele.getName() + " as a non-terminal component");
			}
			ComponentInstance ci = (ComponentInstance) current.ele;
			Node next = getNext(current, ci, nextName);

			if (next != null) {
				current.addChild(next);
				current = next;
				path = path + (path.length() == 0 ? nextName : "." + nextName);
			} else {
				throw new BadPathException("Can't resolve " + pv.getComponentPath());
			}
		}
	}

	private PropertyExpression getPropValue(PropertyValue pv) throws UnhandledVariableTypeException {
		ATSVVariableType type = pv.getType();
		if (type == ATSVVariableType.FLOAT || type == ATSVVariableType.DISCRETE_FLOAT) {
			return PropertyUtils.createRealValue(pv.getValueAsFloat());
		} else if (type == ATSVVariableType.INTEGER) {
			return PropertyUtils.createIntegerValue(pv.getValueAsInt());
		} else if (type == ATSVVariableType.STRING && pv instanceof LiteralPropertyValue) {
			return PropertyUtils.createStringValue(pv.getValueAsString());
		} else if (type == ATSVVariableType.STRING && pv instanceof ReferencePropertyValue) {
			InstanceReferenceValue irf = InstanceFactory.eINSTANCE.createInstanceReferenceValue();
			irf.setReferencedInstanceObject(referencedInstances.get(pv.getValueAsString()));
			return irf;
		}
		throw new UnhandledVariableTypeException(
				"Can't get the property value for " + pv.getPropertyName() + " in " + pv.getComponentPath());
	}

	private Node getNext(Node cur, ComponentInstance ele, String name) throws BadPathException {
		if (cur.children.containsKey(name)) {
			return cur.children.get(name);
		}

		// List instead of a set since components are more likely to be encountered than
		// connections or features, so we want to make sure those are checked first
		List<NamedElement> eles = new LinkedList<>();
		eles.addAll(ele.getAllComponentInstances());
		eles.addAll(ele.getAllFeatureInstances());
		eles.addAll(ele.getAllEnclosingConnectionInstances());

		for (NamedElement ne : eles) {
			if (ne.getName().equalsIgnoreCase(name)) {
				return new Node(ne);
			}
		}

		throw new BadPathException("Can't find " + name + " as a child of " + ele.getName());
	}

	/**
	 * This class is used to build a tree-like structure that corresponds, partially, to the 
	 * instance model. The idea is that it is created using the set of choicepoint specifications
	 * (see processChoicepoints(...)) and then traversed when creating / inserting the property
	 * values (see buildPAList(...))
	 */
	private class Node {

		/**
		 * Maps child element name -> node
		 */
		public Map<String, Node> children = new HashMap<>();

		/**
		 * The element this node corresponds to. Used for name lookups and property assignments
		 */
		public NamedElement ele;

		/*- These three elements are used for property specification. All three
		 * lists must be exactly the same size, as they are traversed simultaneously.
		 * That is, a component with the properties MySet::MyProp1 = 5 and
		 * MySet::MyProp2 = 10 would be realized as:
		 *
		 * propSet = {"MySet", "MySet"}
		 * propNames = {"MyProp1", "MyProp2"}
		 * vals = {5, 10}
		 */
		/**
		 * The ordered list of property set names. If empty, there are no properties
		 * to assign at this node (though there are at a child of this node).
		 */
		public List<String> propSet = new LinkedList<>();

		/**
		 * The ordered list of property names. 
		 */
		public List<String> propName = new LinkedList<>();

		/**
		 * The ordered list of property values.
		 */
		public List<PropertyExpression> vals = new LinkedList<>();

		public Node(NamedElement ele) {
			this.ele = ele;
		}

		public void addChild(Node child) {
			children.put(child.ele.getName(), child);
		}
	}

	public void setReferencedInstances(Map<String, ComponentInstance> referencedInstances) {
		this.referencedInstances = referencedInstances;
	}
}
