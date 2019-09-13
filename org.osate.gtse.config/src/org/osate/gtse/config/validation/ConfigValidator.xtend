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

import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.validation.Check
import org.osate.aadl2.Classifier
import org.osate.aadl2.ComponentCategory
import org.osate.aadl2.ComponentClassifier
import org.osate.aadl2.ComponentImplementation
import org.osate.aadl2.ComponentType
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
	public static val PARAMETER_NOT_CLASSIFIER = 'parameterNotClassifier'
	public static val PARAMETER_NOT_PROPERTY = 'parameterNotProperty'

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
						switch classifier {
							ComponentType case !AadlUtil.isSameOrExtends(classifier, candidateRef.type): {
								error('''The type of «candidateRef.getQualifiedName» is not an extension of «classifier.getQualifiedName»''',
									candidate, null, NOT_EXTENSION)
							}
							ComponentImplementation case !AadlUtil.isSameOrExtends(classifier, candidateRef): {
								error(candidateRef.getQualifiedName + " is not an extension of " +
									classifier.getQualifiedName, candidate, null, NOT_EXTENSION)
							}
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
	
	/*
	 * TODO Refactor this method
	 * 
	 * This method is ugly, unformatted, and poorly structured. It first looks at the type of the argument and then
	 * checks if the parameter is the right type. The check should be the other way around as well as the error
	 * messages. This is being committed in order to have a working commit to revert to as I refactor.
	 */
	@Check
	def void typeCheckArgument(Argument argument) {
		val parameter = argument.parameter
		if (parameter !== null && !parameter.eIsProxy && (parameter.classifier === null || !parameter.classifier.eIsProxy)) {
			val value = argument.value
			if (value instanceof NamedElementRef) {
				val ref = value.ref
				if (!ref.eIsProxy) {
					if (ref instanceof ComponentClassifier) {
						val choices = parameter.choices
						if (choices instanceof CandidateList) {
							val valid = choices.candidates.filter(NamedElementRef).map[it.ref].filter(Classifier).exists[candidate |
								AadlUtil.isSubClassifier(candidate, ref)
							]
							if (!valid) {
								error("Argument is not one of the choices for " + parameter.name, value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, ARGUMENT_NOT_CHOICE)
							}
						} else if (parameter.classifier === null) {
							error(parameter.name + " is not a classifier parameter", value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, PARAMETER_NOT_CLASSIFIER)
						} else if (!AadlUtil.isSubClassifier(parameter.classifier, ref)) {
							error(ref.getQualifiedName + " does not extend from " + parameter.classifier.getQualifiedName, value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
						}
					} else if (ref instanceof Configuration) {
						if (parameter.classifier === null) {
							error(parameter.name + " is not a classifier parameter", value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, PARAMETER_NOT_CLASSIFIER)
						} else if (ref.extended === null || !AadlUtil.isSubClassifier(parameter.classifier, ref.extended)) {
							error(ref.name + " does not extend from " + parameter.classifier.getQualifiedName, value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
						}
					} else if (ref instanceof ConfigParameter) {
						if (ref.classifier !== null && !ref.classifier.eIsProxy) {
							val refChoices = ref.choices
							if (refChoices instanceof CandidateList) {
								val paramChoices = parameter.choices
								if (paramChoices instanceof CandidateList) {
									val valid = refChoices.candidates.forall[argCandidate |
										if (argCandidate instanceof NamedElementRef) {
											val argCandidateRef = argCandidate.ref
											if (argCandidateRef.eIsProxy) {
												true
											} else if (argCandidateRef instanceof Classifier) {
												paramChoices.candidates.filter(NamedElementRef).map[it.ref].filter(Classifier).exists[paramCandidate | AadlUtil.isSubClassifier(paramCandidate, argCandidateRef)]
											} else {
												false
											}
										} else {
											false
										}
									]
									if (!valid) {
										error("Not all of the choices of " + ref.name + " extend from any choices of " + parameter.name, value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
									}
								} else {
									val valid = refChoices.candidates.forall[argCandidate |
										if (argCandidate instanceof NamedElementRef) {
											val argCandidateRef = argCandidate.ref
											if (argCandidateRef.eIsProxy) {
												true
											} else if (argCandidateRef instanceof Classifier) {
												AadlUtil.isSubClassifier(parameter.classifier, argCandidateRef)
											} else {
												false
											}
										} else {
											false
										}
									]
									if (!valid) {
										error("Not all of the choices of " + ref.name + " extend from " + parameter.classifier.getQualifiedName, value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
									}
								}
							} else {
								val paramChoices = parameter.choices
								if (paramChoices instanceof CandidateList) {
									val valid = paramChoices.candidates.filter(NamedElementRef).map[it.ref].filter(Classifier).exists[candidate | AadlUtil.isSubClassifier(candidate, ref.classifier)]
									if (!valid) {
										error(ref.classifier.getQualifiedName + " does not extend from any choices of " + parameter.name, value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
									}
								} else {
									if (!AadlUtil.isSubClassifier(parameter.classifier, ref.classifier)) {
										error(ref.classifier.getQualifiedName + " does not extend from " + parameter.classifier.getQualifiedName, value, ConfigPackage.Literals.NAMED_ELEMENT_REF__REF, NOT_EXTENSION)
									}
								}
							}
						} else if (ref.propertyType !== null) {
							val refChoices = ref.choices
							if (refChoices instanceof CandidateList) {
								val paramChoices = parameter.choices
								if (paramChoices instanceof CandidateList) {
									val valid = refChoices.candidates.forall[refCandidate |
										if (refCandidate instanceof PropertyValue) {
											paramChoices.candidates.filter(PropertyValue).exists[paramCandidate | EcoreUtil.equals(paramCandidate.exp, refCandidate.exp)]
										} else {
											false
										}
									]
									if (!valid) {
										error("The choices from " + ref.name + " is not a subset of the choices from " + parameter.name, value, null, ARGUMENT_NOT_CHOICE)
									}
								} else {
									if (parameter.propertyType === null) {
										error(parameter.name + " is not a property parameter", value, null, PARAMETER_NOT_PROPERTY)
									}
								}
							} else {
								val paramChoices = parameter.choices
								if (paramChoices instanceof CandidateList) {
									error(parameter.name + " has choices, but " + ref.name + " does not", value, null, ARGUMENT_NOT_CHOICE)
								} else {
									if (parameter.propertyType === null) {
										error(parameter.name + " is not a property parameter", value, null, PARAMETER_NOT_PROPERTY)
									}
								}
							}
						}
					}
				}
			} else if (value instanceof PropertyValue) {
				val choices = parameter.choices
				if (choices instanceof CandidateList) {
					if (!choices.candidates.filter(PropertyValue).exists[candidate | EcoreUtil.equals(candidate.exp, value.exp)]) {
						error("Argument is not one of the choices for " + parameter.name, value, null, ARGUMENT_NOT_CHOICE)
					}
				} else if (parameter.propertyType === null) {
					// TODO Check that the property types match
					error(parameter.name + " is not a property parameter", value, null, PARAMETER_NOT_PROPERTY)
				}
			}
		}
	}
}
