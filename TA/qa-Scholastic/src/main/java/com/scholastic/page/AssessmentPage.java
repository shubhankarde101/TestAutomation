package com.scholastic.page;

import com.scholastic.utils.ApplicationUtils;
import com.scholastic.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AssessmentPage {

    private WebDriver driver;

    public AssessmentPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Assessment']")
    public WebElement btnAssessment;

    @FindBy(xpath = "//div[@class='litpro-test-content']//button")
    private WebElement btnLipProTest;

    @FindBy(xpath = "//button[text()='Take the Test Now']")
    private WebElement btnTakeTestNow;

    @FindBy(xpath = "//button[text()='Start Test']")
    private WebElement btnStartTest;

    @FindBy(xpath = "//button[text()='Resume']")
    private WebElement btnResume;

    @FindBy(xpath = "//button[text()=' Next ']")
    private List<WebElement> btnNext;

    public boolean getExamCompletionStatus() {
        boolean flag = false;
        ApplicationUtils.handleLoaderSpinner();
        if (btnLipProTest.isEnabled()) {
            System.out.println("Let's start/resume exam");
            String text = btnLipProTest.getText();
            if (text.contains("Resume")) {
                SeleniumUtils.clickUsingJs(driver, btnLipProTest);
                ApplicationUtils.handleLoaderSpinner();
                btnResume.click();
            } else {
                SeleniumUtils.clickUsingJs(driver, btnLipProTest);
                ApplicationUtils.handleLoaderSpinner();
                btnTakeTestNow.click();
                btnStartTest.click();
            }
            flag = ApplicationUtils.writeExam(driver,"litpro-end");
        }
        return flag;
    }


}
