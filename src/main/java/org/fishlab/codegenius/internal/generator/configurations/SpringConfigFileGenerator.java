package org.fishlab.codegenius.internal.generator.configurations;

import org.fishlab.codegenius.Project;

public class SpringConfigFileGenerator extends ProjectConfigFileGenerator{
	
	private String folder ="/spring/";

	private String[] []templateMappings={
			{"spring/app-datasource-mysql.xml.vm","app-datasource-mysql.xml"},
			{"spring/orm-hibernate4-mysql.xml.vm","orm-hibernate4-mysql.xml"},
			{"spring/app-service.xml.vm","app-service.xml"},
			{"spring/app-tx-aop.xml.vm","app-tx-aop.xml"},
			{"spring/app-include.xml.vm","app-include.xml"},
			{"spring/mvc-views.xml.vm","mvc-views.xml"}
			
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
