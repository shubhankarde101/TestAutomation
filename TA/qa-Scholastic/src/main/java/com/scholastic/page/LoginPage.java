package com.scholastic.page;

import com.scholastic.utils.SeleniumUtils;
import com.scholastic.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	public WebElement inputUserName;

	@FindBy(name = "password")
	public WebElement inputPwd;

	@FindBy(xpath = "//button[contains(text(), 'LOGIN')]")
	public WebElement btnLogin;

    public String verifyUserIsLoggedIn() {

        String cssLocatorLblLoggedIn = "span#user-name";
        WaitElement.waitVisibilityOf(driver,By.cssSelector(cssLocatorLblLoggedIn));
		return SeleniumUtils.getElementByCss(driver, cssLocatorLblLoggedIn).getText().trim();
	}

}
