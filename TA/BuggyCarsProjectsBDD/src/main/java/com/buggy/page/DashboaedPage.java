package com.buggy.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buggy.utils.WaitElement;

public class DashboaedPage {

	private WebDriver driver;

	public DashboaedPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//a[.='Register']")
	public WebElement lnkRegister;

	@FindBy(xpath = ".//button[.='Login']")
	public WebElement btnLogin;

	@FindBy(xpath = ".//*[@class='nav-link disabled']")
	public WebElement lblNavItem;

	@FindBy(xpath = ".//a[.='Profile']")
	public WebElement lnkProfile;

	@FindBy(xpath = ".//a[.='Buggy Rating']")
	public WebElement lnkBuggyRating;

	@FindBy(xpath = ".//*[contains(@src, 'overall')]")
	public WebElement lnkOverallCars;

	public void userLogin(String name, String text) {
		By by = By.xpath(".//input[@name='" + name + "']");
		WaitElement.waitVisibilityOf(driver, by);
		driver.findElement(by).sendKeys(text);

	}

	public String verifyProfile(String id) {
		By by = By.xpath(".//input[@id=\"" + id + "\"]");
		WaitElement.waitVisibilityOf(driver, by);
		return driver.findElement(by).getAttribute("value").trim();

	}

}
