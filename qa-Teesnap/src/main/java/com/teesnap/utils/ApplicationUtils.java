package com.teesnap.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApplicationUtils {

    public static void handleLoaderSpinner(WebDriver driver)
    {
        String loader = "//p[contains(text(), 'Please Wait')]";
        WaitElement.waitInVisibilityOf(driver, By.xpath(loader));
    }

}
