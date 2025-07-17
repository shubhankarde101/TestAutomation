package com.automation.utilities;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

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

public class ExtentReport extends ActionMethods{

	WebDriver driver = null;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test1;

	@BeforeSuite
	public void initialize()
	{
		htmlReporter= new ExtentHtmlReporter("DemoTest.html");		    
		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// creates a toggle for the given test, adds all log events under it    
		test1 = extent.createTest("MyFirstTest", "Sample description");		
	}	

	@BeforeTest
	public void setUpTest()
	{ 
		try {
			PropertiesFile.setProperty("Browser", "Chrome");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PropertiesFile.setProperty("URL", "https://www.facebook.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(PropertiesFile.readPropertiesFile().getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", path+"\\exe\\chromedriver.exe");		
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setAcceptInsecureCerts(true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1400,800");
			options.addArguments("disable-infobars");		
			//options.addArguments("--headless");
			options.merge(cap);		
			if(driver==null)
				driver = new ChromeDriver(options);		
		}}

	@Test
	public void googleSearch1() throws AWTException, InterruptedException
	{
		try{

			driver.get(PropertiesFile.readPropertiesFile().getProperty("URL"));
			driver.findElement(By.xpath("")).click();


		}catch(Exception e1)

		{
			System.out.println(e1.getMessage());
			e1.printStackTrace();
			driver.get("https://www.google.com");
			driver.manage().window().maximize();
			WebElement elmnt = driver.findElement(By.cssSelector("input[aria-label='Search']"));
			if(elmnt.isDisplayed())
			{   
				click(elmnt);			
				test1.pass("element clicked");
			}
			else{test1.fail("element is not clickable");}
			type(elmnt, "test google search");
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_ENTER);
			//Thread.sleep(1500);
			rob.setAutoWaitForIdle(true);
			rob.keyRelease(KeyEvent.VK_ENTER);		
			sync(driver,driver.findElement(By.cssSelector("div span.hb2Smf")));
		}
	}		
	@AfterTest	
	public void TearDown()
	{
		//driver.close();
		driver.quit();			
	}
	@AfterSuite
	public void Finalize()
	{
		extent.flush();

	}



























}


