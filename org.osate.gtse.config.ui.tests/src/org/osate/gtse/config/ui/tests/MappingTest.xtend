package org.osate.gtse.config.ui.tests

import com.itemis.xtext.testing.XtextTest
import java.io.ByteArrayInputStream
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.jobs.IJobManager
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.emf.common.util.URI
import org.eclipse.ui.actions.WorkspaceModifyOperation
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ErrorCollector
import org.junit.runner.RunWith
import org.osate.core.AadlNature
import org.osate.gtse.config.config.ConfigPkg
import org.osate.gtse.config.generator.AbstractMapping
import org.osate.gtse.config.generator.ComponentMapping
import org.osate.gtse.config.generator.ConfigGenerator

import static org.hamcrest.Matchers.*
import org.apache.log4j.Logger
import java.io.IOException
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

@RunWith(XtextRunner)
@InjectWith(ConfigUiInjectorProvider)
class MappingTest extends XtextTest {

	/*
	 * Use static variable to keep model across tests. Requires only a single workspace build.
	 * Test 2, 3, ... are 1000 times as fast with this optimization. First test method still takes a couple of seconds.
	 */
	static ConfigPkg pkg
	
	static ConfigGenerator cg = new ConfigGenerator

	static boolean once = true
	
	@Rule
	public ErrorCollector collector = new ErrorCollector

	@Before
	/**
	 * All tests use the same model
	 */
	def void initWorkspace() {
		val configFile = 'T.config'
		val aadlFile = 'T.aadl'
		val modelRoot = 'org.osate.gtse.config.ui.tests/models/'
		val projectName = "test"

		if (once) {
			once = false
			createProject(projectName)
			setResourceRoot("platform:/resource/" + projectName)
			val configText = readFile(modelRoot + configFile)
			Assert.assertTrue('Could not read file ' + configFile, configText.length > 1)
			val aadlText = readFile(modelRoot + aadlFile)
			Assert.assertTrue('Could not read file ' + aadlFile, aadlText.length > 1)
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

	@Test
	def test13() {
		testHelper('CWBS13', #[
			'w' -> 'T::W1.i',
			'w.a' -> 'T::A.i1,T::A.i2'
		])
	}

	def testHelper(String cfgName, List<Pair<String, String>> expected) {
		val root = pkg.configurations.findFirst[name == cfgName]
		val ms = cg.makeMappings(pkg, root).tail.toList
		assertMappings(ms, expected)
	}

	def void assertMappings(List<AbstractMapping> ms, List<Pair<String, String>> ex) {
		val strings = ms.filter(ComponentMapping).map[pathName -> (new ConfigGenerator).print(value)]
		//ex.forEach[assertTrue('Missing mapping: ' + key + ' -> ' + value, strings.contains(it))]
		ex.forEach[collector.checkThat('Missing mapping: ' + key + ' -> ' + value, strings, hasItem(it))]
		//strings.forEach[assertTrue('Unexpected mapping: \'' + key + '\' -> \'' + value + '\'', ex.contains(it))]
		strings.forEach[collector.checkThat('Unexpected mapping: \'' + key + '\' -> \'' + value + '\'', ex, hasItem(it))]
	}

	static val Logger LOGGER = Logger.getLogger(MappingTest);

	protected val workspaceRoot = ResourcesPlugin.workspace.root

	/**
	 * Create a project with subdirectories in the current workspace.
	 */
	def createProject(String projectName, String... srcDirs) {
		val project = workspaceRoot.getProject(projectName)
		val operation = new WorkspaceModifyOperation() {
			override execute(IProgressMonitor monitor) {
				if (!project.exists) {
					project.create(monitor)
					project.open(monitor)

					val description = project.getDescription
					description.natureIds = #["org.eclipse.xtext.ui.shared.xtextNature", AadlNature.ID]
					project.setDescription(description, monitor)

					for (srcDir : srcDirs) {
						val src = project.getFolder(srcDir)
						src.create(true, true, monitor)
					}
				}
			}
		}
		operation.run(null)

		project
	}

	/**
	 * Create a set of files in the workspace given as filename -> text pairs
	 */
	def protected createFiles(Pair<String, String>... models) {
		val operation = new WorkspaceModifyOperation() {
			override execute(IProgressMonitor monitor) {
				for (Pair<String, String> model : models) {
					val uri = URI.createURI(resourceRoot + "/" + model.key)
					createFile(uri, model.value)
				}
			}
		}
		operation.run(null)
		waitForBuild
	}

	def IFile createFile(URI uri, String content) {
		val file = workspaceRoot.getFile(new Path(uri.toPlatformString(true)))

		Assert.assertTrue(file.getParent.exists)

		LOGGER.info("creating " + uri + " in test method " + this.class.simpleName + "." +
			new Throwable().fillInStackTrace.getStackTrace().get(1).methodName)

		try {
			val stream = new ByteArrayInputStream(content.getBytes(file.charset))
			if (file.exists) {
				file.setContents(stream, true, true, null)
			} else {
				file.create(stream, true, null)
			}
			stream.close()
		} catch (Exception e) {
			LOGGER.error(e)
		}
		file
	}

	def void waitForBuild() {
		val IJobManager jobManager = Job.getJobManager();
		try {
			jobManager.join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
			jobManager.join(ResourcesPlugin.FAMILY_MANUAL_BUILD, null);
		} catch (InterruptedException e) {
			waitForBuild
		}
	}

	def protected String readFile(String path) {
		var String result = ''
		try {
			val url = new URL('platform:/plugin/' + path)
			val inputStream = url.openConnection().inputStream
			val in = new BufferedReader(new InputStreamReader(inputStream))
			var String inputLine

			while ((inputLine = in.readLine()) !== null) {
				result += inputLine + '\n'
			}
			
			in.close()
		} catch (IOException e) {
			result = ''
		}
		result
	}
}
