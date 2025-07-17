package StepDef;

import java.io.File;
import java.io.FileOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.pageObjects.TestPage;
import com.automation.utilities.ActionMethods;
import com.automation.utilities.PropertiesFile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	public static ActionMethods user;
	public static TestPage testPageobj;	
	public String message = "";
	FileOutputStream fop = null;
	File file;
	byte[][] screenshotsArray = new byte[10][10000];
	public static PropertiesFile props;
	public static RaiseJiraBug bug;

	protected static void initializeUtils() {
		try {
			
			props = PropertiesFile.getInstanceOfSingletonPropertyClass();		
			user = new ActionMethods();	
			bug = new RaiseJiraBug();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void loadDriver() {
		
		WebDriverManager.chromedriver().setup();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setAcceptInsecureCerts(true);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--allow-insecure-websocket-from-https-origin");
		options.addArguments("disable-extensions");
		options.addArguments("--headless");
		//options.addArguments("start-maximized");
		options.addArguments("--window-size=1400,600");
		options.merge(cap);
		driver = new ChromeDriver(options);
		driver.get(System.getProperty("user.dir")+File.separator+props.readPropertiesFile("URL"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		By by = By.xpath(".//*[@class='banner-alert-close']");
		user.syncByLocator(driver, by);
		driver.findElement(by).click();		
	}
	
	protected void initializePageObjects()
	{
		testPageobj = new TestPage();		
	}

	protected static void closeBrowser() {		
		driver.quit();
	}

}
