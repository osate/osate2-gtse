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

import java.util.ArrayDeque
import java.util.Deque
import java.util.List
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.validation.Check
import org.osate.aadl2.Classifier
import org.osate.aadl2.ComponentCategory
import org.osate.aadl2.ComponentClassifier
import org.osate.aadl2.ComponentImplementation
import org.osate.aadl2.IntegerLiteral
import org.osate.aadl2.NamedElement
import org.osate.aadl2.Property
import org.osate.aadl2.PropertyType
import org.osate.aadl2.RealLiteral
import org.osate.aadl2.StringLiteral
import org.osate.aadl2.Subcomponent
import org.osate.aadl2.modelsupport.util.AadlUtil
import org.osate.gtse.config.config.Argument
import org.osate.gtse.config.config.Assignment
import org.osate.gtse.config.config.CandidateList
import org.osate.gtse.config.config.Combination
import org.osate.gtse.config.config.Condition
import org.osate.gtse.config.config.ConfigPackage
import org.osate.gtse.config.config.ConfigParameter
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.ConfigValue
import org.osate.gtse.config.config.Configuration
import org.osate.gtse.config.config.Constraint
import org.osate.gtse.config.config.ElementRef
import org.osate.gtse.config.config.Limit
import org.osate.gtse.config.config.NamedElementRef
import org.osate.gtse.config.config.OutputVariable
import org.osate.gtse.config.config.PropertyValue
import org.osate.gtse.config.config.Relation
import org.osate.gtse.config.config.SetValue
import org.osate.gtse.config.config.Type

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType

/*
 * TODO
 * 
 * High priority:
 * Left-side Constraints refer to parameters.
 * Equals is type safe.
 * 
 * Low priority:
 * Property type checking (don't worry about reference for now).
 * Overriding is consistent with other configurations.
 */
class ConfigValidator extends AbstractConfigValidator {
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
	public static val CATEGORY_MISMATCH = 'categoryMismatch'
	public static val NOT_EXTENSION = 'notExtension'
	public static val NOT_IMPLEMENTATION = 'notImplementation'
	public static val UNSAFE = 'unsafe'
	public static val ARGUMENT_NOT_CHOICE = 'argumentNotChoice'
	public static val WITH_CYCLES = 'withCycles'
	public static val CLASSIFIER_CYCLES = 'classifierCycles'
	public static val SUBCOMPONENT_ARRAY = 'subcomponentArray'
	public static val DUPLICATE_ASSIGNMENT = 'duplicateAssignment'

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
	def protected checkPropertyAssignment(NamedElement element, Property property, ConfigValue value) {
		if (element !== null) {
			checkPropertyApplies(element, property)
		}
		if (value !== null) {
			checkPropertyValueType(property.propertyType, value)
		}
	}

	// does property apply to named element
	def protected checkPropertyApplies(NamedElement element, Property property) {
		if (!element.acceptsProperty(property)) {
			error('Property ' + property.getQualifiedName() + ' does not apply to ' + element.name,
				ConfigPackage.Literals.ASSIGNMENT__PROPERTY, PROPERTY_DOES_NOT_APPLY);
		}
	}

	// is value an element of the property type
	def protected checkPropertyValueType(PropertyType type, ConfigValue value) {
		if (!(value instanceof PropertyValue)) {
			error('Not a property value', ConfigPackage.Literals.ASSIGNMENT__VALUE, NOT_PROPERTY_VALUE)
		}
	}

