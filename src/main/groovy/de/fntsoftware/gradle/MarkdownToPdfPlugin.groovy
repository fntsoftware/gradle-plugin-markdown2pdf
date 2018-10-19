package de.fntsoftware.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class MarkdownToPdfPlugin implements Plugin<Project> {
	@Override
	void apply(Project project) {
		// disable logging
		System.setProperty('xr.util-logging.loggingEnabled', 'false')

		project.fileTree([dir: '.', include: '*.md']).files.each { file ->
			def fileNameWithoutExtension = file.name.take(file.name.lastIndexOf('.'))

			def pdfTask = project.tasks.create("${fileNameWithoutExtension.toLowerCase()}ToPdf", MarkdownToPdfTask)
			pdfTask.inputFile = file
			pdfTask.outputFile = project.buildDir.path + '/' + fileNameWithoutExtension + '.pdf'

			def htmlTask = project.tasks.create("${fileNameWithoutExtension.toLowerCase()}ToHtml", MarkdownToHtmlTask)
			htmlTask.inputFile = file
			htmlTask.outputFile = project.buildDir.path + '/' + fileNameWithoutExtension + '.html'
		}
		project.extensions.add('markdownToPdf', new MarkdownToPdf())
	}
}
