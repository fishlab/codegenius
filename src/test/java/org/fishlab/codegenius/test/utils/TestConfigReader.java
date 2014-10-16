package org.fishlab.codegenius.test.utils;

import java.io.File;

import org.fishlab.codegenius.util.prefs.Preference;
import org.fishlab.codegenius.util.prefs.PreferenceManager;
import org.junit.Test;

public class TestConfigReader {

	@Test
	public void test() throws Exception {
		Preference prefs;
		prefs = PreferenceManager.loadPreference(new File("genius/prefs/x.cg"));
		System.out.println(prefs.getString("skill"));
		prefs.emtpty();
		prefs.setBoolean("fuck", true);
		prefs.setFloat("you", 3.1415926f);
		prefs.setString("name", "lorem asd");
		PreferenceManager.savePreference(prefs, new File("genius/prefs/x2.cg"));
	}

}
