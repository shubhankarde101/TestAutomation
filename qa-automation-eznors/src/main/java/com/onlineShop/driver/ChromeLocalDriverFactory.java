package com.onlineShop.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class ChromeLocalDriverFactory{



    private ChromeOptions chromeOptions;
    private static int var = 0;

    ChromeLocalDriverFactory(ChromeOptions chromeOptions) {
        this.chromeOptions = chromeOptions;
    }

    WebDriver driver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }


}
