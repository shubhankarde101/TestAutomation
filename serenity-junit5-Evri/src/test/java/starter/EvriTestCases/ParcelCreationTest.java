package starter.EvriTestCases;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import starter.PageObjects.EvriPageObject;

@ExtendWith(SerenityJUnit5Extension.class)
class ParcelCreationTest {

    @Steps
    EvriPageObject evryPage;

    @Test()
    @Order(1)
    @DisplayName("Check that the following error messages appear if you click Login button without entering credential")
    void testUserLoginWithoutCredentials() throws InterruptedException {
        evryPage.openHomePage();
        evryPage.acceptPopup()
                .clickLoginWithoutEnteringCredentials()
                .verifyErrorMessage();
    }

    @Test()
    @Order(2)
    @DisplayName("Check that user can successfully login to application with valid credentials")
    void testUserCanLoginWithValidCredentials() throws InterruptedException {
        evryPage.openHomePage();
        evryPage.acceptPopup()
                .applicationLogin("Sudhakar@abc.com","Sudh@9876")
                .acceptNoThanks()
                .verifyUserLoggedIn();


    }
    

    @Test()
    @Order(3)
    @DisplayName("Check that user can create Send parcel request")
    void testSendParcelRequest() throws InterruptedException {
        evryPage.openHomePage();
        evryPage.acceptPopup()
                .applicationLogin("Sudhakar@abc.com","Sudh@9876")
                .acceptNoThanks()
                .enterPostCode("BD11 1NE","BD11 1ND")
                .selectWeight()
                .getPrice()
                .enterParcelMatrixData()
                .enterParceldetails()
                .enterReceiverDetails()
                .verifyParcelRequest();
    }
}

