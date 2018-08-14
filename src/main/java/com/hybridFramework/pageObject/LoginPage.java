package com.hybridFramework.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridFramework.helper.JavaScript.JavaScriptHelper;
import com.hybridFramework.helper.Logger.LoggerHelper;
import com.hybridFramework.helper.Wait.WaitHelper;
import com.hybridFramework.helper.genericHelper.GenericHelper;
import com.hybridFramework.testBase.Config;
import com.hybridFramework.testBase.TestBase;

public class LoginPage {


	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	WaitHelper waitHelper;
	
	
	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	WebElement signin;
	
	@FindBy(xpath="//*[@id='email']")
	WebElement emailAddress;
	
	@FindBy(xpath="//*[@id='passwd']")
	WebElement password;
	
	@FindBy(xpath="//*[@id='SubmitLogin']")
	WebElement submitLogin;
	
	@FindBy(xpath="//*[@id='center_column']/p")
	WebElement successMsgObject;
	
	@FindBy(xpath="//*[@id='email_create']")
	WebElement registration;
	
	@FindBy(xpath="//*[@id='SubmitCreate']")
	WebElement createAnAccount;
	
	
	// if somebody has to access a method of login page, they have to create
	// an object
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, signin, new Config(TestBase.OR).getExplicitWait());
	}
	
	public void clickOnSignInLink() {
		log.info("Clicking on SignIn link");
		signin.click();
		
	}
	
	public void enterEmailAddress(String emailAddress) {
		log.info("Entering email address: "+emailAddress );
		this.emailAddress.sendKeys(emailAddress);
	}
	
	public void enterPassword(String password) {
		log.info("Entering Password: "+password );
		this.password.sendKeys(password);
	}
	
	
	public HomePage clickOnSubmitButton() {
		log.info("clicking on Submit button...");
		new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		return new HomePage(driver);
	}
	
	public boolean verifySuccessLoginMsg() {
		return new GenericHelper().isDisplayed(successMsgObject);
	}
	
	
	public void enterRegistrationEmail() {
		String email = System.currentTimeMillis()+"@gmail.com";
		log.info("Entering registration email: "+email);
		registration.sendKeys(email);
	}
	
	public RegistrationPage clickOnCreateAccount() {
		createAnAccount.click();
		return new RegistrationPage(driver);
	}
	
	
	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSubmitButton();
	}
}
