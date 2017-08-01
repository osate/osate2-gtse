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
import org.osate.aadl2.ComponentClassifier
import org.osate.aadl2.ComponentPrototype
import org.osate.aadl2.FeatureGroup
import org.osate.aadl2.FeatureGroupPrototype
import org.osate.aadl2.FeatureGroupType
import org.osate.aadl2.Subcomponent
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.Configuration
import org.osate.gtse.config.config.ElementRef
import org.osate.gtse.config.config.NamedElementRef

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
class ConfigScopeProvider extends AbstractConfigScopeProvider {

	def IScope scope_Extension_extended(EObject context, EReference reference) {
		val pkg = context.getContainerOfType(ConfigPkg)
		val parent = scope_ComponentClassifier(context, reference)
		pkg.configurations.scopeFor(parent)
	}

	def IScope scope_Property(EObject context, EReference reference) {
		val scope = delegateGetScope(context, reference)
		new SimpleScope(scope.allElements.map [
			EObjectDescription.create(name.toString("::"), EObjectOrProxy)
		], false)
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
				val container = context.eContainer.eContainer
				if (container instanceof NamedElementRef) {
					switch c : container.ref {
						ComponentClassifier: c
						Configuration: c.firstExtended
					}
				} else {
					val config = context.getContainerOfType(Configuration)
					config.firstExtended
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

	def ComponentClassifier firstExtended(Configuration config) {
		if (config.extensions.empty) {
			null
		} else {
			val first = config.extensions.head.extended
			if (first.eIsProxy)
				null
			else
				switch (first) {
					ComponentClassifier: first
					Configuration: first.firstExtended
					default: null
				}
		}

	}
}
