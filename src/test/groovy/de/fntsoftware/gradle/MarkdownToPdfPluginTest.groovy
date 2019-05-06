package de.fntsoftware.gradle

import com.vladsch.flexmark.Extension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.parser.Parser
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class MarkdownToPdfPluginTest {
	private final String pluginId = "de.fntsoftware.gradle.markdown-to-pdf"

	private class BuildFile {
		private BuildFile buildFile

		@Before
		void setup() {
			this.buildFile = new BuildFile()
		}

		@After
		void after() {
			File temp = new File(project.projectDir, 'READMETest.pdf')
			if (temp.exists()) {
				temp.delete()
			}
		}

		@Test
		void markdownToPdfPluginAddsMarkdownToPdfTaskToProject() {
			Project project = ProjectBuilder.builder().build()
			new File(project.projectDir, 'README.md').createNewFile()

			project.pluginManager.apply this.pluginId


			assertTrue(project.tasks.readmeToPdf instanceof MarkdownToPdfTask)
		}

		@Test
		void taskSetOptionTestsOptionsSet() {
			Project project = ProjectBuilder.builder().build()
			project.pluginManager.apply this.pluginId
			MarkdownToPdfTask task = project.tasks.create("myTask", MarkdownToPdfTask)
			Extension extension = TablesExtension.create()
			task.setOption(Parser.EXTENSIONS, Arrays.asList(extension))

			assertEquals(task.options.get(Parser.EXTENSIONS), Arrays.asList(extension))
		}

		@Test
		void markdownToPdfPluginTestsMarkdownToPdfTaskMarkdownToPdf() {
			Project project = ProjectBuilder.builder().build()
			project.pluginManager.apply this.pluginId
			MarkdownToPdfTask task = project.tasks.create("myTask", MarkdownToPdfTask)
			task.inputFile = getClass().getResource("READMETest.md").toURI()
			task.outputFile = project.projectDir.path + '/READMETest.pdf'
			task.action()

			assertTrue(task.outputFile.exists())
		}
	}
}