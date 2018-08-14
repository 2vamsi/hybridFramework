package com.hybridFramework;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridFramework.helper.Browser.BrowserHelper;
import com.hybridFramework.testBase.Config;
import com.hybridFramework.testBase.TestBase;

public class SampleScript1 extends  Config {


	
	@Test
	public void sample() throws IOException {
		//Config test = new Config();
		//test.loadPropertiesFile();
		//String b = test.getBrowser();
		//System.out.println(test.getWebsite());
		
		//test.getBrowser("Chrome");
		
		BrowserHelper bh = new BrowserHelper(driver);
		bh.open();
		
	}
	
/*	public static void main(String[] args) {
		
		Config test = new Config();
		String browser = test.getBrowser();
		System.out.println(browser);
		
	}*/
}
