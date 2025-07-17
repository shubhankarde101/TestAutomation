package com.mapViewer.startup;

import com.mapViewer.driver.Driver;
import com.mapViewer.page.MapperPage;
import com.mapViewer.report.ReportListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import static com.mapViewer.driver.DriverManager.*;


import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import static java.util.Objects.nonNull;

@Listeners({ ExtentITestListenerClassAdapter.class, ReportListener.class })
public abstract class BaseTest {

	protected static WebDriver driver;
	protected MapperPage mapperPage;

	public static WebDriver getDriver() {
		return driver;
	}


	@BeforeMethod
	public void preCondition() {
		driver = new Driver().createInstance();
		driver.manage().window().maximize();
		driver.get(PropertyLoader.returnConfigValue("url.base1"));
		mapperPage = new MapperPage(driver);
	}

	@AfterMethod
	public void postCondition() {
		if (nonNull(getWebDriver())) {
			getWebDriver().quit();
			unLoadWebDriver();
		}
	}
}