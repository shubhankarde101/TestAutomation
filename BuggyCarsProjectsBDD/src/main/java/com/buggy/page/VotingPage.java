package com.buggy.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buggy.utils.WaitElement;

public class VotingPage {

	private WebDriver driver;

	public VotingPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = ".//div[@class='card-block']//h4//strong")
	public WebElement lblTotalVotes;
	
	@FindBy(xpath = ".//textarea[@id='comment']")
	public WebElement txtareaComment;
	
	@FindBy(xpath = ".//button[.='Vote!']")
	public WebElement btnVote;	
	public String locatorBtnVote = ".//button[.='Vote!']";
	
	@FindBy(xpath = ".//*[@class='table']//tr[1]//td[3]")
	public WebElement userComment;
	
	@FindBy(xpath = ".//a[contains(@href, 'facebook')]")
	public WebElement iconFacebook;
	
	
	public void clickOnDesiredCar(String carName) {
		By by = By.xpath(".//*[contains(@title, '"+carName+"')]");
		WaitElement.waitVisibilityOf(driver, by);
		driver.findElement(by).click();

	}
	public String getTotalVotes() {
		By by = By.xpath(".//div[@class='card-block']//h4//strong");
		WaitElement.waitVisibilityOf(driver, by);
		return driver.findElement(by).getAttribute("innerText").trim();

	}


}
