package com.example.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;

	public HomePage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "inputEmail")
	public WebElement inputEmail;

	@FindBy(id = "inputPassword")
	public WebElement inputPassword;

	@FindBy(xpath = ".//button[text()='Sign in']")
	public WebElement btnLogin;

	@FindBy(xpath = ".//div[@id='test-2-div']//li")
	public List<WebElement> listItems;

	@FindBy(id = "dropdownMenuButton")
	public WebElement optionDefault;

	@FindBy(xpath = ".//div[@class='dropdown-menu show']//a")
	public List<WebElement> listOptions;

	@FindBy(xpath = ".//h1[text()='Test 4']//parent::div//button")
	public List<WebElement> listButtons;

	@FindBy(xpath = ".//h1[text()='Test 5']//parent::div//button")
	public WebElement btnFive;

	@FindBy(xpath = ".//div[contains(@class, 'alert')]")
	public WebElement txtAlertMessage;


	public String getValueFromCell(int row, int col)
	{
		By byCell = By.xpath(".//tr["+String.valueOf(row+1)+"]//td["+String.valueOf(col+1)+"]");
		return driver.findElement(byCell).getText().trim();
	}


}
