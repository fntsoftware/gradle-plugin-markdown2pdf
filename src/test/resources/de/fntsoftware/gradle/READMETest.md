# Markdown-to-PDF Plugin

This Plugin Converts a Markdown-File to a PDF-File based on the v0.32.2 [flexmark-plugin](https://github.com/vsch/flexmark-java).

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
```
Default Task Example:
```
- example
	-- build.gradle
	-- README.md
```
Here you can use the task "readmeToPdf" which will create
```
- example
	-- build.gradle
	-- README.md
	-- build dir
		---README.pdf
```