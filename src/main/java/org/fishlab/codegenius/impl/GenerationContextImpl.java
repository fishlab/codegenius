package org.fishlab.codegenius.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.Project;
import org.fishlab.codegenius.util.PackageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerationContextImpl implements GenerationContext{
	private static Logger log = LoggerFactory.getLogger(GenerationContextImpl.class);
	private String destination;
	private Project project;
	private List<Class<?>> entities =new ArrayList<Class<?>>();
	
	@Override
	public void setOutputDistination(String dest) {
		this.destination=dest;
	}
	
	@Override
	public void setProject(Project project) {
		this.project=project;
	}
	@Override
	public Project getProject() {
		return this.project;
	}

	@Override
	public List<Class<?>> getEntities() {
		return this.entities;
	}

	@Override
	public void addEntity(Class<?> entity) {
		entities.add(entity);
	}

	@Override
	public String getOutputDistination() {
		return this.destination;
	}

	@Override
	public void scanPackage(String packageName) {
		Set<Class<?>> classes =PackageUtil.getClasses(packageName);
		log.info("{} {} found",classes.size(),classes.size()>1?"classes":"class");
		this.entities.addAll(classes);
	}


}
