package org.fishlab.codegenius;

import java.util.List;

public interface CodeGenius {
	
//	public Project getProject();
	public GenerationContext createContext();
	/**添加一个代码生成器*/
	public void addGenerator(Generator generator);
//	public void addGenerators(List<Generator> generators);
	public void addGenerator(Class<? extends Generator> clazz);
	public void addGenerators(List<Class<? extends Generator> >classes);
	/**执行*/
	public void process(GenerationContext context) throws CodeGeniusException;
	/**清除*/
	public void clean(String dest) throws CodeGeniusException;
	
	
}
