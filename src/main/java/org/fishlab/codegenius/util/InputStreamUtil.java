package org.fishlab.codegenius.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtil {
	private static final int BUFFER_SIZE=1024*4;
	public static InputStream toMarkable(InputStream ins) throws IOException{
		ByteArrayOutputStream bos=new ByteArrayOutputStream(BUFFER_SIZE);
		int read=0;
		byte[] buf=new byte[BUFFER_SIZE];
		while ( (read=ins.read(buf))!=-1){//读取并输出
			ins.read(buf);
			bos.write(buf,0,read);
		}
		bos.flush();
		bos.close();
		//转化为可标记的字节流
		ByteArrayInputStream bin=new ByteArrayInputStream(bos.toByteArray());
		return bin;
	}

}
