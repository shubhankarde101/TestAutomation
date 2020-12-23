package com.automation.utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdrivermanagerDemo extends ActionMethods{

	WebDriver driver;
	
	@BeforeTest
	public void setUpTest()
	{ 
		//String path = System.getProperty("user.dir");
		//System.setProperty("webdriver.chrome.driver", path+"\\exe\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setAcceptInsecureCerts(true);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=1400,800");
		options.addArguments("disable-infobars");		
		//options.addArguments("--headless");
		options.merge(cap);		
		if(driver==null)
			driver = new ChromeDriver(options);		
	}
	@Test
	public void googleSearch1()
	{
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		WebElement elmnt = driver.findElement(By.cssSelector("input[aria-label='Search']"));
		click(elmnt);		
		type(elmnt, "test google search");		
	}
	@AfterTest	
	public void TearDown()
	{
		//driver.close();
		driver.quit();			
	}

}


