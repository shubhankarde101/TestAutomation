package com.amazon.driver;

import com.amazon.startup.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.amazon.driver.DriverManager.*;


import static java.util.Objects.isNull;

class ChromeLocalDriverFactory {

    private ChromeOptions chromeOptions;

    ChromeLocalDriverFactory(ChromeOptions chromeOptions) {
        this.chromeOptions = chromeOptions;
    }

    void driver() {
        WebDriver driver = null;
        if (isNull(getWebDriver())) {
            String gridStatus = PropertyLoader.returnConfigValue("Grid");
            String gridUrl = PropertyLoader.returnConfigValue("RemoteUrl");
            if (gridStatus.equals("ON")) {
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                driver = new ChromeDriver(chromeOptions);
            }
            setWebDriver(driver);
        }
    }


}
