package com.hybridFramework.testBase;

public class Config extends TestBase {

	// this class is basically a config reader
	
	//whereever we want the data from config.properties, we can create an object of config and call the method
	//	config.getPassword();
	
	public String getUserName() {
		return OR.getProperty("Username");
	}
	
	public String getPassword() {
		return OR.getProperty("Password");
	}
	
	public String getWebsite() {
		return OR.getProperty("Website");
	}
	
	public int getPageLoadTimeOut() {
		return Integer.parseInt(OR.getProperty("PageLoadTimeOut"));
	}
	
	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("ImplicitWait"));
	}
	
	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("ExplicitWait"));
	}
	
	public String getDBType() {
		return OR.getProperty("DataBase.Type");
	}
	
	
	public String getDbConnStr() {
		return OR.getProperty("DtaBase.ConnectionStr");
	}
	
	public String getBrowser() {
		return OR.getProperty("Browser");
	}
	
/*	public static void main(String[] args) {
		
		System.out.println(Config.class);
	}*/
}
