package org.fishlab.codegenius.util.prefs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.fishlab.codegenius.util.EncodingDetector;
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
		List<String> lss = FileUtils.readLines(file, enc);
		Preference prefs = new HashMapPreferenceImpl();
		String str;
		for (int i = 0; i < lss.size(); i++) {
			str = lss.get(i);
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
	
	public static void savePreference(Preference prefs,File file) throws IOException{
		List<String> lines =  new ArrayList<String>();
 		for (Preference.Entry entry: prefs.entryList()){
			lines.add(entry.getName()+"="+entry.getValue());
		}
		FileUtils.writeLines(file, lines);
		
	}
}
