package de.fntsoftware.gradle

class MarkdownToPdf {
	private static final DEFAULT_RESOURCE = 'bitbucket.css';

	String cssFile;

	private String cssFileContent = null;

	public String getCssFileContent() {
		if(this.cssFileContent == null) {

			if (this.cssFile != null ) {
				//If file not exists, check whether resource exists
				if(new File(this.cssFile) == null && getClass().getResourceAsStream(this.cssFile) != null){
					this.cssFileContent = getClass().getResourceAsStream(this.cssFile).getText()
				}
				else {
					this.cssFileContent = new File(this.cssFile).getText();
				}
			}
			else {
				this.cssFileContent = getClass().getResourceAsStream(DEFAULT_RESOURCE).getText()
			}
		}
		return this.cssFileContent;
	}
}
