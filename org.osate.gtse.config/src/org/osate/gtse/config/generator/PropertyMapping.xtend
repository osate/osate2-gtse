package org.osate.gtse.config.generator

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.osate.aadl2.NamedElement
import org.osate.aadl2.Property
import org.osate.gtse.config.config.ConfigValue

class PropertyMapping extends ComponentMapping {

	/**
	 * Note that this is on the LHS -- the path specifies the model element,
	 * and then this is the property within the element
	 */
	@Accessors
	Property property

	new(List<NamedElement> p, Property prop, ConfigValue v) {
		super(p, v)
		property = prop
	}

	def propertyName() {
		property.name
	}

}
