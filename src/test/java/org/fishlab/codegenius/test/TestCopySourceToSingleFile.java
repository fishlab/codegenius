package org.fishlab.codegenius.test;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.base.ZIPArchiveExtractAndMergeGenerator;

public class TestCopySourceToSingleFile extends ZIPArchiveExtractAndMergeGenerator{

	@Override
	protected String getBaseOutputFolder(GenerationContext context) {
		return "/test-word/";
	}

	@Override
	protected String[][] getExtractionOptions() {
		return new String[][]{
				{"host.zip",".*\\.java","src-host"}
		};
	}

}
