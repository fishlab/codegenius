package org.fishlab.codegenius.util;

public class Property {
	private String name;
	private Object value;
	
	public Property(String name,String value){
		this.name=name;
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
