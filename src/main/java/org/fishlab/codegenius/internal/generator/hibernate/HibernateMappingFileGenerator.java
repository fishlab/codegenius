package org.fishlab.codegenius.internal.generator.hibernate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchClassListGenerator;
import org.fishlab.codegenius.impl.PrefixHandler;
import org.fishlab.codegenius.impl.SubTableNamePrefixHandler;
import org.fishlab.util.lang.ClassUtils;

public class HibernateMappingFileGenerator extends BatchClassListGenerator{
	private static final String FOLDER = "/hbm/";
	private static final String SUFFIX =".hbm.xml";
	private String templateName="hbm.xml.vm";
	
	private PrefixHandler prefix=new SubTableNamePrefixHandler(2,'_');
	
	public void setModelProperties(Map<String,Object> context,Class<?> source) {
		String tableName=source.getSimpleName().toLowerCase();
		context.put("className", source.getName());
		context.put("tableName", tableName);
		String prefix=this.prefix.getPrefix(tableName)+"_";
		context.put("prefix", prefix);
		List<Method> setters=ClassUtils.getAllSettersIncludeSuperClass(source);
		for (int i=0;i<setters.size();i++){
			Method m=setters.get(i);
			if(m.getName().equals("setId")){
				Method id=setters.remove(i);//i--如果需要访问后面的元素
				context.put("id",this.createFieldDescription(id));
				break;
			}
		}
		List<Map<String,String>> props =new ArrayList<Map<String,String>> ();
		for (Method m:setters){
			props.add(this.createFieldDescription(m));
		}
		context.put("props", props);
	}
	
	public Map<String,String> createFieldDescription(Method setter){
		Map<String,String> map=new HashMap<String,String>();
		
		String name=setter.getName().substring(3);
		map.put("name", StringUtils.uncapitalize(name));
		Class<?>[] pm=setter.getParameterTypes();
		if (pm.length==1)
			map.put("type", pm[0].getName());
		return map;
	}

	public String getOutputFileName(Project project, Class<?> source) {
		return project.getProjectSourceFolder()+FOLDER+source.getSimpleName()+SUFFIX;
	}
	
	@Override
	protected String getTemplateName() {
		return this.templateName;
	}

}
