package org.osate.gtse.config.tests

import org.osate.testsupport.Aadl2InjectorProvider

class ConfigAndAadlInjectorProvider extends ConfigInjectorProvider {

	override protected internalCreateInjector() {
		new Aadl2InjectorProvider().injector
		
		super.internalCreateInjector
	}	
}