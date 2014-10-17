package org.fishlab.codegenius.util;

import java.io.File;
import java.net.URISyntaxException;


public class FileUtils {
	public static File fromClasspath(String fn) throws URISyntaxException{
		File f=new File(Thread.currentThread().getContextClassLoader().getResource(fn).toURI());
		return f;
	}
	
}