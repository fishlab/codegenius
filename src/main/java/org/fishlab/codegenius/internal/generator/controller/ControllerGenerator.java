package org.fishlab.codegenius.internal.generator.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchClassListGenerator;
import org.fishlab.codegenius.util.SetterUtil;
import org.fishlab.util.lang.ClassUtils;

public class ControllerGenerator extends BatchClassListGenerator{
	private static final String FOLDER = "/controller/";
	private static final String SUFFIX ="Controller.java";
	private String templateName="Controller.java.vm";
	//主要是 boolean 类型的 getter 是 is开始的
	public static class FieldDesc {
		private String name;
//		private String setter;
		private String getter;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
//		public String getSetter() {
//			return setter;
//		}
//		public void setSetter(String setter) {
//			this.setter = setter;
//		}
		public String getGetter() {
			return getter;
		}
		public void setGetter(String getter) {
			this.getter = getter;
		}
		
	}
	public void setModelProperties(Map<String,Object> context,Class<?> clazz) {
		context.put("class", clazz);
//		TODO:setter and getter method
		
//		List<String> Fields=SetterUtil.getCapitalizedfieldsExcludeId(clazz);
//		context.put("Fields", Fields);
		
		context.put("Fields", this.getAndSetterPairs(clazz));
		String idType=SetterUtil.getSetterType(clazz,"setId");
		if(idType!=null){
			context.put("idType", idType);
		}
	}
	public List<FieldDesc> getAndSetterPairs(Class<?> clazz){
		List<FieldDesc> fddesc = new ArrayList<FieldDesc>();
		List<Method> setters  = ClassUtils.getAllSettersIncludeSuperClass(clazz);
		for (Method m:setters){
			if (!m.getName().equals("getId")){
				FieldDesc fd=new FieldDesc();
				fd.name=m.getName().substring(3);
				if (!m.getParameterTypes()[0].equals(boolean.class)){
					fd.getter="get";
				}else{
					fd.getter="is";
				}
				fddesc.add(fd);
			}
		}
		return fddesc;
	}

	public String getOutputFileName(Project project, Class<?> source) {
		return project.getProjectSourceFolder()+FOLDER+source.getSimpleName()+SUFFIX;
	}
	
	@Override
	protected String getTemplateName() {
		return this.templateName;
	}

}
