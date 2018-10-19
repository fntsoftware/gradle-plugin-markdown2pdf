package de.fntsoftware.gradle

import org.gradle.api.tasks.TaskAction

class MarkdownToHtmlTask extends AbstractMarkdownTask {
	@TaskAction
	void action() {
		File file = new File(this.outputFile.path)
		file << buildHtml()
	}
}
