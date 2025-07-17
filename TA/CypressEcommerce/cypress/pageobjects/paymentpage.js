export default class PaymentPage {


    emailTxt() {
        return cy.get("input#email")

    }

    pwdTxt() {
        return cy.get("input#password")

    }

    submitBtn() {
        return cy.get("input.btnSubmit")

    }


    checkoutBtn() {
        return cy.get("button[data-test='proceed-2']")

    }


    addrTxtBox() {
        return cy.get("input#address")

    }

    cityTxtBox() {
        return cy.get("input#city")

    }

    stateTxtBox() {
        return cy.get("input#state")

    }

    countryTxtBox() {
        return cy.get("input#country")

    }

    postCodeTxtBox() {
        return cy.get("input#postcode")

    }

    chkOutPaymentBtn() {
        return cy.get("button[data-test='proceed-3']")

    }

    paymentMethodDrpDwn() {
        return cy.get("select#payment-method")

    }

    accountnameBox() {
        return cy.get("input#account-name")

    }

    accountNumberBox() {
        return cy.get("input#account-number")

    }

    confirmBtn() {
        return cy.get("button[data-test='finish']")

    }


    orderConfirmationLbl() {
        return cy.get("div#order-confirmation")

    }


    userMenuDrpdwn() {
        return cy.get("a#user-menu")

    }


    navigateToInvoice() {
        return cy.get("a[data-test='nav-my-invoices']")

    }

    latestInvoiceLbl() {
        return cy.get("tbody tr>td:nth-child(1)")

    }

    latestInvoicePrice() {
        return cy.get("tbody tr>td:nth-child(4)")

    }





}