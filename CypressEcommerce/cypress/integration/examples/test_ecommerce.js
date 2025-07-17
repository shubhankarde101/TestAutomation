
import RegistrationPage from '../../pageobjects/registrationpage'
import SearchPage from '../../pageobjects/searchpage'
import CartPage from '../../pageobjects/cartpage'
import PaymentPage from '../../pageobjects/paymentpage'
import "cypress-localstorage-commands";

describe('Test E-Commerce website', () => {

  beforeEach(function () {
    cy.fixture("example.json").then((testdata) => {
      this.testdata = testdata
      this.registrationpage = new RegistrationPage()
      this.searchpage = new SearchPage()
      this.cartpage = new CartPage()
      this.paymentpage = new PaymentPage()
      cy.restoreLocalStorage();
    })
  })


  it('Create a new account through registration', function () {
    cy.visit(Cypress.env("baseUrl"))
    cy.log("Base url opened")
    this.registrationpage.signInLink().click()
    this.registrationpage.registerAccountLink().click()
    this.registrationpage.completeRegistration(this.testdata.FirstName, this.testdata.LastName
      , this.testdata.DOB, this.testdata.Address, this.testdata.PostCode, this.testdata.City
      , this.testdata.State, this.testdata.Country, this.testdata.Phone, this.testdata.Pwd)
    cy.wait(3000)
    cy.getLocalStorage("email").then((value) => {
      const email = String(value);
      this.paymentpage.emailTxt().type(email)
    });
    this.paymentpage.pwdTxt().type(this.testdata.Pwd)
    this.paymentpage.submitBtn().click().then(() => {
      cy.wait(2000)
      this.paymentpage.userMenuDrpdwn().should('be.visible')
      cy.log("Registration Successfull")
    });
  })



  it('Search and filter product', function () {
    cy.visit(Cypress.env("baseUrl"))
    this.registrationpage.signInLink().click()
    cy.getLocalStorage("email").then((value) => {
      const email = String(value);
      this.paymentpage.emailTxt().type(email)
    });
    this.paymentpage.pwdTxt().type(this.testdata.Pwd)
    this.paymentpage.submitBtn().click().then(() => {
      this.paymentpage.userMenuDrpdwn().should('be.visible')
    });
    this.searchpage.homeBtn().click()
    cy.wait(5000)
    cy.validateCheckboxFilter("Hammer")
    this.searchpage.homeBtn().click()
    cy.validateCheckboxFilter("Sander")
    cy.log("Checkbox filter is working as expected")
    cy.validateSearchFilter("Hammer")
    this.searchpage.homeBtn().click()
    cy.validateSearchFilter("Sander")
    cy.log("Search filter is working as expected")


  })


  it('Add to Cart, Checkout and payment', function () {
    cy.visit(Cypress.env("baseUrl"))
    this.registrationpage.signInLink().click()
    cy.getLocalStorage("email").then((value) => {
      const email = String(value);
      this.paymentpage.emailTxt().type(email)
    });
    this.paymentpage.pwdTxt().type(this.testdata.Pwd)
    this.paymentpage.submitBtn().click().then(() => {
      this.paymentpage.userMenuDrpdwn().should('be.visible')
      this.searchpage.homeBtn().click()
      let totalPrice = 0
      cy.addToCartAndFetchPrice("Hammer")
      this.searchpage.homeBtn().click().then(() => {
        cy.wait(5000)
        cy.addToCartAndFetchPrice("Sander")
      })
      cy.log("Item added to Cart")
      this.cartpage.totalPrice().then((ele) => {
        totalPrice = ele.text()
        totalPrice = parseFloat(totalPrice.replace('$', ''))
        cy.log("The calculated total price is: " + totalPrice)
        cy.setLocalStorage("price", totalPrice);
        cy.saveLocalStorage();
        this.cartpage.checkOutBtn().click()
      })
      this.paymentpage.checkoutBtn().click()
      cy.log("Item checked out for payment")
      this.paymentpage.chkOutPaymentBtn().click()
      this.paymentpage.paymentMethodDrpDwn().select("Bank Transfer")
      this.paymentpage.accountnameBox().type("Vinayak Pimpi")
      this.paymentpage.accountNumberBox().type("676767876756")
      this.paymentpage.confirmBtn().click()
      cy.wait(2000)
      cy.log("Payment successful")
      this.paymentpage.confirmBtn().click()
      this.paymentpage.orderConfirmationLbl().should('have.contain', 'Thanks for your order!');
      this.paymentpage.orderConfirmationLbl().then((ele) => {
      let invoiceNo = ele.text().split("Thanks for your order! Your invoice number is ")[1].split(".")[0]
      cy.log("The invoice no is: " + invoiceNo)
      this.paymentpage.userMenuDrpdwn().click()
      this.paymentpage.navigateToInvoice().click()
      this.paymentpage.latestInvoiceLbl().then((element)=>{
        expect(invoiceNo.toLowerCase()).to.eq(element.text().toLowerCase())
        cy.log("Invoice validated successfuly")
          }
      )
      cy.getLocalStorage("price").then((price) => {
          this.paymentpage.latestInvoicePrice().should('have.contain', price)
          cy.log("Total price validated successfuly")
        });
      })

    });

})

})