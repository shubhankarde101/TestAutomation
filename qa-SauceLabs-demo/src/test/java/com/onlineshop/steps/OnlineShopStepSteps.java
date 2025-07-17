package com.onlineshop.steps;




import com.onlineShop.driver.DriverManager;
import com.onlineShop.page.LoginPage;
import com.onlineShop.page.ProductPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import org.junit.Assert;

import static com.onlineShop.startup.PropertyLoader.returnConfigValue;


public class OnlineShopStepSteps extends BaseStep {
    private static final Logger LOGGER = LogManager.getLogger();

    LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());
    ProductPage productPage = new ProductPage(driver);
    double totalProductPrice;

    @Given("User launches the application url")
    public void user_launches_the_application_url() {
        LOGGER.info("--------------Test Started---------------");
        LOGGER.info("Verifying the page title");
       Assert.assertEquals(driver.getTitle().trim(), "Swag Labs");
        LOGGER.info("Page title verified");
    }

    @When("{string} enters the credentials")
    public void user_enters_credentials(String user) {
        LOGGER.info(user + " user is entering credentials");
        loginPage.inputUserName.sendKeys(returnConfigValue(user));
        loginPage.inputPwd.sendKeys(returnConfigValue("pwd"));
        loginPage.btnLogin.click();
        LOGGER.info("Credentials entered");
    }

    @Then("Standard user will be able to login to the application")
    public void standard_user_able_to_SignIn() {
        LOGGER.info("Verify standard user is able to sign in");
        Assert.assertTrue(loginPage.verifyUserIsLoggedIn());
        LOGGER.info("User Signed in successfully");
    }


    @Then("Locked out user will not be able to login to the application with error message {string}")
    public void locked_out_user_unable_to_SignIn(String expectedErrorMessage) {
        LOGGER.info("Verify locked out user is unable to sign in");
        String actualErrorMsg = loginPage.lockedOutUserUnableToLogin();
        LOGGER.info("The actual error message showing is: " + actualErrorMsg);
        Assert.assertEquals(expectedErrorMessage, actualErrorMsg);
        LOGGER.info("User Signed in UnSuccessful");
    }

    @Then("Problem user will be able to login to the application but can't see the images loaded")
    public void problem_user_will_be_able_to_login_to_the_application_but_can_t_see_the_images_loaded() {
        LOGGER.info("Verify problem user is able to sign in but unable to see the images");
        Assert.assertTrue("Images are wrongly loaded successfully", loginPage.verifyImagesNotLoading());
        Assert.assertTrue("User is unable to SignIn", loginPage.verifyUserIsLoggedIn());
        LOGGER.info("Verification Successful");

    }

    @Then("Performance user will be able to login with high loading times")
    public void performance_user_will_log_In_with_High_Loading_Times() {
        LOGGER.info("Verify Performance user is able to sign in with high loading time");
        Assert.assertTrue("User is unable to SignIn", loginPage.verifyUserIsLoggedIn());
        LOGGER.info("Verification Successful");
    }

    @Given("user will add product {string} to basket and capture price")
    public void user_will_add_product_to_basket_and_capture_price(String product) {
        LOGGER.info("Selecting the product "+product);
        totalProductPrice = totalProductPrice + Double.parseDouble(productPage.selectProductAndCapturePrice(product));
        LOGGER.info("The total Price is: "+totalProductPrice);
        LOGGER.info("Product selected");
    }

    @Then("user will remove product {string} from basket")
    public void user_will_remove_product_from_basket(String product) {
        LOGGER.info("Removing the product "+product);
        String priceToReduce = productPage.selectProductAndCapturePrice(product);
        LOGGER.info("Price to reduce is: "+priceToReduce);
        totalProductPrice = totalProductPrice - Double.parseDouble(priceToReduce);
        LOGGER.info("Product removed");
    }

    @Given("user will checkout with proper details")
    public void user_will_checkout_with_proper_details() {
       Assert.assertEquals(productPage.checkoutAndContinue(),Double.toString(totalProductPrice));
       LOGGER.info("Checkout successful");
    }

    @Given("user will complete the order after verifying the total price of the order")
    public void user_will_complete_the_order_after_verifying_the_total_price_of_the_order() {
        Assert.assertEquals(productPage.finishOrder(),"Thank you for your order!");
        LOGGER.info("Order placed");
    }

}
