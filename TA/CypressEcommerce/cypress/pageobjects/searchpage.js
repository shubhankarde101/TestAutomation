export default class ContactListPage {
    
    
    searchBoxTxt() {
        return cy.get("input[data-test='search-query']")

    }

    searchSubmitBtn() {
        return cy.get("button[data-test='search-submit']")

    }

    categoryCheckBoxes() {
        return cy.get("input[type='checkbox']")

    }

    cardItemContainers() {
        return cy.get("a.card")

    }

    homeBtn() {
        return cy.get("a[data-test='nav-home']")

    }

    cardItemPrices() {
        return cy.get("a.card div.card-footer>span>span")

    }

    catdItemHeaders() {
        return cy.get("a.card div.card-body h5")

    }

}