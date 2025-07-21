package com.teesnap.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitElement {

    public static void waitVisibilityOf(WebDriver driver, By by) {
        int timeWait = 30;
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public static void configurableWaitVisibilityOf(WebDriver driver, By by, int timeWait) {
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public static void waitInVisibilityOf(WebDriver driver, By by) {
        int timeWait = 30;
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }

    public static void configurableWaitInVisibilityOf(WebDriver driver, By by, int timeWait) {
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }

}
