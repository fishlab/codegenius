package org.fishlab.codegenius.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.fishlab.codegenius.GenerationContext;
import org.fishlab.codegenius.util.EncodingDetector;

//import com.lowagie.text.Document;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.rtf.RtfWriter2;

/**
 * *将ZIP文件里面的指定文档合并成一个文件，支持ZIP文件中多种不同常用编码方式
 * @author Black Lotus
 *
 */
public abstract class ZIPArchiveExtractAndMergeGenerator extends AbstractGenerator{

	private static final int BUFFER_SIZE=1024*4;
	protected abstract String getBaseOutputFolder(GenerationContext context);

	/** ExtractionOptions 为ExtractionOption的二维数组<br>
	 *  ExtractionOption  为一维数组,长度为3,分别代表 zipArchiveName,matcherRegex,outfileName
	 * */
	protected abstract String[][] getExtractionOptions();

	protected void extractOne(String option[],Writer writer) throws IOException{
		String zipArchiveName=option[0],
		matcherRegex=option[1];
		File zipFile = new File(zipArchiveName);
		InputStream fin=null;
		if(zipFile.exists()){
			fin = new FileInputStream(zipFile);
		}else{
			fin=Thread.currentThread().getContextClassLoader().getResourceAsStream(zipArchiveName);
		}
		if (fin==null) return;
		ZipArchiveInputStream zin =new ZipArchiveInputStream(fin);
		ArchiveEntry archiveEntry = null;
		byte[] buf=new byte[BUFFER_SIZE];
		while ((archiveEntry = zin.getNextEntry()) != null) {
			String entryFileName = archiveEntry.getName();
			if (!archiveEntry.isDirectory()&&entryFileName.matches(matcherRegex)){
//				BufferedOutputStream bos= new BufferedOutputStream(output);
				ByteArrayOutputStream bos=new ByteArrayOutputStream(BUFFER_SIZE);
				long fsize=archiveEntry.getSize();
				long totalRead=0;
				int read=0;
				while (totalRead <fsize){//读取并输出
					read = zin.read(buf);
					totalRead += read;
					bos.write(buf,0,read);
					
				}
				bos.flush();
				bos.close();
				//编码转化
				ByteArrayInputStream bin=new ByteArrayInputStream(bos.toByteArray());
				String charset=EncodingDetector.getCharset(bin, (int)fsize);
				InputStreamReader ir=new InputStreamReader(bin,charset);
				System.out.println(charset);
				char[] cbuf=new char[BUFFER_SIZE];
				while((read=ir.read(cbuf))!=-1){
					writer.write(cbuf,0,read);
				};
			}
		}
		zin.close();
		writer.flush();
		writer.close();
	}

	@Override
	public void doGenerate(GenerationContext context) throws Exception {
		String baseFolder = context.getOutputDistination()+"/" +this.getBaseOutputFolder(context);
		for(String[] option:this.getExtractionOptions()){
			if (option.length==3){
				File file=new File(baseFolder+option[2]);
				this.prepareForOutput(file);
//				合并为word TODO:word中会出现 ?
//				File file2=new File(baseFolder+option[2]+".doc");
//				Document document = new Document(PageSize.A4);
//				RtfWriter2.getInstance(
//						document,new FileOutputStream(file));
//				document.open();
//				Paragraph para=new Paragraph(writer.toString());
//				document.add(para);
//				document.close();
				
				StringWriter writer=new StringWriter();
				this.extractOne(option,writer);
				FileUtils.write(file, writer.toString());
			}
		}
	}
	

}
