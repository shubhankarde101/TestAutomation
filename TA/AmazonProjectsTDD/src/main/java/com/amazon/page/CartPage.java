package com.amazon.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

	private WebDriver driver;

	private static final Logger LOGGER = LogManager.getLogger();


	public CartPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
	private WebElement btnProceedToCheckout;

	public void verifyCartAndCheckout() {
		try {
			By priceInAddToCartPage = By.xpath(".//div[contains(@class,'item-price')]//span[contains(@class,'price')]");
			String priceInCart = driver.findElement(priceInAddToCartPage).getAttribute("innerText").trim();
			System.out.println("Actual product price is: " + priceInCart);
			Assert.assertTrue(priceInCart.contains(DashboaedPage.productPrice), "product price not matched");
			btnProceedToCheckout.click();
		}catch (Exception e)
		{
			LOGGER.info("Something wrong"+e.getLocalizedMessage());
		}

	}

}
