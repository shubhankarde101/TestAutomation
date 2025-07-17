package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All th objects belonging to one page will be defined in java class
public class HomePageUsingFindBy {
// 1. Is to call the driver object from testcase to Pageobject file
	
	//Concatenate driver
	public HomePageUsingFindBy(WebDriver webDriver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(webDriver), this);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement Preferences;
	
	
	
	
	//driver.findElementByXpath("//android.widget.TextView[@text='Preference']");
	
	
	
	
}