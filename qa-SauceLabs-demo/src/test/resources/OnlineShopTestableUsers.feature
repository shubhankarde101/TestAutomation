Feature: Automation of testable user flows of an Online Shop application

  Scenario: Verify that standard user is able to place an order
    Given User launches the application url
    When  "standard_user" enters the credentials
    Then  Standard user will be able to login to the application
    And user will add product "Test.allTheThings() T-Shirt (Red)" to basket and capture price
    And user will add product "Sauce Labs Bike Light" to basket and capture price
    And user will add product "Sauce Labs Backpack" to basket and capture price
    And user will remove product "Sauce Labs Bike Light" from basket
    And user will checkout with proper details
    And user will complete the order after verifying the total price of the order

  Scenario: Verify that standard user is able to login to the application
    Given User launches the application url
    When  "standard_user" enters the credentials
    Then  Standard user will be able to login to the application

  Scenario: Verify that locked out user is not able to login to the application
    Given User launches the application url
    When  "locked_out_user" enters the credentials
    Then  Locked out user will not be able to login to the application with error message "Sorry, this user has been locked out."

  Scenario: Verify that problem is able to login to the application but cant see the images
    Given User launches the application url
    When  "problem_user" enters the credentials
    Then  Problem user will be able to login to the application but can't see the images loaded

  Scenario: Verify that problem is able to login to the application but cant see the images
    Given User launches the application url
    When  "performance_glitch_user" enters the credentials
    Then  Performance user will be able to login with high loading times


