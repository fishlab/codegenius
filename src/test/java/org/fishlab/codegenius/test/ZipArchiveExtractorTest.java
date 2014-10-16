package org.fishlab.codegenius.test;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.base.ZipArchiveExtractor;

public class ZipArchiveExtractorTest extends ZipArchiveExtractor{

	@Override
	protected String getBaseOutputFolder(GenerationContext context) {
		return context.getProject().getResourceFolder()+"/";
	}

	@Override
	protected String[][] getExtractionOptions() {
		return new String[][]{
			{"org/fishlab/codegenius/res/WebContent.zip",".*","extract/"},
			{"org/fishlab/codegenius/res/WebContent.zip",".*\\.txt","extract-txt/"}
		};
	}

}
