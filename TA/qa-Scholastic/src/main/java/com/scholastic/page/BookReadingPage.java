package com.scholastic.page;

import com.scholastic.utils.ApplicationUtils;
import com.scholastic.utils.SeleniumUtils;
import com.scholastic.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookReadingPage {

    private WebDriver driver;

    public BookReadingPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.next-button button")
    private WebElement btnNext;

    @FindBy(css = "div.prev-button button")
    private WebElement btnPrevious;

    @FindBy(css = "div.jump-to-page span")
    private WebElement totalPageCount;

    @FindBy(css = "div.modal-close-btn")
    private WebElement btnClose;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> thinkAgainCheckboxes;

    @FindBy(xpath = "//button[contains(text(),' X ')]")
    private List<WebElement> btnOverLay;

    public void selectZone(String bookName) {
        String bookLocator = ".//*[text()='" + bookName + "']//..";
        WaitElement.waitVisibilityOf(driver, By.xpath(bookLocator));
        driver.findElement(By.xpath(bookLocator)).click();

    }

    public List<WebElement> selectBookFrom(String section, String action) {
        String bookLocator = ".//*[text()='" + section + "']//..//..//div[contains(@class, 'flip-card-inner')]//button[text()='" + action + "']";
        WaitElement.waitVisibilityOf(driver, By.xpath(bookLocator));
        return driver.findElements(By.xpath(bookLocator));

    }

    public boolean launchDashboard() {
        ApplicationUtils.handleLoaderSpinner();
        SeleniumUtils.waitForPageLoad(driver);
        SeleniumUtils.flexibleWait(5);
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("Main Window Handle: " + mainWindowHandle);
        ApplicationUtils.mainWindowHandle = mainWindowHandle;
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                if (Objects.requireNonNull(driver.getTitle()).contains("Scholastic Literacy Pro")) {
                    System.out.println("Switched to window with handle: " + handle);
                    System.out.println("New window title: " + driver.getTitle());
                    break;
                }
                driver.switchTo().defaultContent();
            }
        }
        String homeLocator = "[id*='home-tab']";
        SeleniumUtils.waitForPageLoad(driver);
        SeleniumUtils.waitForText(driver, By.cssSelector(homeLocator), "Home", 10, 500);
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("dashboard");
    }


    public void startReading(String section, int bookThreshold) {
        launchDashboard();
        SeleniumUtils.waitForPageLoad(driver);
        ApplicationUtils.handleLoaderSpinner();
        List<WebElement> btnBookReading = selectBookFrom(section, "Read");
        System.out.println("The list size is: " + btnBookReading.size());
        List<WebElement> evenIndexList = IntStream.range(0, btnBookReading.size()) // Create a stream of indices
                .filter(i -> i % 2 == 0)
                .mapToObj(btnBookReading::get)
                .collect(Collectors.toList());
        WebElement action = evenIndexList.stream().findFirst().orElseThrow(() -> new RuntimeException("No elements found in the list!"));
        if (action.getText().trim().equals("Read")) {
            System.out.println("The action selected is:" + action.getText());
            SeleniumUtils.clickUsingJs(driver, action);
            ApplicationUtils.handleLoaderSpinner();
            LocalTime startTime = LocalTime.now();
            By startOver = By.xpath("//*[@alt='StartOver']");
            WaitElement.waitVisibilityOf(driver, startOver);
            driver.findElement(startOver).click();
            By btnYes = By.xpath("//*[contains(text(),'Yes')]");
            driver.findElement(btnYes).click();
            System.out.println("Start time: " + startTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
            int count = Integer.parseInt(totalPageCount.getText().replace("/", "").trim());
            System.out.println("The total page count is: " + count);
            ApplicationUtils.handleLoaderSpinner();
            if (count <= bookThreshold) {
                readPageByPage(count);
                SeleniumUtils.clickUsingJs(driver, btnNext);
                ApplicationUtils.handleLoaderSpinner();
                ApplicationUtils.getButton("Explore All Books").get(0).click();
            } else {
                System.out.println("The book size is too big to read through automation script, hence partially read");
                int pageCount = 1;
                readPageByPage(pageCount);
                btnClose.click();
            }
            LocalTime endTime = LocalTime.now();
            Duration duration = Duration.between(startTime, endTime);
            long minutes = duration.toMinutes();
            long seconds = duration.getSeconds() % 60;
            String readingDuration = "Total time spent reading: " + minutes + " minutes and " + seconds + " seconds";
            System.out.println(readingDuration);
            ApplicationUtils.logout();
        }
    }

    public void readPageByPage(int maxPage) {
        for (int i = 0; i < maxPage; i++) {
            System.out.println("Reading page: "+(i+1));
            SeleniumUtils.clickUsingJs(driver, btnNext);
            ApplicationUtils.handleLoaderSpinner();
            if (!thinkAgainCheckboxes.isEmpty()) {
                ApplicationUtils.getButton("Check").get(0).click();
                ApplicationUtils.getButton("Next").get(0).click();
            }
        }
    }
}
