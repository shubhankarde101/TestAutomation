package com.enabel.startup;

import com.enabel.driver.Driver;
import com.enabel.page.EnLabelPage;
import com.enabel.report.ReportListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import static com.enabel.driver.DriverManager.*;


import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import static java.util.Objects.nonNull;

@Listeners({ ExtentITestListenerClassAdapter.class, ReportListener.class })
public abstract class BaseTest {

	protected static WebDriver driver;


	protected EnLabelPage enLabelPage;


	public static WebDriver getDriver() {
		return driver;
	}


	@BeforeMethod
	public void preCondition() {
		driver = new Driver().createInstance();
		driver.manage().window().maximize();
		driver.get(PropertyLoader.returnConfigValue("url.base1"));
		enLabelPage = new EnLabelPage(driver);
	}

	@AfterMethod
	public void postCondition() {
		if (nonNull(getWebDriver())) {
			getWebDriver().quit();
			unLoadWebDriver();
		}
	}
}