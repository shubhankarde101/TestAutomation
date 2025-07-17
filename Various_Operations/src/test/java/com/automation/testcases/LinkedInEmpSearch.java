package com.automation.testcases;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.DataRepo.Inputs;
import com.automation.pageObjects.PageObjects;
import com.automation.utilities.ActionMethods;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LinkedInEmpSearch extends ActionMethods {

	WebDriver driver;
	PageObjects po;
	Logger Log = Logger.getLogger(LinkedInEmpSearch.class.getName());

	@BeforeTest
	public void setUp() {
		try {

			DOMConfigurator.configure(("logger/log4j.xml"));
			Log.info("-----------------------Test Started---------------------");
			Log.info("Setting up the driver");
			// Setting up the driver
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setAcceptInsecureCerts(true);
			ChromeOptions options = new ChromeOptions();
			options.addExtensions(new File("contactOut.crx"));
			//options.addArguments("--window-size=1400,800");
			options.addArguments("disable-infobars");		
			Log.info("Chrome options added");
			// options.addArguments("--headless");
			options.merge(cap);
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			// Initializing Page Object
			Log.info("Initializing Page Object");
			po = PageFactory.initElements(driver, PageObjects.class);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// Login to "contactOut" extension
	@Test
	public void login_To_ContactOut_Extension() throws InterruptedException {
		// Validate employee details through extension
		// Fetch unique extension id from chrome webstore
		Log.info("Opened the concatOut extension login page");		
		driver.get(Inputs.valueOf("URL").getData());	
		po.contactOutBtnLogin.click();
		switchTab();
		po.contactOutEmail.sendKeys(Inputs.valueOf("USER_ID").getData());
		po.contactOutPwd.sendKeys(Inputs.valueOf("PWD").getData()); 
		click(po.contactOutBtnLogin);
		Log.info("Logged in to the extension");
		Assert.assertEquals(driver.getTitle().trim(), "ContactOut - Find anyone's email");			 
	} 

	// Login to LinkedIn
	@Test(enabled = false)
	public void login_To_LinkedIn_and_Search() throws InterruptedException {

		Log.info("Opened linkedin login page");
		driver.get("https://www.linkedin.com/");
		click(po.linkedInBtnSignIn);
		po.linkedInEmail.sendKeys("de.subho9@gmail.com");
		po.linkedInPwd.sendKeys("testpassword");
		click(po.linkedInBtnLogin);		
		Log.info("Logged in to linkedin");		
		po.ele_linkedInSearchBox.sendKeys("Sam Smith");
		syncByLocator(driver, po.linkedInEmpSrchResults, 60);
		click(po.ele_linkedInEmpSrchResults.get(0));		
		Log.info("Employee Searched");			 

	}	  

	@AfterTest
	public void tear_Down() {
		// Closing the driver
		driver.quit();
		Log.info("Driver closed");
		Log.info("Test Ended");
	}

	private void switchTab() {
		// TODO Auto-generated method stub
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		//driver.close();
		//driver.switchTo().window(tabs.get(0));
	}

}
