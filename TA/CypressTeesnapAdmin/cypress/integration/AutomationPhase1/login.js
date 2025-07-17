
import "cypress-localstorage-commands";
import Loginpage from "../../pageobjects/loginpage";

describe("Teesnap Admin login functionalities", () => {

  beforeEach(function () {
    cy.fixture("data.json").then((testdata) => {
      this.testdata = testdata
      this.login = new Loginpage();
      cy.restoreLocalStorage();
    })
  })

  it("Verify_user_is_able_to_login_with_valid_credentials", function () {
     cy.visit(Cypress.env("baseUrl"))
      this.login.inputUser().type(this.testdata.Email);
      cy.screenshot("Enter user name");
      this.login.inputPwd().type(this.testdata.pwd);
      cy.screenshot("Enter password");
      this.login.signInLink().click()
      this.login.userLabel()
      .should('be.visible')
      .and('contain.text', this.testdata.userlabel);
      cy.log("Validation Successfull")
    });

    // it('Verify user is able to open the application @smoke', function () {
    //   cy.visit(Cypress.env("baseUrl"))
    //  });
})


  

