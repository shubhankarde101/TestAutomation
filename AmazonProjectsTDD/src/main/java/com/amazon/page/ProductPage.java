package com.amazon.page;

import com.amazon.utils.WaitElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductPage {

    private WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogger();


    public ProductPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div#nav-cart-count-container")
    private WebElement btnGoToCart;

    public void addToCart() {
        try {
            Set<String> handles = driver.getWindowHandles();
            driver.switchTo().window(handles.stream().collect(Collectors.toList()).get(1));
            WaitElement.hardWait(2);
            By productTitleBy = By.cssSelector("span#productTitle");
            String actualTitle = driver.findElement(productTitleBy).getAttribute("innerText").trim();
            System.out.println("Actual product title is: " + actualTitle);
            Assert.assertTrue(actualTitle.contains(DashboaedPage.productTitle), "Product title not matched");
            By addToCartBtn = By.xpath(".//input[@id='add-to-cart-button']");
            WaitElement.waitPresenceOf(driver, addToCartBtn);
            driver.findElement(addToCartBtn).click();
            WaitElement.hardWait(5);
            driver.navigate().refresh();
            btnGoToCart.click();
        }catch (Exception e)
        {
            LOGGER.info("Something wrong"+e.getLocalizedMessage());
        }
    }
}
