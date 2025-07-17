package PageObjects;

import enums.LogType;
import enums.WaitStrategy;
import factory.ExplicitWaitFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import reports.FrameWorkLogger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginPage {

    @FindBy(css = "div.orangehrm-login-error")
    private WebElement firstPageElements;

    @FindBy(css = "div.orangehrm-login-error p")
    private List<WebElement> lblCredentials;

    @FindBy(css = "input[name='username']")
    private WebElement inputUserIdBox;

    @FindBy(css = "input[name='password']")
    private WebElement inputpwdBox;

    @FindBy(css = "button[type='submit']")
    private WebElement btnLogin;

    @FindBy(xpath = ".//h6[.='Dashboard']")
    private WebElement lblDashboard;

    public void login() {
        FrameWorkLogger.log(LogType.INFO, "Capturing user details");
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, firstPageElements);
        Map<String, String> hashMap = lblCredentials.stream().
                map(x -> x.getAttribute("innerText").trim().split(":"))
                .collect(Collectors.toMap(
                        keyValue1 -> keyValue1[0].trim(),
                        keyValue2 -> keyValue2[1].trim()
                ));
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "User details");
        FrameWorkLogger.log(LogType.INFO, "Entering user Id");
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, inputUserIdBox);
        inputUserIdBox.sendKeys(hashMap.get("Username"));
        FrameWorkLogger.log(LogType.INFO, "Entering user Pwd");
        inputpwdBox.sendKeys(hashMap.get("Password"));
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "After entering credentials");
        FrameWorkLogger.log(LogType.INFO, "Clicking login button");
        btnLogin.click();
        FrameWorkLogger.log(LogType.INFO, "Login button clicked");
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, lblDashboard);
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "Dashboard Page opened");
    }


}
