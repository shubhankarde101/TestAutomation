package com.example.startup;

import static com.example.startup.PropertyLoader.returnConfigValue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.buggy.page.DashboaedPage;

import com.example.driver.DriverFactory;
import com.example.report.ReportListener;

@Listeners({ ExtentITestListenerClassAdapter.class, ReportListener.class })
public abstract class BaseTest {

	protected static WebDriver driver;
	protected DashboaedPage dashboardpage;
	

	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void preCondition() {
		driver = new DriverFactory().createInstance();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(returnConfigValue("url.base"));
		dashboardpage = new DashboaedPage(driver);
		

	}

	@AfterSuite
	public void postCondition() {
		driver.quit();
	}
}