/* 
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
 */
package org.osate.gtse.config.scoping

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.EObjectDescription
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.impl.SimpleScope
import org.osate.aadl2.Aadl2Package
import org.osate.aadl2.Classifier
import org.osate.aadl2.ComponentClassifier
import org.osate.aadl2.ComponentPrototype
import org.osate.aadl2.ContainedNamedElement
import org.osate.aadl2.ContainmentPathElement
import org.osate.aadl2.Element
import org.osate.aadl2.FeatureGroup
import org.osate.aadl2.FeatureGroupPrototype
import org.osate.aadl2.FeatureGroupType
import org.osate.aadl2.NamedElement
import org.osate.aadl2.ReferenceValue
import org.osate.aadl2.Subcomponent
import org.osate.gtse.config.config.Assignment
import org.osate.gtse.config.config.Combination
import org.osate.gtse.config.config.Condition
import org.osate.gtse.config.config.ConfigParameter
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.Configuration
import org.osate.gtse.config.config.ElementRef
import org.osate.gtse.config.config.NamedElementRef
import org.osate.gtse.config.config.NestedAssignments

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType
import static extension org.osate.aadl2.modelsupport.ResolvePrototypeUtil.resolveComponentPrototype
import static extension org.osate.aadl2.modelsupport.ResolvePrototypeUtil.resolveFeatureGroupPrototype

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
class ConfigScopeProvider extends AbstractConfigScopeProvider {

	def IScope scope_ConfigPkg_root(ConfigPkg context, EReference reference) {
		context.configurations.scopeFor
	}

	def IScope scope_Combination_configuration(EObject context, EReference reference) {
		val pkg = context.getContainerOfType(ConfigPkg)
		pkg.configurations.scopeFor
	}

	def IScope scope_Argument_parameter(EObject context, EReference reference) {
		val parent = context.eContainer
		val ne = switch (parent) {
			Combination: {
				parent.configuration
			}
			NamedElementRef: {
				parent.ref
			}
		}
		if (ne instanceof Configuration)
			ne.parameters.scopeFor
		else
			IScope.NULLSCOPE
	}

	def IScope scope_Property(EObject context, EReference reference) {
		val scope = delegateGetScope(context, reference)
		new SimpleScope(scope.allElements.map [
			EObjectDescription.create(name.toString("::"), EObjectOrProxy)
		], false)
	}

	def IScope scope_NumberValue_unit(ConfigParameter context, EReference reference) {
		createUnitLiteralsScopeFromPropertyType(context.propertyType.propertyType)
	}

	def IScope scope_NumberValue_unit(Assignment context, EReference reference) {
		createUnitLiteralsScopeFromPropertyType(context.property.propertyType)
	}

	def IScope scope_NamedElementRef_ref(EObject context, EReference reference) {
		val config = context.getContainerOfType(Configuration)
		val parameters = config.parameters
		val pkg = context.getContainerOfType(ConfigPkg)
		val parent = scope_ComponentClassifier(context, reference)
		parameters.scopeFor(pkg.configurations.scopeFor(parent))
	}

	def IScope scope_ComponentClassifier(EObject context, EReference reference) {
		new SimpleScope(delegateGetScope(context, reference).allElements.map [
			if (Aadl2Package.eINSTANCE.componentImplementation.isSuperTypeOf(EObjectOrProxy.eClass)) {
				convertImplName
			} else {
				EObjectDescription.create(name.toString("::"), EObjectOrProxy)
			}
		], false)
	}

	def IScope scope_ComponentImplementation(ConfigPkg context, EReference reference) {
		new SimpleScope(delegateGetScope(context, reference).allElements.map[convertImplName], true)
	}

	def private convertImplName(IEObjectDescription description) {
		val implName = description.name.lastSegment.split("\\.")
		val newName = QualifiedName.create(description.name.skipLast(1).toString("::") + "::" + implName.get(0),
			implName.get(1))
		EObjectDescription.create(newName, description.EObjectOrProxy)
	}

