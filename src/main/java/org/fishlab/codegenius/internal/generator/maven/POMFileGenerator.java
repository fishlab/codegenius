package org.fishlab.codegenius.internal.generator.maven;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.fishlab.app.framework.Autowired;
import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.base.TemplateBasedGenerator;
import org.fishlab.codegenius.impl.CodeGeniusDescrition;

/**生成pom.xml文件,不属于 CodeGenerator
 * */
public class POMFileGenerator extends TemplateBasedGenerator{
	@Autowired VelocityEngine velocityEngine;
	@Autowired CodeGeniusDescrition cg;
	
	private String outputFileName = "pom.xml";
	private String templateName=  "pom.xml.vm";

	@Override
	protected String getTemplateName() {
//		return this.getTemplateInTheSamePackage(this.getClass(), "pom.xml.vm");
		return this.templateName;
	}
	
	@Override
	protected String getOutputFileName(GenerationContext context) {
		return this.outputFileName;
	}

	@Override
	protected void setTemplateProperties(GenerationContext context,
			Map<String, Object> model) {
		
	}



	

	
}
