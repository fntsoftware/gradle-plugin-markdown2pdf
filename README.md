# Markdown-to-PDF Plugin

This Plugin Converts a Markdown-File to a PDF-File based on the [flexmark-plugin](https://github.com/vsch/flexmark-java).
To use this Plugin you can use:

```
buildscript {
  repositories {
		maven {
			url 'https://plugins.gradle.org/m2/'
		}
  }
  dependencies {
		classpath 'gradle.plugin.de.fntsoftware.gradle:markdown-to-pdf:1.1.0'
  }
}

apply plugin: 'de.fntsoftware.gradle.markdown-to-pdf'
```

## Configurations
It is possible to create own tasks with input and output files. The input file will be converted from Markdown-File to PDF-File. Optionally you can configure a path for your own CSS-File. If no path is configured, a default CSS-File is used. **For all Markdown-Files in the build.gradle directory are already default tasks with the output in the build directory.**

```
markdownToPdf{
	cssFile = 'PATH/TO/CSSFILE.css'
}
task exampleTask1(type: MarkdownToPdfTask){
	inputFile = '/PATH/TO/README.md'
	outputFile = '/PATH/TO/README.pdf'
}

task exampleTask2(type: MarkdownToPdfTask){
	inputFile = '/PATH/TO/CHANGELOG.md'
	outputFile = '/PATH/TO/CHANGELOG.pdf'
}

task exampleTask3(type: MarkdownToHtmlTask){
	inputFile = '/PATH/TO/CHANGELOG.md'
	outputFile = '/PATH/TO/CHANGELOG.html'
}
```

### Adding Parser Options
By default, no parser options are set.  To add parser options, you will need to create a task to use the setOption() function.

```
import com.vladsch.flexmark.ext.tables.*
import com.vladsch.flexmark.parser.Parser

...

dependencies {
	...
	classpath 'com.vladsch.flexmark:flexmark-ext-tables:0.34.52'
}
...

task exampleTaskSetParserOption(type: MarkdownToHtmlTask){
	inputFile = '/PATH/TO/CHANGELOG.md'
	outputFile = '/PATH/TO/CHANGELOG.html'
	setOption(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()))
}
```

In the above example, the TablesExtension is added. So you will need to import the correct packages and dependencies into your `build.gradle`.

## Default tasks
Directory Layout Example:
```
- example
	-- build.gradle
	-- README.md
```
Here the task "readmeToPdf" will create
```
- example
	-- build.gradle
	-- README.md
	-- build
		---README.pdf
```
Here the task "readmeToHtml" will create
```
- example
	-- build.gradle
	-- README.md
	-- build
		---README.html
```
Additionally there are `buildPdf` and `buildHtml` tasks that build all markdown files. 