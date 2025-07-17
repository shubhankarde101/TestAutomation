package com.scholastic.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitElement {

    public static void waitVisibilityOf(WebDriver driver, By by) {
        try {
            int timeWait = 5;
            WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
            explicitWaitByElement.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception ignored){}

    }

    public static void waitVisibilityOfElement(WebDriver driver, WebElement element) {
        try {
        int timeWait = 5;
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.visibilityOf(element));
        }catch (Exception ignored){}

    }

    public static void waitVisibilityOfElements(WebDriver driver, WebElement element) {
        try {
        int timeWait = 5;
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.visibilityOfAllElements(element));
        }catch (Exception ignored){}
    }

    public static void configurableWaitVisibilityOf(WebDriver driver, By by, int timeWait) {
        try {
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception ignored){}

    }

    public static void waitInVisibilityOf(WebDriver driver, By by) {
        try {
        int timeWait = 10;
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }catch (Exception ignored){}

    }

    public static void configurableWaitInVisibilityOf(WebDriver driver, By by, int timeWait) {
        try {
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(timeWait));
        explicitWaitByElement.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }catch (Exception ignored){}
    }

}
