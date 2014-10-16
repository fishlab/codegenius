package org.fishlab.codegenius.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.fishlab.codegenius.GenerationContext;
/**@author Black Lotus
 * 复制某一类路径下的文件
 * */
public abstract class FileListCopyier extends AbstractGenerator{
	
	protected abstract String[][] getInputAndOutputFileNameMappings();
	
	protected abstract String getBaseClasspath();
	protected abstract String getBaseOutputFolder(GenerationContext context);
	
	@Override
	public void doGenerate(GenerationContext context) throws Exception {
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
//		String baseClasspath =context.getOutputDistination()+"/"+ this.getBaseClasspath();
		String baseClasspath = this.getBaseClasspath();
		String baseOutputFodler =this.getBaseOutputFolder(context);
		for (String[] mapping:this.getInputAndOutputFileNameMappings()){
			if (mapping.length==2){
				BufferedInputStream bis= new BufferedInputStream(currentClassLoader.getResourceAsStream(baseClasspath+mapping[0]));
				File outputFile=new File(baseOutputFodler+mapping[1]);
				this.prepareForOutput(outputFile);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));
				byte[] buf=new byte[1024*4];
				int read=0;
				while((read=bis.read(buf))!=-1){
					bos.write(buf,0,read);
				}
				bos.flush();
				bis.close();
				bos.close();
			}
		}
	}
	


}
