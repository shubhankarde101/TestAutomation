export default class CartPage {
    
    
    addToCartBtn() {
        return cy.get("button#btn-add-to-cart")

    }

    navigateCartBtn() {
        return cy.get("a[data-test='nav-cart']")

    }


    totalPrice() {
        return cy.get("tfoot tr td:nth-of-type(5)")

    }

    
    checkOutBtn() {
        return cy.get("button[data-test='proceed-1']")

    }

}