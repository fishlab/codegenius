package org.fishlab.codegenius.test;

import org.fishlab.codegenius.CodeGenius;
import org.fishlab.codegenius.CodeGeniusException;
import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.impl.CodeGeniusImpl;
import org.fishlab.codegenius.impl.GenerationContextImpl;
import org.fishlab.codegenius.internal.generator.hibernate.HibernateMappingFileGenerator;
import org.fishlab.codegenius.internal.generator.maven.POMFileGenerator;
import org.fishlab.codegenius.internal.generator.service.ServiceInterfaceGenerator;
import org.fishlab.codegenius.testdata.Person;
import org.junit.Test;

public class TestCodeGenius {
	CodeGenius cg =new CodeGeniusImpl();
	
	@Test
	public void test(){
		cg.addGenerator(HibernateMappingFileGenerator.class);
		cg.addGenerator(ServiceInterfaceGenerator.class);
		cg.addGenerator(POMFileGenerator.class);
		GenerationContext context= new GenerationContextImpl();
		context.setOutputDistination("../cgdemo");
		context.addEntity(Person.class);
		context.setProject(this.demoProject());
		try {
			cg.process(context);
		} catch (CodeGeniusException e) {
			e.printStackTrace();
		}
	}
	private Project demoProject() {
		Project project=new Project();
		project.setName("cg_demo");
		project.setGroupId("org.fishlab.codegenius");
		project.setArtifactId("cg_demo");
		project.setVersion("1.0.0-demo");
//		project.setPackaging("war");
		project.setProperty("hibernate.version", "4.1.7.Final");
		project.setProperty("spring.version","3.2.5.RELEASE");
		project.setProperty("test","测试一下");
		return project;
	}
}
