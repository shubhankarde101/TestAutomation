
Cypress.Commands.add('editMannualBillRevisionforBillTypeUB', () => {

});



Cypress.Commands.add('editMannualBillRevision', (billRevisionIsTo) => {


})



Cypress.Commands.add('editMannualBillRevisionforBillTypeUBNoRevCodeProvided', (billRevisionIsTo) => {


});



Cypress.Commands.add(
  'editManualBillRevisionUnified',
  (profile = 'default') => {

    if (profile === 'UBNoRevCode') {
      cy.LogNReport(`✅ Verify Bill ID is blank value before saving revision`);
      cy.get(global.Selectors.bills.elements.bill_Id).should('be.visible').should('have.value', '')
      cy.LogNReport('Verify User is able to Edit Provider Charge and FCS/UR Amount in Bill Charges table');
      cy.get(global.Selectors.bills.elements.activityLogTableElementRow).should('have.length.at.least', 1).each(($row, index) => {
        const $providerInput = $row.find(global.Selectors.bills.elements.providerChargeInput);
        const $fsInput = $row.find(global.Selectors.bills.elements.fsAllowedAmountInput);
        const $revCode = $row.find(global.Selectors.bills.elements.revenueCodeInput);


        if ($providerInput.length) {
          cy.wrap($providerInput).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
              .type('100', { force: true });
            cy.LogNReport(`✅ Row ${index + 1}: Provider Charge set to $100`);
          });
        } else {
          cy.LogNReport(`Row ${index + 1}: Provider Charge input not found`);
        }

        if ($fsInput.length) {
          cy.wrap($fsInput).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
              .type('100', { force: true });
            cy.LogNReport(`Row ${index + 1}: FCS/UR amount set to $100`);
          });
        }
        else {
          cy.LogNReport(`Row ${index + 1}: FCS/UR input not found`);
        }
        if ($revCode.length) {
          cy.wrap($revCode).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
          });
        }
        else {
          cy.LogNReport(`Row ${index + 1}: Rev code not found`);
        }
        cy.LogNReport('Verify When User Saves Mannual Bill Revision Revision and Rev Code is kept empty');
        cy.get(global.Selectors.bills.elements.saveRevision, { timeout: 2000 }).scrollIntoView().should('exist').click({ force: true });
        cy.get(global.Selectors.bills.elements.ubMannualBillTableIncomplete).invoke('text')
          .then((text) => {
            cy.LogNReport(`Message for Rev Code Incomplete ${text}`)
          })
      })
    }

    if (profile === 'UB') {
      cy.get(global.Selectors.bills.elements.bill_Id).scrollIntoView().should('be.visible').should('have.value', '')
      cy.LogNReport('Verify User is able to Edit Provider Charge and FCS/UR Amount in Bill Charges table');
      cy.wait(5000)
      cy.get(global.Selectors.bills.elements.activityLogTableElementRow).should('have.length.at.least', 1).each(($row, index) => {
        const $providerInput = $row.find(global.Selectors.bills.element.providerChargeInput);
        const $fsInput = $row.find(global.Selectors.bills.element.fsAllowedAmountInput);
        const $revCode = $row.find(global.Selectors.bills.element.revenueCodeInput);

        if ($providerInput.length) {
          cy.wrap($providerInput).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
              .type('100', { force: true });
            cy.LogNReport(`✅ Row ${index + 1}: Provider Charge set to $100`);
          });
        }

        if ($fsInput.length) {
          cy.wrap($fsInput).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
              .type('100', { force: true });
            cy.LogNReport(`✅ Row ${index + 1}: FCS/UR amount set to $100`);
          });
        }
        if ($revCode.length) {
          cy.wrap($revCode).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
              .type('420', { force: true });
            cy.LogNReport(`Row ${index + 1}: Rev Code is`);
          });
        }
      });
      cy.LogNReport('Verify User is able Save Mannual Bill Revision Revision and get new Bill ID');
      cy.get(global.Selectors.bills.elements.saveRevision, { timeout: 2000 }).scrollIntoView().should('exist').click({ force: true });
      cy.wait(6000)
      cy.get(global.Selectors.bills.elements.newBillID).last()
        .then(($el) => {
          let newBillID = $el.text().trim()
          cy.LogNReport(`✅ Verify New Bill ID generated is : ${newBillID}`)
          cy.wrap(newBillID).as('newBillID')
        });

      cy.get(global.Selectors.bills.elements.billRevisionType).invoke('attr', 'ng-reflect-value')
        .then((value) => {
          cy.LogNReport(`✅ Verify Bill Revision Type is : ${value}`)
        })
      cy.LogNReport('Verify User is able Calculate Bill Revision Amount');
      cy.get(global.Selectors.bills.elements.calculateRevisionButton, { timeout: 2000 }).should('exist').click({ force: true })
      cy.get(global.Selectors.bills.elements.finshBillRevision, { timeout: 2000 }).should('exist').click({ force: true });
      cy.get(global.Selectors.bills.elements.invoicePage).should('be.visible')
      cy.LogNReport('✅ Verify User is able to finish bill revision and is navigated to Invoice Page')

    }

    //Post-save actions
    if (profile === 'default') {


      cy.LogNReport(`✅ Verify Bill ID is blank value before saving revision`);
      cy.get(global.Selectors.bills.elements.bill_Id).should('be.visible').should('have.value', '')
      cy.wait(3000)
      cy.LogNReport('Verify User is able to Edit Provider Charge and FCS/UR Amount in Bill Charges table');
      cy.wait(2000)
      cy.get(global.Selectors.bills.elements.activityLogTableElementRow).last().should('have.length.at.least', 1).each(($row, index) => {
        const $providerInput = $row.find(global.Selectors.bills.elements.providerChargeInput);
        const $fsInput = $row.find(global.Selectors.bills.elements.fsAllowedAmountInput);
        if ($providerInput.length) {
          cy.wrap($providerInput).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
              .type('100', { force: true });
            cy.LogNReport(`✅ Row ${index + 1}: Provider Charge set to $100`);
          });
        } else {
          cy.LogNReport(`Row ${index + 1}: Provider Charge input not found`);
        }

        if ($fsInput.length) {
          cy.wrap($fsInput).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
              .type('100', { force: true });
            cy.LogNReport(`Row ${index + 1}: FCS/UR amount set to $100`);
          });
        }
        else {
          cy.LogNReport(`Row ${index + 1}: FCS/UR input not found`);
        }
        if ($revCode.length) {
          cy.wrap($revCode).scrollIntoView().should('be.visible').then($input => {
            cy.wrap($input)
              .clear({ force: true })
          });
        }
        else {
          cy.LogNReport(`Row ${index + 1}: Rev code not found`);
        }
      })
      cy.LogNReport('Verify User is able Save Mannual Bill Revision Revision and get new Bill ID');
      cy.get(global.Selectors.bills.elements.saveRevision, { timeout: 2000 }).scrollIntoView().should('exist').click({ force: true });
      cy.wait(6000)
      cy.get(global.Selectors.bills.elements.newBillID).last()
        .then(($el) => {
          let newBillID = $el.text().trim()
          cy.LogNReport(`✅ Verify New Bill ID generated is : ${newBillID}`)
          cy.wrap(newBillID).as('newBillID')
        });

      cy.get(global.Selectors.bills.elements.billRevisionType).invoke('attr', 'ng-reflect-value')
        .then((value) => {
          cy.LogNReport(`✅ Verify Bill Revision Type is : ${value}`)
        })
      if (billRevisionIsTo == "Save") {
        cy.LogNReport('Verify User is able Calculate Bill Revision Amount');
        cy.get(global.Selectors.bills.elements.calculateRevisionButton, { timeout: 2000 }).should('exist').click({ force: true })

        cy.LogNReport('✅ Verify User is able to finish bill revision and is navigated to Invoice Page')
        cy.get(global.Selectors.bills.elements.finshBillRevision, { timeout: 2000 }).should('exist').click({ force: true });
        cy.get(global.Selectors.bills.elements.invoicePage).should('be.visible')
      } else {
        cy.LogNReport(`Verify User is able to Cancel Mannual Bill Revision and is navigated to Invoice Page`);
        cy.wait(4000);
        cy.get(global.Selectors.bills.elements.cancelBillRevision, { timeout: 8000 })
          .scrollIntoView()
          .click({ force: true });
        cy.wait(3000)
        cy.get(global.Selectors.bills.elements.invoicePage, { timeout: 10000 })
          .should('be.visible')
          .then(() => {
            cy.LogNReport('✅ User is Navigated to Invoice Page');
          });
      }






    }
  });


