#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Login into application

#  Scenario: Positive test validating login
  Scenario Outline: Positive test validating login #running the same scenario with multiple data set
    Given Initalize the browser with chrome
    And Navigate to "https://qaclickacademy.com" site
    And Click on Login link in homepage to land on secure sign in page 
    When User enters <username> and <password> and logs in
    Then Verify that user is successfully logged in
    Then Close browser
   Examples:
   |username|password|
   |"user1"|"password1"|
   |"user2"|"password2"|
   
