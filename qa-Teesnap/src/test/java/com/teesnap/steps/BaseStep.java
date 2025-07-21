package com.teesnap.steps;


import com.teesnap.driver.DriverFactory;
import com.teesnap.driver.DriverManager;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.teesnap.startup.PropertyLoader.returnConfigValue;

public class BaseStep {
    public void initPageObject()
    {
        WebDriver driver = new DriverFactory().createInstance();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(returnConfigValue("url.prod.base"));
        DriverManager.setWebDriver(driver);

    }
}