package StepDef;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.automation.pageObjects.LoginPage;
import com.automation.picoContainer.Container;
import com.automation.utilities.PropertiesFile;

public class BasePage {

	public static WebDriver driver;
	static LoginPage loginPageobj;	
	public static Actions action;
	public static String message = "";
	FileOutputStream fop = null;
	File file;
    static byte[][] screenshotsArray = new byte[10][10000];
	static Date date;
	static String BrowserName = "";
	static Properties data = null;	
	
	
	BasePage()
	{
	 
	 data =PropertiesFile.readPropertiesFile();
	}
    public void loadDriver()
    {
	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/exe/chromedriver.exe" );
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--allow-running-insecure-content");
	options.addArguments("--allow-insecure-websocket-from-https-origin");
	options.addArguments("disable-extensions");		
	driver =  new ChromeDriver(options);	
	
	
	loginPageobj = PageFactory.initElements(driver, LoginPage.class);	
	driver.get(data.getProperty("URL"));	
	
	driver.manage().deleteAllCookies();		
	driver.manage().window().maximize();		
	action = new Actions(driver);
	//Capabilities cap1 = ((RemoteWebDriver) driver).getCapabilities();
	//BrowserName = cap1.getBrowserName().toLowerCase();		
	System.out.println("BrowserName == "+BrowserName);
    }
    
    void closeBrowser()
    {
	  driver.close();
	  driver.quit();	
}
    
}


