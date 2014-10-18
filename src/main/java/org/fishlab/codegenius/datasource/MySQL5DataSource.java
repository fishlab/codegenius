package org.fishlab.codegenius.datasource;

import java.io.IOException;

import org.fishlab.codegenius.DataSource;
import org.fishlab.codegenius.util.prefs.Preference;

public class MySQL5DataSource extends DataSource{
	public MySQL5DataSource(String url, String user, String password) {
		super(url, user, password);
		this.name="MySQL5";
		try {
			Preference prefs=this.loadDatasourcePrefs("MySQL");
			this.driver=prefs.getString("driver");
			System.out.println("driver:"+driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}