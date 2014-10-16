package org.fishlab.codegenius.internal.generator.testing;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchTemplateBasedGenerator;
import org.fishlab.util.lang.ClassUtils;
/**生成Service层的spring测试用例
 * @author BL
 * */
public class SpringJunit4TestCaseGenerator extends BatchTemplateBasedGenerator{
	private String testFolder="/test/";
	private String caseFolder="service/";
	private String testCaseTemplateName="ServiceTestCase.java.vm";
	/*
	private String[][] basicMappings={
			{"TransactionalSpringTest.java.vm","TransactionalSpringTest.java"},
			{"CustomTestWatcher.java.vm","CustomTestWatcher.java"},
			{"CommonTest.java.vm","CommonTest.java"},
			{"ServiceTest.java.vm","ServiceTest.java"}
	};
	*/
	
	/*
	private List<GenerationTask> createBasicTasks(GenerationContext context,String baseFolder){
		List<GenerationTask> basic=new ArrayList<GenerationTask>();
		
		for(String[] mapping:this.basicMappings){
			GenerationTask t=new GenerationTask();
			t.templateName=mapping[0];
			t.outputFileName=baseFolder+mapping[1];
			basic.add(t);
		}
		return basic;
	}*/
	
	@Override
	protected List<GenerationTask> getGenerationTasks(GenerationContext context) {
		Project project =context.getProject();
		String baseFolder =project.getTestFolder()+"/"+project.getPackagePath()+this.testFolder;
		List<GenerationTask> ret = this.createGenerationTasks(context.getEntities(),baseFolder);
		return ret;
//		return this.createGenerationTasks(context.getEntities());
	}

	
	public static class SetterDescription{
		String name;
		String defaultValue;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDefaultValue() {
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		
	}
	static Map<Class<?>,String> defaultValueMappings =new HashMap<Class<?>,String>();
	static{
		defaultValueMappings.put(int.class, "17");
		defaultValueMappings.put(Integer.class, "17");		
		defaultValueMappings.put(String.class, "\"cg-str\"");
		
		defaultValueMappings.put(Date.class, "new java.util.Date()");
		
		defaultValueMappings.put(short.class, "(short)17");
		defaultValueMappings.put(Short.class, "(short)17");
		defaultValueMappings.put(char.class, "'c'");
		defaultValueMappings.put(Character.class, "'c'");
		defaultValueMappings.put(long.class, "17L");
		defaultValueMappings.put(Long.class, "17L");
		defaultValueMappings.put(boolean.class, "false");
		defaultValueMappings.put(Boolean.class, "false");
		defaultValueMappings.put(float.class, "(float)17.0");
		defaultValueMappings.put(Float.class, "(float)17.0");
		defaultValueMappings.put(double.class, "17.0");
		defaultValueMappings.put(Double.class, "17.0");
		
//		TODO:add if need
	}
	private List<SetterDescription> createSetterDescriptionsExcludeId(List<Method> setters){
		List<SetterDescription> lsdesc = new ArrayList<SetterDescription>();
		for (Method setter:setters){
			String setterName=setter.getName();
				if (!setterName.equals("setId")){
				Class<?> type =setter.getParameterTypes()[0];
				String defaultValue = defaultValueMappings.get(type);
				if (defaultValue !=null){
					SetterDescription desc= new SetterDescription();
					desc.name=setterName;
					desc.defaultValue=defaultValue;
					lsdesc.add(desc);
				}
			}
		}
		return lsdesc;
	}
	private List<GenerationTask> createGenerationTasks(List<Class<?>> classes,String baseFolder){
		List<GenerationTask> ls=new ArrayList<GenerationTask> ();
		for (Class<?> clazz:classes){
			Map<String,Object> model =new HashMap<String,Object>();
			model.put("class", clazz);
			List<Method> setters=ClassUtils.getAllSettersIncludeSuperClass(clazz);
//			List<String> imports=new ArrayList<String> ();
//			model.put("imports",imports);
			model.put("setterDescriptions", this.createSetterDescriptionsExcludeId(setters));
			GenerationTask t = new GenerationTask();
			t.templateName=this.testCaseTemplateName;
			t.model=model;
			t.outputFileName=baseFolder+this.caseFolder+this.makeTestClassOutputFileName(clazz);
			ls.add(t);
		}
		return ls;
		
	}
	
	private String makeTestClassOutputFileName(Class<?> clazz){
		return "Test"+clazz.getSimpleName()+"Service.java";
	}

}
