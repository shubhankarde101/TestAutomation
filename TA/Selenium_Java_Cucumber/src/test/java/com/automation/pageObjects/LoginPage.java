package com.automation.pageObjects;

//jhgjg

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	public WebElement LoginId;
	
	@FindBy(how = How.XPATH, using = "//input[@id='pass']")
	public WebElement Password;
	
	@FindBy(how=How.XPATH, using="//input[@id='u_0_e']")
	public WebElement Female;
	
	public By getDay()
	{
		By Day = By.xpath("//select[@id='day']");
		return Day;
	}
	public By getMonth()
	{
		By Month = By.xpath("//select[@id='month']");
		return Month;
	}
	public By getYear()
	{
		By Year = By.xpath("//select[@id='year']");
		return Year;
	}

}
