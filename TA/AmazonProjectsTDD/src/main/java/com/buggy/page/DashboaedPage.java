package com.buggy.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.example.utils.WaitElement;

public class DashboaedPage {

	private WebDriver driver;

	public DashboaedPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "nav-hamburger-menu")
	public WebElement lnkHamburger;

	@FindBy(xpath = ".//*[.=' About this item ']//following-sibling::ul//span")
	public List<WebElement> listSectionTexts;

	public void clickLink(String item) {

		By byLink = By.xpath(".//a[.='" + item + "']");
		WaitElement.waitPresenceOf(driver, byLink);
		WebElement element = driver.findElement(byLink);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

	}

	public void selectBrandSamsung() {

		By byChkBox = By.xpath(".//*[.='Samsung']//parent::a//input");
		WaitElement.waitPresenceOf(driver, byChkBox);
		WebElement element = driver.findElement(byChkBox);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	}

	public void selectPriceHighToLow() {
		By byButton = By.xpath(".//*[@class='a-button a-button-dropdown a-button-small']");
		WaitElement.waitVisibilityOf(driver, byButton);
		WebElement elementBtn = driver.findElement(byButton);
		elementBtn.click();
		By byHighToLow = By.xpath(".//li[.='Price: High to Low']//a");
		WaitElement.waitVisibilityOf(driver, byHighToLow);
		WebElement elementHighToLow = driver.findElement(byHighToLow);
		elementHighToLow.click();

	}

	public void selectSecondHighest() {

		By bySecondHighest = By.xpath("(.//*[@class='a-price-whole'])[2]");
		WaitElement.waitVisibilityOf(driver, bySecondHighest);
		WebElement elementSecondHighest = driver.findElement(bySecondHighest);
		elementSecondHighest.click();

	}

	public void printSectionTextsToConsole() {

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		By bySSectionText = By.xpath(".//*[.=' About this item ']//following-sibling::ul//span");
		WaitElement.waitVisibilityOf(driver, bySSectionText);
		List<WebElement> elementSecondHighest = driver.findElements(bySSectionText);
		Assert.assertTrue(elementSecondHighest.size()>0);
		elementSecondHighest.forEach(element -> System.out.println(element.getText()));

	}

}
