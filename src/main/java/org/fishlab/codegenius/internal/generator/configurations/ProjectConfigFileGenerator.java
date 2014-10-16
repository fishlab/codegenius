package org.fishlab.codegenius.internal.generator.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchTemplateBasedGenerator;


public abstract class ProjectConfigFileGenerator extends BatchTemplateBasedGenerator{

	@Override
	protected List<GenerationTask> getGenerationTasks(GenerationContext context) {
		List<GenerationTask> tasks = new ArrayList<GenerationTask>();
		Project project = context.getProject();
		for (String[] templateMapping : this.getTemplateMappings()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("project", context.getProject());
			GenerationTask task = new GenerationTask();
			task.templateName = templateMapping[0];
			task.model = model;
			task.outputFileName = getBaseFolder(project)+templateMapping[1];
			tasks.add(task);
		}
		return tasks;
	}
	protected abstract String getBaseFolder(Project project);
	protected abstract String[][] getTemplateMappings();
}
