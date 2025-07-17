package com.affle.iOS.Action;

import com.affle.iOS.driver.DriverManager;
import com.affle.iOS.reports.FrameWorkLogger;
import com.affle.iOS.enums.LogType;
import com.affle.iOS.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ActionPage {

    protected AppiumUtil appiumutil = new AppiumUtil((AppiumDriver) DriverManager.getWebDriver());

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"lblName\"]")
    protected List<MobileElement> lblNameLists;

    protected void chooseItemAndClick(String name, List<MobileElement> elements) {
        elements.parallelStream()
                .filter(mobileElement -> mobileElement.getText().contains(name))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    protected void click(MobileElement mobileElement, String elementName) {
        mobileElement.click();
        System.out.println(elementName + "is clicked successfully");
    }

    protected void click(By by, String elementName) {
        click((MobileElement) DriverManager.getWebDriver().findElement(by), elementName);
        System.out.println(elementName + "is clicked successfully");
    }

    protected void click(String locatorType, String value, String elementName) {
        if (locatorType.equalsIgnoreCase("xpath")) {
            click(By.xpath(value), elementName);
        }
        System.out.println(elementName + "is clicked successfully");
    }
    public void scrollToSpecificElementAndClick(By by) {
        List<MobileElement> ele = DriverManager.getWebDriver().findElements(by);
        int i=1;int max=6;
        while (ele.isEmpty()||!ele.get(0).isDisplayed()) {
            Dimension dimension = DriverManager.getWebDriver().manage().window().getSize();
            double screenHeightStart = dimension.getHeight() * 0.5;
            int scrollStart = (int) screenHeightStart;
            double screenHeightEnd = dimension.getHeight() * 0.2;
            int scrollEnd = (int) screenHeightEnd;
            int center = (int) (dimension.width * 0.5);
            new IOSTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                    .press(PointOption.point(center, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(center, scrollEnd)).release().perform();
            if (!DriverManager.getWebDriver().findElements(by).isEmpty()) {
                DriverManager.getWebDriver().findElement(by).click();
                break;
            }
            if(i==max)
                break;
            System.out.println("Scrolling "+i+" times...");
            i++;
        }
    }
    protected void scrollToSpecificElement(By by ) {
        List<MobileElement> ele = DriverManager.getWebDriver().findElements(by);
        int i=1;int max=3;
        while (ele.isEmpty()||!ele.get(0).isDisplayed()) {
            Dimension dimension = DriverManager.getWebDriver().manage().window().getSize();
            double screenHeightStart = dimension.getHeight() * 0.5;
            int scrollStart = (int) screenHeightStart;
            double screenHeightEnd = dimension.getHeight() * 0.2;
            int scrollEnd = (int) screenHeightEnd;
            int center = (int) (dimension.width * 0.5);
            new IOSTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                    .press(PointOption.point(center, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(center, scrollEnd)).release().perform();
            if(i==max)
                break;
            System.out.println("Scrolling "+i+" times...");
            i++;

        }
    }

    protected void dragAndDrop(IOSElement source, IOSElement target) {
        new IOSTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(ElementOption.element(target))
                .release()
                .perform();
    }

    protected void swipe(IOSElement source, IOSElement target){
        new IOSTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .moveTo(ElementOption.element(target))
                .release()
                .perform();
    }

    protected void longPress(IOSElement androidElement){
        new IOSTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(androidElement)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
                .perform();
    }

    protected void tap(IOSElement androidElement){
        new IOSTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .tap(TapOptions.tapOptions().withElement(ElementOption.element(androidElement)))
                .perform();
    }

    protected List<String> getDesiredValue(List<WebElement> elements, Function<WebElement,String> function){
        return elements.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public WebElement wait_until_MobileElementIs_Visible(WebDriver driver, By locator) {
        FrameWorkLogger.log(LogType.INFO,"15 secs - Waiting for element using -" + locator);
       return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> wait_until_MobileElementIs_Visible(List<WebElement> elements){
      return new WebDriverWait(DriverManager.getWebDriver(),15)
               .until(ExpectedConditions.visibilityOfAllElements(elements));
    }




}
