package com.hybridFramework.productDetailsPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hybridFramework.helper.Logger.LoggerHelper;
import com.hybridFramework.pageObject.HomePage;
import com.hybridFramework.pageObject.LoginPage;
import com.hybridFramework.pageObject.ProductCategoryPage;
import com.hybridFramework.testBase.Config;
import com.hybridFramework.testBase.TestBase;

public class VerifyProductCounts extends TestBase {

	private final Logger log = LoggerHelper.getLogger(VerifyProductCounts.class);
	LoginPage loginPage;
	HomePage homePage;
	
	
	@Test
	public void testVerifyProductCounts() {
		
		log.info(VerifyProductCounts.class.getName()+" test Started");
		
		Config config = new Config(OR);
		driver.get(config.getWebsite());
		
		HomePage homepage = new HomePage(driver);
		
		ProductCategoryPage pCate= homepage.clickOnMenu(homepage.womenMenu);
		//select price filter
		pCate.selectColor(pCate.Orange);
		
		int count = pCate.getTotalProducts();
		
		
		
		// giving some time for the page to load after applying the filter
		
		List<WebElement> price = pcategoryPage.getAllProductsPrice();
		ArrayList<Integer> array = new ArrayList<Integer>();
		Iterator <WebElement> itr = price.iterator();
		
		while(itr.hasNext()) {
			String p = itr.next().getText();
			if(p.contains("$")) {
				String actualData = p.substring(1);
				System.out.println(actualData);
				double p1 = Double.parseDouble(actualData);
				int productPrice = (int) (p1);
				array.add(productPrice);
			}
		}
		
		System.out.println(array);
		//
		for(int i=0; i< array.size()-1; i++) {
			
			if(array.get(i) <= array.get(i+1)) {
				System.out.println("obj.get(i):-" + array.get(i));
				System.out.println("obj.get(i):-" + array.get(i+1));
			}
			else {
				Assert.assertTrue(false, "Price filter is not working");
			}
		}
	}
	
}
