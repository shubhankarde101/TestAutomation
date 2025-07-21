package com.onlineShop.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElement {

	public static void waitVisibilityOf(WebDriver driver, By by) {
		int timeWait = 20;
		WebDriverWait explicitWaitByElement = new WebDriverWait(driver, timeWait);
		explicitWaitByElement.until(ExpectedConditions.visibilityOfElementLocated(by));

	}
	
	public static void waitInVisibilityOf(WebDriver driver, By by) {
		int timeWait = 20;
		WebDriverWait explicitWaitByElement = new WebDriverWait(driver, timeWait);
		explicitWaitByElement.until(ExpectedConditions.invisibilityOfElementLocated(by));

	}
	public static void waitPresenceOf(WebDriver driver, By by) {
		int timeWait = 20;
		WebDriverWait explicitWaitByElement = new WebDriverWait(driver, timeWait);
		explicitWaitByElement.until(ExpectedConditions.presenceOfElementLocated(by));

	}

}
