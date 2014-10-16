package org.fishlab.codegenius.test;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.base.FileListCopyier;
/**@author Black Lotus
 * 复制某一类路径下的文件,测试
 * */
public class FileListCopyingTestGenerator extends FileListCopyier{

	private String[][] mappings={
			{"WebContent.zip","WebContent-copied.zip"},
	};
	@Override
	protected String[][] getInputAndOutputFileNameMappings() {
		return this.mappings;
	}

	@Override
	protected String getBaseClasspath() {
		return "org/fishlab/codegenius/res/";
	}

	@Override
	protected String getBaseOutputFolder(GenerationContext context) {
		return context.getProject().getResourceFolder()+"/";
	}

}
