package org.osate.gtse.config.ui.tests

import java.util.List
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ErrorCollector
import org.junit.runner.RunWith
import org.osate.core.test.OsateTest
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.generator.ConfigGenerator
import org.osate.gtse.config.generator.ConfigGenerator.Mapping

import static org.hamcrest.Matchers.*

@RunWith(XtextRunner)
@InjectWith(ConfigUiInjectorProvider)
class MappingTest extends OsateTest {

	static ConfigPkg pkg

	static ConfigGenerator cg = new ConfigGenerator

	static boolean once = true
	
	@Rule
	public ErrorCollector collector = new ErrorCollector

	@Before
	override setUp() {
	}

	@After
	override cleanUp() {
	}

	@Before
	def void initWorkspace() {
		if (once) {
			once = false
			createProject(projectName)
			setResourceRoot("platform:/resource/" + projectName)
			val configFile = 'T.config'
			val configText = readFile('org.osate.gtse.config.ui.tests/models/T.config')
			Assert.assertTrue('Could not read file T.config', configText.length > 1)
			val aadlFile = 'T.aadl'
			val aadlText = readFile('org.osate.gtse.config.ui.tests/models/T.aadl')
			Assert.assertTrue('Could not read file T.aadl', aadlText.length > 1)
			createFiles(configFile -> configText, aadlFile -> aadlText)
			suppressSerialization
			val result = testFile(configFile, aadlFile)
			pkg = result.resource.contents.head as ConfigPkg
		}
	}

	@Test
	def test00() {
		testHelper('CWBS00', #[
			'w' -> 'T::W1.i'
		])
	}

	@Test
	def test01() {
		testHelper('CWBS01', #[
			'w' -> 'T::W1.i',
			'w.a' -> 'T::A.i1'
		])
	}

	@Test
	def test02() {
		testHelper('CWBS02', #[
			'w' -> 'T::W1.i',
			'w.a' -> 'T::A.i1,T::A.i2'
		])
	}

	@Test
	def test03() {
		testHelper('CWBS03', #[
			'w' -> 'T::W1.i',
			'w.a' -> 'T::A.i2'
		])
	}

	@Test
	def test04() {
		testHelper('CWBS04', #[
			'w' -> 'T::W1.i',
			'w.a' -> 'T::A.i1'
		])
	}

	@Test
	def test10() {
		testHelper('CWBS10', #[
			'w' -> 'T::W1.i,T::W2.i'
		])
	}

	@Test
	def test11() {
		testHelper('CWBS11', #[
			'w' -> 'T::W1.i,T::W2.i',
			'w.a' -> 'T::A.i2'
		])
	}

	@Test
	def test12() {
		testHelper('CWBS12', #[
			'w' -> 'T::W1.i,T::W2.i',
			'w.a' -> 'T::A.i1,T::A.i2'
		])
	}

	def testHelper(String cfgName, List<Pair<String, String>> expected) {
		val root = pkg.configurations.findFirst[name == cfgName]
		val ms = cg.makeMappings(pkg, root).tail.toList
		assertMappings(ms, expected)
	}

	def void assertMappings(List<Mapping> ms, List<Pair<String, String>> ex) {
		val strings = ms.map[pathName -> ConfigGenerator.print(value)]
		//ex.forEach[assertTrue('Missing mapping: ' + key + ' -> ' + value, strings.contains(it))]
		ex.forEach[collector.checkThat('Missing mapping: ' + key + ' -> ' + value, strings, hasItem(it))]
		//strings.forEach[assertTrue('Unexpected mapping: \'' + key + '\' -> \'' + value + '\'', ex.contains(it))]
		strings.forEach[collector.checkThat('Unexpected mapping: \'' + key + '\' -> \'' + value + '\'', ex, hasItem(it))]
	}

}
