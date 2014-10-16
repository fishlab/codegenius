package org.fishlab.codegenius.internal.generator.validaton;

import java.util.Map;

import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.base.BatchClassListGenerator;
import org.fishlab.codegenius.util.SetterUtil;

public class ValidationScriptGenerator extends BatchClassListGenerator{
	private String folder="/scripts/validation/";
	private String suffix="-form-validation.js";
	private String template="form-validation.js.vm";
	@Override
	public void setModelProperties(Map<String, Object> context, Class<?> clazz) {
		context.put("fields",SetterUtil.getfieldsExcludeId(clazz));
	}

	@Override
	public String getOutputFileName(Project project, Class<?> clazz) {
		return project.getResourceFolder()+this.folder+clazz.getSimpleName().toLowerCase()+this.suffix;
	}

	@Override
	protected String getTemplateName() {
		return this.template;
	}

}
