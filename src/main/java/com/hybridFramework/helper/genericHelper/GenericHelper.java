package com.hybridFramework.helper.genericHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.hybridFramework.helper.Logger.LoggerHelper;


public class GenericHelper {

	
private static final Logger log = LoggerHelper.getLogger(GenericHelper.class);
	
	public String readValueFromElement(WebElement element) {

		if (null == element){
			log.info("weblement is null");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			log.error(e);
			Reporter.log(e.fillInStackTrace().toString());
			return null;
		}

		if (!displayed){
			return null;
		}
		String text = element.getText();
		log.info("weblement value is.."+text);
		return text;
	}
	
//above we are returning the text of the element, 
	// below we are returning the attribute value
	public String readValueFromInput(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		String value = element.getAttribute("value");
		log.info("weblement Value is.."+value);
		return value;
	}
	
	
	// we are writing a wrapper method for isDisplayed, because
	// when the element is displayed it will return true
	// but when not displayed it will not return false, it will throw an exception
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.."+element);
			return true;
		} catch (Exception e) {
			log.info(e);
			Reporter.log(e.fillInStackTrace().toString());
			return false;
		}
	}
	
	
	// we are expecting the element not to be displayed here ( opposite of above) 
	protected boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.."+element);
			return false;
		} catch (Exception e) {
			log.error(e);
			Reporter.log(e.fillInStackTrace().toString());
			return true;
		}
	}
	
	
	protected String getDisplayText(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		return element.getText();
	}
	
	// below is static method, so we can directly call the method  ( no need to create object) 
	public static synchronized String getElementText( WebElement element) {
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			log.info("Element not found " + ex);
			Reporter.log(ex.fillInStackTrace().toString());
		}
		return elementText;
	}
	
}
