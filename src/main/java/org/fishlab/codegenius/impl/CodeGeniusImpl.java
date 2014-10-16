package org.fishlab.codegenius.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.app.VelocityEngine;
//import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.fishlab.app.framework.ApplicationContext;
import org.fishlab.app.framework.FrameworkException;
import org.fishlab.codegenius.Generator;
import org.fishlab.codegenius.CodeGenius;
import org.fishlab.codegenius.CodeGeniusException;
import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.util.StructuredGlobbingResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeGeniusImpl implements CodeGenius{
	private static Logger log = LoggerFactory.getLogger(CodeGenius.class);
	protected ApplicationContext appContext;
	protected List<Generator> generators;
	
	public CodeGeniusImpl(){
		this.appContext=new ApplicationContext();
		this.generators =new ArrayList<Generator>();
		this.registDefaultComponents();
	}

	private void registDefaultComponents() {
//		TODO:maybe some problem
//		this.appContext.regist(this);
		//VelocityEngine
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
		velocityEngine.setProperty(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
//		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//		velocityEngine.setProperty(VelocityEngine.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("structured.resource.loader.class", StructuredGlobbingResourceLoader.class.getName());
		velocityEngine.setProperty(VelocityEngine.RESOURCE_LOADER, "structured");
//		velocityEngine.setApplicationAttribute("cg", new CodeGeniusDescrition() );
		velocityEngine.init();
		//properties
		
		this.appContext.regist(velocityEngine);
		
	}
	
	@Override
	public void clean(String dest) throws CodeGeniusException {
		
	}


	@Override
	public void addGenerator(Class<? extends Generator> clazz) {
		this.appContext.regist(clazz);
	}
	
	public void addGenerators(List<Class<? extends Generator> >classes){
		for (Class<?> clazz :classes){
			this.appContext.regist(clazz);
		}
	}
	
	@Override
	public void addGenerator(Generator generator) {
		try {
			this.appContext.awareInstance(generator);
//			this.appContext.regist(generator);
			this.generators.add(generator);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}
//	@Override
//	public void addGenerators(List<Generator> generators) {
//		for (Generator g:generators){
//			this.addGenerator(g);
//		}
//	}

	@Override
	public void process(GenerationContext context) throws CodeGeniusException {
		this.processPrepare(context);
		
		List<Generator> gens=null;
		try {
			gens=this.appContext.findInstances(Generator.class);
			gens.addAll(this.generators);
			for (Generator cg:gens){
				log.info("{} is running...",cg.getName());
				cg.init(context);
				boolean genRequired = cg.isGenerateRequired();
				if (genRequired){
					log.info("{} doGenerating...",cg.getName());
					cg.doGenerate();
				}else{
					log.info("{} generating skiped",cg.getName());
				}
			}
			log.info("all generation done,have fun.");
		}catch(Exception e){
			log.error("generation error,please contact me");
			e.printStackTrace();
			throw new CodeGeniusException(e.getMessage());
		}
	
	}

	private void processPrepare(GenerationContext context) throws CodeGeniusException{
		File dest=new File(context.getOutputDistination());
		if(dest.exists()){
			if(!dest.isDirectory()){
				throw new CodeGeniusException("output path is not a directory");
			}
		}else{
			if (!dest.mkdirs() ){
				throw new CodeGeniusException("create output directory failed");
			}
		}
	}

	@Override
	public GenerationContext createContext() {
		return new GenerationContextImpl();
	}



}
