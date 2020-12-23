package com.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageObjects {

	@FindBy(how = How.NAME, using = "q")
	public WebElement input_GoogleSearch;
	
	@FindBy(how = How.XPATH, using = ".//div[@id='result-stats']")
	public WebElement lbl_SrchResultStatus;
	
	@FindBy(how = How.XPATH, using = ".//div[@role='heading']//following-sibling::div//*[normalize-space(text())='Ad']")
	public List<WebElement> label_Google_Ad;	
	
	@FindBy(how = How.XPATH, using = ".//table[@role='presentation']//a")
	public List<WebElement> links_Google_Page_Navigation;
	
	public By get_Ad_Url()
	{
		return By.xpath(".//following-sibling::span//*[contains(normalize-space(text()),'www')]");
		
	}	
	public By get_Ad_Context()
	{
		return By.xpath(".//parent::div//preceding-sibling::div/span");
		
	}
	
	
}
