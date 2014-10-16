package org.fishlab.codegenius.internal.generator.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchTemplateBasedGenerator;
import org.fishlab.codegenius.util.SetterUtil;

public class VmFileGenerator extends BatchTemplateBasedGenerator{


	@Override
	protected List<GenerationTask> getGenerationTasks(GenerationContext context) {
		Project project=context.getProject();
		List<GenerationTask> tasks=new ArrayList<GenerationTask>();
		for (Class<?> clazz:context.getEntities()){
			Map<String,Object> model =new HashMap<String,Object>();
			String var=clazz.getSimpleName().toLowerCase();
			
			model.put("class", clazz);
			model.put("var", var);
			List<String> filds=SetterUtil.getfieldsExcludeId(clazz);
			model.put("fields", filds);
			GenerationTask add=new GenerationTask();
			add.templateName="data-add.vm.vm";
			add.outputFileName=project.getResourceFolder()+"/vtl/data/"+var+"-add.vm";
			add.model=model;
			
			GenerationTask detail=new GenerationTask();
			detail.templateName="data-detail.vm.vm";
			detail.outputFileName=project.getResourceFolder()+"/vtl/data/"+var+"-detail.vm";
			detail.model=model;
			
			GenerationTask edit=new GenerationTask();
			edit.templateName="data-edit.vm.vm";
			edit.outputFileName=project.getResourceFolder()+"/vtl/data/"+var+"-edit.vm";
			edit.model=model;
			
			GenerationTask list=new GenerationTask();
			list.templateName="data-list.vm.vm";
			list.outputFileName=project.getResourceFolder()+"/vtl/data/"+var+"-list.vm";
			list.model=model;
			
			tasks.add(add);
			tasks.add(detail);
			tasks.add(edit);
			tasks.add(list);

		}
		return tasks;
	}




}
