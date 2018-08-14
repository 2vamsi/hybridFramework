package com.hybridFramework.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.hybridFramework.excelReader.Excel_reader;
import com.hybridFramework.helper.Wait.WaitHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// since test base is a parent class, we need to create lot of important methods here
// Wherever we call the method, browser object should get created

public class TestBase {
	
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());


	public WebDriver driver;
	public static Properties OR;
	public File f1;
	public FileInputStream file;

	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	
	public Excel_reader excelreader;

	static {
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/hybridFramework/report/report_"
				+ formater.format(calender.getTime()) + ".html", false);
	}

	@BeforeTest
	public void launchBrowser() {
		try {
			loadPropertiesFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Config config = new Config(OR);
		getBrowser(config.getBrowser());
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.setImplicitWait(config.getImplicitWait(), TimeUnit.SECONDS);
		waitHelper.setPageLoadTimeout(config.getPageLoadTimeOut(), TimeUnit.SECONDS);
	}
	
	
	// method to launch the browser irrespective of OS
	public void getBrowser(String browser) {
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equalsIgnoreCase("firefox")) {  // nested if
				//System.out.println("Entered Windows - Firefox. Your project locaiton is:");
				//System.out.println(System.getProperty("user.dir"));
				
				System.setProperty("webdriver.gecko.driver", // from Selenium 3.0 onwards, we need to user driver for firefox also
						System.getProperty("user.dir") + "/drivers/geickodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				//System.out.print("Entered Windows - Chrome. Your project locaiton is: ");
				//System.out.println(System.getProperty("user.dir"));
				//System.out.print("Your OS is: ");
				//System.out.println(System.getProperty("os.name"));

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				driver = new ChromeDriver();
			
			} else if (browser.equalsIgnoreCase("ie")) {
			//System.out.print("Entered Windows - Chrome. Your project locaiton is: ");
			//System.out.println(System.getProperty("user.dir"));
			//System.out.print("Your OS is: ");
			//System.out.println(System.getProperty("os.name"));

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			}
		}
		else if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equalsIgnoreCase("firefox")) {
				//System.out.println("Entered Mac Firefox. Your project locaiton is: ");
				//System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geickodriver");
				driver = new FirefoxDriver();
				
			} else if (browser.equalsIgnoreCase("chrome")) {
				//System.out.println("Entered Mac Chrome. Your project locaiton is: ");
				//System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver ");
				driver = new ChromeDriver();
			}
		}
	}

	// method to load properties file

	public void loadPropertiesFile() throws IOException {
		
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		 // making the object of properties class. In java Properties is a class
		OR = new Properties();

		f1 = new File(System.getProperty("user.dir") + "/src/main/java/com/hybridFramework/config/config.properties");
		
		 // creating the object of file class, so that we can work with the files
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading config.properties");

		f1 = new File(System.getProperty("user.dir") + "/src/main/java/com/hybridFramework/config/or.properties");
		file = new FileInputStream(f1); // to read the file in one shot
		OR.load(file);
		logger.info("loading OR.properties :)");

		f1 = new File(System.getProperty("user.dir") + "/src/main/java/com/hybridFramework/properties/homepage.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading homepage.properties");
	}

	// method to capture the screenshot during run time

	public String getScreenshot(String imageName) throws IOException {

		if (imageName.equals("")) {
			imageName = "blank";
		}
        	// driver object has no capability to capture the screenshot
            // so we need to cast the driver with TakeScreenshot interface
            // and call a method called getScreenshotAs
		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir") + "/src/main/java/com/hybridFramework/screenshot/";

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation + imageName + "_" + formater.format(calendar.getTime()) + ".png";
		File destFile = new File(actualImageName); // converting string into file, so that we can save it

		//FileUtils.copyFile(image, destFile);
		FileHandler.copy(image, destFile);
		return actualImageName;
 
	}

	/**  methods for syncrhonozation **/

	// explicit wait
	public WebElement waitForElement(WebDriver driver, long time, WebElement element) {
					WebDriverWait wait = new WebDriverWait(driver, time);
					return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	
	   // explicit wait with polling interval
	@SuppressWarnings("deprecation")
	public WebElement waitForElementWithPollingInterval(WebDriver driver, long time, WebElement element) {
						WebDriverWait wait = new WebDriverWait(driver, time);
						wait.pollingEvery(5, TimeUnit.SECONDS);
						// wait.pollingEvery(5, TimeUnit.SECONDS);
						wait.ignoring(NoSuchElementException.class);
						return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void implicitWait(long time) {
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	
	public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + "test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, 	result.getName() + "test is skipped and skip reason is : - " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + " test is failed. " + result.getThrowable());
			String screen = getScreenshot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + "test is Started");
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
	//	driver.quit();
		extent.endTest(test);
		extent.flush();
	}

	public WebElement getLocator(String locator) throws Exception {

		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));

		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));

		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));

		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.tagName(locatorValue));

		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));

		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));

		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));

		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));

		else
			throw new Exception("Unknown locator Type:' " + locatorType + "'");
	}

	
	
	public  List <WebElement> getLocators(String locator) throws Exception {

		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));

		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));

		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));

		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.tagName(locatorValue));

		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));

		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));

		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));

		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));

		else
			throw new Exception("Unknown locator Type:' " + locatorType + "'");
	}
	
	public WebElement getWebElement(String locator) throws Exception {
		return getLocator(OR.getProperty(locator));
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception {
		return getLocators(OR.getProperty(locator));
	}
	
	
/*	private String getScreenShot() {
		// TODO Auto-generated method stub
		return null;
	}*/

	// method to read properties file
	public void getPropertiesData() {
	}

	public String[][] getData(String excelName, String sheetName) {
		String excelLocation = System.getProperty("user.dir")+"/src/main/java/com/hybridFramework/data/"+excelName;
		System.out.println(excelLocation);
		excelreader = new Excel_reader();
		return excelreader.getExcelData(excelLocation, sheetName);
	}
	
	public static void main(String[] args) throws Exception {
		TestBase test = new TestBase();
		
		//test.getBrowser("chrome");
		test.loadPropertiesFile();
		System.out.println(test.OR.getProperty("url"));
		//System.out.println(test.OR.getProperty("testname"));
		
		//test.getWebElements("GmailLink");
		//test.getLocator(test.OR.getProperty("username"));
		
		//Config test1 = new Config(OR);
		//String browser = test1.getBrowser();
		//System.out.println(browser);
	}
}
