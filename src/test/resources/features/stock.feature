Feature: Stock check

  Background:
    Given a product "soda" for 50 in stock with price 20.00 for each product
    And a product "carrot" for 75 in stock with price 175.00 for each product

  Scenario: Buy product more than stock
    When I buy 55 "soda" that more than stock
    Then There are 50 "soda" in stock

  Scenario: Buy product less than stock
    When I buy 25 "carrot" that less than stock
    Then There are 50 "carrot" in stock