	def protected checkSubcomponentAssignment(NamedElement element, ConfigValue value) {
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

	def protected getClassifier(ConfigValue value) {
		if (value instanceof NamedElementRef)
			value.classifier
		else
			null
	}

	def protected getClassifier(NamedElementRef neRef) {
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

	def protected checkCombination(Classifier cl, Combination comb) {
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

	@Check
	def checkConstraintRelation(Constraint cons) {
		val rel = cons.relation
		if (!#{Relation.NONE, Relation.RQ, Relation.FB}.contains(rel))
			error('Only \'requires\' and \'forbids\' are allowed here', ConfigPackage.Literals.CONSTRAINT__RELATION,
				INVALID_CONSTRAINT_RELATION)
	}

	@Check
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

	@Check
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

	@Check
	def checkLimitRelation(Limit limit) {
		val rel = limit.relation
		if (!#{Relation.EQ, Relation.NEQ, Relation.GT, Relation.GTE, Relation.LT, Relation.LTE}.contains(rel))
			error('Only ==, !=, <, <=, >=, and > are allowed here', ConfigPackage.Literals.LIMIT__RELATION,
				INVALID_LIMIT_RELATION)
	}

	@Check
	def void checkParameterCategory(ConfigParameter parameter) {
		val classifier = parameter.classifier
		if (classifier !== null && !classifier.eIsProxy && classifier.category != ComponentCategory.ABSTRACT &&
			parameter.category != classifier.category) {
			error('''The category of «classifier.category» «classifier.getQualifiedName» is not «parameter.category»''',
				ConfigPackage.Literals.CONFIG_PARAMETER__CLASSIFIER, CATEGORY_MISMATCH)
		}
	}

	@Check
	def void checkChoicesMatchType(ConfigParameter parameter) {
		val choices = parameter.choices
		if (choices instanceof CandidateList) {
			if (parameter.classifier !== null && !parameter.classifier.eIsProxy) {
				checkChoicesMatchClassifier(choices, parameter.classifier)
			} else if (parameter.propertyType !== null && !parameter.propertyType.eIsProxy) {
				// TODO Check that the property types match
				checkChoicesArePropertyValues(choices)
			}
		}
	}

	def protected void checkChoicesMatchClassifier(CandidateList choices, ComponentClassifier classifier) {
		choices.candidates.forEach [ candidate |
			if (candidate instanceof NamedElementRef) {
				val candidateRef = candidate.ref
				if (!candidateRef.eIsProxy) {
					if (candidateRef instanceof ComponentImplementation) {
						if (!AadlUtil.isSubClassifier(classifier, candidateRef)) {
							error(candidateRef.getQualifiedName + " is not an extension of " +
								classifier.getQualifiedName, candidate, null, NOT_EXTENSION)
						}
					} else {
						error("Choice must be a component implementation", candidate, null, NOT_IMPLEMENTATION)
					}
				}
			} else {
				error("Choice must be a component implementation", candidate, null, NOT_IMPLEMENTATION)
			}
		]
	}

	def protected void checkChoicesArePropertyValues(CandidateList choices) {
		choices.candidates.filter[!(it instanceof PropertyValue)].forEach [ candidate |
			error("Choice must be a property value", candidate, null, NOT_PROPERTY_VALUE)
		]
	}

	@Check
	def void checkUnsafe(Combination combination) {
		if (combination.unsafe) {
			warning("'unsafe' not supported", ConfigPackage.Literals.COMBINATION__UNSAFE, UNSAFE)
		}
	}

	@Check
	def void typeCheckArgument(Argument argument) {
		val parameter = argument.parameter
		if (parameter !== null && !parameter.eIsProxy) {
			if (parameter.classifier !== null && !parameter.classifier.eIsProxy) {
				switch value : argument.value {
					NamedElementRef case !value.ref.eIsProxy: {
						switch ref : value.ref {
							Classifier: typeCheckClassifierArgument(parameter, ref, value)
							Configuration: typeCheckClassifierArgument(parameter, ref, value)
							ConfigParameter: typeCheckClassifierArgument(parameter, ref, value)
						}
					}
					PropertyValue:
						error("Argument must be a classifier or configuration", value, null, NOT_CLASSIFIER)
				}
			} else if (parameter.propertyType !== null && !parameter.propertyType.eIsProxy) {
				typeCheckPropertyArgument(parameter, argument.value)
			}
		}
	}

	def protected void typeCheckClassifierArgument(ConfigParameter parameter, Classifier argument,
		NamedElementRef namedElementRef) {
		if (AadlUtil.isSubClassifier(parameter.classifier, argument)) {
			val choices = parameter.candidates.filter(NamedElementRef).map[it.ref].filter(Classifier).toList
			if (!choices.empty && !choices.exists[AadlUtil.isSubClassifier(it, argument)]) {
				error("Argument is not one of the choices for " + parameter.name, namedElementRef,
					ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, ARGUMENT_NOT_CHOICE)
			}
		} else {
			error(argument.getQualifiedName + " does not extend from " + parameter.classifier.getQualifiedName,
				namedElementRef, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
		}
	}

	def protected void typeCheckClassifierArgument(ConfigParameter parameter, Configuration argument,
		NamedElementRef namedElementRef) {
		if (argument.extended === null) {
			error(argument.name + " does not extend from " + parameter.classifier.getQualifiedName, namedElementRef,
				ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
		} else if (!argument.extended.eIsProxy) {
			if (AadlUtil.isSubClassifier(parameter.classifier, argument.extended)) {
				val choices = parameter.candidates.filter(NamedElementRef).map[it.ref].filter(Classifier)
				if (!choices.empty && !choices.exists[AadlUtil.isSubClassifier(it, argument.extended)]) {
					error("Argument is not one of the choices for " + parameter.name, namedElementRef,
						ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, ARGUMENT_NOT_CHOICE)
				}
			} else {
				error(argument.name + " does not extend from " + parameter.classifier.getQualifiedName, namedElementRef,
					ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
			}
		}
	}

	def protected void typeCheckClassifierArgument(ConfigParameter parameter, ConfigParameter argument,
		NamedElementRef namedElementRef) {
		if (argument.classifier !== null && !argument.classifier.eIsProxy) {
			if (AadlUtil.isSubClassifier(parameter.classifier, argument.classifier)) {
				val paramChoices = parameter.candidates.filter(NamedElementRef).map[it.ref].filter(Classifier).toList
				if (!paramChoices.empty) {
					if (paramChoices.exists[AadlUtil.isSubClassifier(it, argument.classifier)]) {
						val choicesValid = argument.candidates.forall [ argCandidate |
							if (argCandidate instanceof NamedElementRef) {
								switch argCandidateRef : argCandidate.ref {
									case argCandidateRef.eIsProxy: true
									Classifier: paramChoices.exists[AadlUtil.isSubClassifier(it, argCandidateRef)]
									default: false
								}
							} else {
								false
							}
						]
						if (!choicesValid) {
							error('''Not all choices of «argument.name» extend from any choices of «parameter.name»''',
								namedElementRef, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
						}
					} else {
						error(argument.classifier.getQualifiedName + " does not extend from any choices of " +
							parameter.name, namedElementRef, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF,
							NOT_EXTENSION)
					}
				}
			} else {
				error(argument.classifier.getQualifiedName + " does not extend from " +
					parameter.classifier.getQualifiedName, namedElementRef,
					ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
			}
		} else if (argument.propertyType !== null && !argument.propertyType.eIsProxy) {
			error("Argument must be a classifier or configuration", namedElementRef, null, NOT_CLASSIFIER)
		}
	}

	// TODO Check that the property types match
	def protected void typeCheckPropertyArgument(ConfigParameter parameter, ConfigValue argument) {
		val paramChoices = parameter.candidates.filter(PropertyValue).map[it.exp].toList
		if (argument instanceof NamedElementRef) {
			val ref = argument.ref
			if (!ref.eIsProxy) {
				if (ref instanceof ConfigParameter) {
					if (ref.classifier !== null && !ref.classifier.eIsProxy) {
						error("Argument must be a property value", argument, null, NOT_PROPERTY_VALUE)
					} else if (ref.propertyType !== null && !ref.propertyType.eIsProxy && !paramChoices.empty) {
						val refChoices = ref.candidates
						if (refChoices.empty) {
							error('''«parameter.name» has choices, but «ref.name» does not''', argument,
								ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, ARGUMENT_NOT_CHOICE)
						} else {
							val valid = refChoices.forall [ refCandidate |
								switch refCandidate {
									PropertyValue case paramChoices.exists[EcoreUtil.equals(it, refCandidate.exp)]: true
									default: false
								}
							]
							if (!valid) {
								error("The choices from " + ref.name + " is not a subset of the choices from " +
									parameter.name, argument, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF,
									ARGUMENT_NOT_CHOICE)
							}
						}
					}
				} else {
					error("Argument must be a property value", argument, null, NOT_PROPERTY_VALUE)
				}
			}
		} else if (argument instanceof PropertyValue) {
			if (!paramChoices.empty && !paramChoices.exists[EcoreUtil.equals(it, argument.exp)]) {
				error("Argument is not one of the choices for " + parameter.name, argument, null, ARGUMENT_NOT_CHOICE)
			}
		}
	}

	def protected List<ConfigValue> getCandidates(ConfigParameter parameter) {
		switch choices : parameter.choices {
			CandidateList: choices.candidates
			default: emptyList
		}
	}

	@Check
	def void checkWithCycles(Configuration configuration) {
		configuration.eAllContents.filter(Combination).filter[!it.configuration.eIsProxy].forEach [ combination |
			val next = combination.configuration
			val visited = new ArrayDeque
			visited.push(configuration)
			visited.push(next)
			if (cycleExists(next, visited)) {
				error("The with hierarchy of " + next.name + " contains cycles", combination,
					ConfigPackage.Literals.COMBINATION__CONFIGURATION, WITH_CYCLES)
			}
		]
	}

	def protected boolean cycleExists(Configuration configuration, Deque<Configuration> visited) {
		configuration.eAllContents.filter(Combination).map[it.configuration].filter[!it.eIsProxy].exists [ next |
			visited.contains(next) || {
				visited.push(next)
				val nextHasCycle = cycleExists(next, visited)
				visited.pop
				nextHasCycle
			}
		]
	}

	@Check
	def void checkClassifierCycles(Configuration configuration) {
		val classifier = configuration.extended
		if (classifier !== null && !classifier.eIsProxy) {
			configuration.parameters.forEach [ parameter |
				val paramType = parameter.classifier
				if (paramType !== null && !paramType.eIsProxy) {
					if (AadlUtil.isSubClassifier(classifier, paramType)) {
						error("Parameter type cannot extend from " + classifier.getQualifiedName, parameter,
							ConfigPackage.Literals.CONFIG_PARAMETER__CLASSIFIER, CLASSIFIER_CYCLES)
					} else {
						val invalidChoices = parameter.candidates.filter(NamedElementRef).filter [ choice |
							val ref = choice.ref
							if (ref instanceof ComponentImplementation) {
								!ref.eIsProxy && AadlUtil.isSubClassifier(classifier, ref)
							} else {
								false
							}
						]
						invalidChoices.forEach [ choice |
							error("Parameter choice cannot extend from " + classifier.getQualifiedName, choice, null,
								CLASSIFIER_CYCLES)
						]
					}
				}
			]

			val assignments = configuration.eAllContents.filter(Assignment)
			val arguments = configuration.eAllContents.filter(Argument)
			val values = assignments.map[it.value] + arguments.map[it.value]
			val invalidValues = values.filter(NamedElementRef).filter [ value |
				val refClassifier = switch ref : value.ref {
					Classifier: ref
					Configuration: ref.extended
				}
				refClassifier !== null && !refClassifier.eIsProxy && AadlUtil.isSubClassifier(classifier, refClassifier)
			]
			invalidValues.forEach [ value |
				error("Value cannot extend from " + classifier.getQualifiedName, value,
					ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, CLASSIFIER_CYCLES)
			]
		}
	}

	@Check
	def void checkReferenceToSubcomponentArray(ElementRef ref) {
		val element = ref.element
		if (!element.eIsProxy && element instanceof Subcomponent && !(element as Subcomponent).arrayDimensions.empty) {
			error("Cannot refer to subcomponent array", ref, ConfigPackage.Literals.ELEMENT_REF__ELEMENT,
				SUBCOMPONENT_ARRAY)
		}
	}
	
	@Check
	def void checkUniqueAssignment(Configuration configuration) {
		checkUniqueAssignment(configuration.assignments)
	}
	
	@Check
	def void checkUniqueAssignment(NamedElementRef namedElementRef) {
		checkUniqueAssignment(namedElementRef.assignments)
	}
	
	@Check
	def void checkUniqueAssignment(List<Assignment> assignments) {
		val propertyAssignments = assignments.filter[!it.wildcard && it.property !== null && !it.property.eIsProxy]
		propertyAssignments.groupBy[it.property].values.filter[it.size > 1].flatten.forEach[assignment |
			error("Duplicate assignment for " + assignment.property.getQualifiedName, assignment, ConfigPackage.Literals.ASSIGNMENT__PROPERTY, DUPLICATE_ASSIGNMENT)
		]
		
		val refAssignments = assignments.filter[!it.wildcard && it.ref !== null]
		val firstRefs = refAssignments.map[assignment |
			var current = assignment.ref
			while (current.prev !== null) {
				current = current.prev
			}
			current
		]
		val groupedRefs = firstRefs.filter[!it.element.eIsProxy].groupBy[it.element]
		groupedRefs.values.filter[it.size > 1].flatten.forEach[elementRef |
			error("Duplicate assignment for " + elementRef.element.name, elementRef, null, DUPLICATE_ASSIGNMENT)
		]
	}
}
