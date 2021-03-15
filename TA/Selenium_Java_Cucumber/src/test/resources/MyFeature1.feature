Feature: To validate the changes made in MyFeature1

  Scenario: Facebook Login Page Verification
  
    Given Facebook Home Page loads successfully
    Then Enter LoginId "de,subho9@gmail.com"
    Then Enter Password "Password" 
    Then I will verify the "Log In" button   
    Then Create user account and enter the following details
    |15       |
    |Mar      |
    |2020     |
    |Female   |
    