package com.hybridFramework.helper.DropDown;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import com.hybridFramework.helper.Logger.LoggerHelper;

public class DropDownHelper {

	private WebDriver driver;
	
	private Logger Log = LoggerHelper.getLogger(DropDownHelper.class);
	
	public DropDownHelper(WebDriver driver) {
		this.driver= driver;
		Log.debug("DropDownHelper : " + this.driver.hashCode());
	}

	
	public void SelectUsingVisibleValue(WebElement element, String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		Log.info("Locator :  "+element+ " and value:  "+ visibleValue );
	}
	
	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		Log.info("Element :  "+element+ " and value:  "+ value );
		return value;
	}
	
	public void SelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		Log.info("Locator :  "+element+ " and value:  "+ index );
	}
	
	public List<String> getAllDropDownVallues(WebElement locator) {
		
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		
		LinkedList<String> valueList = new LinkedList<String>();
		
		for (WebElement  element : elementList) {
			Log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
	
}
