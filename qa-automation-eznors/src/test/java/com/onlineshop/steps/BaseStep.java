package com.onlineshop.steps;

import static com.onlineShop.startup.PropertyLoader.returnConfigValue;

import java.util.concurrent.TimeUnit;

import com.onlineShop.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.onlineShop.driver.DriverFactory;
import com.onlineShop.page.LoginPage;

public class BaseStep {

    public  static WebDriver driver;

    public void initPageObject()
    {
        driver = new DriverFactory().createInstance();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(returnConfigValue("url.base"));
        DriverManager.setWebDriver(driver);

    }
}