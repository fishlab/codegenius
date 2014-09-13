package org.fishlab.codegenius.test;

import java.io.File;

import org.junit.Test;

public class TestMkdirinotherProject {
	@Test
	public void test(){
		File dir=new File("../codegenius_web");
		if(dir.exists()){
			for(File f:dir.listFiles()){
				System.out.println(f.getName());
			}
		}
		
	}

}
