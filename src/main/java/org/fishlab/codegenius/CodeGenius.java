package org.fishlab.codegenius;

public interface CodeGenius {
	
	public Project getProject();
	public void addGenerator();
	
	public void process() throws CodeGeniusException;
	public void clean() throws CodeGeniusException;
	
//	public void 
	public void setOutputDistination(String dest);
	
}
