package org.fishlab.codegenius.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.fishlab.app.framework.Autowired;
import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.impl.CodeGeniusDescrition;
import org.fishlab.codegenius.util.PackageName;
/**@author Black Lotus
 * 用于 模板:数据:文件=1:1:1的生成器
 * */
public abstract class TemplateBasedGenerator extends AbstractGenerator{
	@Autowired VelocityEngine velocityEngine;
	@Autowired CodeGeniusDescrition cg;
	protected String getTemplateInTheSamePackage(Class <?> clazz,String name){
		return PackageName.fromClass(clazz).toPath()+"/"+name;
	}
	protected String getTemplateInTheSamePackage(String name){
		return PackageName.fromClass(this.getClass()).toPath()+"/"+name;
	}
	protected abstract String getOutputFileName(GenerationContext context);
	
	protected abstract String getTemplateName();
	
	protected abstract void setTemplateProperties(GenerationContext context,Map<String, Object> model);
	
	public void doGenerate(GenerationContext context) throws Exception{
		String fileName = context.getOutputDistination()+"/"+this.getOutputFileName(context);
		File outputFile =new File(fileName);
//		TODO:if file exists	
		this.prepareForOutput(outputFile);		
		Template tpl=this.velocityEngine.getTemplate(getTemplateInTheSamePackage(this.getTemplateName()));
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("cg", cg);
		velocityContext.put("project", context.getProject());
		Map<String,Object> model = new HashMap<String,Object>();
		this.setTemplateProperties(context,model);
		for (String key:model.keySet()){
			velocityContext.put(key, model.get(key));
		}
		Writer fw=new OutputStreamWriter(new FileOutputStream(outputFile));
		tpl.merge(velocityContext, fw);
		fw.flush();
		fw.close();
	}

}
