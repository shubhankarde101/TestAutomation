import "cypress-localstorage-commands";

export default class RegistrationPage {
    
    
    signInLink() {
        return cy.get("a[data-test='nav-sign-in']")

    }

    registerAccountLink() {
        return cy.get("a[data-test='register-link']")

    }

    firstNameTxt() {
        return cy.get("input#first_name")

    }

    lastNameTxt() {
        return cy.get("input#last_name")

    }

    dobTxt() {
        return cy.get(" ")

    }

    addressTxt() {
        return cy.get("input#address")

    }

    postcodeTxt() {
        return cy.get("input#postcode")

    }

    cityTxt() {
        return cy.get("input#city")

    }

    stateTxt() {
        return cy.get("input#state")

    }

    countrtyDrpDown() {
        return cy.get("select#country")

    }

    phoneTxt() {
        return cy.get("input#phone")

    }

    emailTxt() {
        return cy.get("input#email")

    }

    pwdTxt() {
        return cy.get("input#password")

    }

    registerBtn() {
        return cy.get("button.btnSubmit")

    }

     generateRandomEmail() {
        const characters = 'abcdefghijklmnopqrstuvwxyz0123456789';
        let email = '';
        for (let i = 0; i < 10; i++) {
          const randomIndex = Math.floor(Math.random() * characters.length);
          email += characters.charAt(randomIndex);
        }
        return email + '@example.com';
      }
      
    generateEmail()
      {
        const characters = 'abcdefghijklmnopqrstuvwxyz0123456789';
        let email = '';
            for (let i = 0; i < 10; i++) {
              const randomIndex = Math.floor(Math.random() * characters.length);
              email += characters.charAt(randomIndex);
            }
        email=email+"@example.com"
        return email
      }
      

    completeRegistration(fn, ln, dob, add, pc, city, state, coun, phn, pwd)
    {
       this.firstNameTxt().type(fn)
        this.lastNameTxt().type(ln)
        this.dobTxt().type(dob)
        this.addressTxt().type(add)
        this.postcodeTxt().type(pc)
        this.cityTxt().type(city)
        this.stateTxt().type(state)
        cy.wait(1000)
        this.countrtyDrpDown().select(coun)
        this.phoneTxt().type(phn)
        let email  = this.generateEmail()
        this.emailTxt().type(email)
        this.pwdTxt().type(pwd)
        cy.wait(2000)
        this.registerBtn().click().then(() => {
            cy.url().should('include', '/login');
          });
        cy.setLocalStorage("email", email);
        cy.saveLocalStorage();


    }


}