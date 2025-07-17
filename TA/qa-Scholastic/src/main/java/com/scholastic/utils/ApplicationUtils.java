package com.scholastic.utils;


import com.scholastic.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import java.util.List;

public class ApplicationUtils {

    public static String mainWindowHandle ;

    public static void handleLoaderSpinner()
    {
        String loader = "//p[contains(text(), 'Please Wait')]";
        WaitElement.waitInVisibilityOf(DriverManager.getWebDriver(), By.xpath(loader));
        SeleniumUtils.flexibleWait(5);
    }
    public static List<WebElement> getAnswerLists()
    {
        By byAnswerLists = By.xpath("//div[contains(@class, 'answer')]//div[contains(@class,'answer')]");
        WaitElement.waitVisibilityOf(DriverManager.getWebDriver(),byAnswerLists);
        return  DriverManager.getWebDriver().findElements(byAnswerLists);
    }

    public static List<WebElement> getButton(String btnText)
    {
        By btn = By.xpath("//button[contains(text(),\""+btnText+"\")]");
        WaitElement.waitVisibilityOf(DriverManager.getWebDriver(),btn);
        return  DriverManager.getWebDriver().findElements(btn);
    }

    public static List<WebElement> getNextButton()
    {
        By nextBtn = By.xpath("//button[contains(@class, 'finish')] | //button[contains(@class, 'next')]");
        WaitElement.waitVisibilityOf(DriverManager.getWebDriver(),nextBtn);
        return  DriverManager.getWebDriver().findElements(nextBtn);
    }

    public static List<WebElement> getCongratsMsg(String msg)
    {
        By msgCongrats = By.cssSelector("div[class*='"+msg+"']");
        WaitElement.waitVisibilityOf(DriverManager.getWebDriver(),msgCongrats);
        return  DriverManager.getWebDriver().findElements(msgCongrats);
    }

    public static void logout()
    {
        By profileIcon = By.cssSelector("#profile-icon");
        DriverManager.getWebDriver().findElement(profileIcon).click();
        By logOut = By.xpath("//a[text()='Logout']");
        DriverManager.getWebDriver().findElement(logOut).click();
        DriverManager.getWebDriver().switchTo().window(mainWindowHandle);
        Assert.assertEquals(1,DriverManager.getWebDriver().getWindowHandles().size());
    }

    public static boolean writeExam(WebDriver driver,String finisMsg) {
        getAnswerLists().get(1).click();
        while (!getNextButton().isEmpty()) {
            SeleniumUtils.clickUsingJs(driver,getNextButton().get(0));
            if (!getAnswerLists().isEmpty()) {
                WebElement answerEle = getAnswerLists().get((int) (Math.random() * getAnswerLists().size()));
                SeleniumUtils.scrollToElement(driver,answerEle);
                answerEle.click();
            }
            if(!getButton("Let's Go").isEmpty()) {
                getButton("Let's Go").get(0).click();
            }
        }
        return !getCongratsMsg(finisMsg).isEmpty();
    }

}
