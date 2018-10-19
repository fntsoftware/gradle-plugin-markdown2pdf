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

			def task = project.tasks.create("${fileNameWithoutExtension.toLowerCase()}ToPdf", MarkdownToPdfTask)

			task.inputFile = file
			task.outputFile = project.buildDir.path + '/' + fileNameWithoutExtension + '.pdf'
		}
		project.extensions.add('markdownToPdf', new MarkdownToPdf())
	}
}

