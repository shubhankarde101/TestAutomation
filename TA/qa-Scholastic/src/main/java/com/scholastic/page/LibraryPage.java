package com.scholastic.page;

import com.scholastic.utils.ApplicationUtils;
import com.scholastic.utils.SeleniumUtils;
import com.scholastic.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LibraryPage {

    private WebDriver driver;

    public LibraryPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Library']")
    private WebElement btnLibrary;

    @FindBy(css = "div.smart-search-list li")
    private List<WebElement> inputSearchResults;

    @FindBy(css = "p.detail-description")
    private List<WebElement> populatedBooks;

    @FindBy(css = "input#hasQuiz")
    private WebElement quizFilter;

    @FindBy(xpath = "//button[text()='Start Now']")
    private WebElement btnStartNow;
    @FindBy(css = "span[class*='title']")
    private List<WebElement> quizTakenMsg;

    //a[text()='Read Again']

    String courseQuizLocator = "//button[text()='Quiz']";

    public void openLibrary() {
        ApplicationUtils.handleLoaderSpinner();
        SeleniumUtils.clickUsingJs(driver, btnLibrary);
        ApplicationUtils.handleLoaderSpinner();
    }

    public void searchBook(String bookName) {
        openLibrary();
        String inputSearchLocator = "input#search-field";
        WaitElement.waitVisibilityOf(driver, By.cssSelector(inputSearchLocator));
        SeleniumUtils.getElementByCss(driver, inputSearchLocator).sendKeys(bookName);
        ApplicationUtils.handleLoaderSpinner();
        String searchResultLocator = "div.smart-search-list li";
        WaitElement.waitVisibilityOf(driver, By.cssSelector(searchResultLocator));
        SeleniumUtils.getElementsByCss(driver, searchResultLocator).stream().filter(book -> book.getText().trim().equalsIgnoreCase(bookName)).findFirst().orElseThrow().click();
        ApplicationUtils.handleLoaderSpinner();
    }

    public boolean verifyTheDesiredBookPopulated(String bookName) {
        SeleniumUtils.waitForPageLoad(driver);
        return populatedBooks.stream().filter(book -> book.getText().trim().equalsIgnoreCase(bookName)).findFirst().orElseThrow().isDisplayed();
    }

    public void filterQuiz() {
        openLibrary();
        SeleniumUtils.clickUsingJs(driver, quizFilter);
        ApplicationUtils.handleLoaderSpinner();
        WaitElement.waitVisibilityOf(driver, By.xpath(courseQuizLocator));
    }

    public boolean writeQuiz() {
        boolean flag = false;
        List<WebElement> quizes = driver.findElements(By.xpath(courseQuizLocator));
        List<WebElement> oddIndexList = IntStream.range(0, quizes.size()) // Create a stream of indices
                .filter(i -> i % 2 != 0) // Filter only odd indices
                .mapToObj(quizes::get) // Map indices to elements of the list
                .collect(Collectors.toList());
        System.out.println("The list size is: " + oddIndexList.size());
        WebElement action = oddIndexList.get((int) (Math.random() * oddIndexList.size()));
        if (action.getText().trim().equals("Quiz")) {
            SeleniumUtils.clickUsingJs(driver, action);
            ApplicationUtils.handleLoaderSpinner();
            if(quizTakenMsg.isEmpty())
            {   btnStartNow.click();
                if (!ApplicationUtils.getButton("Resume").isEmpty()) {
                    ApplicationUtils.getButton("Start Over").get(0).click();
                }
                flag =  ApplicationUtils.writeExam(driver, "score-bar");
            }
        }
        return flag;
    }
}
