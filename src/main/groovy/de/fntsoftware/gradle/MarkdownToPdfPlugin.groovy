package de.fntsoftware.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class MarkdownToPdfPlugin implements Plugin<Project> {
	private static final FORBIDDEN_CHARACTERS = [" ", "/", "\\", ":", "<", ">", "\"", "?", "*", "|"]

	@Override
	void apply(Project project) {
		// disable logging
		System.setProperty('xr.util-logging.loggingEnabled', 'false')

		def buildPdfTask = project.tasks.create('buildPdf')
		def buildHtmlTask = project.tasks.create('buildHtml')

		project.fileTree([dir: '.', include: '*.md']).files.each { file ->
			def fileNameWithoutExtension = file.name.take(file.name.lastIndexOf('.'))
			def taskName = fileNameWithoutExtension.toLowerCase()

			taskName = FORBIDDEN_CHARACTERS.inject(taskName, { name, character -> name.replace(character, "") })

			def pdfTask = project.tasks.create("${taskName}ToPdf", MarkdownToPdfTask)
			pdfTask.inputFile = file
			pdfTask.outputFile = project.buildDir.path + '/' + fileNameWithoutExtension + '.pdf'
			buildPdfTask.dependsOn(pdfTask)

			def htmlTask = project.tasks.create("${taskName}ToHtml", MarkdownToHtmlTask)
			htmlTask.inputFile = file
			htmlTask.outputFile = project.buildDir.path + '/' + fileNameWithoutExtension + '.html'
			buildHtmlTask.dependsOn(htmlTask)
		}
		project.extensions.add('markdownToPdf', new MarkdownToPdf())
	}
}
