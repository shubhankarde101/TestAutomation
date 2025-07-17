Feature: PetStore user functionalities

  Scenario: Verify admin will be able to create a new pet store user
    Given admin processes the user data payload
    When admin hits the create API
    Then admin will capture the user details
    And admin will verify the new user is created

  Scenario: Verify admin will be able to retrieve the new user details
    Given admin hits the get API
    Then admin will verify the new user is retrieved

  Scenario: Verify admin will be able to delete the user
    When admin hits the delete API
    Then admin will verify the user is deleted












