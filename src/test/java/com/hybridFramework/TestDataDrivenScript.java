package com.hybridFramework;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridFramework.testBase.TestBase;

public class TestDataDrivenScript extends TestBase {

	@DataProvider(name="testData")
	public Object [][] dataSource() {
		return getData("TestData.xlsx", "LoginTestData");
	}
	
	@Test(dataProvider="testData")
	public void testLogin(String userName, String password, String runMode) {
		System.out.println("userName: - "+userName);
		System.out.println("password: - " + password);
		System.out.println("runmode: -" + runMode);
		//driver.findElement(By.xpath("")).sendKeys(userName);
		//driver.findElement(By.xpath("")).sendKeys(password);
		
	}
}
