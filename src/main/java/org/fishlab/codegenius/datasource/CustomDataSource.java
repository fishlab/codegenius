package org.fishlab.codegenius.datasource;

import org.fishlab.codegenius.DataSource;

public class CustomDataSource extends DataSource{

	public CustomDataSource(String name,String driver,String url, String user, String password) {
		super(url, user, password);
		this.name=name;
		this.driver=driver;
	}

}
