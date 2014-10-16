package org.fishlab.codegenius.internal.generator.configurations;

import org.fishlab.codegenius.Project;

public class WebConfigFileGenerator extends ProjectConfigFileGenerator {
	
	private String folder = "/WEB-INF/";
	private String[][] templateMappings = {
			{ "web/web.xml.vm", "web.xml" },
			{ "web/velocity-toolbox.xml.vm", "velocity-toolbox.xml" } 
	};

	@Override
	protected String[][] getTemplateMappings() {
		return this.templateMappings;
	}

	@Override
	protected String getBaseFolder(Project project) {
		return project.getResourceFolder()+this.folder;
	}

}
