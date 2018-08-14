package com.hybridFramework.registration;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hybridFramework.helper.Logger.LoggerHelper;
import com.hybridFramework.pageObject.LoginPage;
import com.hybridFramework.pageObject.MyAccountPage;
import com.hybridFramework.pageObject.RegistrationPage;
import com.hybridFramework.testBase.Config;
import com.hybridFramework.testBase.TestBase;

public class Registration extends TestBase {

	private final Logger log = LoggerHelper.getLogger(Registration.class);
	LoginPage loginPage;
	RegistrationPage register;
	MyAccountPage myAccountPage;
	
	@Test
	public void testRegistration() {
	log.info(Registration.class.getName()+ "- Test Started");	
	
	Config config = new Config(OR);
	driver.get(config.getWebsite());
	
	loginPage = new LoginPage(driver);
	loginPage.clickOnSignInLink();
	loginPage.enterRegistrationEmail();
	loginPage.clickOnCreateAccount();
	
	register = new RegistrationPage(driver);
	register.setMrRadioButton();
	//register.setMrsRadioButton();
	register.setFirstName("firstname");
	register.setLastName("lastname");
	register.setPassword("password");
	
	register.setDay("5");
	register.setMonth("June");
	register.setYear("2000");

	register.setAddress("address");
	//register.setYourAddressFirstName("yourAddressFirstName");
	//register.setYourAddressLastName("yourAddressLastName");
	register.setyourAddressCity("yourAddressCity");
	register.setyourAddressState("Alaska");
	register.setyourAddressPostCode("99501");
	register.setMobilePhone("99999999");
	register.setAddressAlias("addressAlias");
	
	register.clickRegistration();
	
	myAccountPage = new MyAccountPage(driver);
	if(myAccountPage.verifySuccessRegistrationMsg()) {
		log.info("Registration is Successful");
	}
	else {
		Assert.assertTrue(false, "Registration is not Successful");
	}
	}
}
