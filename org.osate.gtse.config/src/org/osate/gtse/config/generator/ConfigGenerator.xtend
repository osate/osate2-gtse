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
import org.eclipse.xtend.lib.annotations.Accessors
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
import org.osate.aadl2.ReferenceType
import org.osate.aadl2.ReferenceValue
import org.osate.aadl2.Subcomponent
import org.osate.gtse.config.config.Argument
import org.osate.gtse.config.config.Assignment
import org.osate.gtse.config.config.CandidateList
import org.osate.gtse.config.config.Combination
import org.osate.gtse.config.config.ConfigFactory
import org.osate.gtse.config.config.ConfigParameter
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.ConfigValue
import org.osate.gtse.config.config.Configuration
import org.osate.gtse.config.config.ElementRef
import org.osate.gtse.config.config.NamedElementRef
import org.osate.gtse.config.config.NestedAssignments
import org.osate.gtse.config.config.PropertyValue
import org.osate.gtse.config.config.Relation

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

	static class Mapping {
		
		@Accessors
		Iterable<NamedElement> path = #[]

		@Accessors
		Property property

		@Accessors
		ConfigValue value

		new(List<NamedElement> p, ConfigValue v) {
			path = p
			value = v
		}

		new(List<NamedElement> p, Property prop, ConfigValue v) {
			path = p
			property = prop
			value = v
		}

		def isSubcomponentMapping() {
			property === null
		}

		def isPropertyMapping() {
			property !== null
		}

		def prepend(NamedElement element) {
			path = #[element] + path
			this
		}

		def pathName() {
			path.tail.map[name].join('.')
		}
		
		def propertyName() {
			if (isPropertyMapping) property.name else ''
		}
		
		def valueString() {
			value.print
		}
	}

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		val package = resource.contents.head as ConfigPkg
		val text = mkString(package, makeMappings(package, package.root))
		fsa.generateFile('paths.txt', text)
	}

	def makeMappings(ConfigPkg pkg, Configuration rootConfig) {
		val rootComp = rootConfig.extended
		val lookup = makeLookup(rootConfig)
		val arguments = makeArgumentList(rootConfig.combined, newHashMap)
		val replacements = if(rootComp === null) #[] else process(rootComp, lookup, arguments)
		replacements
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

	def Iterable<Mapping> process(NamedElement ne, Iterable<Pair<ElementRef, Assignment>> lookup,
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
			propertiesFromNested + localResult + handleAssignedValue(ne, downAssignment.value, lookup, arguments)
		}
	}

	def Iterable<Mapping> handleAssignedValue(NamedElement ne, ConfigValue cfgValue,
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
						m + propertiesFromWith + continueDown(ne, obj, localLookup + withLookup + lookup, args)
					}
					Configuration: {
						val withLookup = makeLookupList(obj, value.combined)
						val args = makeArgumentList(value.arguments, value.combined, arguments)
						val m = makeComponentMapping(ne, obj, cfgValue)
						val propertiesFromConfiguration = makeLocalPropertyMappings(ne, obj.assignments)
						val propertiesFromWith = makePropertyMappings(ne, obj, value.combined)
						m + propertiesFromConfiguration + propertiesFromWith +
							continueDown(ne, obj.extended, localLookup + withLookup + lookup, args)
					}
					ConfigParameter: {
						val withLookup = makeLookupList(value.combined)
						val args = makeArgumentList(value.combined, arguments)
						val propertiesFromWith = makePropertyMappings(ne, value.combined)
						var argValue = arguments.get(obj)
						while (argValue instanceof ConfigParameter) {
							argValue = arguments.get(argValue)
						}

						if (argValue === null)
							#[new Mapping(#[ne], cfgValue)] + propertiesFromWith +
								continueDown(ne, obj.classifier, localLookup + withLookup + lookup, args)
						else {
							propertiesFromWith +
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

	def Iterable<Mapping> continueDown(NamedElement current, Classifier cl,
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
			new Mapping(#[ne], value.property, value.value)
		]
	}

	def static makeLocalPropertyMappings(NamedElement ne, Iterable<Assignment> assignments) {
		assignments.filter[ref === null && isProperty].map [
			new Mapping(#[ne], property, value)
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

	def private static makeComponentMapping(NamedElement ne, Configuration cfg, ConfigValue cfgValue) {
		makeComponentMapping(ne, cfg.extended, cfgValue)
	}

	def private static makeComponentMapping(NamedElement ne, Classifier cl, ConfigValue cfgValue) {
		if (ne instanceof Classifier || ne.classifier != cl)
			#[new Mapping(#[ne], cfgValue)]
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
	def Map<ConfigParameter, ConfigValue> makeArgumentList(List<Argument>localArgs, List<Combination> combs,
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
			c.arguments.forEach [args.put(parameter, value)]
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
		!a.isProperty || {
			val pv = a.value
			pv instanceof PropertyValue && ne.acceptsProperty(a.property)
		}
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

	def String mkString(ConfigPkg pkg, Iterable<Mapping> replacements) {
		{
			if (pkg.analyses.empty)
				''
			else
				'Analyses=' + pkg.analyses.join(',')
		} + '\n\n' + {
			replacements.map [ r |
				val tl = r.path.tail
				val pathName = tl.map[name].join('.')
				if (r.isSubcomponentMapping) {
					if (tl.empty) {
						'componentImplementationName=' + r.value.print + '\n'
					} else {
						'SubcompChoice-' + pathName + '=' + r.value.print
					}
				} else if (r.isPropertyMapping) {
					// TODO: handle parameter as value
					if (r.property.propertyType instanceof ReferenceType) {
						'RefPropertyValue-' + pathName + '-' + r.property.print + '=' +
							serializer.serialize(r.value).trim // FIXME
					} else if (r.property.propertyType instanceof ListType &&
						(r.property.propertyType as ListType).elementType instanceof ReferenceType) {
						val pv = r.value as PropertyValue
						val a = pv.getContainerOfType(Assignment)
						var ref = a.ref
						// reference value is relative to assignment's container
						var refPath = pathName
						while (ref !== null && refPath.endsWith(ref.element.name)) {
							refPath = refPath.substring(0, refPath.length - ref.element.name.length - 1)
							ref = ref.prev
						}
						'RefPropertyValue-' + pathName + '-' + r.property.print + '=' + refPath + '.' +
							serializer.serialize((pv.exp as ReferenceValue).path).trim
					} else {
						'LitPropertyValue-' + pathName + '-' + r.property.print + '-' + propertyType(r.property) + '=' +
							serializer.serialize(r.value).trim
					}
				}
			].join('\n')
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

	/**
	 * Print configuration value
	 */
	static dispatch def String print(ConfigValue value) {
		'unhandled dispatch case print (' + value.class.name + ')'
	}

	static dispatch def String print(CandidateList l) {
		l.candidates.map[print].join(',')
	}

	static dispatch def String print(NamedElementRef ner) {
		ner.ref.print
	}

	static dispatch def String print(PropertyValue pv) {
		'PROPERTY VALUE'
	}

	/**
	 * Print named element
	 */
	static dispatch def String print(NamedElement ne) {
		ne.name
	}

	static dispatch def String print(Classifier cl) {
		cl.qualifiedName()
	}

	static dispatch def String print(Configuration cfg) {
		cfg.extended.print
	}

	static dispatch def String print(Property p) {
		p.qualifiedName().replaceAll(':', '\\\\:')
	}

	static dispatch def String print(ConfigParameter p) {
		val ch = p.choices
		if (ch === null)
			'#no choices given'
		else
			ch.print
	}

	static dispatch def String print(Relation r) {
		switch r {
			case EQ: 'eq'
			case NEQ: 'neq'
			case GT: 'gt'
			case GTE: 'gte'
			case LT: 'lt'
			case LTE: 'lte'
		}
	}
}
