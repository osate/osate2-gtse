package org.osate.gtse.config.generator

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.osate.aadl2.NamedElement

abstract class AbstractMapping {

	@Accessors
	Iterable<NamedElement> path = #[]

	new(List<NamedElement> p) {
		path = p
	}

	def prepend(NamedElement element) {
		path = #[element] + path
		this
	}

	def pathName() {
		path.tail.map[name].join('.')
	}

}
