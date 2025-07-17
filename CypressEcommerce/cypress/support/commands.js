// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
//import 'cypress-file-upload';

Cypress.Commands.add('validateCheckboxFilter', (product) => {
    let foundProduct = false;
    let elebffer
    cy.get("div.checkbox label").each(($el, index, $list) => {
        const text = $el.text()
        cy.log("Product is: " + text)
        if (text.includes(product)) {
            foundProduct = true
            cy.log("Selected product is: " + text)
            $list[index].click()
            cy.wait(5000)
            elebffer = $list[index]

        }

    })
        .then(() => {
            cy.get("a.card div.card-body h5").each(($el, index, $list) => {
                const actual = $el.text()
                expect(actual.toLowerCase()).to.contain(product.toLowerCase())

            })
        }).then(() => {
            if (foundProduct) {
                elebffer.click()
                cy.wait(5000)

            }

        })
})


Cypress.Commands.add('validateSearchFilter', (product) => {
    cy.get("input[data-test='search-query']").type(product)
    cy.get("button[data-test='search-submit']").click()
    cy.wait(5000)
    cy.get("a.card div.card-body h5").each(($el, index, $list) => {
        const actual = $el.text()
        expect(actual.toLowerCase()).to.contain(product.toLowerCase())

    })
})


Cypress.Commands.add('addToCartAndFetchPrice', (product) => {
    let foundProduct = false;
    let elebffer
    let calculatedPrice = 0
    cy.get("div.checkbox label").each(($el, index, $list) => {
        const text = $el.text()
        cy.log("Product is: " + text)
        if (text.includes(product)) {
            foundProduct = true
            cy.log("Selected product is: " + text)
            $list[index].click()
            cy.wait(5000)
            elebffer = $list[index]

        }

    })
        .then(() => {
            cy.get("a.card div.card-body h5").eq(0).click()
            cy.get("button#btn-add-to-cart").click()
            cy.get("a[data-test='nav-cart']").click()            
            
        })
    }

)













//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })