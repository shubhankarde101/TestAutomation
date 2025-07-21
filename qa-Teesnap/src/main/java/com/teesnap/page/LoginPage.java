package com.teesnap.page;

import com.teesnap.utils.SeleniumUtils;
import com.teesnap.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	public WebElement inputUserName;

	@FindBy(id = "password")
	public WebElement inputPwd;

	@FindBy(xpath = "//button[contains(text(), 'LOG IN')]")
	public WebElement btnLogin;

    public String verifyUserIsLoggedIn() {

        String cssLocatorLblLoggedIn = "p.profile-name";
        WaitElement.waitVisibilityOf(driver,By.cssSelector(cssLocatorLblLoggedIn));
		return SeleniumUtils.getElementByCss(driver, cssLocatorLblLoggedIn).getText().trim();
	}

}
