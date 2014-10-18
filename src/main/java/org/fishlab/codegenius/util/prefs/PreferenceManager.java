package org.fishlab.codegenius.util.prefs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.fishlab.codegenius.util.EncodingDetector;
import org.fishlab.codegenius.util.InputStreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PreferenceManager {
	private static Logger log = LoggerFactory
			.getLogger(PreferenceManager.class);

	public static Preference enmtyPreference(){
		return  new HashMapPreferenceImpl();
	}
	public static Preference loadPreference(File file) throws IOException {
		String enc = EncodingDetector.getCharset(file);
		log.info("detected file encoding:" + enc);
		List<String> lines = FileUtils.readLines(file, enc);
		return readAsLines(lines);
	}
	private static Preference readAsLines(List<String> lines) {
		Preference prefs = new HashMapPreferenceImpl();
		String str;
		for (int i = 0; i < lines.size(); i++) {
			str = lines.get(i);
			int p0, p1;
			p0 = str.indexOf("#");
			p1 = str.indexOf("=");
			if (p1 != -1 && (p0 == -1 || p1 < p0)) {
				String k, v;
				k = str.substring(0, p1).trim();
				p1++;
				if (p0 == -1) {
					v = str.substring(p1).trim();
				} else {
					v = str.substring(p1, p0).trim();
				}
				// System.out.println(k+"::"+v);
				prefs.setString(k, v);
			}
		}
		return prefs;
	}
	public static Preference loadPreference(InputStream input) throws IOException {
		List<String> lines= readInputStreamAsList(input);
		return readAsLines(lines);
	}
	
	public static List<String> readInputStreamAsList(InputStream input) throws IOException {
		List<String> lines=new ArrayList<String>();
		String enc=null;
		if (input.markSupported()){
			enc = EncodingDetector.getCharset(input,input.available());
		}else{
			InputStream markable= InputStreamUtil.toMarkable(input);
			input.close();
			input=markable;
			enc = EncodingDetector.getCharset(markable,markable.available());
		}
		BufferedReader bfr =new BufferedReader(new InputStreamReader(input,enc));
		String line=null;
		while((line=bfr.readLine())!=null){
			lines.add(line);
		}
		return lines;
	}
	public static void savePreference(Preference prefs,File file) throws IOException{
		List<String> lines =  new ArrayList<String>();
 		for (Preference.Entry entry: prefs.entryList()){
			lines.add(entry.getName()+"="+entry.getValue());
		}
		FileUtils.writeLines(file, lines);
		
	}
}
