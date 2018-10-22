package de.fntsoftware.gradle

import org.gradle.api.tasks.TaskAction

import com.vladsch.flexmark.pdf.converter.PdfConverterExtension

class MarkdownToPdfTask extends AbstractMarkdownTask {
	@TaskAction
	void action() {
		PdfConverterExtension.exportToPdf(this.outputFile.path, buildHtml(), "", options)
	}
}
