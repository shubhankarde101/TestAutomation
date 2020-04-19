package com.automation.utilities;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumGrid extends ActionMethods{

	WebDriver driver = null;
		
	@BeforeTest
	public void setUpTest() throws MalformedURLException
	{ 		
			//String path = System.getProperty("user.dir");
			//System.setProperty("webdriver.chrome.driver", path+"\\exe\\chromedriver.exe");		
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WIN8_1);
			//options.addArguments("--headless");
			String url = "http://192.168.1.4:4444/wd/hub";
			if(driver==null)
				
				driver = new RemoteWebDriver(new URL(url), cap);		
		}

	@Test
	public void googleSearch1() throws Exception
	{
		try{			
			driver.get("https://www.google.com");
			driver.manage().window().maximize();
			WebElement elmnt = driver.findElement(By.cssSelector("input[aria-label='Search']"));					
			type(elmnt, "test google search");
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1500);
			rob.setAutoWaitForIdle(true);
			rob.keyRelease(KeyEvent.VK_ENTER);		
			sync(driver,driver.findElement(By.cssSelector("div span.hb2Smf")));

		}catch(Exception e1)

		{
			throw e1;
		}
	}		
	@AfterTest	
	public void TearDown()
	{
		//driver.close();
		driver.quit();			
	}
	
	



























}


