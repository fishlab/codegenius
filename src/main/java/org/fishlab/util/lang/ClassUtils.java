package org.fishlab.util.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**@author wu
 * */
public class ClassUtils {
	private static final String SETMETHOD_PREFIX="set";
	/**获取一个类以及超类实现的所有接口
	 * */
	public static List<Class<?>> getInterfacesIncludeSuperClass(final Class<?> clazz) {
		List<Class<?>> types=new ArrayList<Class<?>>();
		for (Class<?> superClass = clazz; superClass != Object.class;
				superClass = superClass.getSuperclass()) {
			for(Class<?> intf: superClass.getInterfaces()){
				if (!types.contains(intf)){
					types.add(intf);
				}
			}
		}
		return types;
	}
	/**获取一个类以及超类实现的所有属性
	 * */
	public static List<Field> getAllFieldsIncludeSuperClass(final Class<?> clazz) {
		List<Field> lf=new ArrayList<Field>();
		for (Class<?> superClass = clazz; 
				superClass != Object.class; 
				superClass = superClass.getSuperclass()) {
				Field[] f=superClass.getDeclaredFields();
				for (int j=0;j<f.length;j++){
					lf.add(f[j]);
				}
		}
		return lf;
	}
	
	/**获取一个类以及超类实现的所有方法
	 * */
	public static List<Method> getAllMethodsIncludeSuperClass(final Class<?> clazz) {
		List<Method> lf=new ArrayList<Method>();
		for (Class<?> superClass = clazz; 
				superClass!=null&&superClass != Object.class; 
				superClass = superClass.getSuperclass()) {
				Method[] f=superClass.getDeclaredMethods();
				for (int j=0;j<f.length;j++){
					lf.add(f[j]);
				}
		}
		return lf;
	}
	/**获取一个属性的set方法*/
	public static List<Method> getAllSettersIncludeSuperClass(final Class<?> clazz) {
		List<Method> ls=new ArrayList<Method>();
		for (Class<?> superClass = clazz; 
				superClass!=null&&superClass != Object.class; 
				superClass = superClass.getSuperclass()) {
				Method[] methods=superClass.getDeclaredMethods();
				for (int i=0;i<methods.length;i++){
					Method m=methods[i];
					if(m.getName().startsWith(SETMETHOD_PREFIX)){
						Class <?>[] paramTypes=m.getParameterTypes();
						if(paramTypes.length==1){
							ls.add(m);
						}
					}
				}
		}
		return ls;

	}
	
}