	def IScope scope_ElementRef_element(ElementRef context, EReference reference) {
		val cls = if (context.prev === null) {
				var topElementRef = context
				while (topElementRef.eContainer instanceof ElementRef) {
					topElementRef = topElementRef.eContainer as ElementRef
				}
				switch container: topElementRef.eContainer.eContainer {
					NamedElementRef: {
						switch c : container.ref {
							ComponentClassifier: c
							Configuration: c.extended
							ConfigParameter: c.classifier
						}
					}
					NestedAssignments: {
						val a = container.eContainer as Assignment
						switch e : a.ref.element {
							Subcomponent: e.allClassifier
							FeatureGroup: e.allFeatureGroupType
						}
					}
					Condition: {
						val n = container.getContainerOfType(NamedElementRef)
						if (n !== null) {
							switch c : n.ref {
								ComponentClassifier: c
								Configuration: c.extended
								ConfigParameter: c.classifier
							}
						} else {
							val a = container.getContainerOfType(Assignment)
							if (a !== null) {
								switch e : a.ref.element {
									Subcomponent: e.allClassifier
									FeatureGroup: e.allFeatureGroupType
								}
							} else {
								val config = context.getContainerOfType(Configuration)
								config.extended
							}
						}
					}
					default: {
						val config = context.getContainerOfType(Configuration)
						config.extended
					}
				}
			} else {
				switch previousElement : context.prev.element {
					case null,
					case previousElement.eIsProxy:
						// Don't provide a scope if the previous element could not be resolved
						null
					Subcomponent: {
						switch subcomponentType : previousElement.allSubcomponentType {
							ComponentClassifier: subcomponentType
							ComponentPrototype: null
						// subcomponentType.resolveComponentPrototype(previousCpe)
						}
					}
					FeatureGroup: {
						switch featureType : previousElement.allFeatureType {
							FeatureGroupType: featureType
							FeatureGroupPrototype: null
						// featureType.resolveFeatureGroupPrototype(previousCpe)
						}
					}
				}
			}
		cls?.allMembers?.filterRefined?.scopeFor ?: IScope::NULLSCOPE
	}

	// Reference is from ContainmentPathElement in Properties.xtext
	override scope_ContainmentPathElement_namedElement(Element context, EReference reference) {
		val container = context.eContainer
		val Classifier namespace = switch container {
			ReferenceValue: {
				// Scoping for first element of a reference value when providing
				container.getContainerOfType(Assignment)?.namespaceForAssignment(true)
			}
			ContainedNamedElement: {
				// Scoping for first element of the applies to
				container.getContainerOfType(Assignment)?.namespaceForAssignment(false)
			}
			ContainmentPathElement: {
				// Scoping for chained element after the first element
				container.classifierForPreviousContainmentPathElement
			}
		}
		namespace?.allMembers?.filterRefined?.scopeFor ?: IScope::NULLSCOPE
	}

	def private static Classifier namespaceForAssignment(Assignment assignment, boolean skip) {
		if (!skip && assignment.ref !== null) {
			// ID before #
			assignment.ref.element?.namespaceFor
		} else {
			// just #property
			switch container: assignment.eContainer {
				NestedAssignments:
					container.getContainerOfType(Assignment).namespaceForAssignment(false)
				NamedElementRef:
					switch e : container.ref {
						Classifier:
							e.namespaceFor
						Configuration:
							e.extended?.namespaceFor
						ConfigParameter:
							e.classifier?.namespaceFor
					}
			}
		}
	}

	def private static Classifier namespaceFor(NamedElement ne) {
		switch ne {
			FeatureGroup: {
				switch featureType : ne.allFeatureType {
					FeatureGroupType:
						featureType
					FeatureGroupPrototype:
						featureType.resolveFeatureGroupPrototype(ne.getContainerOfType(Classifier))
				}
			}
			Subcomponent: {
				switch subcomponentType : ne.allSubcomponentType {
					ComponentClassifier:
						subcomponentType
					ComponentPrototype:
						subcomponentType.resolveComponentPrototype(ne.getContainerOfType(Classifier))
				}
			}
		}

	}

	def protected static getClassifierForPreviousContainmentPathElement(ContainmentPathElement previousCpe) {
		switch previousElement : previousCpe.namedElement {
			case null,
			case previousElement.eIsProxy:
				// Don't provide a scope if the previous element could not be resolved
				null
			Subcomponent: {
				switch subcomponentType : previousElement.allSubcomponentType {
					ComponentClassifier:
						subcomponentType
					ComponentPrototype:
						subcomponentType.resolveComponentPrototype(previousCpe)
				}
			}
			FeatureGroup: {
				switch featureType : previousElement.allFeatureType {
					FeatureGroupType:
						featureType
					FeatureGroupPrototype:
						featureType.resolveFeatureGroupPrototype(previousCpe)
				}
			}
		}
	}

}
