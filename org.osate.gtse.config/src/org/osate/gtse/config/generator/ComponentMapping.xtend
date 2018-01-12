package org.osate.gtse.config.generator

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.osate.aadl2.NamedElement
import org.osate.gtse.config.config.ConfigValue


/**
 * This class implements subcomponent choice specifications
 */
class ComponentMapping extends AbstractMapping {

	/**
	 * The value -- either a classifier or a property value or a parameter.
	 */
	@Accessors
	ConfigValue value

	new(List<NamedElement> p, ConfigValue v) {
		super(p)
		value = v
	}

}
