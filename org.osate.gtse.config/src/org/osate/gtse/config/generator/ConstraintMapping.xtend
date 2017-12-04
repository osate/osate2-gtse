package org.osate.gtse.config.generator

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.osate.aadl2.NamedElement
import org.osate.gtse.config.config.Constraint

class ConstraintMapping extends AbstractMapping {
	
	@Accessors
	Constraint constraint
	
	new(List<NamedElement> p, Constraint c) {
		super(p)
		constraint = c
	}
	
}