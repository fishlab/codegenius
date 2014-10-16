package org.fishlab.codegenius;

import java.util.List;

/**@author Black Lotus
 * 上下文内容
 * */
public interface GenerationContext {
	/*输出路径*/
	public void setOutputDistination(String dest);
	public String getOutputDistination();
	/**项目*/
	public void setProject(Project project);
	
	public Project getProject();
	/**实体类*/
	public List<Class<?>> getEntities();
	
	public void scanPackage(String packageName);
	
	public void addEntity(Class<?> entity);
	
	
}
