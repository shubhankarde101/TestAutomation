package com.mapViewer.page;

import com.mapViewer.utils.WaitElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class MapperPage {

	private WebDriver driver;

	private static final Logger LOGGER = LogManager.getLogger();


	public MapperPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = ".//*[@title='Zoom in']")
	private WebElement btnZoomIn;

	@FindBy(xpath = ".//*[@title='Zoom out']")
	private WebElement btnZoomOut;

	String findAddrLocator = ".//*[@title='Search']";
	@FindBy(xpath = ".//*[@title='Search']")
	private WebElement btnFindAddress;

	String searchAddressLocator = ".//*[@title='Search']";

	@FindBy(xpath = ".//form//*[@aria-label='Search']")
	private WebElement searchAddress;

	String txtAddressLocator = "//*[contains(@class, 'esri-search-result-renderer__more-results-item')]";

	@FindBy(xpath = ".//*[contains(@class, 'esri-search-result-renderer__more-results-item')]")
	private List<WebElement> txtAddress;

	public void findAddress(String country)
	{
		WaitElement.hardWait(5);
		WaitElement.waitVisibilityOf(driver,By.xpath(findAddrLocator));
		btnFindAddress.click();
		WaitElement.hardWait(5);
		WaitElement.waitVisibilityOf(driver,By.xpath(searchAddressLocator));
		searchAddress.click();
		searchAddress.sendKeys(country);
		WaitElement.hardWait(5);
		btnFindAddress.click();
		WaitElement.hardWait(5);
	}
	public String verifyAddress()
	{
		WaitElement.waitVisibilityOf(driver,By.xpath(txtAddressLocator));
		return txtAddress.get(0).getText().trim();
	}

	public void zoomFunctionality()
	{
		btnZoomIn.click();
		btnZoomIn.click();
		btnZoomIn.click();
		WaitElement.hardWait(5);
		btnZoomOut.click();
		btnZoomOut.click();
		btnZoomOut.click();

	}

}
