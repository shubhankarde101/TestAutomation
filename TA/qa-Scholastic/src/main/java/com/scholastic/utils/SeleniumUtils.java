package com.scholastic.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumUtils {

    public static void clickUsingJs(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void flexibleWait(int second)
    {
        try {
            Thread.sleep(second* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static WebElement getElementByXpath(WebDriver driver, String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));

    }

    public static List<WebElement> getElementsByXpath(WebDriver driver, String xpathLocator) {
        return driver.findElements(By.xpath(xpathLocator));

    }

    public static WebElement getElementByCss(WebDriver driver, String cssLocator) {
        return driver.findElement(By.cssSelector(cssLocator));

    }

    public static List<WebElement> getElementsByCss(WebDriver driver, String cssLocator) {
        return driver.findElements(By.cssSelector(cssLocator));

    }

    public static void waitForPageLoad(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int timeout = 30; // Maximum wait time in seconds
        int interval = 500; // Polling interval in milliseconds
        long endTime = System.currentTimeMillis() + timeout * 1000;
        while (System.currentTimeMillis() < endTime) {
            try {
                String readyState = jsExecutor.executeScript("return document.readyState").toString();
                if ("complete".equals(readyState)) {
                    break;
                }
            } catch(Exception e) {
                e.getStackTrace();
            }
            SeleniumUtils.flexibleWait(interval); // Wait for the next poll
        }
    }
    public static void waitForText(WebDriver driver, By locator, String textToAppear, int timeoutSeconds, int pollingMillis) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.pollingEvery(Duration.ofMillis(pollingMillis));

        try {
            wait.until((ExpectedCondition<Boolean>) driverInstance -> {
                assert driverInstance != null;
                WebElement element = driverInstance.findElement(locator);
                return element.getText().contains(textToAppear);
            });
        } catch (Exception ignored) {
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            System.out.println("Scrolled to element: " + element);
        } catch (Exception e) {
            System.out.println("Failed to scroll to the element. Exception: " + e.getMessage());
        }
    }

}
