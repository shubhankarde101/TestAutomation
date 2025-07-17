package com.buggycar.steps;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.buggy.startup.BaseTest;
import com.buggy.utils.WaitElement;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class BuggyCarsTestSteps extends BaseTest {
	private static final Logger LOGGER = LogManager.getLogger();

	@Before
	public void setUp() {
		preCondition();
	}

	@Given("User lanches the application url")
	public void validatePageTitle() throws InterruptedException {

		LOGGER.info("Verifying the page title");
		Assert.assertEquals(driver.getTitle().trim(), "Buggy Cars Rating", "Page title not matched");

	}

	@Then("Verify user is able to complete the registration")
	public void completeRegistration() throws InterruptedException {

		LOGGER.info("Clicking on register link");
		dashboardpage.lnkRegister.click();
		String userName = "testusername_" + Math.random();
		registrationpage.userRegistration("username", userName);
		userprofile.setUserName(userName);
		String userFirstName = "testuserfirstname_" + Math.random();
		registrationpage.userRegistration("firstName", userFirstName);
		userprofile.setFirstName(userFirstName);
		String userLastName = "testuserlastname_" + Math.random();
		registrationpage.userRegistration("lastName", userLastName);
		userprofile.setLastName(userLastName);
		String pwd = "BuggyCar@1234";
		registrationpage.userRegistration("password", pwd);
		userprofile.setPassWord(pwd);
		registrationpage.userRegistration("confirmPassword", pwd);
		registrationpage.btnRegister.click();
		LOGGER.info("Registration completed");

	}

	@Then("Verify user is able to login to the application")
	public void userLogin() throws InterruptedException {

		LOGGER.info("Clicking on login button");
		dashboardpage.btnLogin.click();
		dashboardpage.userLogin("login", userprofile.getUserName());
		dashboardpage.userLogin("password", userprofile.getPassWord());
		dashboardpage.btnLogin.click();
		Assert.assertEquals(dashboardpage.lblNavItem.getText().trim(), "Hi, " + userprofile.getFirstName(),
				"User name not matched");
		LOGGER.info("User is logged in");

	}

	@Then("Verify user is able to verify the profile section")
	public void verifyUserProfile() throws InterruptedException {

		LOGGER.info("Verifying user profile");
		dashboardpage.lnkProfile.click();
		Assert.assertEquals(dashboardpage.verifyProfile("username"), userprofile.getUserName(),
				"User name not matched");
		Assert.assertEquals(dashboardpage.verifyProfile("firstName"), userprofile.getFirstName(),
				"User first name not matched");
		Assert.assertEquals(dashboardpage.verifyProfile("lastName"), userprofile.getLastName(),
				"User first name not matched");
		LOGGER.info("Profile validation completed");

	}

	@Then("Verify user is able to vote for a particular car model")
	public void votingCars() throws InterruptedException {

		dashboardpage.lnkBuggyRating.click();
		LOGGER.info("Voting process started");
		dashboardpage.lnkOverallCars.click();
		votingpage.clickOnDesiredCar("Lamborghini Diablo");
		int previousVoteCount = Integer.parseInt(votingpage.getTotalVotes());
		votingpage.txtareaComment.sendKeys("Voting done");
		votingpage.btnVote.click();
		WaitElement.waitInVisibilityOf(driver, By.xpath(votingpage.locatorBtnVote));
		driver.navigate().refresh();
		int laterVoteCount = Integer.parseInt(votingpage.getTotalVotes());
		Assert.assertEquals(laterVoteCount, previousVoteCount + 1, "Votes not matched");
		LOGGER.info("Voting is done");

	}
	
	@Then("Verify the voters comment in the gridbox")
	public void verifyVoterComment()
	{
		Assert.assertEquals(votingpage.userComment.getText().trim(), "Voting done", " Voter user Comment not matched");

	}
	
	@Then("click on the facebook icon and verify the new tab opened and return back")
	public void verifyFbIcon()
	{
		votingpage.iconFacebook.click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}

	@After
	public void tearDown() {
		postCondition();
	}

}
