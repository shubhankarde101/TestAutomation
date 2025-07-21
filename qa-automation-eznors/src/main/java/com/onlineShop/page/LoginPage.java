package com.onlineShop.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.onlineShop.utils.WaitElement;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	public WebElement inputUserName;

	@FindBy(id = "password")
	public WebElement inputPwd;

	@FindBy(id = "login-button")
	public WebElement btnLogin;
	@FindBy(id = "logout_sidebar_link")
	public WebElement lnkLogOut;

	public boolean verifyUserIsLoggedIn() {
		By byHamburgerMenu = By.xpath(".//button[text()='Open Menu']");
		WaitElement.waitVisibilityOf(driver, byHamburgerMenu);
		driver.findElement(byHamburgerMenu).click();
		return lnkLogOut.isEnabled();
	}

	public String lockedOutUserUnableToLogin() {
		By byErrMsg = By.xpath(".//h3");
		WaitElement.waitVisibilityOf(driver, byErrMsg);
        return driver.findElement(byErrMsg).getAttribute("innerText").split(":")[1].trim();
	}

	public boolean verifyImagesNotLoading()
	{
		By byImgs = By.xpath(".//a//img");
		WaitElement.waitVisibilityOf(driver, byImgs);
		List<String> li = driver.findElements(byImgs).stream()
				.map(x->x.getAttribute("src")).collect(Collectors.toList());
		boolean allSame = li.stream().distinct().limit(2).count() <= 1;
        return  allSame;
	}


}
