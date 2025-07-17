package com.amazon.page;

import com.amazon.utils.SeleniumUtils;
import com.amazon.utils.WaitElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EnLabelPage {

    private WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogger();

    public EnLabelPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    public void categoryAndSubCategoryPages(String category) {
        try {
            By byCategory = By.xpath(".//*[@class='horizontal-menu']//a[contains(text(),'" + category + "')]");
            WaitElement.waitVisibilityOf(driver, byCategory);
            WebElement elementCategory = driver.findElement(byCategory);
            StringBuilder url = new StringBuilder(elementCategory.getAttribute("href"));
            LOGGER.info("The URL for category " + category + " is " + url);
            SeleniumUtils.mouseHover(driver, elementCategory);
            List<WebElement> elementSubcategories =
                    driver.findElements
                    (By.xpath(
                            "//*[@class='horizontal-menu']//a[contains(text(),'"+ category + "')]//parent::li/ul//a"));
            elementSubcategories.forEach(subCategory -> LOGGER.info(subCategory.getAttribute("innerText")));
            String currentHandle = driver.getWindowHandle();
            if (elementSubcategories.isEmpty()) {
                LOGGER.info("No sub categories present for this category " + category);
            } else {
                for (int i=0;i<elementSubcategories.size();i++) {
                    String urlSub = elementSubcategories.get(i).getAttribute("href");
                    LOGGER.info("The new URL for the sub category " + elementSubcategories.get(i).getText() + " is " + urlSub);
                    WebDriver driver1 = driver.switchTo().newWindow(WindowType.TAB);
                    driver1.navigate().to(urlSub);
                    Assert.assertFalse(driver1.findElements(By.cssSelector("div#page")).isEmpty());
                    driver1.close();
                    driver.switchTo().window(currentHandle);
                    elementCategory = driver.findElement(byCategory);
                    SeleniumUtils.mouseHover(driver, elementCategory);
                    elementSubcategories =
                            driver.findElements
                                    (By.xpath(
                                            "//*[@class='horizontal-menu']//a[contains(text(),'"+ category + "')]//parent::li/ul//a"));

                }
            }

        } catch (Exception e) {
            LOGGER.info("Something wrong" + e.getLocalizedMessage());
        }
    }
}
