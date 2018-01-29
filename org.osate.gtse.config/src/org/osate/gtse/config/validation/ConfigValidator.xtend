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
package org.osate.gtse.config.validation

import org.eclipse.xtext.validation.Check
import org.osate.gtse.config.config.Condition
import org.osate.gtse.config.config.ConfigPackage
import org.osate.gtse.config.config.Constraint
import org.osate.gtse.config.config.Relation
import org.osate.gtse.config.config.SetValue

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class ConfigValidator extends AbstractConfigValidator {

	public static val INVALID_CONDITION_RELATION = 'invalidConditionRelation'
	public static val INVALID_CONSTRAINT_RELATION = 'invalidConstraintRelation'

//	public static val INVALID_NAME = 'invalidName'
//
//	@Check
//	def checkGreetingStartsWithCapital(Greeting greeting) {
//		if (!Character.isUpperCase(greeting.name.charAt(0))) {
//			warning('Name should start with a capital', 
//					INVALID_NAME)
//		}
//	}
	@Check(FAST)
	def checkConstraintRelation(Constraint cons) {
		val rel = cons.relation
		if (!#{Relation.NONE, Relation.RQ, Relation.FB}.contains(rel))
			error('Only \'requires\' and \'forbids\' are allowed here', ConfigPackage.Literals.CONSTRAINT__RELATION,
				INVALID_CONSTRAINT_RELATION)
	}

	@Check(FAST)
	def checkConditionRelation(Condition cond) {
		val constraint = cond.getContainerOfType(Constraint)
		val rel = cond.relation
		if (constraint.relation == Relation.NONE) {
			// simple comparison
			if (!#{Relation.EQ, Relation.NEQ}.contains(rel))
				error('Only == and != are allowed here', ConfigPackage.Literals.CONDITION__RELATION,
					INVALID_CONDITION_RELATION)
		} else {
			// requires/forbids constraint
			if (cond == constraint.condition) {
				if (!#{Relation.EQ}.contains(rel))
					error('Only == is allowed here', ConfigPackage.Literals.CONDITION__RELATION,
						INVALID_CONDITION_RELATION)
			} else {
				if (cond.rhs instanceof SetValue) {
					if (!#{Relation.IN}.contains(rel))
						error('Only \'in\' is allowed here', ConfigPackage.Literals.CONDITION__RELATION,
							INVALID_CONDITION_RELATION)
				} else {
					if (!#{Relation.EQ, Relation.NEQ}.contains(rel))
						error('Only == and != are allowed here', ConfigPackage.Literals.CONDITION__RELATION,
							INVALID_CONDITION_RELATION)
				}
			}
		}
	}

}
