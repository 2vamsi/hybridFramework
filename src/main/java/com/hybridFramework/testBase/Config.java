package com.hybridFramework.testBase;

import java.util.Properties;

public class Config extends TestBase {

	// this class is basically a config reader
	
	//where-ever we want the data from config.properties, we can create an object of config and call the method
	//	eg :   config.getPassword();
	
	
	private Properties OR;
	public Config(Properties OR) {
		this.OR = OR;
	}
	
	public String getUserName() {
		return OR.getProperty("Username");
	}
	
	public String getPassword() {
		return OR.getProperty("Password");
	}
	
	public String getWebsite() {
		return OR.getProperty("Website");
	}
	
	// when we read from config file, values will be string
	// we are converting them into integer so that we can pass them to wait helper methods
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
