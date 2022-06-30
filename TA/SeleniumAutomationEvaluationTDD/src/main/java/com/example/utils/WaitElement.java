package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElement {

	public static void waitVisibilityOf(WebDriver driver, By by) {
		int timeWait = 30;
		WebDriverWait explicitWaitByElement = new WebDriverWait(driver, timeWait);
		explicitWaitByElement.until(ExpectedConditions.visibilityOfElementLocated(by));

	}
	public static void waitPresenceOf(WebDriver driver, By by) {
		int timeWait = 30;
		WebDriverWait explicitWaitByElement = new WebDriverWait(driver, timeWait);
		explicitWaitByElement.until(ExpectedConditions.presenceOfElementLocated(by));

	}
	
	public static void waitVisibilityOfElement(WebDriver driver, WebElement ele) {
		int timeWait = 30;
		WebDriverWait explicitWaitByElement = new WebDriverWait(driver, timeWait);
		explicitWaitByElement.until(ExpectedConditions.visibilityOf(ele));

	}	
	
}
