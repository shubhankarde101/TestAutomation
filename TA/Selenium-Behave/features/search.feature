Feature: Search

    Scenario: Searching
        Given I load the website
        When I search for "selenium"
        Then I should see "https://www.selenium.dev" in the results

