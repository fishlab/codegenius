package org.fishlab.codegenius.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class TestClasspathFileReader {
	@Test
	public void case1() throws IOException{
//		InputStream is=this.getClass().getResourceAsStream("TestLog.class");
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
//		InputStream is =ClassLoader.getSystemResourceAsStream("org/fishlab/codegenius/res/urls.zip") ;
		InputStream is =currentClassLoader.getResourceAsStream ("org/fishlab/codegenius/res/WebContent.zip") ;
		System.out.println(is.available());
	
	}

}
