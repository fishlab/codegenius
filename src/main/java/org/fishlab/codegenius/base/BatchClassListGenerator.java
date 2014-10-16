package org.fishlab.codegenius.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
/**@author Black Lotus
 * 用于 模板:数据:文件=1:m:m的批量生成器,适用于为每个类批量生成的情况
 * */
public abstract class BatchClassListGenerator extends BatchTemplateBasedGenerator {

	public abstract void setModelProperties(Map<String,Object> context, Class<?> clazz) ;

	public abstract String getOutputFileName(Project project, Class<?> clazz);

	@Override
	protected List<GenerationTask> getGenerationTasks(GenerationContext context) {
		List<GenerationTask> tasks = new ArrayList<GenerationTask>();
		Project project = context.getProject();
		for (Class<?> clazz : context.getEntities()) {
			Map<String, Object> model = new HashMap<String, Object>();
			this.setModelProperties(model, clazz);
			GenerationTask task = new GenerationTask();
			task.templateName = this.getTemplateName();
			task.model = model;
			task.outputFileName = this.getOutputFileName(project, clazz);
			tasks.add(task);
		}
		return tasks;
	}

	protected abstract String getTemplateName();

}