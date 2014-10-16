package org.fishlab.codegenius.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**简单文件搜索工具
 * */
public class FileFinder {
	
	 static class FilenameRegexFilter  implements FilenameFilter{
		private String regex;
		FilenameRegexFilter (String regex){
			this.regex=regex;
		}
		@Override
		public boolean accept(File dir, String name) {
			return name.matches(regex);
		}  
		
	}
	 
	 /**只适用于非jar包中的文件资源*/
	public static List<File> findFileFromClasspath(String baseFodler,String regex){
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		List<File> results =new ArrayList<File>();
		try {
			Enumeration<URL> urls = currentClassLoader.getResources(baseFodler);
			FilenameFilter filter =new FilenameRegexFilter(regex);
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				System.out.println(url);
				File folder = new File(url.toURI());
				if (folder.isDirectory()){
//					folder.li
					File[] found =folder.listFiles(filter);
					if (found.length>0){
						results.addAll(Arrays.asList( found) );
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return results;
	}
	 /**文件系统*/
	public static List<File> findFilefromFilesystem(String baseFodler,String regex){
		List<File> results =new ArrayList<File>();
		File base = new File(baseFodler);
		FilenameFilter filter = new FilenameRegexFilter(regex);
		if (base.isDirectory()) {
			File[] found = base.listFiles(filter);
			if (found.length > 0) {
				results.addAll(Arrays.asList(found));
			}
		}

		return results;
	}

}
