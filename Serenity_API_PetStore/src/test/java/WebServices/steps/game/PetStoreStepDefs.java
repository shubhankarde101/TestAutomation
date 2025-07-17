package WebServices.steps.game;

import WebServices.model.petStore.request.PetStoreCreateUserRequestPayload;
import WebServices.model.petStore.response.PetStoreCreateUserResponsePayload;
import WebServices.utils.Listener;
import WebServices.utils.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;

public class PetStoreStepDefs {

    @Steps
    PetStoreSteps petStoreSteps;

    PetStoreCreateUserResponsePayload responsePayload;
    PetStoreCreateUserRequestPayload requestPayload = new PetStoreCreateUserRequestPayload();

    @Given("admin processes the user data payload")
    public void admin_has_access_to_the_api_end_point() throws IOException {
        requestPayload.setUsername(Utils.getPropertyFrom("username"));
        requestPayload.setEmail(Utils.getPropertyFrom("email"));
        requestPayload.setFirstName(Utils.getPropertyFrom("firstname"));
        requestPayload.setLastName(Utils.getPropertyFrom("lastname"));
        requestPayload.setPassword(Utils.getPropertyFrom("password"));
        requestPayload.setId(Integer.parseInt(Utils.getPropertyFrom("id")));
        requestPayload.setPhone(Utils.getPropertyFrom("phone"));
        requestPayload.setUserStatus(Integer.parseInt(Utils.getPropertyFrom("status")));
        Serenity.recordReportData().withTitle("The request data is").andContents(requestPayload.toString());
    }

    @When("admin hits the create API")
    public void admin_hits_the_create_api(){
        petStoreSteps.createUser(requestPayload);
    }

    @Then("admin will capture the user details")
    public void admin_will_capture_the_user_details() {
        responsePayload = then().extract().response().as(PetStoreCreateUserResponsePayload.class);
    }

    @And("admin will verify the new user is created")
    public void admin_will_verify_the_new_user_is_created() {
        assertThat("Status code not matching", responsePayload.getCode() == 200);
        Serenity.recordReportData().withTitle("Status code is: ").andContents(String.valueOf(responsePayload.getCode()));
    }

    @Given("admin hits the get API")
    public void admin_hits_the_get_api(){
        petStoreSteps.getUser(Utils.getPropertyFrom("username"));
    }

    @Then("admin will verify the new user is retrieved")
    public void admin_will_verify_the_new_user_is_retrieved(){
        assertThat("First name not matched", then().extract().jsonPath().getString("firstName")
                .equalsIgnoreCase(Utils.getPropertyFrom("firstname")));
        assertThat("Last name not matched", then().extract().jsonPath().getString("lastName")
                .equalsIgnoreCase(Utils.getPropertyFrom("lastname")));
        assertThat("Email not matched", then().extract().jsonPath().getString("email")
                .equalsIgnoreCase(Utils.getPropertyFrom("email")));
        assertThat("Password not matched", then().extract().jsonPath().getString("password")
                .equalsIgnoreCase(Utils.getPropertyFrom("password")));
        assertThat("Phone not matched", then().extract().jsonPath().getString("phone")
                .equalsIgnoreCase(Utils.getPropertyFrom("phone")));
        assertThat("User status not matched", then().extract().jsonPath().getString("userStatus")
                .equalsIgnoreCase(Utils.getPropertyFrom("status")));
        assertThat("User id not matched", then().extract().jsonPath().getString("id")
                .equalsIgnoreCase(Utils.getPropertyFrom("id")));

    }

    @Given("admin hits the delete API")
    public void admin_hits_the_delete_api(){
        petStoreSteps.deleteUser(Utils.getPropertyFrom("username"));
    }

    @Then("admin will verify the user is deleted")
    public void admin_will_verify_the_user_is_deleted() {
        then().statusCode(200);
        petStoreSteps.getUser(Utils.getPropertyFrom("username"));
        then().statusCode(404);

    }


}

