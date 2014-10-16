package org.fishlab.codegenius.util;

public class PackageName {
	private String name;
	public static PackageName fromClass(Class<?> clazz){
		PackageName pn=new PackageName();
		String className=clazz.getName();
		int p0=className.lastIndexOf(".");
		if(p0!=-1){
			pn.name=className.substring(0,p0);
		}else{
			pn.name=className;
		}
		return pn;
	}
	
	public String getName() {
		return name;
	}

	public String toPath(){
		return name.replace(".", "/");
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
}
