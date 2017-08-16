package org.osate.gtse.config.naming

import org.eclipse.emf.ecore.EObject
import org.osate.aadl2.NamedElement
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.Configuration
import org.osate.xtext.aadl2.naming.Aadl2QualifiedNameProvider

class ConfigQualifiedNameProvider extends Aadl2QualifiedNameProvider {

	override getFullyQualifiedName(EObject obj) {
		switch obj {
			ConfigPkg:
				getConverter().toQualifiedName(obj.name)
			Configuration: {
				val pkg = obj.eContainer as NamedElement
				getConverter().toQualifiedName(pkg.name + '::' + obj.name)
			}
			default:
				null
		}
	}

}
