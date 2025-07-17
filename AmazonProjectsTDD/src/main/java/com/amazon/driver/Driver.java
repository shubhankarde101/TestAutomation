package com.amazon.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    ChromeLocalDriverFactory chromeLocalDriverFactory;

    public Driver() {
        ChromeOptions chromeOptions = new ChromeOptionsFactory().build();
        chromeLocalDriverFactory = new ChromeLocalDriverFactory(chromeOptions);
    }

    public WebDriver createInstance(){
        chromeLocalDriverFactory.driver();
        return DriverManager.getWebDriver();
    }
}
