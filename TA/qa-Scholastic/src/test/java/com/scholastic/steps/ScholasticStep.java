package com.scholastic.steps;

import com.scholastic.driver.DriverManager;
import com.scholastic.page.AssessmentPage;
import com.scholastic.page.BookReadingPage;
import com.scholastic.page.LibraryPage;
import com.scholastic.page.LoginPage;
import com.scholastic.utils.ApplicationUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.util.Objects;

import static com.scholastic.startup.PropertyLoader.returnConfigValue;


public class ScholasticStep extends BaseStep {
    private static final Logger LOGGER = LogManager.getLogger();

    LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());
    BookReadingPage bookReadingPage = new BookReadingPage(DriverManager.getWebDriver());

    LibraryPage libraryPage = new LibraryPage(DriverManager.getWebDriver());

    AssessmentPage assessmentPage = new AssessmentPage(DriverManager.getWebDriver());

    @Given("user launches the application url")
    public void user_launches_the_application_url() {
        LOGGER.info("--------------Test Started---------------");
        LOGGER.info("The current URL is: "+DriverManager.getWebDriver().getCurrentUrl());
        LOGGER.info("Verifying the page title");
        Assert.assertEquals(Objects.requireNonNull(DriverManager.getWebDriver().getTitle()).trim(), "Scholastic Learning Zone");
        LOGGER.info("Page title verified");
    }

    @When("user enters the credentials")
    public void user_enters_credentials() {
        LOGGER.info("User is entering credentials");
        LOGGER.info("The current user is: "+returnConfigValue("user"));
        loginPage.inputUserName.sendKeys(returnConfigValue("user"));
        loginPage.inputPwd.sendKeys(returnConfigValue("pwd"));
        loginPage.btnLogin.click();
        LOGGER.info("Credentials entered");
    }

    @Then("user will be able to login to the application")
    public void standard_user_able_to_SignIn() {
        LOGGER.info("Verify standard user is able to sign in");
        Assert.assertTrue(returnConfigValue("loggedInUserLabel").contains(loginPage.verifyUserIsLoggedIn()));
        LOGGER.info("User Signed in successfully");
    }

    @And("the student dashboard page should be launched")
    public void launch_Student_Dashboard() {
        Assert.assertTrue("Dashboard not launched",bookReadingPage.launchDashboard());
        LOGGER.info("Student dashboard launched successfully");
    }

    @And("user will be able to select the zone {string}")
    public void user_selects_zone(String zone) {
        LOGGER.info("User selects zone");
        bookReadingPage.selectZone(zone);
        LOGGER.info("Zone selected");
    }

    @And("user will be able to read any book from section {string} with page threshold {int}")
    public void user_reads_a_book(String section, int limit) {
        LOGGER.info("User started book reading");
        bookReadingPage.startReading(section,limit);
        LOGGER.info("Reading completed");
        LOGGER.info("--------------Test Ended---------------");
    }
    @Then("user search for the book {string}")
    public void userSearchForTheBook(String book) {
        LOGGER.info("Searching a book in the library");
        libraryPage.searchBook(book);
        LOGGER.info("Search completed");
    }

    @And("the resulted book {string}  will be shown")
    public void theResultedBookWillBeShown(String book) {
        LOGGER.info("Validating the book is searchable");
        Assert.assertTrue("The desired book is not populated",libraryPage.verifyTheDesiredBookPopulated(book));
        LOGGER.info("Validation successful");
    }
    @And("user writes the Lit-pro test exam")
    public void userWritesTheLitProTestExam() {
        LOGGER.info("Starting the assessment");
        assessmentPage.btnAssessment.click();
        Assert.assertTrue("The Exam is already taken, please try later",assessmentPage.getExamCompletionStatus());
        ApplicationUtils.getButton("Start Reading").get(0).click();
        ApplicationUtils.handleLoaderSpinner();
        ApplicationUtils.logout();
        LOGGER.info("Validation successful");
    }

    @Then("user filters quiz from Library")
    public void userFiltersQuiz() {
        LOGGER.info("Taking a quiz through filter in Library page");
        libraryPage.filterQuiz();
        LOGGER.info("Quiz completed");
    }

    @And("user finishes the quiz")
    public void userFinishesQuiz() {
        LOGGER.info("Taking a quiz through filter in Library page");
        Assert.assertTrue("The quiz is already taken",libraryPage.writeQuiz());
        LOGGER.info("Quiz completed");
        ApplicationUtils.getButton("Earn More Points").get(0).click();
        ApplicationUtils.handleLoaderSpinner();
        ApplicationUtils.logout();
        LOGGER.info("Validation successful");
    }
}
