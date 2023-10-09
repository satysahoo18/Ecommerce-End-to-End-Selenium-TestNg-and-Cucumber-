@tag
Feature: Purchasing the product
  I want to use this template for my feature file
  
  Background:
  Given Land on login Page

  @tag2
  Scenario Outline: Purchasing the product with positive data
    Given Logged in using username <username> and password <password>
    When Add the product <product> to the cart
    And Checkout product <product> and submit the order
    Then "THANKYOU FOR THE ORDER." success message is displayed after on confirmation page
    Examples: 
      | username                      | password    | product    |
      | satyabratasahoo981@gmail.com  | S.saty@6991 | Zara coat 3|
    
