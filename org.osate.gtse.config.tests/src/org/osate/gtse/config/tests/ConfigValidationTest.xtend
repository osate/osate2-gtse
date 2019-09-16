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
package org.osate.gtse.config.tests

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.util.StringInputStream
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.osate.gtse.config.config.ConfigPackage
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.validation.ConfigValidator

@RunWith(XtextRunner)
@InjectWith(ConfigAndAadlInjectorProvider)
class ConfigValidationTest {

	@Inject Provider<XtextResourceSet> resourceSetProvider

	@Inject extension ParseHelper<ConfigPkg> parseHelper

	@Inject extension ValidationTestHelper validationHelper

	static XtextResourceSet rs

	@Before
	def void setup() {
		rs = resourceSetProvider.get
		// must load like this, parseHelper will always create ConfigPkg
		val t = rs.createResource(URI.createFileURI('T.aadl'))
		t.load(new StringInputStream(aadlPackage), emptyMap)
		val ps = rs.createResource(URI.createFileURI('PS.aadl'))
		ps.load(new StringInputStream(aadlPropertySet), emptyMap)
	}

	def void clean(ResourceSet rs) {
		val resources = rs.resources
		resources.tail.forEach[unload]
		for (var i = resources.size(); i > 2; i--) {
			resources.remove(i - 1)
		}
	}

	// validation of configurations
	
	@Test
	def void validateRoot() {
		'''
			root CF1		
			configuration CF1
		'''.parse.assertNoIssues
	}

	@Test
	def void missingRoot() {
		'''
		'''.parse.assertError(ConfigPackage.eINSTANCE.configPkg, ConfigValidator.NO_ROOT)
	}

	@Test
	def void unresolvedRoot() {
		'''
			root CF1
		'''.parse.assertError(ConfigPackage.eINSTANCE.configPkg, Diagnostic.LINKING_DIAGNOSTIC)
	}

	@Test
	def void emptyConfiguration() {
		val result = '''
			root C
			configuration C extends T::WBS.i
		'''.parse(rs)
		result.assertNoIssues
	}

	@Test
	def void simplePropertyAssignment() {
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i {
				#PS::p => "string"
			}
		'''.parse(rs)
		result.assertNoIssues
	}

	@Test
	def void simplePropertyAssignmentNotApplies() {
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i {
				w#PS::p => "string"
			}
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.assignment, ConfigValidator.PROPERTY_DOES_NOT_APPLY)
	}

	@Test
	def void simplePropertyAssignmentWrongType() {
		// TODO type mismatch not detected
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i {
				#PS::p => 1
			}
		'''.parse(rs)
		result.assertNoIssues
	}

