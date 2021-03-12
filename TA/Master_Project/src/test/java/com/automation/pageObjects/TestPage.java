package com.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.utilities.AutoHealLocator;

import StepDef.BasePage;

public class TestPage extends BasePage {

	public WebElement getCoumnName(WebDriver driver, String param) {
		String locator = AutoHealLocator.HealLocator(driver, "xpath", ".//th[.='" + param + "']");
		return getWebElementByXpath(locator);

	}

	public List<WebElement> getCurrencyName(String param) {

		String locator = AutoHealLocator.HealLocator(driver, "xpath", ".//td[contains(@class, '" + param + "')]");
		return getWebElementsByXpath(locator);

	}

	public List<WebElement> getCurrencyData(String currName, String change, String param) {

		String locator = AutoHealLocator.HealLocator(driver, "xpath",
				"//tbody//tr[.//td[contains(@class, '" + change + "_change')] and contains(@id,'" + currName
						+ "')]//td[count(//thead//th[.='" + param + "']//preceding-sibling::th)+1]//a");

		return getWebElementsByXpath(locator);

	}

	public WebElement getNavBar(String param) {

		String locator = AutoHealLocator.HealLocator(driver, "xpath",
				".//li[@class='dropdown']//a[contains(.,'" + param + "')]");
		return getWebElementByXpath(locator);

	}

	public WebElement getNavDropdown(String param) {

		String locator = AutoHealLocator.HealLocator(driver, "xpath",
				".//li[@class='dropdown open']//a[contains(.,'" + param + "')]//following-sibling::ul");
		return getWebElementByXpath(locator);

	}

	public WebElement getSearchInput() {

		String locator = AutoHealLocator.HealLocator(driver, "xpath",
				".//div[contains(@class, 'form-group') and not(contains(@class, 'bottom'))]//input");
		return getWebElementByXpath(locator);

	}	

	public WebElement getCurrSwitchBtn() {

		return getWebElementByCss(AutoHealLocator.HealLocator(driver, "css",
				"button#currency-switch-button"));
	}
	
	public List<WebElement> getCurrSwitchDropDown() {

		return getWebElementsByCss(AutoHealLocator.HealLocator(driver, "css",
				"div#currency-switch ul li a:not([data-fiat])"));
	}
	

	private WebElement getWebElementByXpath(String locator) {
		user.syncByLocator(driver, By.xpath(locator));
		return driver.findElement(By.xpath(locator));

	}

	private List<WebElement> getWebElementsByXpath(String locator) {
		user.syncByLocator(driver, By.xpath(locator));
		return driver.findElements(By.xpath(locator));

	}
	
	private WebElement getWebElementByCss(String locator) {
		user.syncByLocator(driver, By.cssSelector(locator));
		return driver.findElement(By.cssSelector(locator));

	}

	private List<WebElement> getWebElementsByCss(String locator) {
		user.syncByLocator(driver, By.cssSelector(locator));
		return driver.findElements(By.cssSelector(locator));

	}

}
