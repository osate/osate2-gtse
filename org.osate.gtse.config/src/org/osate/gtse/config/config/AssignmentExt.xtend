package org.osate.gtse.config.config

class AssignmentExt {

	static def boolean isWildcard(Assignment a) {
		false
	}

	static def boolean isProperty(Assignment a) {
		a.property !== null
	}

	static def boolean isAnnex(Assignment a) {
		false
	}
	
	static def boolean isNested(Assignment a) {
		a.value.assignments !== null
	}
}
