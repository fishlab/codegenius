package org.fishlab.codegenius.internal.generator.service;

import java.util.Map;

import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchClassListGenerator;
import org.fishlab.codegenius.util.SetterUtil;

public class ServiceImplGenerator extends BatchClassListGenerator{
	private static final String FOLDER ="/service/impl/";
	private static final String SUFFIX ="ServiceImpl.java";
	
	@Override
	public String getTemplateName() {
		return  "ServiceImpl.java.vm";
	}

	@Override
	public String getOutputFileName(Project project, Class<?> source) {
		return project.getSourceFolder()+"/"+project.getPackageName().replace(".", "/")+FOLDER+source.getSimpleName()+SUFFIX;
	}

	@Override
	public void setModelProperties(Map<String, Object> context, Class<?> clazz) {
		context.put("class", clazz);
		String idType=SetterUtil.getSetterType(clazz,"setId");
		if(idType!=null){
			context.put("idType", idType);
		}
	}

}
