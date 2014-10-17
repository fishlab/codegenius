package org.fishlab.codegenius.test;


import org.fishlab.codegenius.datasource.MySQL5DataSource;
import org.fishlab.codegenius.util.prefs.Preference;
import org.junit.Assert;
import org.junit.Test;

public class TestDataSourcePreference {
	@Test
	public void case_1() throws Exception{
		Preference prefs=new MySQL5DataSource("","","").loadDatasourcePrefs("MySQL");
		Assert.assertNotNull(prefs);
	}
}
