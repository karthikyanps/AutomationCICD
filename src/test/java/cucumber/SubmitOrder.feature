
@tag
Feature: Submit the order from eCommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confimation page

    Examples: 
      | name  									| password |	productName	|
      | karthikyanps@gmail.com 	| Nitin@19 |	ZARA COAT 3	|
