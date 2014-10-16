package org.fishlab.codegenius.internal.generator.controller;

import java.util.List;
import java.util.Map;

import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchClassListGenerator;
import org.fishlab.codegenius.util.SetterUtil;

public class ControllerGenerator extends BatchClassListGenerator{
	private static final String FOLDER = "/controller/";
	private static final String SUFFIX ="Controller.java";
	private String templateName="Controller.java.vm";
	
	public void setModelProperties(Map<String,Object> context,Class<?> clazz) {
		context.put("class", clazz);
//		TODO:setter and getter method
		List<String> Fields=SetterUtil.getCapitalizedfieldsExcludeId(clazz);
		context.put("Fields", Fields);
		String idType=SetterUtil.getSetterType(clazz,"setId");
		if(idType!=null){
			context.put("idType", idType);
		}
	}

	public String getOutputFileName(Project project, Class<?> source) {
		return project.getProjectSourceFolder()+FOLDER+source.getSimpleName()+SUFFIX;
	}
	
	@Override
	protected String getTemplateName() {
		return this.templateName;
	}

}
