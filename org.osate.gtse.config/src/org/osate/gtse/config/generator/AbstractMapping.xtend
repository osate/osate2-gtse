package org.osate.gtse.config.generator

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.osate.aadl2.NamedElement


/**
 * This is the "left hand side" of the config specifications
 * 
 * Essentially, it's a path into the instance model -- note that the
 * first element is the instance model root.
 */
abstract class AbstractMapping {

	/**
	 * The path segments -- joined by '.' in the string representation
	 */
	@Accessors
	Iterable<NamedElement> path = #[]

	new(List<NamedElement> p) {
		path = p
	}

	/**
	 * Used to build the path
	 */
	def prepend(NamedElement element) {
		path = #[element] + path
		this
	}

	/**
	 * Formats the path. Doesn't use the first element (instance model root)
	 */
	def pathName() {
		path.tail.map[name].join('.')
	}

}
