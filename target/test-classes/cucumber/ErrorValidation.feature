
@tag
Feature: Error validation for login page
  I want to use this template for my feature file


  @Regression
  Scenario Outline: Error validation by giving wrong password
    Given Land on login Page
    When Logged in using username <username> and password <password>
    Then "Incorrect email or password." error message is displayed

    Examples: 
      | username                      | password    |
      | satyabratasahoo981@gmail.com  | S.saty@691 |