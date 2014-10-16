package org.fishlab.codegenius.internal.generator.configurations;

import org.fishlab.codegenius.Project;

public class GeneralConfigFileGenerator extends ProjectConfigFileGenerator {
	
	private String folder = "/";
	private String[][] templateMappings = {
			{ "general/ehcache.xml.vm", "ehcache.xml" },
			{ "general/logback.xml.vm", "logback.xml" } 
	};

	@Override
	protected String[][] getTemplateMappings() {
		return this.templateMappings;
	}

	@Override
	protected String getBaseFolder(Project project) {
		return project.getConfigFolder()+this.folder;
	}
}