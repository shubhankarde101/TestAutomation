Feature: Verify user ia able to login to application and reading one book

  @smoke
  Scenario: 1_Verify user is able to login to application
    Given user launches the application url
    When  user enters the credentials
    Then  user will be able to login to the application
    And user will be able to select the zone "LITERACY PRO NEXTGEN"
    And the student dashboard page should be launched

  @smoke
  Scenario: 2_Verify user is able to read one book
    Given user launches the application url
    When  user enters the credentials
    Then  user will be able to login to the application
    And user will be able to select the zone "LITERACY PRO NEXTGEN"
    And   user will be able to read any book from section "New Arrivals" with page threshold 30


  @smoke
  Scenario Outline: 3_Verify user is able to search a book from the Library
    Given user launches the application url
    When  user enters the credentials
    Then  user will be able to login to the application
    And user will be able to select the zone "LITERACY PRO NEXTGEN"
    And the student dashboard page should be launched
    Then user search for the book "<book_name>"
    And the resulted book "<book_name>"  will be shown
    Examples:
      | book_name    |
      | Pirates!     |

  @smoke
  Scenario: 4_Verify user is able to write assessment
    Given user launches the application url
    When  user enters the credentials
    Then  user will be able to login to the application
    And user will be able to select the zone "LITERACY PRO NEXTGEN"
    And the student dashboard page should be launched
    And user writes the Lit-pro test exam

  @smoke
  Scenario: 5_Verify user is able filter and write quiz from Library
    Given user launches the application url
    When  user enters the credentials
    Then  user will be able to login to the application
    And user will be able to select the zone "LITERACY PRO NEXTGEN"
    And the student dashboard page should be launched
    Then user filters quiz from Library
    And user finishes the quiz