	@Test
	def void simpleClassifierAssignment() {
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i {
				w => T::W1.i
			}
		'''.parse(rs)
		result.assertNoIssues
	}

	@Test
	def void simpleClassifierAssignmentNot() {
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i {
				w => 1
			}
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.assignment, ConfigValidator.NOT_CLASSIFIER)
	}

	@Test
	def void simpleClassifierAssignmentWrongType() {
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i {
				w => T::A.i1
			}
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.assignment, ConfigValidator.CLASSIFIER_MISMATCH)
	}

	@Test
	def void consistentCombination() {
		val result = '''
			root C0
			configuration C0 extends T::WBS.i
			configuration C1 extends T::WBS.i
			configuration C2 extends T::WBS.i with C0 & C1
		'''.parse(rs)
		result.assertNoIssues
	}
	
	@Test
	def inconsistentCombination() {
		val result = '''
			root C0
			configuration C0 extends T::WBS.i
			configuration C1 extends T::WBS.i1
			configuration C2 extends T::WBS.i with C0 & C1
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.combination, ConfigValidator.INCONSISTENT_COMBINATION)
	}
	
	@Test
	def void addedArguments1() {
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i {
				w => T::A.i1(p => v)
			}
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.ARGUMENTS_NOT_ALLOWED)
	} 
	
	@Test
	def void addedArguments2() {
		val result = '''
			root CWBS00
			configuration CWBS00 extends T::WBS.i
			configuration CWBS01 extends T::WBS.i {
				w => CWBS00(p => v)
			}
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.ARGUMENTS_NOT_ALLOWED)
	} 
	
	@Test
	def void addedArguments3() {
		val result = '''
			root CWBS00
			configuration CWBS01 extends T::WBS.i {
				w => CWBS00(p => v)
			}
		'''.parse(rs)
		// CWBS00 unresolved
		result.assertNoError(ConfigValidator.ARGUMENTS_NOT_ALLOWED)
	} 
	
	@Test
	def void addedArguments4() {
		val result = '''
			root C0
			configuration C0 extends T::WBS.i
			configuration C1 extends T::WBS.i with C0(p => v)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.combination, ConfigValidator.ARGUMENTS_NOT_ALLOWED)
	} 
	
	@Test
	def void addedArguments5() {
		val result = '''
			root C0
			configuration C1 extends T::WBS.i with C0(p => v)
		'''.parse(rs)
		// C0 unresolved
		result.assertNoError(ConfigValidator.ARGUMENTS_NOT_ALLOWED)
	} 
	
	// validation of constraints

	// validation of outputs
	
	@Test
	def void validLimitRelations() {
		'''
			outputs {
				v1: int == 5,
				v1: int != 5,
				v1: int < 5,
				v1: int <= 5,
				v1: int >= 5,
				v1: int > 5
			}
		'''.parse.assertNoErrors(ConfigPackage.eINSTANCE.limit, ConfigValidator.INVALID_LIMIT_RELATION)
	}

	@Test
	def void invalidLimitRelation1() {
		'''
			outputs {
				v1: int requires 5
			}
		'''.parse.assertError(ConfigPackage.eINSTANCE.limit, ConfigValidator.INVALID_LIMIT_RELATION)
	}

	@Test
	def void invalidLimitRelation2() {
		'''
			outputs {
				v1: int forbids 5
			}
		'''.parse.assertError(ConfigPackage.eINSTANCE.limit, ConfigValidator.INVALID_LIMIT_RELATION)
	}

	@Test
	def void invalidLimitRelation3() {
		'''
			outputs {
				v1: int in 5
			}
		'''.parse.assertError(ConfigPackage.eINSTANCE.limit, ConfigValidator.INVALID_LIMIT_RELATION)
	}

	@Test
	def void invalidBoundType() {
		'''
			outputs {
				v1: int == 'string'
			}
		'''.parse.assertError(ConfigPackage.eINSTANCE.outputVariable, ConfigValidator.INVALID_BOUND_TYPE)
	}
	
	@Test
	def void testCheckParameterCategory() {
		val result = '''
			root C0
			configuration C0(p: device T::W)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.configParameter, ConfigValidator.CATEGORY_MISMATCH)
	}
	
	@Test
	def void testCheckChoiceExtendsParameterType() {
		val result = '''
			root C0
			configuration C0(p: system T::W from (T::WBS.i))
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testCheckChoiceExtendsParameterImpl() {
		val result = '''
			root C0
			configuration C0(p: system T::W.i from (T::WBS.i))
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testCheckChoiceIsImpl() {
		val result = '''
			root C0
			configuration C0(p: system T::W from (T::W))
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_IMPLEMENTATION)
	}
	
	@Test
	def void testCheckChoiceIsNamedElementRef() {
		val result = '''
			root C0
			configuration C0(p: system T::W from ("property value"))
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.propertyValue, ConfigValidator.NOT_IMPLEMENTATION)
	}
	
	@Test
	def void testCheckChoiceIsPropertyValue() {
		val result = '''
			root C0
			configuration C0(p: PS::p from (T::W))
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_PROPERTY_VALUE)
	}
	
	@Test
	def void testCheckUnsafe() {
		val result = '''
			root C0
			configuration C0 extends T::W
			configuration C1 extends T::W with unsafe C0
		'''.parse(rs)
		result.assertWarning(ConfigPackage.eINSTANCE.combination, ConfigValidator.UNSAFE)
	}
	
	@Test
	def void testClassifierArgumentNotChoice() {
		val result = '''
			root C0
			configuration C0(p: system T::W from (T::W1.i) extends T::W
			configuration C1 extends T::W with C0(p => T::W2.i)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.ARGUMENT_NOT_CHOICE)
	}
	
	@Test
	def void testClassifierArgumentNotExtension() {
		val result = '''
			root C0
			configuration C0(p: system T::W) extends T::W
			configuration C1 extends T::W with C0(p => T::WBS)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testConfigurationArgumentHasNoExtends() {
		val result = '''
			root C0
			configuration C0(p: system T::W) extends T::W
			configuration C1 extends T::W with C0(p => C2)
			configuration C2
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testConfigurationArgumentNotChoice() {
		val result = '''
			root C0
			configuration C0(p: system T::W from (T::W1.i)) extends T::W
			configuration C1 extends T::W with C0(p => C2)
			configuration C2 extends T::W2.i
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.ARGUMENT_NOT_CHOICE)
	}
	
	@Test
	def void testConfigurationArgumentNotExtension() {
		val result = '''
			root C0
			configuration C0(p: system T::W) extends T::W
			configuration C1 extends T::W with C0(p => C2)
			configuration C2 extends T::WBS
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testParameterArgumentClassifierChoicesNotSubset() {
		val result = '''
			root C0
			configuration C0(p: system T::W from (T::W1.i)) extends T::W
			configuration C1(arg: system T::W from (T::W2.i)) extends T::W with C0(p => arg)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testParameterArgumentNotChoice() {
		val result = '''
			root C0
			configuration C0(p: system T::W from (T::W1.i)) extends T::W
			configuration C1(arg: system T::W2.i) extends T::W with C0(p => arg)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testParameterArgumentNotExtension() {
		val result = '''
			root C0
			configuration C0(p: system T::W) extends T::W
			configuration C1(arg: system T::WBS) extends T::W with C0(p => arg)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_EXTENSION)
	}
	
	@Test
	def void testParameterArgumentNotClassifier() {
		val result = '''
			root C0
			configuration C0(p: system T::W) extends T::W
			configuration C1(arg: PS::p) extends T::W with C0(p => arg)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_CLASSIFIER)
	}
	
	@Test
	def void testPropertyArgumentNotClassifier() {
		val result = '''
			root C0
			configuration C0(p: system T::W) extends T::W
			configuration C1 extends T::W with C0(p => "property value")
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.propertyValue, ConfigValidator.NOT_CLASSIFIER)
	}
	
	@Test
	def void testParameterArgumentNotProperty() {
		val result = '''
			root C0
			configuration C0(p: PS::p) extends T::W
			configuration C1(arg: system T::W) extends T::W with C0(p => arg)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_PROPERTY_VALUE)
	}
	
	@Test
	def void testParameterArgumentPropertyChoicesNotSubset() {
		val result = '''
			root C0
			configuration C0(p: PS::p from ("property value 1")) extends T::W
			configuration C1(arg: PS::p from ("property value 2")) extends T::W with C0(p => arg)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.ARGUMENT_NOT_CHOICE)
	}
	
	@Test
	def void testParameterArgumentHasNoChoices() {
		val result = '''
			root C0
			configuration C0(p: PS::p from ("property value 1")) extends T::W
			configuration C1(arg: PS::p) extends T::W with C0(p => arg)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.ARGUMENT_NOT_CHOICE)
	}
	
	@Test
	def void testClassifierArgumentNotProperty() {
		val result = '''
			root C0
			configuration C0(p: PS::p) extends T::W
			configuration C1 extends T::W with C0(p => T::W)
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.namedElementRef, ConfigValidator.NOT_PROPERTY_VALUE)
	}
	
	@Test
	def void testPropertyArgumentNotChoice() {
		val result = '''
			root C0
			configuration C0(p: PS::p from ("property value 1") extends T::W
			configuration C1 extends T::W with C0(p => "property value 2")
		'''.parse(rs)
		result.assertError(ConfigPackage.eINSTANCE.propertyValue, ConfigValidator.ARGUMENT_NOT_CHOICE)
	}
	
	val aadlPropertySet = '''
		property set PS is
			p: aadlstring applies to (system implementation);
		end PS;
	'''

	val aadlPackage = '''
		package T
		public
			system WBS
			end WBS;
			
			system implementation WBS.i
				subcomponents
					w: system W;
			end WBS.i;
			
			system implementation WBS.i1
				subcomponents
					w: system W;
			end WBS.i1;
			
			system W
			end W;
			
			system implementation W.i
				subcomponents
					a: device A;
			end W.i;
			
			system W1 extends W
			end W1;
			
			system implementation W1.i extends W.i
				subcomponents
					b: device B;
			end W1.i;
		
			system W2 extends W
			end W2;
			
			system implementation W2.i extends W.i
				subcomponents
					c: device C;
			end W2.i;
			
			device A
			end A;
			
			device implementation A.i1
			end A.i1;
			
			device implementation A.i2
			end A.i2;
			
			device B
			end B;
			
			device implementation B.i1
			end B.i1;
		
			device implementation B.i2
			end B.i2;
		
			device C
			end C;
			
			device implementation C.i1
			end C.i1;
		
			device implementation C.i2
			end C.i2;
		
		end T;
	'''
}
