Feature: Quote creation scenarios
  @myTag1
  Scenario Outline: Verify broker is able to create a new quote
    Given the broker is authorized to use this application
    When  broker opens the quote page
    Then  broker will select the option to create a new quote
    And broker will click on the next option
    And broker will select the country "<Country>"
    And broker will select the primary insured "<Insure>"
    And broker will select the class of business "<Business>"
    And broker will click on the next option
    And broker will enter inception date as "<Date>"
    And broker will enter the AUM value as "<AUM>"
    And broker will enter the premium value as "<Premium>"
    And broker will click on the next option
    And broker will verify the quote is being created

    Examples:
      | Country | Insure    | Business | Date     | AUM | Premium |
      | Japan   | AAA Inc   | Property | 11021023 | 333 | 555     |
      | China   | Newco Ltd | Energy   | 11021022 | 444 | 236     |

  Scenario: Verify broker is able to see the pending stocks
    Given the broker is authorized to use this application
    When  broker opens the quote page
    Then  broker will select the option to get the pending stocks
    And broker will click on the next option
    And broker will be on the pending quote page
    And broker can see all the pending quotes listed there
    And broker will click on the next option
    And broker will be landed on the home page

  Scenario Outline: Verify broker is not able to create a new quote without entering mandatory details
    Given the broker is authorized to use this application
    When  broker opens the quote page
    Then  broker will select the option to create a new quote
    And broker will click on the next option
    And broker will click on the next option
    And broker will get the error message "This is a required question" in red color "#D93025"
    And broker will select the country "<Country>"
    And broker will click on the next option
    And broker will get the error message "This is a required question" in red color "#D93025"
    And broker will select the primary insured "<Insure>"
    And broker will click on the next option
    And broker will get the error message "This is a required question" in red color "#D93025"
    And broker will select the class of business "<Business>"
    And broker will click on the next option
    And broker will click on the next option
    And broker will get the error message "This is a required question" in red color "#D93025"
    And broker will enter inception date as "<Date>"
    And broker will click on the next option
    And broker will get the error message "This is a required question" in red color "#D93025"
    And broker will enter the AUM value as "<AUM>"
    And broker will click on the next option
    And broker will get the error message "This is a required question" in red color "#D93025"

    Examples:
      | Country | Insure    | Business | Date     | AUM |
      | Japan   | AAA Inc   | Property | 11021023 | 333 |

  @myTag1
  Scenario: Verify the back button and clear form functionalities
    Given the broker is authorized to use this application
    When  broker opens the quote page
    Then  broker will select the option to get the pending stocks
    And broker will click on the next option
    And broker will clear the form
    And broker will be landed on the home page
    Then  broker will select the option to get the pending stocks
    And broker will click on the next option
    And broker will click on the back button
    And broker will be landed on the home page




