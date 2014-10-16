package org.fishlab.codegenius;

public interface Generator {

	String getName();

	void setName(String name);

	/**是否需要执行生成*/
	boolean isGenerateRequired();
	
	void init(GenerationContext context);
	/**执行的生成过程*/
	void doGenerate() throws Exception;
	

}