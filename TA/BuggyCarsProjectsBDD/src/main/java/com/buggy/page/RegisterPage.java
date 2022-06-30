package com.buggy.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buggy.utils.WaitElement;

public class RegisterPage {

	private WebDriver driver;

	public RegisterPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//button[.='Register']")
	public WebElement btnRegister;

	public void userRegistration(String id, String text) {
		By by = By.xpath(".//input[@id='" + id + "']");
		WaitElement.waitVisibilityOf(driver, by);
		driver.findElement(by).sendKeys(text);

	}

}
