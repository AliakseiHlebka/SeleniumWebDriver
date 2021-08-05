Feature: As a user I want to login to Google Mail account

  @bdd
  Scenario: User logs in with valid credentials
    Given there is a user with valid credentials
    When the user logs in to Google Mail account
    Then Home Page is opened
