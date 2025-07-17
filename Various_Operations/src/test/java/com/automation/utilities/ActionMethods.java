package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/*import com.target.objects.HomePageObjects;
import com.target.objects.PensionPageObjects;*/

public class ActionMethods {

	public  void sync(WebDriver driver, WebElement element) {
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickWithSikuli(String value)
	{
		Screen s = new Screen();
		Pattern img = new Pattern(value);
		try {
			s.find(img).highlight();
			s.click(img);			
			
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	
	public  void syncByLocator(WebDriver driver, String locator, long timeVal) {
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, timeVal).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void type(WebElement element, String str) {
		element.clear();
		element.sendKeys(str.toString());

	}

	public  void click(WebElement element) {
		element.click();
	}

	public  boolean isElementPrsent(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public  String readProperty(String key) {
		String return_val = "";
		try {
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "/Data/Input.properties");
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			// get the property value and returning
			return_val = prop.getProperty(key);
		} catch (Exception ex) {
			ex.printStackTrace();		
	   }

		return return_val;

	}
	
	 public  void takeSnapShot(WebDriver webdriver, String file) throws Exception{

	        //Convert web driver object to TakeScreenshot
		    String fileWithPath = System.getProperty("user.dir") + "/Screenshots/"+file+".png";
		    System.out.println(fileWithPath);
	        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
	        //Call getScreenshotAs method to create image file
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        //Move image file to new destination
	        File DestFile=new File(fileWithPath);
	        //Copy file at destination
	        FileUtils.copyFile(SrcFile, DestFile);
	    }

	
public  void highlightElement(WebDriver driver, WebElement ele) {    
    // draw a border around the found element
    if (driver instanceof JavascriptExecutor) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", ele);
    }
    
  }
    
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


