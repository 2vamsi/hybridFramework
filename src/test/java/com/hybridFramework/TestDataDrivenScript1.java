package com.hybridFramework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridFramework.testBase.TestBase;

public class TestDataDrivenScript1 extends TestBase {

	@DataProvider(name="testData")
	public Object [][] dataSource() {
		return getData("TestData.xlsx", "Registration");
	}
	
	@Test(dataProvider="testData")
	public void testLogin(String var1, String var2, String var3, String var4, String var5, String var6) {
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
		System.out.println(var4);
		System.out.println(var5);
		System.out.println(var6);
		
	}
}
