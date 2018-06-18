package com.hybridFramework.helper.assertionHelper;

import org.openqa.selenium.WebElement;

import com.hybridFramework.helper.Logger.LoggerHelper;

public class VerificationHelper {

	private static final org.testng.log4testng.Logger log = LoggerHelper.getLogger(VerificationHelper.class);

	public static synchronized boolean verifyElementPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();
			log.info(element.getText() + "is displayed");
		} catch (Exception ex) {
			log.error("Element not found " + ex);
		}
		return isDisplayed;
	}

	public static synchronized boolean verifyElementNotPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			element.isDisplayed();
			log.info(element.getText() + "is displayed");
		} catch (Exception ex) {
			log.error("Element not found " + ex);
			isDisplayed = true;
		}
		return isDisplayed;
	}

	public static synchronized boolean verifyTextEquals(WebElement element, String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equals(expectedText)) {
				log.info("acatual text is: " + actualText + ". Expected text is: " + expectedText);
				return flag = true;
			} else {
				log.error("acatual text is: " + actualText + ". Expected text is: " + expectedText);
				return flag;
			}
		} catch (Exception ex) {
			log.error("acatual text is: " + element.getText() + ". Expected text is: " + expectedText);
			log.info("text not matching");
		}
		return flag;
	}

}
