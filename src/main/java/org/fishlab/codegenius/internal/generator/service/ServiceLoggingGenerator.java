package org.fishlab.codegenius.internal.generator.service;

import java.util.Map;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.TemplateBasedGenerator;

public class ServiceLoggingGenerator  extends TemplateBasedGenerator{
	private static final String FOLDER ="/service/support/";
	
	private String outputFileName ="AspectServiceLogging.java";
	private String templateName= "AspectServiceLogging.java.vm";
	
	
	@Override
	protected String getOutputFileName(GenerationContext context) {
		Project project =context.getProject();
		return project.getSourceFolder()+"/"+project.getPackagePath()+FOLDER+this.outputFileName;
	}

	@Override
	public String getTemplateName() {
		return this.templateName;
	}

	@Override
	public void setTemplateProperties(GenerationContext context,
			Map<String, Object> model) {
		
	}

}
