package starter.PageObjects;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class QuotePage extends PageObject {

    @FindBy(xpath = ".//div[@role='heading']")
    public List<WebElement> lblHeadings;

    @FindBy(xpath = ".//span[contains(text(), 'quote')]")
    public List<WebElement> lblQuoteActions;

    @FindBy(xpath = ".//*[text()='Next']")
    public WebElement btnNext;

    @FindBy(xpath = ".//*[text()='Back']")
    public WebElement btnBack;

    @FindBy(xpath = ".//span[text()='Primary Country']//ancestor::div[@class='Qr7Oae']//*[text()='Choose']")
    public WebElement btnChoosePrimaryCountry;

    @FindBy(xpath = ".//span[text()='Primary Insured']//ancestor::div[@class='Qr7Oae']//*[text()='Choose']")
    public WebElement btnChoosePrimaryInsured;

    @FindBy(xpath = ".//input[@type='date']")
    public WebElement inputDate;

    @FindBy(xpath = ".//*[text()='AUM Value ($)']//ancestor::div[@class='Qr7Oae']//input")
    public WebElement inputAum;

    @FindBy(xpath = ".//*[text()='Premium ($)']//ancestor::div[@class='Qr7Oae']//input")
    public WebElement inputPremium;

    @FindBy(xpath = ".//*[text()='Success — Quote created']")
    public WebElement txtQuoteCreatedSuccessfully;

    @FindBy(xpath = ".//div[@class='Qr7Oae']")
    public List<WebElement> lblPendingQuotes;

    @FindBy(xpath = ".//*[text()='This is a required question']")
    public WebElement txtErrorMsg;

    @FindBy(xpath = ".//*[text()='Clear form']")
    public List<WebElement> lnkClearForm;


    JavascriptExecutor executor = (JavascriptExecutor) getDriver();

    public void openHomePage() {
        open();
    }

    public String verifyPageTitle() {
        return getDriver().getTitle();
    }

    public void selectQuoteAction(String action) {
        lblQuoteActions.stream().
                filter(x -> x.getAttribute("innerText").trim().
                        equalsIgnoreCase(action)).
                findFirst().get().click();
    }

    public void clickNextButton() {
        btnNext.click();
    }

    public void clickBackButton() {
        btnBack.click();
    }

    public void selectPrimaryCountry(String country) {
        btnChoosePrimaryCountry.click();
        By by = By.xpath(".//div[@class='OA0qNb ncFHed QXL7Te']//span[text()='" + country + "']");
        explicitWaitForVisibilityOfAnElement(by);
        WebElement ele = getDriver().findElement(by);
        executor.executeScript("arguments[0].click();", ele);

    }

    public void selectPrimaryInsured(String insured) {
        btnChoosePrimaryInsured.click();
        By by = By.xpath(".//div[@class='OA0qNb ncFHed QXL7Te']//span[text()='" + insured + "']");
        explicitWaitForVisibilityOfAnElement(by);
        WebElement ele = getDriver().findElement(by);
        executor.executeScript("arguments[0].click();", ele);
    }


    public void selectBusinessClass(String businessClass) {
        getDriver().findElement(By.xpath(".//span[text()='" + businessClass + "']//ancestor::div[@class='bzfPab wFGF8']/div")).click();
    }

    public void enterDate(String date) {
        inputDate.sendKeys(date);
    }

    public void enterAUM(String aum) {
        inputAum.clear();
        inputAum.sendKeys(aum);
    }

    public void enterPremium(String premium) {
        inputPremium.clear();
        inputPremium.sendKeys(premium);
    }

    public boolean verifyQuoteCreated()
    {
        return txtQuoteCreatedSuccessfully.isDisplayed();
    }

    public void explicitWaitForVisibilityOfAnElement(By byEle) {
        (new WebDriverWait(getDriver(), Duration.ofMillis(10000)))
                .until(ExpectedConditions.visibilityOfElementLocated(byEle));
    }


}

