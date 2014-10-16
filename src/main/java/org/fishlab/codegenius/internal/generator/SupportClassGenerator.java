package org.fishlab.codegenius.internal.generator;

import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.base.FileListCopyier;
import org.fishlab.codegenius.util.prefs.Preference;

public class SupportClassGenerator extends FileListCopyier{

	@Override
	public boolean isGenerateRequired() {
		return !this.getPreference().isTrue("flag");
	}

	@Override
	protected String[][] getInputAndOutputFileNameMappings() {
		String flist[] = {
				"orm/Mapper.java.source",
				"service/AspectServiceLogging.java.source",
				"test/CommonTest.java.source",
				"test/CustomTestWatcher.java.source",
				//spring mvc
				"spring/mvc/AuthorizationException.java.source",
				"spring/mvc/AuthorizationInterceptor.java.source",
				"spring/mvc/CharsetInterceptor.java.source",
				"spring/mvc/DateEditor.java.source",
				"spring/mvc/MultipleViewResolver.java.source",
				//spring test
				"spring/test/ServiceTest.java.source",
				"spring/test/TransactionalSpringTest.java.source",
		};
		String[][] mappings =new String [flist.length][2];
		for (int i = 0; i < flist.length; i++) {
			String file = flist[i];
			mappings[i][0]=file;
			mappings[i][1]=file.replace(".source", "");
		}
//		return new String[][]{
//				{"suport/spring/test","zip/urls.zip"},
//				{"source/Support.java.source","source/Support.java"}
//		};
		return mappings;
	}

	@Override
	protected String getBaseClasspath() {
		return "org/fishlab/codegenius/res/support/";
	}

	@Override
	protected String getBaseOutputFolder(GenerationContext context) {
//		return "support/";
		return context.getProject().getSourceFolder()+"/org/fishlab/codegenius/support/";
	}

	@Override
	protected void afterGenerate() {
		Preference prefs= this.getPreference();
//		开发模式
		prefs.emtpty();
//		prefs.setBoolean("flag", true);
	}

//	@Override
//	public void doGenerate(GenerationContext context) throws Exception {
////		do nothing
//	}



}
