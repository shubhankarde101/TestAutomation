package com.affle.iOS.driver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private DriverManager(){}
   private static final ThreadLocal<IOSDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static IOSDriver getWebDriver() {
        return webDriverThreadLocal.get();
    }

    public static   void setWebDriver(IOSDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    public static void unLoadWebDriver(){
        webDriverThreadLocal.remove();
    }
}
