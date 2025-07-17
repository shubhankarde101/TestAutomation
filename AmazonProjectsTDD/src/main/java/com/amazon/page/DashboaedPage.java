package com.amazon.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.amazon.utils.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amazon.utils.WaitElement;

public class DashboaedPage {

    private WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogger();


    public static String productTitle;
    public static String productRating;
    public static String productPrice;

    public DashboaedPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#twotabsearchtextbox")
    private WebElement inputSearchBar;

    @FindBy(css = "input#nav-search-submit-button")
    private WebElement btnSearchIcon;

    public void clickOnHamburgerMenu() {
        try {
            By byHamburger = By.id("nav-hamburger-menu");
            WaitElement.waitPresenceOf(driver, byHamburger);
            WebElement element = driver.findElement(byHamburger);
            SeleniumUtils.actionClick(driver, element);
        } catch (Exception e) {
            LOGGER.info("Something wrong"+e.getLocalizedMessage());

        }
    }

    public void clickLink(String item) {
        try {
            WaitElement.hardWait(2);
            By byLink = By.xpath(".//*[.='" + item + "']");
            WaitElement.waitPresenceOf(driver, byLink);
            WebElement element = driver.findElement(byLink);
            SeleniumUtils.actionClick(driver, element);
        } catch (Exception e) {
            LOGGER.info("Something wrong"+e.getLocalizedMessage());

        }
    }

    public void selectBrand(String brand) {
        By byChkBox = By.xpath("(.//a[@class='a-link-normal']//*[.='" + brand + "'])[2]");
        WaitElement.waitVisibilityOf(driver, byChkBox);
        WebElement element = driver.findElement(byChkBox);
        SeleniumUtils.scrollIntoView(driver, element);
        element.click();
    }

    public void selectPriceHighToLow() {
        By byButton = By.xpath(".//select[@name='s']");
        WaitElement.waitVisibilityOf(driver, byButton);
        WebElement elementB = driver.findElement(byButton);
        SeleniumUtils.clickUsingJs(driver, elementB);
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
        Assert.assertFalse(elementSecondHighest.isEmpty());
        elementSecondHighest.forEach(element -> System.out.println(element.getText()));
    }

    public void searchItem(String item) {
        WaitElement.waitVisibilityOfElement(driver, inputSearchBar);
        inputSearchBar.sendKeys(item);
        btnSearchIcon.click();
    }

    public void selectConnectivityType(String param) {
        try {
            WaitElement.hardWait(2);
            By byChkBox = By.xpath(".//li[@aria-label='" + param + "']//input");
            WaitElement.waitVisibilityOf(driver, byChkBox);
            WebElement element = driver.findElement(byChkBox);
            SeleniumUtils.scrollIntoView(driver, element);
            SeleniumUtils.clickUsingJs(driver, element);
        } catch (Exception e) {
            LOGGER.info("Something wrong"+e.getLocalizedMessage());
        }
    }

    public void selectNthItem(int index) {
        try {
            By title = By.xpath("//div[contains(@class, 'search-results')]//div[contains(@data-component-type, 'search')]//div[contains(@class, 'title')]/h2/a/span");
            By rating = By.xpath("//div[contains(@class, 'search-results')]//div[contains(@data-component-type, 'search')]//*[contains(@class, 'a-icon-star')]/span");
            By price = By.xpath("//div[contains(@class, 'search-results')]//div[contains(@data-component-type, 'search')]//*[contains(@class, 'price-instructions-')]//*[@class='a-price']//span[@ class='a-price-whole']");
            WaitElement.waitVisibilityOf(driver, title);
            WebElement productElement = driver.findElements(title).get(index);
            productTitle = productElement.getAttribute("innerText").trim();
            productPrice = driver.findElements(price).get(index).getAttribute("innerText").trim();
            productRating = driver.findElements(rating).get(index).getAttribute("innerText").trim();
            productElement.click();
        } catch (Exception e) {
            LOGGER.info("Something wrong"+e.getLocalizedMessage());
        }
    }


}
