package com.affle.iOS.driver;

import com.affle.iOS.enums.Modes;
import com.affle.iOS.utils.PropertyUtils;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

import static com.affle.iOS.driver.DriverManager.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public final class Driver {
    private Driver() {
    }

    public static void initDriver() {
        if (isNull(getWebDriver())) {
            IOSDriver driver = DriverFactory.getDriver(Modes.valueOf(PropertyUtils
                                    .getPropertyValue("mode")
                                    .toUpperCase()));
            setWebDriver(driver);
        }
    }

    public static void quitDriver() {
        if (nonNull(getWebDriver())) {
            getWebDriver().quit();
            unLoadWebDriver();
        }
    }
}
