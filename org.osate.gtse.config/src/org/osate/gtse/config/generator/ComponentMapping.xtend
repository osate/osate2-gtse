package org.osate.gtse.config.generator

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.osate.aadl2.NamedElement
import org.osate.gtse.config.config.ConfigValue

class ComponentMapping extends AbstractMapping {

	@Accessors
	Iterable<NamedElement> path = #[]

	@Accessors
	ConfigValue value

	new(List<NamedElement> p, ConfigValue v) {
		super(p)
		value = v
	}

}
