/* 
 * OSATE2-GT
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
package org.osate.gtse.config.generator

import com.google.inject.Inject
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.serializer.ISerializer
import org.osate.aadl2.AadlBoolean
import org.osate.aadl2.AadlInteger
import org.osate.aadl2.AadlReal
import org.osate.aadl2.AadlString
import org.osate.aadl2.Classifier
import org.osate.aadl2.ComponentImplementation
import org.osate.aadl2.ComponentType
import org.osate.aadl2.Element
import org.osate.aadl2.FeatureGroup
import org.osate.aadl2.FeatureGroupType
import org.osate.aadl2.ListType
import org.osate.aadl2.NamedElement
import org.osate.aadl2.Property
import org.osate.aadl2.PropertyExpression
import org.osate.aadl2.ReferenceType
import org.osate.aadl2.ReferenceValue
import org.osate.aadl2.Subcomponent
import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType
import org.osate.atsv.integration.EngineConfigModel.ValuesModel
import org.osate.atsv.integration.exception.BadConfigurationException
import org.osate.atsv.integration.network.Limit
import org.osate.atsv.integration.preparser.EngineConfigGenerator
import org.osate.gtse.config.config.Argument
import org.osate.gtse.config.config.Assignment
import org.osate.gtse.config.config.CandidateList
import org.osate.gtse.config.config.Combination
import org.osate.gtse.config.config.ConfigElement
import org.osate.gtse.config.config.ConfigFactory
import org.osate.gtse.config.config.ConfigParameter
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.ConfigValue
import org.osate.gtse.config.config.Configuration
import org.osate.gtse.config.config.Constraint
import org.osate.gtse.config.config.ElementRef
import org.osate.gtse.config.config.NamedElementRef
import org.osate.gtse.config.config.NestedAssignments
import org.osate.gtse.config.config.OutputVariable
import org.osate.gtse.config.config.PropertyValue
import org.osate.gtse.config.config.Relation
import org.osate.gtse.config.config.SetValue
import org.osate.gtse.config.config.Type

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType
import static extension org.osate.gtse.config.config.AssignmentExt.*

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
// TODO
// - dotted name for config; configs are classifiers
// - check applicability of wildcards for subcomponents
// - add classifier option to lhs of assignments
// - add match mode selection (type only, classifier match, type extension, etc.
// - lots of validation
class ConfigGenerator extends AbstractGenerator {

	@Inject
	ISerializer serializer

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		val package = resource.contents.head as ConfigPkg
//		val text = mkString(package, makeMappings(package, package.root))
		callApi(package.root, makeMappings(package, package.root), package.outputs, package.analyses)
//		fsa.generateFile('paths.txt', text)
	}

	private dispatch def callApi(ComponentMapping m, EngineConfigGenerator ecg) {
		if (m.value instanceof NamedElementRef) {
			val ne = (m.value as NamedElementRef).ref
			if (ne instanceof ConfigParameter) {
				if (ne.choices === null) {
					throw new BadConfigurationException(m.mkString)
				}
				val vm = new ValuesModel((ne.choices as CandidateList).candidates.map[print].toArray(#[]))
				ecg.addChoicePointDefinition(m.path.tail.map[name].join('.'), ATSVVariableType.STRING, vm)
			}
		}
	}

	private dispatch def callApi(ConstraintMapping m, EngineConfigGenerator ecg) {
		val cond = m.constraint.condition
		val pathName = m.path.tail.map[name].join('.')
		val condLHS = cond.lhs
		val condRHS = cond.rhs
		val condRelat = cond.relation

		val topRelat = m.constraint.relation

		if (topRelat == Relation.RQ || topRelat == Relation.FB) {
			val cons = m.constraint.consequence
			val consLHSstr = prepend(pathName, cons.lhs.print)
			val consRHS = cons.rhs
			if (consRHS instanceof SetValue) {
				if (topRelat == Relation.RQ) {
					ecg.addMembershipConstraint(condLHS.print, condRHS.print, consLHSstr, consRHS.elements.map[print])
				} else if (topRelat == Relation.FB) {
					ecg.addExclusionConstraint(condLHS.print, condRHS.print, consLHSstr, consRHS.elements.map[print])
				}
			} else {
				if (topRelat == Relation.RQ) {
					ecg.addRequiresConstraint(condLHS.print, condRHS.print, consLHSstr, consRHS.print)
				} else if (topRelat == Relation.FB) {
					ecg.addForbidsConstraint(condLHS.print, condRHS.print, consLHSstr, consRHS.print)
				}
			}
		} else if (condRelat == Relation.EQ) {
			ecg.addEqualityConstraint(prepend(pathName, condLHS.print), prepend(pathName, condRHS.print))
		} else if (condRelat == Relation.NEQ) {
			ecg.addUniquenessConstraint(prepend(pathName, condLHS.print), prepend(pathName, condRHS.print))
		}
	}

	private def prepend(String prefix, String str) {
		if (prefix === null || prefix.isEmpty)
			str
		else
			prefix + '.' + str
	}

	private dispatch def callApi(PropertyMapping m, EngineConfigGenerator ecg) {
		val tl = m.path.tail
		val pathName = tl.map[name].join('.')
		// TODO: handle parameter as value
		if (m.property.propertyType instanceof ReferenceType) {
			// Hack, part 1, for reference property
			val s = 'RefPropertyValue-' + pathName + '-' + m.property.print + '=' + serializer.serialize(m.value).trim // FIXME
			ecg.addChoicePointDefinition(pathName, ATSVVariableType.STRING, new ValuesModel)
		} else if (m.property.propertyType instanceof ListType &&
			(m.property.propertyType as ListType).elementType instanceof ReferenceType) {
			// Hack, part 2, for reference property
			val pv = m.value as PropertyValue
			val a = pv.getContainerOfType(Assignment)
			var ref = a.ref
			// reference value is relative to assignment's container
			var refPath = pathName
			while (ref !== null && refPath.endsWith(ref.element.name)) {
				refPath = refPath.substring(0, refPath.length - ref.element.name.length - 1)
				ref = ref.prev
			}
			val s = 'RefPropertyValue-' + pathName + '-' + m.property.print + '=' +
				prepend(refPath, serializer.serialize((pv.exp as ReferenceValue).path).trim)
			ecg.addChoicePointDefinition(pathName, ATSVVariableType.STRING, new ValuesModel)
			ecg.addChoicePointDefinition(pathName, ATSVVariableType.REFERENCE,
				new ValuesModel(serializer.serialize((pv.exp as ReferenceValue).path).trim))
		} else {
//			val s = 'LitPropertyValue-' + pathName + '-' + m.property.print + '-' + propertyType(m.property) + '=' +
//				serializer.serialize(m.value).trim
			ecg.addChoicePointDefinition(
				pathName,
				ATSVVariableType.getTypeByName(propertyType(m.property)),
				new ValuesModel(serializer.serialize(m.value).trim.replaceAll("\\D*", "").split(","))
			)
		}
	}

	private dispatch def callApi(AbstractMapping m, EngineConfigGenerator ecg) {
		// TODO: Should throw an error
	}

	private def callApiOutput(OutputVariable o, EngineConfigGenerator ecg) {
		var type = ATSVVariableType.getTypeByName(o.type.print)
		if (o.limit === null) {
			ecg.addOutputVariable(o.name, type, null);
		} else {
			ecg.addOutputVariable(o.name, type, new Limit(o.limit.relation.print, o.limit.bound.print));
		}
	}

	private def callApi(Configuration rootConfig, Iterable<AbstractMapping> choicepointIter,
		List<OutputVariable> outputs, List<String> analyses) {
		val ecg = new EngineConfigGenerator()
		ecg.initializeFields
		val s = rootConfig.print
		ecg.setPackageAndComponentName(rootConfig.print)
		choicepointIter.forEach[callApi(ecg)]
		outputs.forEach[callApiOutput(ecg)]
		ecg.addAnalyses(analyses.join(','))
		ecg.execute()
	}

	def makeMappings(ConfigPkg pkg, Configuration rootConfig) {
		val rootComp = rootConfig.extended
		val lookup = makeLookup(rootConfig)
		val arguments = makeArgumentList(rootConfig.combined, newHashMap)
		val mappings = if(rootComp === null) #[] else process(rootComp, lookup, arguments)
		mappings
	}

	static private def Iterable<Pair<ElementRef, Assignment>> makeLookup(Configuration cfg) {
		val ner = ConfigFactory.eINSTANCE.createNamedElementRef
		ner.ref = cfg
		val a = ConfigFactory.eINSTANCE.createAssignment
		a.value = ner
		#[null -> a]
	}

	static private def propertyType(Property p) {
		switch p.propertyType {
			AadlInteger: 'int'
			AadlBoolean: 'bool'
			AadlString: 'string'
			AadlReal: 'float'
			default: ''
		}
	}

	def Iterable<AbstractMapping> process(NamedElement ne, Iterable<Pair<ElementRef, Assignment>> lookup,
		Map<ConfigParameter, ConfigValue> arguments) {
		val downAssignment = lookup.findFirst[key === null && !value.isProperty]?.value
		val localResult = makePropertyMappings(ne, lookup) + {
			if (ne instanceof Configuration)
				makeLocalPropertyMappings(ne, ne.assignments)
			else
				#[]
		}
		if (downAssignment === null)
			// no applicable entry found, continue
			localResult + continueDown(ne, ne.classifier, lookup, arguments)
		else {
			val nestedAssignments = switch value: downAssignment.value {
				NamedElementRef: value.assignments
				NestedAssignments: value.assignments
				default: #[]
			}
			val propertiesFromNested = makeLocalPropertyMappings(ne, nestedAssignments)
			val nestedConstraints = switch value: downAssignment.value {
				NamedElementRef: value.constraints
				NestedAssignments: value.constraints
				default: #[]
			}
			val constraintsFromNested = makeLocalConstraintMappings(ne, nestedConstraints)
			propertiesFromNested + constraintsFromNested + localResult +
				handleAssignedValue(ne, downAssignment.value, lookup, arguments)
		}
	}

	def Iterable<AbstractMapping> handleAssignedValue(NamedElement ne, ConfigValue cfgValue,
		Iterable<Pair<ElementRef, Assignment>> lookup, Map<ConfigParameter, ConfigValue> arguments) {
		switch value : cfgValue {
			NamedElementRef: {
				// lookups generated from assignments in '{' ... '}'
				val localLookup = makeLocalLookupList(value.assignments)
				switch obj: value.ref {
					Classifier: {
						val withLookup = makeLookupList(value.combined)
						val args = makeArgumentList(value.combined, arguments)
						val m = makeComponentMapping(ne, obj, cfgValue)
						val propertiesFromWith = makePropertyMappings(ne, value.combined)
						val constraintsFromWith = makeConstraintMappings(ne, value.combined)
						m + propertiesFromWith + constraintsFromWith +
							continueDown(ne, obj, localLookup + withLookup + lookup, args)
					}
					Configuration: {
						val withLookup = makeLookupList(obj, value.combined)
						val args = makeArgumentList(value.arguments, value.combined, arguments)
						val m = makeComponentMapping(ne, obj, cfgValue)
						val propertiesFromConfiguration = makePropertyMappings(ne, obj, value.combined)
						val constraintsFromConfiguration = makeConstraintMappings(ne, obj, value.combined)
						m + propertiesFromConfiguration + constraintsFromConfiguration +
							continueDown(ne, obj.extended, localLookup + withLookup + lookup, args)
					}
					ConfigParameter: {
						val withLookup = makeLookupList(value.combined)
						val args = makeArgumentList(value.combined, arguments)
						val propertiesFromWith = makePropertyMappings(ne, value.combined)
						val constraintsFromWith = makeConstraintMappings(ne, value.combined)
						var argValue = arguments.get(obj)
						while (argValue instanceof ConfigParameter) {
							argValue = arguments.get(argValue)
						}

						if (argValue === null)
							#[new ComponentMapping(#[ne], cfgValue)] + propertiesFromWith + constraintsFromWith +
								continueDown(ne, obj.classifier, localLookup + withLookup + lookup, args)
						else {
							// FIXME what if arg is config and there's a with clause
							propertiesFromWith + constraintsFromWith +
								handleAssignedValue(ne, argValue, localLookup + withLookup + lookup, args)
						}
					}
					default:
						// Case missing in the implementation
						throw new RuntimeException("unexpected named element ref in rhs of assignment")
				}
			}
			NestedAssignments: {
				val localLookup = makeLocalLookupList(value.assignments)
				continueDown(ne, ne.classifier, localLookup + lookup, arguments)
			}
			default:
				// Case missing in the implementation
				throw new RuntimeException("unexpected rhs of assignment")
		}

	}

	def Iterable<AbstractMapping> continueDown(NamedElement current, Classifier cl,
		Iterable<Pair<ElementRef, Assignment>> lookup, Map<ConfigParameter, ConfigValue> arguments) {
		if (cl === null)
			#[]
		else {
			val replacements = cl.allNamedElements.map [ ne |
				val result = process(ne, lookup.specialize(ne), arguments)
				result
			].flatten
			replacements.map[prepend(current)]
		}
	}

	def static makePropertyMappings(NamedElement ne, Iterable<Pair<ElementRef, Assignment>> lookup) {
		lookup.filter[key === null && value.isProperty].map [
			new PropertyMapping(#[ne], value.property, value.value)
		]
	}

	def static makeLocalPropertyMappings(NamedElement ne, Iterable<Assignment> assignments) {
		assignments.filter[ref === null && isProperty].map [
			new PropertyMapping(#[ne], property, value)
		]
	}

	def static makePropertyMappings(NamedElement ne, List<Combination> combs) {
		if (combs === null)
			#[]
		else
			linearize(combs.map[configuration]).map[makeLocalPropertyMappings(ne, assignments)].flatten
	}

	def static makePropertyMappings(NamedElement ne, Configuration cfg, List<Combination> combs) {
		val cfgs = if(combs === null) #[cfg] else #[cfg] + combs.map[configuration]
		linearize(cfgs).map[makeLocalPropertyMappings(ne, assignments)].flatten
	}

	def static makeLocalConstraintMappings(NamedElement ne, Iterable<Constraint> constraints) {
		constraints.map [
			new ConstraintMapping(#[ne], it)
		]
	}

	def static makeConstraintMappings(NamedElement ne, List<Combination> combs) {
		if (combs === null)
			#[]
		else
			linearize(combs.map[configuration]).map[makeLocalConstraintMappings(ne, constraints)].flatten
	}

	def static makeConstraintMappings(NamedElement ne, Configuration cfg, List<Combination> combs) {
		val cfgs = if(combs === null) #[cfg] else #[cfg] + combs.map[configuration]
		linearize(cfgs).map[makeLocalConstraintMappings(ne, constraints)].flatten
	}

	def private static makeComponentMapping(NamedElement ne, Configuration cfg, ConfigValue cfgValue) {
		makeComponentMapping(ne, cfg.extended, cfgValue)
	}

	def private static makeComponentMapping(NamedElement ne, Classifier cl, ConfigValue cfgValue) {
		if (ne instanceof Classifier || ne.classifier != cl)
			#[new ComponentMapping(#[ne], cfgValue)]
		else
			#[]
	}

	protected dispatch static def getClassifier(NamedElement ne) {
		null
	}

	protected dispatch static def getClassifier(Subcomponent s) {
		s.allClassifier
	}

	protected dispatch static def getClassifier(FeatureGroup fg) {
		fg.allFeatureGroupType
	}

	/**
	 * Create an assignment lookup list from the given configuration
	 */
	def Iterable<Pair<ElementRef, Assignment>> makeLookupList(Configuration cfg) {
		linearize(cfg).map[makeLocalLookupList(assignments)].flatten
	}

	def Iterable<Pair<ElementRef, Assignment>> makeLookupList(List<Combination> combs) {
		if (combs === null)
			#[]
		else
			linearize(combs.map[configuration]).map[makeLocalLookupList(assignments)].flatten
	}

	def Iterable<Pair<ElementRef, Assignment>> makeLookupList(Configuration cfg, List<Combination> combs) {
		val cfgs = if(combs === null) #[cfg] else #[cfg] + combs.map[configuration]
		linearize(cfgs).map[makeLocalLookupList(assignments)].flatten
	}

	def List<Configuration> linearize(Configuration cfg) {
		val acc = newLinkedHashSet()
		linHelper(cfg, acc)
		acc.toList.reverseView
	}

	def static List<Configuration> linearize(Iterable<Configuration> cfgs) {
		val acc = newLinkedHashSet()
		cfgs?.forEach[linHelper(it, acc)]
		acc.toList.reverseView
	}

	def static void linHelper(Configuration config, HashSet<Configuration> acc) {
		val cfgs = config.combined.map[configuration]
		cfgs.forEach[linHelper(it, acc)]
		acc.add(config)
	}

	protected def Iterable<Pair<ElementRef, Assignment>> makeLocalLookupList(Iterable<Assignment> lookup) {
		if(lookup === null) #[] else lookup.map[mkPair]
	}

	protected def mkPair(Assignment a) {
		val startRef = {
			var r = a.ref
			while (r?.prev !== null)
				r = r.prev
			r
		}
		(startRef -> a)
	}

	/**
	 * Create a parameter lookup list from the given configuration
	 * Keep last argument found via DFS. Multiple arguments for the parameter are not allowed unless they are identical.
	 */
	def Map<ConfigParameter, ConfigValue> makeArgumentList(List<Argument> localArgs, List<Combination> combs,
		Map<ConfigParameter, ConfigValue> arguments) {
		val args = new HashMap(arguments)
		localArgs.forEach[args.put(parameter, value)]
		argsHelper(combs, args)
		args
	}

	def Map<ConfigParameter, ConfigValue> makeArgumentList(List<Combination> combs,
		Map<ConfigParameter, ConfigValue> arguments) {
		val args = new HashMap(arguments)
		argsHelper(combs, args)
		args
	}

	private def void argsHelper(Iterable<Combination> combs, Map<ConfigParameter, ConfigValue> args) {
		combs.forEach [ c |
			c.arguments.forEach[args.put(parameter, value)]
			argsHelper(c.configuration.combined, args)
		]
	}

	/**
	 * Filter lookup list by current named element and strip the current element from the keys of the remaining elements
	 */
	protected def List<Pair<ElementRef, Assignment>> specialize(Iterable<Pair<ElementRef, Assignment>> lookup,
		NamedElement ne) {
		if (lookup.empty)
			new ArrayList
		else {
			val result = specialize(lookup.tail, ne)
			val first = lookup.head
			val ref = first.key
			val a = first.value

			if (a.wildcard) {
				// keep all wild cards
				result.add(0, ref -> a)
			}

			if (ref !== null && ref.element == ne) {
				// filter entries that match this element
				if (ref == a.ref) {
					if (propertyApplies(a, ne))
						// key = null indicates that the value applies to this element
						result.add(0, null -> a)
				} else {
					// calculate the opposite of prev here, reference is unidirectional
					var r = a.ref
					while (r !== null) {
						if (r.prev === ref) {
							result.add(0, r -> a)
							r = null
						} else {
							r = r.prev
						}
					}
				}

			}
			result
		}
	}

	protected static def propertyApplies(Assignment a, NamedElement ne) {
		!a.isProperty || ne.acceptsProperty(a.property)
	}

	protected dispatch def Iterable<NamedElement> allNamedElements(ComponentType t) {
		t.allFeatures + t.allFlowSpecifications + t.allModes + t.allModeTransitions + t.allPrototypes
	}

	protected dispatch def Iterable<NamedElement> allNamedElements(ComponentImplementation i) {
		i.allModes + i.allModeTransitions + i.allPrototypes + i.allConnections + i.allEndToEndFlows + i.allFeatures +
			i.type.allFlowSpecifications + i.allModes + i.allModeTransitions + i.allSubcomponents
	}

	protected dispatch def Iterable<NamedElement> allNamedElements(FeatureGroupType t) {
		#[] + t.allFeatures
	}

	protected dispatch def Iterable<NamedElement> allNamedElements(Element e) {
		#[]
	}

	def String mkString(ConfigPkg pkg, Iterable<AbstractMapping> mappings) {
		{
			if (pkg.analyses.empty)
				''
			else
				'Analyses=' + pkg.analyses.join(',')
		} + '\n\n' + {
			mappings.filter(ComponentMapping).map[mkString].join('\n')
		} + '\n\n' + {
			mappings.filter(ConstraintMapping).map[mkString].join('\n')
		} + '\n\n' + {
			if (pkg.outputs.empty)
				''
			else
				pkg.outputs.map [ o |
					val tn = o.type.toString
					#['Output', o.name, Character.toUpperCase(tn.charAt(0)) + tn.substring(1)].join('-') + {
						if (o.limit !== null) {
							val bound = serializer.serialize(o.limit.bound).trim
							val str = if(bound.startsWith("'")) bound.substring(1, bound.length - 1) else bound
							'=' + o.limit.relation.print + '-' + str
						} else
							''
					}
				].join('\n')
		}
	}

	dispatch def mkString(AbstractMapping m) {
		''
	}

	dispatch def mkString(ComponentMapping m) {
		val tl = m.path.tail
		val pathName = tl.map[name].join('.')
		if (tl.empty) {
			'componentImplementationName=' + m.value.print + '\n'
		} else {
			'SubcompChoice-' + pathName + '=' + m.value.print
		}
	}

	dispatch def mkString(ConstraintMapping m) {
		val tl = m.path.tail
		val pathName = tl.map[name].join('.')
		if (m.constraint.consequence === null) {
			val cond = m.constraint.condition
			val r = cond.relation.print
			val left = pathName + '.' + cond.lhs.print
			val right = pathName + '.' + cond.rhs.print
			r + 'Configurator-' + left + '=' + right
		} else {
			'#unprocessed constraint for ' + m.path.map[name].join('.')
		}

	}

	dispatch def mkString(PropertyMapping m) {
		val tl = m.path.tail
		val pathName = tl.map[name].join('.')
		// TODO: handle parameter as value
		if (m.property.propertyType instanceof ReferenceType) {
			'RefPropertyValue-' + pathName + '-' + m.property.print + '=' + serializer.serialize(m.value).trim // FIXME
		} else if (m.property.propertyType instanceof ListType &&
			(m.property.propertyType as ListType).elementType instanceof ReferenceType) {
			val pv = m.value as PropertyValue
			val a = pv.getContainerOfType(Assignment)
			var ref = a.ref
			// reference value is relative to assignment's container
			var refPath = pathName
			while (ref !== null && refPath.endsWith(ref.element.name)) {
				refPath = refPath.substring(0, refPath.length - ref.element.name.length - 1)
				ref = ref.prev
			}
			'RefPropertyValue-' + pathName + '-' + m.property.print + '=' + refPath + '.' +
				serializer.serialize((pv.exp as ReferenceValue).path).trim
		} else {
			'LitPropertyValue-' + pathName + '-' + m.property.print + '-' + propertyType(m.property) + '=' +
				serializer.serialize(m.value).trim
		}
	}

	/**
	 * Print configuration value
	 */
	dispatch def String print(ConfigValue value) {
		'unhandled dispatch case print (' + value.class.name + ')'
	}

	dispatch def String print(CandidateList l) {
		l.candidates.map[print].join(',')
	}

	dispatch def String print(NamedElementRef ner) {
		ner.ref.print
	}

	dispatch def String print(PropertyExpression prex) {
		serializer.serialize(prex).trim
	}

	dispatch def String print(PropertyValue pv) {
		serializer.serialize(pv)
	}

	/**
	 * Print named element
	 */
	dispatch def String print(NamedElement ne) {
		ne.name
	}

	dispatch def String print(Classifier cl) {
		cl.qualifiedName()
	}

	dispatch def String print(Configuration cfg) {
		cfg.extended.print
	}

	dispatch def String print(Property p) {
		p.qualifiedName().replaceAll(':', '\\\\:')
	}

	dispatch def String print(ConfigParameter p) {
		val ch = p.choices
		if (ch === null)
			'#no choices given'
		else
			ch.print
	}

	dispatch def String print(Type t) {
		t.getName()
	}

	dispatch def String print(Relation r) {
		switch r {
			case EQ: 'Eq'
			case NEQ: 'Neq'
			case GT: 'gt'
			case GTE: 'gte'
			case LT: 'lt'
			case LTE: 'lte'
			case FB: 'forbids'
			case RQ: 'requires'
		}
	}

	dispatch def String print(ConfigElement e) {
		e.element.print + {
			if (e.property === null)
				''
			else
				'-' + e.property.print
		}
	}

	dispatch def String print(ElementRef r) {
		if (r === null)
			''
		else if (r.prev === null)
			r.element.name
		else
			r.prev.print + '.' + r.element.name
	}
}
