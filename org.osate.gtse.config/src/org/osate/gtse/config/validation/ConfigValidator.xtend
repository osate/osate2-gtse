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

import java.util.List
import org.eclipse.xtext.validation.Check
import org.osate.aadl2.ComponentClassifier
import org.osate.aadl2.IntegerLiteral
import org.osate.aadl2.NamedElement
import org.osate.aadl2.Property
import org.osate.aadl2.PropertyType
import org.osate.aadl2.RealLiteral
import org.osate.aadl2.StringLiteral
import org.osate.aadl2.Subcomponent
import org.osate.aadl2.modelsupport.util.AadlUtil
import org.osate.gtse.config.config.Assignment
import org.osate.gtse.config.config.Combination
import org.osate.gtse.config.config.Condition
import org.osate.gtse.config.config.ConfigPackage
import org.osate.gtse.config.config.ConfigParameter
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.ConfigValue
import org.osate.gtse.config.config.Configuration
import org.osate.gtse.config.config.Constraint
import org.osate.gtse.config.config.Limit
import org.osate.gtse.config.config.NamedElementRef
import org.osate.gtse.config.config.OutputVariable
import org.osate.gtse.config.config.PropertyValue
import org.osate.gtse.config.config.Relation
import org.osate.gtse.config.config.SetValue
import org.osate.gtse.config.config.Type

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType
import org.osate.aadl2.Classifier

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class ConfigValidator extends AbstractConfigValidator {
	/*
	 * TODO
	 * 
	 * High priority:
	 * Parameter choices (from) match parameter type.
	 * Parameter actuals match parameter type and optional choices.
	 * Check for with cycles.
	 * Error for arrayable elements, prototypes.
	 * Unique assignment. Unique prefix.
	 * Left-side Constraints refer to parameters.
	 * Equals is type safe.
	 * 
	 * Low priority:
	 * Property type checking (don't worry about reference for now).
	 * Overriding is consistent with other configurations.
	 */

	public static val ARGUMENTS_NOT_ALLOWED = 'argumentsNotAllowed'
	public static val CLASSIFIER_MISMATCH = 'classifierMismatch'
	public static val INCONSISTENT_COMBINATION = 'inconsistentCombination'
	public static val INVALID_BOUND_TYPE = 'invalidBoundType'
	public static val INVALID_CONDITION_RELATION = 'invalidConditionRelation'
	public static val INVALID_CONSTRAINT_RELATION = 'invalidConstraintRelation'
	public static val INVALID_LIMIT_RELATION = 'invalidLimitRelation'
	public static val NO_ROOT = 'noRoot'
	public static val NOT_CLASSIFIER = 'notAClassifier'
	public static val NOT_PROPERTY_VALUE = 'notAPropertyValue'
	public static val NOT_SUBCOMPONENT = 'notASubcomponent'
	public static val PROPERTY_DOES_NOT_APPLY = 'propertyDoesNotApply'

//	public static val INVALID_NAME = 'invalidName'
//
//	@Check
//	def checkGreetingStartsWithCapital(Greeting greeting) {
//		if (!Character.isUpperCase(greeting.name.charAt(0))) {
//			warning('Name should start with a capital', 
//					INVALID_NAME)
//		}
//	}
	@Check(NORMAL)
	def checkRoot(ConfigPkg pkg) {
		if (pkg.root === null)
			error('Missing root configuration', ConfigPackage.Literals.CONFIG_PKG__ROOT, NO_ROOT)
	}

	@Check
	def checkAssignment(Assignment assign) {
		val element = assign.ref?.element
		val value = assign.value
		val property = assign.property
		if (property !== null) {
			checkPropertyAssignment(element, property, value)
		} else if (element !== null) {
			checkSubcomponentAssignment(element, value)
		}
	}

	// assigning a value to a property
	def checkPropertyAssignment(NamedElement element, Property property, ConfigValue value) {
		if (element !== null) {
			checkPropertyApplies(element, property)
		}
		if (value !== null) {
			checkPropertyValueType(property.propertyType, value)
		}
	}

	// does property apply to named element
	def checkPropertyApplies(NamedElement element, Property property) {
		if (!element.acceptsProperty(property)) {
			error('Property ' + property.getQualifiedName() + ' does not apply to ' + element.name,
				ConfigPackage.Literals.ASSIGNMENT__PROPERTY, PROPERTY_DOES_NOT_APPLY);
		}
	}

	// is value an element of the property type
	def checkPropertyValueType(PropertyType type, ConfigValue value) {
		if (!(value instanceof PropertyValue)) {
			error('Not a property value', ConfigPackage.Literals.ASSIGNMENT__VALUE, NOT_PROPERTY_VALUE)
		}
	}

	def checkSubcomponentAssignment(NamedElement element, ConfigValue value) {
		val classifier = value.classifier
		if (classifier === null) {
			error('Expecting a classifier value', ConfigPackage.Literals.ASSIGNMENT__VALUE, NOT_CLASSIFIER)
		}
		if (element instanceof Subcomponent) {
			if (!AadlUtil.isokClassifierSubstitutionTypeExtension(element.classifier, classifier)) {
				error('Assigned classifier does not match subcomponent classifier',
					ConfigPackage.Literals.ASSIGNMENT__VALUE, CLASSIFIER_MISMATCH)
			}
		} else {
			error('Can only assign to a subcomponent', ConfigPackage.Literals.ASSIGNMENT__VALUE, NOT_SUBCOMPONENT)
		}
	}

	def getClassifier(ConfigValue value) {
		if (value instanceof NamedElementRef)
			value.classifier
		else
			null
	}

	def getClassifier(NamedElementRef neRef) {
		val ref = neRef.ref
		val cls = switch ref {
			ComponentClassifier: ref
			Configuration: ref.extended
			ConfigParameter: ref.classifier
			default: null
		}
		if (cls.eIsProxy)
			null
		else
			cls
	}

	@Check
	def checkConfigurationCombination(Combination combination) {
		val container = combination.eContainer
		val cls = switch container {
			Configuration: container.extended
			NamedElementRef: container.classifier
			default: throw new Exception()
		}
		checkCombination(cls, combination)
	}

	def checkCombination(Classifier cl, Combination comb) {
		if (cl !== null && !cl.eIsProxy) {
			if (!comb.unsafe && !comb.configuration.eIsProxy) {
				if (!AadlUtil.isSameOrExtends(comb.configuration.extended, cl)) {
					error(
						'Configuration ' + comb.configuration.name +
							' is for a classifier which is not an ancestor of ' + cl.getQualifiedName(),
						ConfigPackage.Literals.COMBINATION__CONFIGURATION, INCONSISTENT_COMBINATION)
				}
			}
		}
	}

	@Check
	def checkNamedElementRefArguments(NamedElementRef ne) {
		// TODO check arg types, list length
		val ref = ne.ref
		if (!ne.arguments.empty && !ref.eIsProxy) {
			if (ref instanceof Configuration) {
				if (ref.parameters.empty) {
					error('Configuration does not have parameters', ConfigPackage.Literals.NAMED_ELEMENT_REF__ARGUMENTS,
						ARGUMENTS_NOT_ALLOWED)
				}
			} else {
				error('Arguments are allowed for configurations only',
					ConfigPackage.Literals.NAMED_ELEMENT_REF__ARGUMENTS, ARGUMENTS_NOT_ALLOWED)
			}
		}
	}

	@Check
	def checkCombinationArguments(Combination comb) {
		// TODO check arg types, list length
		if (!comb.arguments.empty && !comb.configuration.eIsProxy) {
			if (comb.configuration.parameters.empty) {
				error('Configuration does not have parameters', ConfigPackage.Literals.COMBINATION__ARGUMENTS,
					ARGUMENTS_NOT_ALLOWED)
			}
		}
	}

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

	@Check(FAST)
	def checkOutputs(OutputVariable output) {
		if (output.limit !== null) {
			val bound = output.limit.bound
			switch output.type {
				case Type.INT:
					if (!(bound instanceof IntegerLiteral)) {
						error('Bound must be an integer', ConfigPackage.Literals.OUTPUT_VARIABLE__LIMIT,
							INVALID_BOUND_TYPE)
					}
				case Type.FLOAT: {
					if (!(bound instanceof IntegerLiteral || bound instanceof RealLiteral)) {
						error('Bound must be an int or a float', ConfigPackage.Literals.OUTPUT_VARIABLE__LIMIT,
							INVALID_BOUND_TYPE)
					}
				}
				case Type.STRING: {
					if (!(bound instanceof StringLiteral)) {
						error('Bound must be a string', ConfigPackage.Literals.OUTPUT_VARIABLE__LIMIT,
							INVALID_BOUND_TYPE)
					}
				}
			}
		}
	}

	@Check(FAST)
	def checkLimitRelation(Limit limit) {
		val rel = limit.relation
		if (!#{Relation.EQ, Relation.NEQ, Relation.GT, Relation.GTE, Relation.LT, Relation.LTE}.contains(rel))
			error('Only ==, !=, <, <=, >=, and > are allowed here', ConfigPackage.Literals.LIMIT__RELATION,
				INVALID_LIMIT_RELATION)
	}

}
