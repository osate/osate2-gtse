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
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.osate.gtse.config.config.ConfigPkg

@RunWith(XtextRunner)
@InjectWith(ConfigInjectorProvider)
class ConfigParsingTest {
	@Inject extension ParseHelper<ConfigPkg> parseHelper

	@Test
	def void parseRoot() {
		val result = '''
			root CF1
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration1() {
		val result = '''
			configuration C
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration2() {
		val result = '''
			configuration C {}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration3() {
		val result = '''
			configuration C extends P::C.i
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration4() {
		val result = '''
			configuration C extends P::C.i with C1
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration5() {
		val result = '''
			configuration C extends P::C.i with C1 & C2
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration6() {
		val result = '''
			configuration C {
				a => Q::c.i
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration7() {
		val result = '''
			configuration C {
				a => X,
				b => Y
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration8() {
		val result = '''
			configuration C {
				#p => 123 ms
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration9() {
		val result = '''
			configuration C {
				a#p => 123
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration10() {
		val result = '''
			configuration C {
				a => X with Y & Z
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration11() {
		val result = '''
			configuration C {
				a => {
					b => B
				}
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration12() {
		val result = '''
			configuration C {
				a.b => B
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration13() {
		val result = '''
			configuration C {
				a => C {
					b => B
				}
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration14() {
		val result = '''
			configuration C {
				a => C with X & Y {
					b => B
				}
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration15() {
		val result = '''
			configuration C (p1: system S)
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration16() {
		val result = '''
			configuration C (p1: system S) extends D with X
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration17() {
		val result = '''
			configuration C extends D with X (p => v)
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration18() {
		val result = '''
			configuration C {
				a => D (p => v)
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration19() {
		val result = '''
			configuration C {
				#a => p with X & Y
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseConfiguration20() {
		val result = '''
			configuration C (p: t from (1, 2, 3))
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

	@Test
	def void parseAnalyses() {
		val result = '''
			analyses {
				'org.osate.analysis1',
				"org.osate.analysis2"
			}
		'''.parse
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}

}
