package com.scholastic.steps;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.scholastic.driver.DriverFactory;
import com.scholastic.driver.DriverManager;
import org.openqa.selenium.WebDriver;

import static com.scholastic.startup.PropertyLoader.returnConfigValue;

public class BaseStep {
    public void initPageObject()
    {
        WebDriver driver = new DriverFactory().createInstance();
        driver.manage().window().maximize();
        driver.get(returnConfigValue("url.base"));
        DriverManager.setWebDriver(driver);

    }
}