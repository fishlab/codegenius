package org.fishlab.codegenius;

import java.util.ArrayList;
import java.util.List;

import org.fishlab.codegenius.impl.CodeGeniusImpl;
import org.fishlab.codegenius.internal.generator.SupportClassGenerator;
import org.fishlab.codegenius.internal.generator.WebContentExtractor;
import org.fishlab.codegenius.internal.generator.brief.ProjectBreviaryGenerator;
import org.fishlab.codegenius.internal.generator.configurations.GeneralConfigFileGenerator;
import org.fishlab.codegenius.internal.generator.configurations.SpringConfigFileGenerator;
import org.fishlab.codegenius.internal.generator.configurations.WebConfigFileGenerator;
import org.fishlab.codegenius.internal.generator.controller.ControllerGenerator;
import org.fishlab.codegenius.internal.generator.hibernate.HibernateMappingFileGenerator;
import org.fishlab.codegenius.internal.generator.mapper.MapperImplGenerator;
import org.fishlab.codegenius.internal.generator.mapper.MapperInterfaceGenerator;
import org.fishlab.codegenius.internal.generator.maven.POMFileGenerator;
import org.fishlab.codegenius.internal.generator.service.ServiceImplGenerator;
import org.fishlab.codegenius.internal.generator.service.ServiceInterfaceGenerator;
import org.fishlab.codegenius.internal.generator.template.VmFileGenerator;
import org.fishlab.codegenius.internal.generator.testing.SpringJunit4TestCaseGenerator;
import org.fishlab.codegenius.internal.generator.validaton.ValidationScriptGenerator;

/**保存生成器列表配置
* */
public class CodeGeniusConfiguration {
	static final List<Class<? extends Generator> > DEFAULT_GENERATOR = new ArrayList<Class<? extends Generator>>();
	static {
		DEFAULT_GENERATOR.add(POMFileGenerator.class);
		DEFAULT_GENERATOR.add(SupportClassGenerator.class);
		DEFAULT_GENERATOR.add(HibernateMappingFileGenerator.class);
		DEFAULT_GENERATOR.add(MapperInterfaceGenerator.class);
		DEFAULT_GENERATOR.add(MapperImplGenerator.class);
		DEFAULT_GENERATOR.add(ServiceInterfaceGenerator.class);
		DEFAULT_GENERATOR.add(ServiceImplGenerator.class);
		DEFAULT_GENERATOR.add(ProjectBreviaryGenerator.class);
		DEFAULT_GENERATOR.add(GeneralConfigFileGenerator.class);
		DEFAULT_GENERATOR.add(SpringConfigFileGenerator.class);
		DEFAULT_GENERATOR.add(ValidationScriptGenerator.class);
		DEFAULT_GENERATOR.add(WebConfigFileGenerator.class);
		DEFAULT_GENERATOR.add(WebContentExtractor.class);
		DEFAULT_GENERATOR.add(ControllerGenerator.class);
		DEFAULT_GENERATOR.add(VmFileGenerator.class);
		DEFAULT_GENERATOR.add(SpringJunit4TestCaseGenerator.class);
	}
	
	public static CodeGenius defaultGenius(){
		CodeGenius cg =new CodeGeniusImpl();
		cg.addGenerators(DEFAULT_GENERATOR);
		return cg;
	}
}
