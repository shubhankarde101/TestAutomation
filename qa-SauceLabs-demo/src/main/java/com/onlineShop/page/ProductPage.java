package com.onlineShop.page;

import com.onlineShop.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPage {

	private WebDriver driver;

	public ProductPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//a[@class='shopping_cart_link']")
	private WebElement lnkShoppingCart;

	@FindBy(css = "button#checkout")
	private WebElement btnCheckout;

	@FindBy(css = "input#first-name")
	private WebElement inputFirstName;

	@FindBy(css = "input#last-name")
	private WebElement inputLastName;

	@FindBy(css = "input#postal-code")
	private WebElement inputPostalCode;

	@FindBy(css = "input#continue")
	private WebElement btnContinue;

	@FindBy(css = "div.summary_subtotal_label")
	private WebElement lblSummaryTotal;

	@FindBy(css = "button#finish")
	private WebElement btnFinish;

	@FindBy(xpath = ".//h2")
	private WebElement lblThankYouHeader;

	public String selectProductAndCapturePrice(String productName)
	{
		String priceLocator = ".//div[@class='inventory_item_name ' and text()='"+productName+
				"']//ancestor::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']/div";
		String addToCartLocator = ".//div[@class='inventory_item_name ' and text()='"+productName+
				"']//ancestor::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']/button";
		String price = driver.findElement(By.xpath(priceLocator)).getText().trim().replace("$","");
		driver.findElement(By.xpath(addToCartLocator)).click();
		return price;
	}

	public String  checkoutAndContinue()
	{
		lnkShoppingCart.click();
		btnCheckout.click();
		inputFirstName.sendKeys("Test user first name");
		inputLastName.sendKeys("Test user last name");
		inputPostalCode.sendKeys("567890");
		btnContinue.click();
		String total = lblSummaryTotal.getText().trim().split("\\$")[1].trim();
		return  total;
	}

	public String finishOrder()
	{   btnFinish.click();
		return  lblThankYouHeader.getText().trim();
	}




}
