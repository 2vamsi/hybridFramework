package com.hybridFramework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridFramework.testBase.TestBase;

public class TestDataDrivenScript extends TestBase {

	@DataProvider(name="testData")
	public Object [][] dataSource() {
		return getData("TestData.xlsx", "Registration");
	}
	
	@Test(dataProvider="testData")
	public void testLogin(String param1, String param2, String param3, String param4, String param5, String param6) {
		System.out.println("userName: - "+param1);
		System.out.println("password: - " + param2);
		System.out.println("runmode: -" + param3);
		System.out.println("userName: - "+param4);
		System.out.println("password: - " + param5);
		System.out.println("runmode: -" + param6);
		
	}
}
