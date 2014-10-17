package org.fishlab.codegenius.internal.generator.brief;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchTemplateBasedGenerator;
/**生成项目摘要，例如项目主页，每个页面的整体风格
 * */
public class ProjectBreviaryGenerator extends BatchTemplateBasedGenerator{
	private String controllerName="ProjectBreviaryController"; 
	
	@Override
	protected List<GenerationTask> getGenerationTasks(GenerationContext context) {
		Project project = context.getProject();
		GenerationTask navtmpl=new GenerationTask();
		navtmpl.templateName ="nav.vm.vm";
		navtmpl.outputFileName=project.getResourceFolder()+"/vtl/cg/nav.vm";
		
		List<Class<?>> classes =context.getEntities();
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("classes", classes);
		GenerationTask controller =new GenerationTask();
		controller.templateName="ProjectBreviaryController.java.vm";
		controller.model = model;
		controller.outputFileName =project.getSourceFolder()+"/"+project.getPackagePath()+"/controller/"+this.controllerName+".java";
		
		return Arrays.asList(controller,navtmpl);
	}

}
