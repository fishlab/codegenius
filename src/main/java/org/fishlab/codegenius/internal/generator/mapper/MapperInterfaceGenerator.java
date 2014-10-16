package org.fishlab.codegenius.internal.generator.mapper;

import java.util.Map;

import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchClassListGenerator;
/**@author Black Lotus
 * */
public class MapperInterfaceGenerator extends BatchClassListGenerator{

	private static final String FOLDER ="/mapper/";
	private static final String SUFFIX ="Mapper.java";
	
	@Override
	public String getTemplateName() {
		return "Mapper.java.vm";
	}

	@Override
	public String getOutputFileName(Project project, Class<?> source) {
		return project.getSourceFolder()+"/"+project.getPackagePath()+FOLDER+source.getSimpleName()+SUFFIX;
	}

	@Override
	public void setModelProperties(Map<String, Object> context, Class<?> clazz) {
		context.put("class", clazz);
//		String idType=SetterUtil.getSetterType(clazz,"setId");
//		if(idType!=null){
//			context.put("idType", idType);
//		}
	}

}
