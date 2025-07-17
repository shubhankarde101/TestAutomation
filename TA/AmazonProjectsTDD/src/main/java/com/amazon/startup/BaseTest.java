package com.amazon.startup;

import com.amazon.driver.Driver;
import com.amazon.page.CartPage;
import com.amazon.page.EnLabelPage;
import com.amazon.page.ProductPage;
import com.amazon.report.ReportListener;
import com.amazon.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import static com.amazon.driver.DriverManager.*;


import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.amazon.page.DashboaedPage;

import static java.util.Objects.nonNull;

@Listeners({ ExtentITestListenerClassAdapter.class, ReportListener.class })
public abstract class BaseTest {

	protected static WebDriver driver;
	protected DashboaedPage dashBoardpage;
	protected ProductPage productPage;
	protected CartPage cartPage;

	protected EnLabelPage enLabelPage;


	public static WebDriver getDriver() {
		return driver;
	}


	@BeforeMethod
	public void preCondition() {
		driver = new Driver().createInstance();
		driver.manage().window().maximize();
		driver.get(PropertyLoader.returnConfigValue("url.base1"));
		dashBoardpage = new DashboaedPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
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