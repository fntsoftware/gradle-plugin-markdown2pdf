package de.fntsoftware.gradle

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.DataKey
import com.vladsch.flexmark.util.options.MutableDataSet
import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile

class AbstractMarkdownTask extends DefaultTask {

	/** Value which can be resolved using {@link org.gradle.api.Project#file(Object object)} */
	private File inputFile

	/** Value which can be resolved using {@link org.gradle.api.Project#file(Object object)} */
	protected File outputFile

	protected MutableDataSet options = new MutableDataSet()

	@InputFile
	File getInputFile() {
		return this.inputFile
	}

	File setInputFile(Object file) {
		this.inputFile = project.file(file)
	}

	@OutputFile
	File getOutputFile() {
		return this.outputFile
	}

	File setOutputFile(Object file) {
		this.outputFile = project.file(file)
	}

	MutableDataSet setOption(DataKey key, Object value) {
		this.options.set(key, value)
	}

	protected String buildHtml() {
		MarkdownToPdf settings = this.project.extensions.getByType(MarkdownToPdf)
		if (this.inputFile.exists()) {
			def outputDir = new File(outputFile.parent)
			outputDir.mkdirs()

			def parser = Parser.builder(options).build()
			def renderer = HtmlRenderer.builder(options).build()

			def document = parser.parse(this.inputFile.text)
			def css = settings.cssFileContent
			return "<html><head><style>${css}</style></head><body>${renderer.render(document)}</body></html>"
		}
		else {
			throw new InvalidUserDataException("Inputfile does not exist.")
		}
	}
}
