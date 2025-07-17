import "cypress-localstorage-commands";

export default class Loginpage {
    
    inputUser() {
        return cy.get("input[name='username']")

    }

    inputPwd() {
        return cy.get("input[type='password']")

    }


    signInLink() {
        return cy.get("input[type='button']")

    }

    userLabel() {
        return cy.get("div.user-name")

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

}