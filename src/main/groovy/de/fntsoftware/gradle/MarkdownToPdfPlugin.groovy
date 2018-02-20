package de.fntsoftware.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class MarkdownToPdfPlugin implements Plugin<Project> {
	@Override
	void apply(Project project) {
		// disable logging
		System.setProperty('xr.util-logging.loggingEnabled', 'false')

		project.fileTree([dir: '.', include: '*.md']).files.each { file ->
			def fileName = file.getName()
			def fileNameWithoutExtension = fileName.take(fileName.lastIndexOf('.'))

			def task = project.getTasks().create("${fileNameWithoutExtension.toLowerCase()}ToPdf", MarkdownToPdfTask)

			task.inputFile = file
			task.outputFile = project.getBuildDir().getPath() + '/' + fileNameWithoutExtension + '.pdf'
		}
		project.extensions.add('markdownToPdf', new MarkdownToPdf())
	}
}

