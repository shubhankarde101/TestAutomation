package com.affle.iOS.utils;

import com.affle.iOS.Action.ActionPage;
import com.affle.iOS.driver.DriverManager;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.*;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class AppiumUtil  {
    private static Logger log = LogManager.getLogger();
    private final AppiumDriver<WebElement> driver;
    private String parentHandle;
    private Alert alert;
    private WebDriverWait wait;

    public AppiumUtil(AppiumDriver driver) {
        this.driver = driver;
    }

    // ******* EXPLICIT WAITS ON SINGLE ELEMENT ******************//
    // ************************************************************

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS CLICKABLE - DISPLAYED AND ENABLED
    public WebElement wait_until_MobileElementIs_Clickable(WebDriver driver, By locator) {
        log.info("15 secs - Waiting for element using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public WebElement wait_until_MobileElementIs_Visible(WebDriver driver, By locator) {
        log.info("15 secs - Waiting for element using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public WebElement wait_until_MobileElementIs_Present(WebDriver driver, By locator) {
        log.info("15 secs - Waiting for element using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ************* EXPLICIT WAITS ON MULTIPLE ELEMENTS ***************//
    // ************************************************************

    // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS PRESENT
    public List<WebElement> wait_until_MobileElementsAre_Present(WebDriver driver, By locator) {
        log.info("5 secs - Waiting for elements using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS VISIBLE
    public List<WebElement> wait_until_MobileElementsAre_Visible(WebDriver driver, By locator) {
        log.info("5 secs - Waiting for elements using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> wait_until_MobileElementsAre_Visible(
            WebDriver driver, By locator, int timeInSeconds) {
        log.info("5 secs - Waiting for elements using -" + locator);
        wait = new WebDriverWait(driver, timeInSeconds);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // ******** EXPLICIT WAITS ON PAGE TITLE,URL AND ELEMENT_NOT_PRESENT ************//

    public boolean is_MobileElement_NotPresent(WebDriver driver, By locator) {
        log.info("5 secs - checking for element presence using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // **** CONTEXT SWITCHING *****//
    // ************************************************************

    public Set<String> switchToWebViewAndReturnAllContextHandles() {
        log.info("Trying to switch to webview");
        Set<String> availableContexts = driver.getContextHandles();
        log.info("No of Contexts Found  = " + availableContexts.size());
        for (String context : availableContexts) {
            log.info("Context - " + context);
            if (context.matches(".*?WEBVIEW.*?")) {
                log.info("Switching to context " + context);
                driver.context(context);
                log.info("Context switched to -" + driver.getContext());
                break;

            }
        }
        return availableContexts;
    }

    public void switchToNativeContext(Set<String> availableContexts) {
        for (String context : availableContexts) {
            if (context.contains("NATIVE")) {
                log.info("Trying to switch to native context");
                driver.context(context);
                log.info("Switched to Context" + context);
            }
        }
    }

    // **** SCROLL FUNCTIONS (SCROLL'S ON ENTIRE PAGE) *****//
    // ************************************************************


    // ********** TOUCH ACTIONS **************//
    // ************************************************************

    public void pressAndHold(WebElement element1, int timeInSeconds) {
        Rectangle rect1 = element1.getRect();

        TouchAction<?> touch = new TouchAction<>(driver);
        touch
                .longPress(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
                .waitAction(waitOptions(Duration.ofSeconds(timeInSeconds)))
                .release()
                .perform();
        log.info("Press and Hold Action performed");
    }

    public void pressElement(MobileElement element, long seconds) {
        new TouchAction<>(driver)
                .press(element(element))
                .waitAction(waitOptions(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    public void tap(WebElement element) {
        new TouchAction<>(driver).tap(TapOptions.tapOptions().withElement(element(element))).perform();
    }

    public void tap(WebElement element, int milliseconds) {
        new TouchAction<>(driver)
                .tap(TapOptions.tapOptions().withElement(element(element)))
                .waitAction(waitOptions(Duration.ofMillis(milliseconds)))
                .perform();
    }

    public void tapByCoordinates(int x, int y) {
        new TouchAction<>(driver)
                .tap(PointOption.point(x, y))
                .waitAction(waitOptions(Duration.ofMillis(250)))
                .perform();
    }

    public void basicSwipe(WebElement element1, WebElement element2) {
        Rectangle rect1 = element1.getRect();
        Rectangle rect2 = element2.getRect();

        TouchAction<?> touch = new TouchAction<>(driver);
        touch
                .press(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight()))
                .moveTo(
                        PointOption.point(
                                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight()))
                .release()
                .perform();
        log.info("Swipe Action performed");
    }

    public void dragDrop(WebElement element1, WebElement element2) {
        Rectangle rect1 = element1.getRect();
        Rectangle rect2 = element2.getRect();

        TouchAction<?> touch = new TouchAction<>(driver);
        touch
                .longPress(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
                .moveTo(
                        PointOption.point(
                                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight() / 2))
                .release()
                .perform();
        log.info("Drag and drop Action performed");
    }

    // ******************************     IOS FUNCTIONS      ******************************* //
    // ************************************************************

    // ***** RETURN MOBILE ELEMENT ****** //

    public WebElement ios_returnMobileElementUsingAccessibilityId(String accessibilityId) {
        log.info("Trying to find element with id - " + accessibilityId);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.AccessibilityId(accessibilityId));
    }

    public WebElement ios_returnMobileElementUsingName(String name) {
        log.info("Trying to find element with name - " + name);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.name(name));
    }

    public WebElement ios_returnMobileElementUsingClassName(String className) {
        log.info("Trying to find element with class name - " + className);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.className(className));
    }

    public WebElement ios_returnMobileElementUsingXpath(String xPath) {
        log.info("Trying to find element with xPath - " + xPath);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.xpath(xPath));
    }

    public WebElement ios_returnMobileElementUsingPredicateString(String predicateString) {
        log.info("Trying to find element with predicateString - " + predicateString);
        return wait_until_MobileElementIs_Visible(
                driver, MobileBy.iOSNsPredicateString(predicateString));
    }

    public WebElement ios_returnMobileElementUsingClassChain(String classChain) {
        log.info("Trying to find element with classChain - " + classChain);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.iOSClassChain(classChain));
    }

    // ***** PRESENCE OF MOBILE ELEMENT ****** //

    public boolean ios_isElementPresentUsingAccessibilityId(String acessibilityId) {

        try {
            log.info("Trying to find element with AcessibilityId - " + acessibilityId);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.AccessibilityId(acessibilityId)), 5)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingName(String name) {

        try {
            log.info("Trying to find element with name - " + name);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.name(name)), 5).size() >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingXpath(String xPath) {

        try {
            log.info("Trying to find element with xPath - " + xPath);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), 5).size() >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingPredicateString(String predicateString) {

        try {
            log.info("Trying to find element with predicateString - " + predicateString);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.iOSNsPredicateString(predicateString)), 5)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingClassChain(String classChain) {

        try {
            log.info("Trying to find element with classChain - " + classChain);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.iOSClassChain(classChain)), 5)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    // ***** PRESENCE OF MOBILE ELEMENT - WAIT UNTIL TIME SPECIFIED ****** //

    public boolean ios_isElementPresentUsingAccessibilityId(
            String accessibilityId, int timeSeconds) {

        try {
            log.info("Trying to find element with AcessibilityId - " + accessibilityId);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.AccessibilityId(accessibilityId)), timeSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingName(String name, int timeinSeconds) {

        try {
            log.info("Trying to find element with name - " + name);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.name(name)), timeinSeconds).size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingXpath(String xPath, int timeinSeconds) {

        try {
            log.info("Trying to find element with xPath - " + xPath);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), timeinSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingPredicateString(
            String predicateString, int timeinSeconds) {
        try {
            log.info("Trying to find element with predicateString - " + predicateString);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.iOSNsPredicateString(predicateString)), timeinSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingClassChain(String classChain, int timeinSeconds) {

        try {
            log.info("Trying to find element with classChain - " + classChain);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.iOSClassChain(classChain)), timeinSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    // ***** IOS SCROLL FUNCTIONS ****** //

    public void ios_scroll(int starty, int endy) {
        log.info("Trying to scroll down on screen from " + starty + " to " + endy);
        Dimension size = driver.manage().window().getSize();
        int x = size.getWidth() / 2;
        int start_y = (int) (size.getHeight() * starty / 100);
        int end_y = (int) (size.getHeight() * endy / 100);
        new TouchAction<>(driver)
                .press(PointOption.point(x, start_y))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
        log.info("scroll done");
    }

    public void ios_scrollUntilLabelIsFound(String label, int starts, int end, int maxScrollTimes) {
        int retry = 0;
        while (!ios_isElementPresentUsingPredicateString("label =='" + label + "'", 1)) {
            if (retry > maxScrollTimes) {
                break;
            }
            ios_scroll(starts, end);
            retry++;
        }
    }

    public void ios_scrollDown_UsingParentElementToLabel(RemoteWebElement parent, String label) {

        log.info("Trying to scroll down in a container to element with label -" + label);
        String parentID = parent.getId();
        Map<String, String> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", parentID);
        params.put("predicateString", "label == " + label + "");
        ((JavascriptExecutor) driver).executeScript("mobile: scroll", params);
        log.info("scrolled to element with label -" + label);
    }

    public void ios_scrollUp_UsingParentElementToLabel(RemoteWebElement parent, String label) {
        log.info("Trying to scroll down to element with label -" + label);
        String parentID = parent.getId();
        HashMap<String, String> params = new HashMap<>();
        params.put("direction", "up");
        params.put("element", parentID);
        params.put("predicateString", "label == '" + label + "'");
        ((JavascriptExecutor) driver).executeScript("mobile: scroll", params);
        log.info("scrolled to element with label -" + label);
    }

    public boolean ios_swipeToDirection(MobileElement el, String direction) {
        try {
            HashMap<String, String> swipeObject = new HashMap<String, String>();
            switch (direction) {
                case "down":
                    swipeObject.put("direction", "down");
                    break;
                case "up":
                    swipeObject.put("direction", "up");
                    break;
                case "left":
                    swipeObject.put("direction", "left");
                    break;
                case "right":
                    swipeObject.put("direction", "right");
                    break;
            }
            swipeObject.put("element", el.getId());
            ((JavascriptExecutor) driver).executeScript("mobile:swipe", swipeObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Swiping element

    public void ios_swipeElementToTheLeft(WebElement el) {

        log.info("Trying to swipe element to the left");
        int x = el.getLocation().getX();
        int y = el.getLocation().getY();
        // elements x location is the beginning point of the element , multiplying with 4 to get to the
        // middle part of the element
        new TouchAction<>(driver)
                .press(PointOption.point(x * 4, y))
                .waitAction(waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x, y))
                .release()
                .perform();
        log.info("Element swipe to the left performed");
    }

    public void ios_swipeElementToTheRight(WebElement el) {

        log.info("Trying to swipe element to the left");
        int x = el.getLocation().getX();
        int y = el.getLocation().getY();
        // elements x location is the beginning point of the element , multiplying with 4 to get to the
        // middle part of the element
        new TouchAction<>(driver)
                .press(PointOption.point(x, y))
                .waitAction(waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x * 4, y))
                .release()
                .perform();
        log.info("Element flicked to the left");
    }

    public void ios_swipeElementToTheDown(WebElement el) {

        log.info("Trying to swipe element to the left");
        int x = el.getLocation().getX();
        int y = el.getLocation().getY();
        // elements x location is the beginning point of the element , multiplying with 4 to get to the
        // middle part of the element
        new TouchAction<>(driver)
                .press(PointOption.point(x, y))
                .waitAction(waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x, y * 5))
                .release()
                .perform();
        log.info("Element flicked to the down");
    }

    // ********* MULTI TOUCH ACTIONS ***************************//

    public void zoom_Using_MultiTouchActions() {

        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        int halfWidth = width / 2;
        int halfHeight = height / 2;

        MultiTouchAction multiTouch = new MultiTouchAction(driver);
        TouchAction<?> touch1 = new TouchAction<>(driver);
        TouchAction<?> touch2 = new TouchAction<>(driver);

        touch1
                .press(PointOption.point(halfHeight, halfHeight))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, 60))
                .release();
        touch2
                .press(PointOption.point(halfHeight, halfHeight + 40))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, 80))
                .release();

        multiTouch.add(touch1).add(touch2);
        multiTouch.perform();
    }

    // ****************************  SELENIUM FUNCTIONS *****************************//

    // ******* EXPLICIT WAITS ON SINGLE ELEMENT ******************//

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS CLICKABLE - DISPLAYED AND ENABLED
    public WebElement wait_until_ElementIs_Clickable(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public WebElement wait_until_ElementIs_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public WebElement wait_until_ElementIs_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL SELENIUM FINDS 2 WINDOWS
    public void wait_until_Two_Windows_Are_Available(WebDriver driver) {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    // ************* EXPLICIT WAITS ON MULTIPLE ELEMENTS ***************//

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public List<WebElement> wait_until_ElementsAre_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public List<WebElement> wait_until_ElementsAre_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // ******** EXPLICIT WAITS ON PAGE TITLE,URL AND ELEMENT_NOT_PRESENT ************//

    public boolean wait_until_TitleContains(WebDriver driver, String keyword) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.titleContains(keyword));
    }

    public boolean wait_until_URL_Matches(WebDriver driver, String regex) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlMatches(regex));
    }

    public boolean IS_Element_NotPresent(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ************** NORMAL CLICK *******************//

    public void click(WebDriver driver, By locator) {
        wait_until_ElementIs_Present(driver, locator).click();
        waitForPageToLoad(driver);
    }

    // ******************** ACTIONS ***********************//

    // Hover over an element
    public void hover(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(wait_until_ElementIs_Visible(driver, locator)).build().perform();
        log.info("Hover action done on element -" + locator);
        waitForPageToLoad(driver);
    }

    // Hover over an element and click
    public void hoverAndClick(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(wait_until_ElementIs_Visible(driver, locator)).click().build().perform();
        log.info("click action on -" + locator + " performed");
        waitForPageToLoad(driver);
    }

    public void hoverAndClear(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        action.moveToElement(el).click().build().perform();
        el.clear();
        log.info("click and clear actions on -" + el + " performed");
        waitForPageToLoad(driver);
    }

    // Hover over an element, click and press enter
    public void hoverClickAndPressEnter(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Hover over an element click and send data
    public void hoverClickAndSendData(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(data)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Hover over an element click, send data and press enter
    public void hoverClickSendDataAndPressEnter(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(data)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // sender
    public void hoverAndSendData(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .sendKeys(data)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Double click
    public void doubleClick(WebDriver driver, By locator) {
        Actions doubleClick = new Actions(driver);
        doubleClick.doubleClick(wait_until_ElementIs_Visible(driver, locator)).build().perform();
        waitForPageToLoad(driver);
    }

    // Drag and Drop by offset
    public void dragAndDropOffset(WebDriver driver, By locator, int offsetX, int offsetY) {
        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(el, offsetX, offsetY).build().perform();
        waitForPageToLoad(driver);
    }

    // Drag and drop Elements
    public void dragAndDropToElementContainner(
            WebDriver driver, WebElement source, WebElement target) {
        Actions builder = new Actions(driver);
        builder.dragAndDrop(source, target).build().perform();
        waitForPageToLoad(driver);
    }

    // *********** JAVA SCRIPT CLICK **********************************//

    public void jsClick(WebDriver driver, By locator) {
        String code =
                "var fireOnThis = arguments[0];"
                        + "var evObj = document.createEvent('MouseEvents');"
                        + "evObj.initEvent( 'click', true, true );"
                        + "fireOnThis.dispatchEvent(evObj);";

        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        ((JavascriptExecutor) driver).executeScript(code, el);
        waitForPageToLoad(driver);
    }

    public void jsFocusAndClick(WebDriver driver, By locator) {
        WebElement element = wait_until_ElementIs_Present(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForPageToLoad(driver);
    }

    public void jsFocusAndClick(WebDriver driver, WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        waitForPageToLoad(driver);
    }

    // ************ PAGE LOAD STATE ****************************//

    // Get Page State
    public String getPageState(WebDriver driver) {
        WebElement el = driver.findElement(By.cssSelector("body"));
        String code = "return document.readyState";
        String result = (String) ((JavascriptExecutor) driver).executeScript(code, el);
        log.info("PageState-" + result);
        return result;
    }

    // Wait For Page to Load Completely
    public void waitForPageToLoad(WebDriver driver) {
        while (!getPageState(driver).equals("complete")) {
            sleep(1);
        }
    }

    // Wait for Page title to change
    public void waitForPageTitleToChange(WebDriver driver, String title) {
        while (driver.getTitle().equalsIgnoreCase(title)) {
            sleep(1);
        }
        while (driver.getTitle().matches(".*?" + title + ".*?")) {
            sleep(1);
        }
    }

    // ******************** WINDOW HANDLES ******************************//

    public int getWindowHandlesSize(WebDriver driver) {
        log.info("Window Handles -" + driver.getWindowHandles().size());
        return driver.getWindowHandles().size();
    }

    public void switchToNewWindowHandle_After_ClickingOnGivenElement(WebDriver driver, By locator) {

        parentHandle = driver.getWindowHandle();
        log.info("Parent handle -" + parentHandle);
        wait_until_ElementIs_Clickable(driver, locator).click();
        waitForPageToLoad(driver);

        if (driver.getWindowHandles().size()
                >= 2) { // switch to a new window handle if there more than 1 window handles.
            // Switch to new window opened
            for (String winHandle : driver.getWindowHandles()) {
                log.info("Window Handle -" + winHandle);
                if (!winHandle.equals(parentHandle)) {
                    driver.switchTo().window(winHandle);
                    log.info("Switching to Child Window handle");
                    break;
                }
                driver.manage().window().maximize();
            }
        }
    }

    public void switchToNewWindowHandle(WebDriver driver, String parentHandle) {

        int windowHandles = driver.getWindowHandles().size();

        if (windowHandles >= 2) { // switch to a new window handle if there more than 1 window handles.
            for (String winHandle : driver.getWindowHandles()) {
                log.info("Window Handle -" + winHandle);
                if (!winHandle.equals(parentHandle)) {
                    driver.switchTo().window(winHandle);
                    log.info("Switching to Child Window handle -" + winHandle);
                    break;
                }
                driver.manage().window().maximize();
            }
        } else {
            log.info("Window handles found - " + windowHandles);
        }
    }

    public void switchToParentWindowHandle(WebDriver driver) {
        driver.switchTo().window(parentHandle);
        driver.manage().window().maximize();
    }

    // ******************** FRAMES *************//

    public int getNumberOfFrames(WebDriver driver) {
        return driver.findElements(By.tagName("iframe")).size();
    }

    public void SwitchToFrame_ByNumber(int n) {
        driver.switchTo().frame(n);
    }

    public void SwitchToFrame_ByNAME_OR_ByID(String namedID) {
        driver.switchTo().frame(namedID);
    }

    // Switch To default Content - Works to get back from a frame
    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    // ************* ALERTS *********************//

    public boolean isAlertPresent_SwitchToAlert(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public String getAlertText(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void sendTextToAlert(WebDriver driver, String text) {
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    public void closeAlert(WebDriver driver, boolean acceptAlert) {
        alert = driver.switchTo().alert();
        if (acceptAlert) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    // *************** EXTRAS ********************//

    // REFRESH PAGE
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // Sleep
    public void sleep(int s) {
        try {
            Thread.sleep(s * 1000L);
        } catch (InterruptedException | IllegalArgumentException ignored) {
        }
    }

    public boolean verify_Element_NotPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() == 0;
    }

    public void wait_until_Element_is_Not_Present(WebDriver driver, By locator) {
        if (driver.findElements(locator).size() > 0) {
            sleep(2);
        }
    }

    public String randomString() {
        Date date = new Date();
        Format dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateformat.format(date);
    }

    public void clickText(String text) { // used to get focus out of a text box
        hoverAndClick(driver, By.xpath("//*[contains(text(),'" + text + "')]"));
        sleep(2);
    }

    // ************ PERFORMANCE/NETWORK **************************//

    public String getNetworkData() {
        String scriptToExecute =
                "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        return ((JavascriptExecutor) driver).executeScript(scriptToExecute).toString();
    }

    // ************ ROBOT **************************//

    public void setClipboardData(String string) {
        // StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void uploadFile(String fileLocation) {

        if (Platform.getCurrent().toString().matches(".*?(win|WIN).*?")) {
            try {
                setClipboardData(fileLocation);
                Robot robot = new Robot();

                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }

        if (Platform.getCurrent().toString().matches(".*?(mac|MAC).*?")) {
            try {
                setClipboardData(fileLocation);
                Robot robot = new Robot();

                // Cmd + Tab is needed since it launches a Java app and the browser looses focus

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.delay(500);

                // Open Goto window

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_G);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_G);

                // Paste the clipboard value

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_V);

                // Press Enter key to close the Goto window and Upload window

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String generateRandomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public String getRandomNumber() {
        Random random = new Random();
        String randomString = "9836";
        for (int i = 0; i < 6; i++) {
            randomString += Integer.toString(random.nextInt(10));
        }
        return randomString;
    }
    public void clickOnStaticTextElement(List<MobileElement> e, String textVal) {
        try {
            e.stream().filter(x -> x.getAttribute("label").equalsIgnoreCase(textVal)).findFirst().get().click();
        } catch (Exception exception) {
            e.stream().filter(x -> x.getAttribute("value").equalsIgnoreCase(textVal)).findFirst().get().click();
        }
    }

    protected void scrollToSpecificElement(By by ) {
        int count =1;int MaxCount =30;
        while (DriverManager.getWebDriver().findElements(by).isEmpty()||!DriverManager.getWebDriver().findElement(by).isDisplayed()) {
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
            if(count==MaxCount)
                break;
            System.out.println("Scrolling "+count+" times...");
            count++;
        }
    }
    public MobileElement getStaticTextElement(String textVal) {
        String xpath = "//XCUIElementTypeStaticText[contains(@label,\""+textVal+"\")]";
        System.out.println("The xpath is: "+xpath);
        By by = By.xpath(xpath);
        scrollToSpecificElement(by);
        MobileElement e1 = (MobileElement) DriverManager.getWebDriver().findElement(by);
        return e1;
    }
    private static String convertStringToDate(String dateString) {
        DateFormat shortFormat = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH);
        DateFormat mediumFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
        String shortDate = null;
        try {
            shortDate = shortFormat.format(mediumFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(shortDate);
        return shortDate;
    }
}
