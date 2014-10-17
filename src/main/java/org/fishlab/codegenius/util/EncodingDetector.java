package org.fishlab.codegenius.util;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
//import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.Charset;

public class EncodingDetector {
	public static final String DEFAULT_ENCODING="UTF-8";
	static CodepageDetectorProxy proxy;
	static{
		proxy = CodepageDetectorProxy.getInstance();
		// proxy.add(new ParsingDetector(false)); //检测XML HTML等文件编码需要用到antlr
		proxy.add(JChardetFacade.getInstance());
		proxy.add(ASCIIDetector.getInstance());
		proxy.add(UnicodeDetector.getInstance());
	}
	
	public static String getCharset(File f) throws MalformedURLException, IOException {
		String result = DEFAULT_ENCODING;
		Charset cset = null;
		cset = proxy.detectCodepage(f.toURI().toURL());
		if (cset != null) {
			result = cset.name();
		}
		return result;
	}
	
	public static String getCharset(InputStream in,int len) throws IOException{
		Charset cset = null;
		cset = proxy.detectCodepage(in,len);
		return cset.toString();
	}
}
