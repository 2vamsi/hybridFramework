package com.hybridFramework.pageObject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hybridFramework.helper.Logger.LoggerHelper;
import com.hybridFramework.helper.Wait.WaitHelper;

import com.hybridFramework.testBase.Config;
import com.hybridFramework.testBase.TestBase;

public class RegistrationPage {

	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(RegistrationPage.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//*[@id='id_gender1']")
	WebElement mrRadioButton;

	@FindBy(xpath = "//*[@id='id_gender2']")
	WebElement mrsRadioButton;

	@FindBy(xpath = "//*[@id='customer_firstname']")
	WebElement firstName;

	@FindBy(xpath = "//*[@id='customer_lastname']")
	WebElement lastName;

	@FindBy(xpath = "//*[@id='email']")
	WebElement emailAddress;

	@FindBy(xpath = "//*[@id='passwd']")
	WebElement password;

	@FindBy(xpath = "//*[@id='days']")
	WebElement days;

	@FindBy(xpath = "//*[@id='months']")
	WebElement months;

	@FindBy(xpath = "//*[@id='years']")
	WebElement years;

	@FindBy(xpath = "//*[@id='firstname']")
	WebElement yourAddressFirstName;

	@FindBy(xpath = "//*[@id='lastname']")
	WebElement yourAddressLastName;

	@FindBy(xpath = "//*[@id='company']")
	WebElement yourAddressCompany;

	@FindBy(xpath = "//*[@id='address1']")
	WebElement address;

	@FindBy(xpath = "//*[@id='address2']")
	WebElement address2;

	@FindBy(xpath = "//*[@id='city']")
	WebElement yourAddressCity;

	@FindBy(xpath = "//*[@id='id_state']")
	WebElement yourAddressState;

	@FindBy(xpath = "//*[@id='postcode']")
	WebElement yourAddressPostCode;

	@FindBy(xpath = "//*[@id='id_country']")
	WebElement yourAddressCountry;

	@FindBy(xpath = "//*[@id='other']")
	WebElement additionalInformation;

	@FindBy(xpath = "//*[@id='phone']")
	WebElement homePhone;

	@FindBy(xpath = "//*[@id='phone_mobile']")
	WebElement mobilePhone;

	@FindBy(xpath = "//*[@id='alias']")
	WebElement addressAlias;

	@FindBy(xpath = "//*[@id='submitAccount']")
	WebElement registration;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, mrRadioButton, new Config(TestBase.OR).getExplicitWait());
	}

	public void setMrRadioButton() {
		log.info("Selecting Mr. Radio button...");
		this.mrRadioButton.click();
		
	}

	public void setMrsRadioButton() {
		log.info("Selecting Mrs. Radio button...");
		this.mrsRadioButton.click();
	}
	
	public void setFirstName(String firstname) {
		log.info("Entering first name as: "+ firstname);
		this.firstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		log.info("Entering last name as : "+ lastname);
		this.lastName.sendKeys(lastname);
	}
	
	public void setEmailAddress(String emailaddress) {
		log.info("Entering Email Address as : "+ emailaddress);
		this.emailAddress.sendKeys(emailaddress);
	}
	
	public void setPassword(String password) {
		log.info("Entering password as : "+ password);
		this.password.sendKeys(password);
	}
	
	public void setDay(String day) {
		log.info("Selecting day as : "+ day);
		List<WebElement> days = driver.findElements(By.xpath("//*[@id='days']/option"));
		Iterator<WebElement> itr = days.iterator();
		while(itr.hasNext()) {
			WebElement c = itr.next();
			String text = c.getText().trim().toString();
			if(text.equals(day)) {
				//System.out.println(day);
				c.click();
				break;
			}
		}
	}
	
	public void setMonth(String month) {
		log.info("Selecting month as : "+ month);
		List<WebElement> months = driver.findElements(By.xpath("//*[@id='months']/option"));
		Iterator<WebElement> itr = months.iterator();
		while(itr.hasNext()) {
			WebElement click = itr.next();
			String text = click.getText().trim();
			if(text.equals(month)) {
				//System.out.println(month);
				click.click();
				break;
			}
		}
	}
	
	
	public void setYear(String year) {
		log.info("Selecting year as : "+ year);
		List<WebElement> years = driver.findElements(By.xpath("//*[@id='years']/option"));
		Iterator<WebElement> itr = years.iterator();
		while(itr.hasNext()) {
			WebElement click = itr.next();
			String text = click.getText().trim();
			if(text.equals(year)) {
				//System.out.println(month);
				click.click();
				break;
			}
		}
	}
	
	
	public void setYourAddressFirstName(String yourAddressFirstName) {
		log.info("Entering yourAddressFirstName as : "+ yourAddressFirstName);
		this.yourAddressFirstName.sendKeys(yourAddressFirstName);
	}
	
	public void setYourAddressLastName(String yourAddressLastName) {
		log.info("Entering yourAddressFirstName as : "+ yourAddressLastName);
		this.yourAddressLastName.sendKeys(yourAddressLastName);
	}
	
	public void setYourAddressCompany(String yourAddressCompany) {
		log.info("entering yourAddressCompany.." + yourAddressCompany);
		this.yourAddressCompany.sendKeys(yourAddressCompany);
		}
	
	public void setAddress(String address) {
		log.info("Entering address as : "+ address);
		this.address.sendKeys(address);
	}
	
	public void setyourAddressCity(String yourAddressCity) {
		log.info("Entering yourAddressCity as : "+ yourAddressCity);
		this.yourAddressCity.sendKeys(yourAddressCity);
	}
	
	
	public void setyourAddressState(String yourAddressState) {
		log.info("Entering yourAddressState as : "+ yourAddressState);
		new Select(this.yourAddressState).selectByVisibleText(yourAddressState);
	}
	
	
	public void setyourAddressPostCode(String yourAddressPostCode) {
		log.info("Entering yourAddressPostCode as : "+ yourAddressPostCode);
		this.yourAddressPostCode.sendKeys(yourAddressPostCode);
	}
	
	
	public void setyourAddressCountry() {
		log.info("Entering yourAddressCountry as : UnitedStates");
		new Select(this.yourAddressCountry).selectByVisibleText("United States");
	}
	
	
	public void setMobilePhone(String mobilePhone) {
		log.info("Entering yourAddressmobile Phone as : "+ mobilePhone);
		this.homePhone.sendKeys(mobilePhone);
	}
	
	
	public void setAddressAlias(String addressAlias) {
		log.info("Entering addressAlias as : "+ addressAlias);
		this.addressAlias.sendKeys(addressAlias);
	}
	
	
	public void clickRegistration () {
		log.info("Clicking on Register Button");
		this.registration.click();
	}
}
