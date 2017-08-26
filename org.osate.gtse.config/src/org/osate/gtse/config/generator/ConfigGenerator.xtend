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

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.osate.aadl2.Classifier
import org.osate.aadl2.ComponentImplementation
import org.osate.aadl2.ComponentType
import org.osate.aadl2.Element
import org.osate.aadl2.FeatureGroup
import org.osate.aadl2.FeatureGroupType
import org.osate.aadl2.NamedElement
import org.osate.aadl2.Subcomponent
import org.osate.gtse.config.config.Assignment
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.config.Configuration
import org.osate.gtse.config.config.ElementRef

import static extension org.osate.gtse.config.config.AssignmentExt.*
import static extension org.osate.gtse.config.config.ConfigurationExt.*

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class ConfigGenerator extends AbstractGenerator {

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
//		fsa.generateFile('greetings.txt', 'People to greet: ' + 
//			resource.allContents
//				.filter(Greeting)
//				.map[name]
//				.join(', '))
		val rootConfig = (resource.contents.head as ConfigPkg).root
		val rootComp = rootConfig.extended
		val lookup = makeLookupList(rootConfig)
		val paths = rootComp.process(lookup)
		fsa.generateFile('paths.txt',
			paths.map[key].map[map[if (it === null) "NULL" else it.name].join('.')].join('\n'))

	}

	def Iterable<Pair<List<NamedElement>, Assignment>> process(Classifier cl,
		List<Pair<ElementRef, Assignment>> lookup) {
		cl.allNamedElements.map [ ne |
			val result = ne.process(lookup.specialize(ne))
			result
		].flatten
	}

	def Iterable<Pair<List<NamedElement>, Assignment>> process(NamedElement ne,
		List<Pair<ElementRef, Assignment>> lookup) {
		val assignment = lookup.findFirst[p|p.key === null]?.value
		if (assignment === null) {
			// no applicable entry found, continue
			val cl = ne.classifier
			val replacements = cl.process(lookup)
			replacements.map [ p |
				p.key.add(0, ne)
				p
			]
		} else if (assignment.isProperty) {
			#[newLinkedList(ne) -> assignment]
		} else {
			switch rhs : assignment.value {
				
			}
			
		}
	}

	protected dispatch def getClassifier(NamedElement ne) {
		null
	}

	protected dispatch def getClassifier(Subcomponent s) {
		s.allClassifier
	}
	protected dispatch def getClassifier(FeatureGroup fg) {
		fg.allFeatureGroupType
	}
		
	/**
	 * Create a lookup list from the given configuration
	 */
	protected def makeLookupList(Configuration cfg) {
		val result = newLinkedList
		val cfgs = cfg.linearization
		cfgs.forEach[result.addAll(mkPairs)]
		result
	}

	protected def mkPairs(Configuration cfg) {
		cfg.assignments.map[mkPair]
	}

	protected def mkPair(Assignment a) {
		val startRef = {
			var r = a.ref
			while (r.prev !== null)
				r = r.prev
			r
		}
		(startRef -> a)
	}

	/**
	 * Apply the current subcomponent to the keys in the lookup list
	 */
	protected def List<Pair<ElementRef, Assignment>> specialize(Iterable<Pair<ElementRef, Assignment>> lookup,
		NamedElement sub) {
		if (lookup.empty)
			new ArrayList
		else {
			val result = specialize(lookup.tail, sub)
			val first = lookup.head
			if (first.key !== null) {
				val ref = first.key
				val a = first.value
				if (ref.element == sub) {
					if (ref == a.ref)
						// key = null indicates that the value applies to this subcomponent
						result.add(0, (null -> a))
					else {
						// calculate the opposite of prev here, reference is unidirectional
						var r = a.ref
						while (r !== null) {
							if (r.prev === ref) {
								result.add(0, (r -> a))
								r = null
							} else {
								r = r.prev
							}
						}
					}
				}
			}
			result
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
}
