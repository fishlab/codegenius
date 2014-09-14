package org.fishlab.codegenius;

import java.util.Map;

public class Project extends BasicCoordinate{
	
	private String name;
	private String packaging;
	private Map<String,Object> properties;
	
	private Map<String,Dependency> dependencies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Map<String, Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Map<String, Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	} 
	
	
	
}
