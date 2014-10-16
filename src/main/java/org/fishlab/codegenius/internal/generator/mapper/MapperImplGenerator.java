package org.fishlab.codegenius.internal.generator.mapper;

import java.util.Map;

import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchClassListGenerator;
/**@author Black Lotus
 **/
public class MapperImplGenerator extends BatchClassListGenerator{

	private static final String FOLDER ="/mapper/impl/";
	private static final String SUFFIX ="MapperImpl.java";
	
	@Override
	public String getTemplateName() {
		return "MapperImpl.java.vm";
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
