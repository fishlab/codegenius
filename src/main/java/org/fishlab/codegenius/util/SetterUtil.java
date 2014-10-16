package org.fishlab.codegenius.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fishlab.util.lang.ClassUtils;

public class SetterUtil {
	public static String getSetterType(Class<?> clazz,String setterName){
		List<Method> setters=ClassUtils.getAllSettersIncludeSuperClass(clazz);
		for (int i=0;i<setters.size();i++){
			Method m=setters.get(i);
			if(m.getName().equals(setterName)){
				Class<?>[] pm=m.getParameterTypes();
				if (pm.length==1){
					return pm[0].getName();
				}
				break;
			}
		}
		return null;
	}
	
	public static List<String> getSetterNamesFromMethods(List<Method> methods) {
		List<String> ns = new ArrayList<String>();
		for (Method m : methods) {
			String n = m.getName();
			int l = n.length();
			if (l > 3 && n.startsWith("set")) {
				char[] tmp = new char[l - 3];
				char t = n.charAt(3);
				if (t >= 65 && t <= 90) {
					t += 32;
				}
				tmp[0] = t;
				for (int i = 1; i < l - 3; i++) {
					tmp[i] = n.charAt(i + 3);
				}
				ns.add(new String(tmp));
			}
		}
		return ns;

	}
	
	public static List<String> getfieldsExcludeId(Class<?> clazz) {
		List<Method> setters=ClassUtils.getAllSettersIncludeSuperClass(clazz);
		 List<String> fields=new ArrayList<String>();
		for (int i=0;i<setters.size();i++){
//			Method m=setters.get(i);
			String name=setters.get(i).getName().substring(3);
			if(!name.equals("Id")){
				fields.add( StringUtils.uncapitalize(name));
			}
		}
		return fields;
	}
	
	public static List<String> getCapitalizedfieldsExcludeId(Class<?> clazz) {
		List<Method> setters=ClassUtils.getAllSettersIncludeSuperClass(clazz);
		 List<String> fields=new ArrayList<String>();
		for (int i=0;i<setters.size();i++){
//			Method m=setters.get(i);
			String name=setters.get(i).getName().substring(3);
			if(!name.equals("Id")){
				fields.add( name );
			}
		}
		return fields;
	}
}
