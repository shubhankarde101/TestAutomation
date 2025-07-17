package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import static org.assertj.core.api.Assertions.assertThat;
import starter.PageObjects.QuotePage;

public class QuoteStepDefinitions {

    public QuotePage quotePage;
    private static final Logger log = LogManager.getLogger(QuoteStepDefinitions.class);

    @Before
    public void openHomePage() {
        quotePage.openHomePage();
    }

    @Given("the broker is authorized to use this application")
    public void the_broker_is_authorized_to_use_this_application() {
        log.info("Verifying the page title");
        Assert.assertEquals("User is not authorized, please check access", "Ki Mock Platform — Take Home Exercise", quotePage.lblHeadings.get(0).getText().trim());
        Serenity.recordReportData().withTitle("The title is").andContents(quotePage.lblHeadings.get(0).getText());
        log.info("Validation completed");

    }

    @When("broker opens the quote page")
    public void broker_opens_the_quote_page() {
        Assert.assertTrue("User landed into incorrect page", quotePage.lblHeadings.size() == 3);
    }

    @Then("broker will select the option to create a new quote")
    public void broker_will_select_the_option_to_create_a_new_quote() {
        quotePage.selectQuoteAction("Create a new quote");
    }

    @Then("broker will select the country {string}")
    public void broker_will_select_the_country(String country) {
        quotePage.selectPrimaryCountry(country);
    }

    @Then("broker will select the primary insured {string}")
    public void broker_will_select_the_primary_insured(String insured) {
        quotePage.selectPrimaryInsured(insured);
    }

    @Then("broker will select the class of business {string}")
    public void broker_will_select_the_class_of_business(String businessClass) {
        quotePage.selectBusinessClass(businessClass);
    }

    @Then("broker will enter inception date as {string}")
    public void broker_will_enter_inception_date_as(String date) {
        quotePage.enterDate(date);
    }

    @Then("broker will enter the AUM value as {string}")
    public void broker_will_enter_the_aum_value_as(String aum) {
        quotePage.enterAUM(aum);
    }

    @Then("broker will enter the premium value as {string}")
    public void broker_will_enter_the_premium_value_as(String premium) {
        quotePage.enterPremium(premium);
    }

    @Then("broker will verify the quote is being created")
    public void broker_will_verify_the_quote_is_being_created() {
      Assert.assertTrue("Quote not created",quotePage.verifyQuoteCreated());
    }

    @And("broker will click on the next option")
    public void brokerWillClickOnTheNextOption() {
        quotePage.clickNextButton();
    }

    @Then("broker will select the option to get the pending stocks")
    public void brokerWillSelectTheOptionToGetThePendingStocks() {
        quotePage.selectQuoteAction("See my pending quotes");

    }

    @And("broker will be on the pending quote page")
    public void brokerWillBeOnThePendingQuotePage() {
        Assert.assertTrue("User is not in pending quote page",quotePage.lblPendingQuotes.get(0).isDisplayed());
    }

    @And("broker can see all the pending quotes listed there")
    public void brokerCanSeeAllThePendingQuotesListedThere() {
        Assert.assertTrue("Pending quotes are not displayed",quotePage.lblPendingQuotes.size()>0);
    }

    @And("broker will be landed on the home page")
    public void brokerWillBeLandedOnTheQuoteCreationPage() {
        the_broker_is_authorized_to_use_this_application();
    }

    @And("broker will get the error message {string} in red color {string}")
    public void brokerWillGetTheErrorMessageInRedColor(String errorMsg, String color) {
        String actual  = quotePage.txtErrorMsg.getText();
        //Text matching
        Assert.assertEquals("error message is not matching", errorMsg,actual);
        //Color matching
        String rgbaValue = quotePage.txtErrorMsg.getCssValue("color");
        String[] rgbaComponents = rgbaValue.split("\\(")[1].split("\\)")[0].split(",");
        int red = Integer.parseInt(rgbaComponents[0].trim());
        int green = Integer.parseInt(rgbaComponents[1].trim());
        int blue = Integer.parseInt(rgbaComponents[2].trim());
        String hexValue = String.format("#%02X%02X%02X", red, green, blue);
        Assert.assertEquals("Color "+color+" not matching",color,hexValue);
        Serenity.recordReportData().withTitle("The error message color is Red").andContents(hexValue);
    }

    @And("broker will clear the form")
    public void brokerWillClearTheForm() {
        quotePage.lnkClearForm.get(0).click();
        quotePage.lnkClearForm.get(2).click();
    }

    @And("broker will click on the back button")
    public void brokerWillClickOnTheBackButton() {
        quotePage.clickBackButton();
    }
}
