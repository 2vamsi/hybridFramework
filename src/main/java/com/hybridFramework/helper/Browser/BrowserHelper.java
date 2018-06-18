package com.hybridFramework.helper.Browser;

import java.util.LinkedList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.hybridFramework.helper.Logger.LoggerHelper;

public class BrowserHelper {

	
	private WebDriver driver; 
	private Logger Log = LoggerHelper.getLogger(BrowserHelper.class);
	
	
	public BrowserHelper (WebDriver driver) {
		this.driver = driver;
		Log.debug("BrowserHelper :  "+ this.driver.hashCode());
	}
	
	
	public void goBack() {
		driver.navigate().back();
		Log.info("");
	}
	
	
	
	public void goForward() {
		driver.navigate().forward();
		Log.info("");
	}
	
	
	
	public void refresh() {
		driver.navigate().refresh();
		Log.info("");
	}
	
	
	
	public Set<String> getWindowHandles() {
		Log.info("");
		return driver.getWindowHandles();
	}

	
	
	public void SwitchToWindow(int index) {
		LinkedList<String> windowsID = new LinkedList<String> (getWindowHandles());
		if(index < 0 || index> windowsID.size()) 	
				throw new IllegalArgumentException ("Invalid Index : " + index);
		driver.switchTo().window(windowsID.get(index));
		Log.info(index);
	}
	
	
	public void switchToParentWindow() {
		LinkedList<String> windowsID = new LinkedList<String> (getWindowHandles());
		driver.switchTo().window(windowsID.get(0));
		Log.info("");
	}
	
	public void switchToParentWithChildClose() {
		LinkedList<String> windowsID = new LinkedList<String> (getWindowHandles());
		
		for (int i=1; i<windowsID.size(); i++) {
			Log.info(windowsID.get(i));
			driver.switchTo().window(windowsID.get(i));
			driver.close();
		}
		switchToParentWindow();
	}
	
	public void switchToFrame(String nameOrId) {
		
		driver.switchTo().frame(nameOrId);
		Log.info(nameOrId);
	}
	
	
}
