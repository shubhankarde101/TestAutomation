package starter.PageObjects;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class EvriPageObject extends PageObject {

    @FindBy(xpath = ".//div[@class='nav__top-account-item']")
    public WebElement lnkLogin;

    @FindBy(xpath = ".//button[@type='submit']")
    public WebElement btnSubmit;

    @FindBy(xpath = ".//div[@class='auth0-lock-error-invalid-hint']")
    public List<WebElement> lblErrors;

    @FindBy(xpath = "//div//input[@name='email']")
    public WebElement inputEmail;

    @FindBy(xpath = "//div//input[@name='password']")
    public WebElement inputPwd;

    @FindBy(xpath = ".//div[@class='nav__user-name']")
    public WebElement lblLoggedInUser;

    @FindBy(xpath = ".//div[@class='tabs-card']")
    public WebElement divEform;

    @FindBy(xpath = ".//input[contains(@name,'From postcode')]")
    public WebElement inputFromPostCode;

    @FindBy(xpath = ".//input[contains(@name,'To postcode')]")
    public WebElement inputToPostCode;

    @FindBy(xpath = ".//div[@class='e-dropdown__input-arrow']")
    public List<WebElement> drpDownLists;

    @FindBy(xpath = ".//div[@data-icon='tick_circle']")
    public WebElement lblTickCircle;

    @FindBy(xpath = ".//div[@class='e-dropdown__option-text']")
    public List<WebElement> drpDownOptionTexts;

    @FindBy(xpath = ".//button//*[contains(text(), 'Get a price')]")
    public WebElement btnGetPrice;

    @FindBy(xpath = ".//*[@for = 'postable-size']")
    public WebElement lblSelectForPostable;

    @FindBy(xpath = ".//*[@for = 'standard-option']")
    public WebElement lblSelectForStandard;

    @FindBy(xpath = ".//*[@for = 'parcelshop-method']")
    public WebElement lblSelectForDropOff;

    @FindBy(xpath = ".//*[@for = 'courier-delivery-option']")
    public WebElement lblSelectForDelOption;

    @FindBy(xpath = "(.//button[@data-gtm-track='btn - Continue'])[1]")
    public WebElement btnContinue;

    @FindBy(xpath = ".//*[text()=' Parcel contents *']/following-sibling::input")
    public WebElement inputParcelContent;

    @FindBy(xpath = ".//*[text()=' How much is it worth? *']/following-sibling::input")
    public WebElement inputWorthValue;

    @FindBy(xpath = ".//*[text()=' Recipient first name *']/following-sibling::input")
    public WebElement inputRecipientFirstName;

    @FindBy(xpath = ".//*[text()=' Recipient last name *']/following-sibling::input")
    public WebElement inputRecipientLastName;

    @FindBy(xpath = ".//*[text()=' Your email address * ']/following-sibling::input")
    public WebElement inputSenderEmailAddress;

    @FindBy(xpath = ".//*[text()=' Your phone number *']/following-sibling::input")
    public WebElement inputSenderPhoneNumber;

    @FindBy(xpath = ".//*[text()=' Postcode * ']/following-sibling::input")
    public WebElement inputReturnPostCode;

    @FindBy(xpath = "(.//button[@data-gtm-track ='btn - Add to basket'])[1]")
    public WebElement btnAddToBasket;

    @FindBy(xpath = ".//h5[@class='header__item-title']")
    public WebElement lblParcelSelected;

    @FindBy(xpath = ".//div[@class='main__block-of-data']/ul/li")
    public WebElement lblDeliverToAddress;

    @FindBy(xpath = ".//div[@class='main__block-of-data']//div")
    public WebElement lblParcelShopAddress;

    JavascriptExecutor executor = (JavascriptExecutor) getDriver();

    @Step("Opening the Evri login page")
    public void openHomePage() {
        open();
    }

    @Step("User will accept the popup")
    public EvriPageObject acceptPopup() {
        By by = By.xpath(".//button[text()='Accept all cookies']");
        getDriver().findElement(by).click();
        return this;
    }

    @Step("User will select 'no thanks'")
    public EvriPageObject acceptNoThanks() {
        By by = By.xpath(".//*[contains(text(), 'No thanks')]");
        int count = getDriver().findElements(by).size();
        if (count > 0)
            getDriver().findElements(by).get(0).click();
        return this;
    }

    @Step("User will click on login button without entering credentials")
    public EvriPageObject clickLoginWithoutEnteringCredentials() throws InterruptedException {
        lnkLogin.click();
        btnSubmit.click();
        return this;
    }

    @Step("User will verify the error messages")
    public void verifyErrorMessage() {
        Assert.assertTrue(lblErrors.get(0).getText().trim().equalsIgnoreCase("Email can't be blank"));
        Assert.assertTrue(lblErrors.get(1).getText().trim().equalsIgnoreCase("Password can't be blank"));
    }

    @Step("User will click on login button with valid credentials")
    public EvriPageObject applicationLogin(String userId, String pwd) {
        lnkLogin.click();
        inputEmail.sendKeys(userId);
        inputPwd.sendKeys(pwd);
        btnSubmit.click();
        return this;
    }

    @Step("Verify user is able to login to the application")
    public void verifyUserLoggedIn() {
        Assert.assertTrue(lblLoggedInUser.getText().contains("Sudhakar"));
    }

    @Step("Enter From and To postcode")
    public EvriPageObject enterPostCode(String from, String to) {
        inputFromPostCode.sendKeys(from);
        inputToPostCode.sendKeys(to);
        return this;
    }

    @Step("Select weight from drop down")
    public EvriPageObject selectWeight() {
        drpDownLists.get(1).click();
        drpDownOptionTexts.get(0).click();
        return this;
    }

    @Step("Click on Get price button")
    public EvriPageObject getPrice() {
        acceptNoThanks();
        btnGetPrice.click();
        return this;
    }

    @Step("Enter all parcel matrix data")
    public EvriPageObject enterParcelMatrixData() throws InterruptedException {
        acceptNoThanks();
        lblSelectForPostable.click();
        lblSelectForStandard.click();
        lblSelectForDropOff.click();
        lblSelectForDelOption.click();
        btnContinue.click();
        return this;
    }

    @Step("Enter parcel details and continue")
    public EvriPageObject enterParceldetails() throws InterruptedException {
        acceptNoThanks();
        inputParcelContent.sendKeys("My Parcel");
        inputWorthValue.sendKeys("799");
        btnContinue.click();
        return this;
    }

    @Step("Enter receiver details")
    public EvriPageObject enterReceiverDetails() throws InterruptedException {
        acceptNoThanks();
        inputRecipientFirstName.sendKeys("TestFirstName");
        inputRecipientLastName.sendKeys("TestLastName");
        drpDownLists.get(0).click();
        drpDownOptionTexts.get(0).click();
        btnContinue.click();
        inputSenderPhoneNumber.sendKeys("4456764934");
        Thread.sleep(3000);
        drpDownLists.get(0).click();
        drpDownOptionTexts.get(0).click();
        Thread.sleep(2000);
        btnAddToBasket.click();
        Thread.sleep(5000);
        return this;
    }

    @Step("Verify the parcel request")
    public void verifyParcelRequest() {
        acceptNoThanks();
        Assert.assertTrue(lblParcelSelected.isDisplayed());
        Assert.assertEquals("Pacel name not matching", "My Parcel", lblParcelSelected.getText().trim());
        Assert.assertTrue(lblDeliverToAddress.isDisplayed());
        Assert.assertEquals("First and Last name not matching", "TestFirstName TestLastName", lblParcelSelected.getText().trim());
        Assert.assertTrue(lblParcelShopAddress.isDisplayed());
    }

}

