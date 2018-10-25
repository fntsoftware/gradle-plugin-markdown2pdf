package de.fntsoftware.gradle

import static org.junit.Assert.assertTrue

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test

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
			File temp = new File(project.getProjectDir(), 'READMETest.pdf')
			if(temp.exists()) {
				temp.delete()
			}
		}

		@Test
		void markdownToPdfPluginAddsMarkdownToPdfTaskToProject() {
			Project project = ProjectBuilder.builder().build()
			new File(project.getProjectDir(), 'README.md').createNewFile()

			project.pluginManager.apply this.pluginId


			assertTrue(project.tasks.readmeToPdf instanceof MarkdownToPdfTask)
		}

		@Test
		void markdownToPdfPluginTestsMarkdownToPdfTaskMarkdownToPdf() {
			Project project = ProjectBuilder.builder().build()
			project.pluginManager.apply this.pluginId
			MarkdownToPdfTask task = project.tasks.create("myTask", MarkdownToPdfTask.class)
			task.inputFile = getClass().getResource("READMETest.md").toURI()
			task.outputFile = project.getProjectDir().getPath() + '/READMETest.pdf'
			task.action()

			assertTrue(task.outputFile.exists())
		}
	}
}