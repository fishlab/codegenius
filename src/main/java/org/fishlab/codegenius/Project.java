package org.fishlab.codegenius;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**@author Black Lotus
 * 项目描述
 * */
public class Project extends BasicCoordinate{
	private String name="";
	private String sourceFolder ="src";
	private String testFolder ="test";
	private String configFolder="config";
	private String resourceFolder="WebContent";
	private DataSource dataSource;
	private String packaging;
	private Map<String,Object> properties = new HashMap<String,Object>();
	
	private List<Dependency> dependencies=new ArrayList<Dependency>();

	
	public Project(){}
	public Project(String fullName){
		int p0=fullName.lastIndexOf(".");
		if(p0!=-1){
			this.name=fullName.substring(p0+1);
			super.setArtifactId(name);
			super.setGroupId(fullName.substring(0,p0) );
		}else{
			super.setGroupId(fullName);
		}
	}
	
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
	public void setProperty(String name,Object propertie) {
		this.properties.put(name, propertie);
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	public void addDependency(Dependency dependency) {
		this.dependencies.add(dependency);
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	} 
	
	public String getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public String getTestFolder() {
		return testFolder;
	}

	public void setTestFolder(String testFolder) {
		this.testFolder = testFolder;
	}

	public String getConfigFolder() {
		return configFolder;
	}

	public void setConfigFolder(String configFolder) {
		this.configFolder = configFolder;
	}

	public String getResourceFolder() {
		return resourceFolder;
	}

	public void setResourceFolder(String resourceFolder) {
		this.resourceFolder = resourceFolder;
	}

	public String getPackageName(){
		return this.getGroupId()+"."+this.getArtifactId();
	}
	
	public String getPackagePath(){
		return this.getPackageName().replace(".", "/");
	}
	
	public String getProjectSourceFolder(){
		return this.sourceFolder+"/"+this.getPackagePath();
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
