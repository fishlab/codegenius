package org.fishlab.codegenius;

import java.io.IOException;
import java.io.InputStream;

import org.fishlab.codegenius.util.prefs.Preference;
import org.fishlab.codegenius.util.prefs.PreferenceManager;

public abstract class DataSource  {
	protected String name;
	protected String driver;
	protected String url;
	protected String userName;
	protected String userPassword;
	
	public DataSource(String url,String user,String password){
		this.url=url;
		this.userName=user;
		this.userPassword=password;
	}
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}


	public String getUserPassword() {
		return userPassword;
	}
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}

	static String CONFIG_PATH="org/fishlab/codegenius/internal/prefs/datasource";
	public Preference loadDatasourcePrefs(String name) throws IOException{
		Preference prefs =null;
		InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PATH+"/"+name+".prefs");
		if(in!=null){
			prefs=PreferenceManager.loadPreference(in);
		}
		return prefs;
	}
	

}
