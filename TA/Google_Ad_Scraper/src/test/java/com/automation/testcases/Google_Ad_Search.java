package com.automation.testcases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.pageObjects.PageObjects;
import com.automation.pojo.Ad_Pojo;
import com.automation.utilities.ActionMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Google_Ad_Search extends ActionMethods {

	WebDriver driver;
	PageObjects po;
	Logger Log = Logger.getLogger(Google_Ad_Search.class.getName());
	List<Ad_Pojo> ap_lst = new ArrayList<Ad_Pojo>();

	@BeforeTest
	public void setUp() {
		try {

			DOMConfigurator.configure(("logger/log4j.xml"));
			Log.info("-----------------------Test Started---------------------");
			Log.info("Setting up the driver");
			// Setting up the driver
			WebDriverManager.chromedriver().driverVersion("86").setup();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setAcceptInsecureCerts(true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1400,800");
			options.addArguments("disable-infobars");
			// options.addArguments("--headless");
			options.merge(cap);
			if (driver == null)
				driver = new ChromeDriver(options);

			// Initializing Page Object
			Log.info("Initializing Page Object");
			po = PageFactory.initElements(driver, PageObjects.class);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	// Scenario 1: Searching any term in google search and capturing 'Ad' details
	@Test
	public void capture_Ad_Details() {

		try {

			Log.info("Opening the URL");
			driver.get(readProperty("URL"));
			Assert.assertEquals(driver.getTitle().trim(), "Google", "Given URL is wrong");
			takeSnapShot(driver, "PageTitle");
			Log.info("Page title validated");
			driver.manage().window().maximize();
			type(po.input_GoogleSearch, readProperty("Element_To_Search"));
			po.input_GoogleSearch.sendKeys(Keys.ENTER);
			sync(driver, po.lbl_SrchResultStatus);
			int attempt = Integer.parseInt(readProperty("Page_Iterations"));
			for (int i = 1; i < attempt; i++) {

				takeSnapShot(driver, "Srch_Results_" + i);
				Log.info("----------------------------");
				Log.info("Page " + i + " traversed");
				captureAdDetails(driver);
				if (i > 1) {
					click(po.links_Google_Page_Navigation);
				}
			}

		} catch (Exception e2) {

			System.out.println("Opss....some exception occured");
			e2.printStackTrace();

		}
	}

	// Scenario 2: Converting the captured details into Json
	@Test
	public void convert_To_Json() throws IOException {

		Log.info("Adding the Pojo to Json");
		FileOutputStream out = null;
		try {
            
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			out = new FileOutputStream(new File(System.getProperty("user.dir") + "/json/data.json"));
			objectMapper.writeValue(out, ap_lst);

		} catch (Exception e3) {

			e3.printStackTrace();
		} finally {

			out.close();
		}
	}

	@AfterTest
	public void tear_Down() {
		// Closing the driver
		driver.quit();
		Log.info("Driver closed");

	}

	private void captureAdDetails(WebDriver driver2) {
		// TODO Auto-generated method stub
		String ad_url = "", ad_context = "";

		for (WebElement ad_ele : po.label_Google_Ad) {
			Ad_Pojo ap = new Ad_Pojo();
			sync(driver, ad_ele);
			highlightElement(driver, ad_ele);
			ad_url = ad_ele.findElement(po.get_Ad_Url()).getAttribute("innerText").trim();
			ap.setAd_Url(ad_url);
			Log.info("Ad Url is: " + ad_url);
			ad_context = ad_ele.findElement(po.get_Ad_Context()).getAttribute("innerText").trim();
			ap.setAd_Context(ad_context);
			Log.info("Ad Context is: " + ad_context);
			ap_lst.add(ap);
			Pattern pattern = Pattern.compile(readProperty("Given_Regex"));
			Log.info("Regex is : " + readProperty("Given_Regex"));
			if (pattern.matcher(ad_url).matches()) {

				// Scenario 3: Ad matching using Regex and opening that Ad

				Log.info("Matched the given Regex, so opening the Ad Url");
				highlightElement(driver, ad_ele.findElement(po.get_Ad_Url()));
				String current_handle = driver.getWindowHandle();
				WebDriver driver_tab = driver.switchTo().newWindow(WindowType.TAB);
				driver_tab.navigate().to("https://" + ad_url);
				driver_tab.close();
				driver.switchTo().window(current_handle);
				Log.info("Navigated back to the search page");
			}
		}

	}
}
