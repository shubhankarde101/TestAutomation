Feature: To validate the changes made in MyFeature1

  Scenario: Validate Crypto Page Columns
    Given Crypto Page loads successfully
    Then Verify column "Name"
    Then Verify column "Market Cap"
    Then Verify column "Price"
    Then Verify column "Circulating Supply"

  Scenario: Validate Crypto Page Currency Name
    Given Crypto Page loads successfully
    Then Verify currency "Bitcoin" is available
    Then Verify currency "Ethereum" is available
    Then Verify currency "XRP" is available
    Then Verify currency "Enigma" is available
    Then Verify currency "Metaverse ETP" is available

  Scenario: Validate Crypto Page Currency Data for Dollar
    Given Crypto Page loads successfully
    Then Verify currency "bitcoin" for "positive" change with "Price"
      | 7676.31 |
      |  810.35 |
      |   30.61 |
      |    7.17 |
    Then Verify currency "bitcoin" for "positive" change with "Volume (24h)"
      | 4789620000 |
      |  613312000 |
      |   28024200 |
      |     298871 |

  Scenario: Verify Navigation Bar and Search functionality
    Given Crypto Page loads successfully
    Then click on "Market Cap"
    Then click on "Trade Volume"
    Then click on "Trending"
    Then click on "Tools"
    And type something on the search box

  Scenario: Validate Crypto Page Currency Data for Others
    Given Crypto Page loads successfully
    Then Change the currency value to "ETH"
    Then Verify currency "bitcoin" for "positive" change with "Price"
      | 16.60 |
      |  1.75 |
      | 0.066 |
      | 0.015 |

  Scenario: Verify total Market Capitalization value
    Given Crypto Page loads successfully
    Then Change the currency value to "USD"
    Then Verify 'Markt Capital' sum value
                 
