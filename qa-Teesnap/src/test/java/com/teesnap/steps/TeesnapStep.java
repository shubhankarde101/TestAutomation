package com.teesnap.steps;

import com.teesnap.driver.DriverManager;
import com.teesnap.page.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.util.Objects;

import static com.teesnap.startup.PropertyLoader.returnConfigValue;


public class TeesnapStep extends BaseStep {
    private static final Logger LOGGER = LogManager.getLogger();

    LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());

    @Given("user launches the application url")
    public void user_launches_the_application_url() {
        LOGGER.info("--------------Test Started---------------");
        LOGGER.info("Verifying the page title");
        Assert.assertTrue(Objects.requireNonNull(DriverManager.getWebDriver().getTitle()).trim().contains("Teesnap Golf Links"));
        LOGGER.info("Page title verified");
    }

    @When("user enters the credentials")
    public void user_enters_credentials() {
        LOGGER.info("User is entering credentials");
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
    
}
