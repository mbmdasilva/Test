Feature: Reference Rate tests

  Scenario: Market has source as povider
    Given I have a price source with value "source2"
    And  I have a price provider with value "bloomberg"
    When I create a market
    Then a market is successfully created


  Scenario: Market as soucer and not a provider
    Given I have a price source with value "source2"
    And  I have a price provider with value "null"
    When I create a market
    Then a market is successfully created


  Scenario: Market has null source and provider
    Given I have a price source with value "null"
    And  I have a price provider with value "null"
    When I create a market
    Then the market is not succesfully created

  Scenario: No duplicate market
    Given I have markets in a list
    When I add them to the Set
    Then I assert that there is no duplication