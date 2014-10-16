package org.fishlab.codegenius.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.fishlab.codegenius.util.FileFinder;
import org.junit.Test;

public class TestFileFinder {
	@Test
	public void a(){
		List<File> ls= FileFinder.findFilefromFilesystem(
				"doc/html", ".*\\.html");
		for (File f:ls){
			System.out.println(f.getAbsolutePath());
		}
		if (ls.size()>0){
			try {
				System.out.println(FileUtils.readFileToString(ls.get(0)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
