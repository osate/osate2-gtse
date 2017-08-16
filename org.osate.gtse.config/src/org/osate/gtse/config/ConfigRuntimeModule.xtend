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
package org.osate.gtse.config

import com.google.inject.Binder
import com.google.inject.name.Names
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import org.osate.gtse.config.scoping.ConfigScopeProvider
import org.osate.xtext.aadl2.naming.Aadl2QualifiedNameConverter
import org.osate.xtext.aadl2.naming.Aadl2QualifiedNameProvider
import org.osate.xtext.aadl2.scoping.Aadl2ImportedNamespaceAwareLocalScopeProvider
import org.osate.gtse.config.valueconversion.ConfigValueConverters

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class ConfigRuntimeModule extends AbstractConfigRuntimeModule {

	def bindIQualifiedNameConverter() {
		Aadl2QualifiedNameConverter
	}

	override bindIQualifiedNameProvider() {
		Aadl2QualifiedNameProvider
	}

	override configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(
			Aadl2ImportedNamespaceAwareLocalScopeProvider);
	}

	override bindIScopeProvider() {
		ConfigScopeProvider
	}

	override bindIValueConverterService() {
		ConfigValueConverters
	}

}
