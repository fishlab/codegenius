package org.fishlab.codegenius.internal.generator;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.base.ZipArchiveExtractor;

public class WebContentExtractor extends ZipArchiveExtractor{

	@Override
	protected String getBaseOutputFolder(GenerationContext context) {
		return context.getProject().getResourceFolder()+"/";
	}

	@Override
	protected String[][] getExtractionOptions() {
		return new String[][]{
			{"org/fishlab/codegenius/res/WebContent.zip",".*",""},
		};
	}

}
