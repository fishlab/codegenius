package org.fishlab.codegenius.base;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.fishlab.codegenius.GenerationContext;

/**
 * 从classpath里查找ZIP格式的压缩文件里提取符合一定条件的文件至输出指定目录
 * 
 * @author Balck Lotus
 * */
public abstract class ZipArchiveExtractor extends AbstractGenerator {
	private static final int BUFFER_SIZE=1024*4;
	protected abstract String getBaseOutputFolder(GenerationContext context);

	/** ExtractionOptions 为ExtractionOption的二维数组<br>
	 *  ExtractionOption  为一维数组,长度为3,分别代表 zipArchiveName,matcherRegex,extraFolder
	 * */
	protected abstract String[][] getExtractionOptions();

	protected void extractOne(String option[],String baseFolder) throws IOException{
		String zipArchiveName=option[0],
		matcherRegex=option[1],
		extraFolder=option[2];
//		File zipFile = new File(zipArchiveName);
//		InputStream fin = new FileInputStream(zipFile);
		InputStream fin = Thread.currentThread().getContextClassLoader().getResourceAsStream(zipArchiveName);
		ZipArchiveInputStream zin =new ZipArchiveInputStream(fin);
		ArchiveEntry archiveEntry = null;
		byte[] buf=new byte[BUFFER_SIZE];
		while ((archiveEntry = zin.getNextEntry()) != null) {
			String entryFileName = archiveEntry.getName();
			if (!archiveEntry.isDirectory()&&entryFileName.matches(matcherRegex)){
				String outputFilePath = baseFolder+extraFolder+entryFileName;
				File outputFile =new File (outputFilePath);
//				System.out.println("extracting "+outputFilePath);
				this.prepareForOutput(outputFile);
				BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream(outputFile));
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
			}
		}
		zin.close();
	}

	public void extractExample(String zipFilePath, String saveFileDir) {
		File file = new File(zipFilePath);
		if (file.exists()) {
			InputStream is = null;
			ZipArchiveInputStream zais = null;
			try {
				is = new FileInputStream(file);
				zais = new ZipArchiveInputStream(is);
				ArchiveEntry archiveEntry = null;
				while ((archiveEntry = zais.getNextEntry()) != null) {
					String entryFileName = archiveEntry.getName();
					String entryFilePath = saveFileDir + entryFileName;
					byte[] content = new byte[(int) archiveEntry.getSize()];
					zais.read(content);
					OutputStream os = null;
					try {
						File entryFile = new File(entryFilePath);
						os = new FileOutputStream(entryFile);
						os.write(content);
					} catch (IOException e) {
						throw new IOException(e);
					} finally {
						if (os != null) {
							os.flush();
							os.close();
						}
					}

				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (zais != null) {
						zais.close();
					}
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	@Override
	public void doGenerate(GenerationContext context) throws Exception {
		String baseFolder = context.getOutputDistination()+"/" +this.getBaseOutputFolder(context);
		for(String[] option:this.getExtractionOptions()){
			if (option.length==3){
				this.extractOne(option,baseFolder);
			}
		}
	}

}
