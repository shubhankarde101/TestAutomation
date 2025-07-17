package com.enabel.utils;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.enabel.startup.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class SeleniumUtils {

    public static void clickUsingJs(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void actionClick(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.click(element).build().perform();
    }

    public static void mouseHover(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void reportPrint() {
        ExtentTestManager.getTest().addScreenCaptureFromBase64String(takeScreenshot());
    }

    public static String takeScreenshot() {
        return ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BASE64);
    }



}
