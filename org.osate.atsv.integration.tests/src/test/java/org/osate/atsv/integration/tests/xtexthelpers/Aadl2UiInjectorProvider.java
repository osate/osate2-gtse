/*
* generated by Xtext
*/
package org.osate.atsv.integration.tests.xtexthelpers;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class Aadl2UiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return org.osate.xtext.aadl2.ui.internal.Aadl2Activator.getInstance()
				.getInjector("org.osate.xtext.aadl2.Aadl2");
	}

}
