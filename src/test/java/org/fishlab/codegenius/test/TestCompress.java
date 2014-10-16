package org.fishlab.codegenius.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveEntry;
//import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.junit.Assert;
import org.junit.Test;

public class TestCompress {
	@Test
	public void case1() throws IOException{
		ZipArchiveInputStream zipInput =new ZipArchiveInputStream(new FileInputStream("genius/test/archive-file.zip"));
		ArchiveEntry archiveEntry = null;
		int fileCount = 0;
		while ((archiveEntry = zipInput.getNextEntry()) != null) {
			String entryFileName = archiveEntry.getName();
			fileCount ++;
			System.out.println(entryFileName);
		}
		zipInput.close();
		Assert.assertEquals(3, fileCount);

	}

}
