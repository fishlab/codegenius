package org.fishlab.codegenius.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.fishlab.app.framework.Autowired;
import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.impl.CodeGeniusDescrition;
import org.fishlab.codegenius.util.PackageName;

/**
 * 用于 模板:数据:文件=1:1:1的批量生成器<br>
 * project已被默认放到template context中
 * @author Black Lotus
 * */
public abstract class BatchTemplateBasedGenerator extends AbstractGenerator{
	@Autowired VelocityEngine velocityEngine;
	@Autowired CodeGeniusDescrition cg;
	
	public class GenerationTask{
		public GenerationTask() {
		}
		public String templateName;
		public Map<String,Object> model;
		public String outputFileName;
	}
	
//	protected String getTemplateInTheSamePackage(Class <?> clazz,String name){
//		return PackageName.fromClass(clazz).toPath()+name;
//	}
	protected String getTemplateInTheSamePackage(String name){
		return PackageName.fromClass(this.getClass()).toPath()+"/"+name;
	}
	
	@Override
	public void doGenerate(GenerationContext context) throws Exception {
		for (GenerationTask task :this.getGenerationTasks(context)){
			this.generateOne(context,task);
		}
	}
	protected abstract List<GenerationTask> getGenerationTasks(GenerationContext context);
	private void generateOne(GenerationContext context, GenerationTask task) throws IOException {
		String fileName = context.getOutputDistination()+"/"+task.outputFileName;
		File outputFile =new File(fileName);
//		TODO:if file exists	
		this.prepareForOutput(outputFile);		
		Template tpl=this.velocityEngine.getTemplate(getTemplateInTheSamePackage(task.templateName));
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("cg", cg);
		velocityContext.put("project", context.getProject());
		Map<String,Object> model =task.model;
		if (model!=null){
			for (String key:model.keySet()){
				velocityContext.put(key, model.get(key));
			}
		}
		Writer fw=new OutputStreamWriter(new FileOutputStream(outputFile));
		tpl.merge(velocityContext, fw);
		fw.flush();
		fw.close();
	}

}
