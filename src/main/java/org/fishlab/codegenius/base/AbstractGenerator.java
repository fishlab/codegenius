package org.fishlab.codegenius.base;

import java.io.File;
import java.io.IOException;

import org.fishlab.codegenius.Generator;
import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.util.prefs.Preference;
import org.fishlab.codegenius.util.prefs.PreferenceManager;

/**代码生成器基本类
*@author Black Lotus
* */
public abstract class AbstractGenerator implements Generator {
	private static final String PREFERENCE_FOLDER ="/_codegenius/generators/";
	private static final String PREFERENCE_SUFFIX =".prefs";
	protected String name;
	private GenerationContext context;
	
	@Override
	public String getName() {
		if (this.name==null)
			return this.getClass().getSimpleName();
		else
			return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void init(GenerationContext context) {
		this.context=context;
	}
	
	/**默认每次都需要生成**/
	public boolean isGenerateRequired(){
		return true;
	}
	
	
	/**这里可以记录生成的文件*/
	protected void prepareForOutput(File outputFile) throws IOException{
		if(!outputFile.exists()){
			File p = outputFile.getParentFile();
			if (p!=null){
				p.mkdirs();
			}
			outputFile.createNewFile();
		}
	}
	public void doGenerate() throws Exception{
		this.beforeGenrate();
		this.doGenerate(context);
		this.afterGenerate();
		this.savePreference();
	}
	
	protected void afterGenerate() {
		
	}
	protected void beforeGenrate() {
		
	}

	private Preference prefrence;
	private void savePreference() {
		/**保存配置*/
		if (this.prefrence!=null){
			File save=new File(context.getOutputDistination()+PREFERENCE_FOLDER+this.getName()+PREFERENCE_SUFFIX);
			try {
				PreferenceManager.savePreference(this.prefrence, save);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	protected Preference getPreference() {
		/**取得配置文件*/
		if (this.prefrence==null){
			try {
				File file= new File(context.getOutputDistination()+PREFERENCE_FOLDER+this.getName()+PREFERENCE_SUFFIX);
				this.prefrence = PreferenceManager.loadPreference(file);
			} catch (IOException e) {
				this.prefrence= PreferenceManager.enmtyPreference();
			}
		}
		return this.prefrence;
	}
	public abstract void doGenerate(GenerationContext context) throws Exception;
	
}
