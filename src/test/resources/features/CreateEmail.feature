Feature: As a user I want to create an email in my Google Mail account

  Background: User logs in and opens Create New Email popup
    Given there is a user with valid credentials
    When the user logs in to Google Mail account
    Then Home Page is opened
    When the user opens Create New Email popup

  @bdd
  Scenario: User creates an email and deletes it
    When the user fills in email
      | ADDRESSEE           | SUBJECT              | BODY                                            |
      | al.hlebka@gmail.com | Hello from Cucumber  | Hi!\nThis is email body\nBest regards\nCucumber |
    And the user clicks Close New Email button
    And the user opens Drafts page
    And the user opens Draft email with Subject = "Hello from Cucumber"
    And the user deletes draft email
    Then Drafts page contains "Нет сохраненных черновиков." header

  @bdd
  Scenario Outline: User creates an email and sends it
    When the user sets Email Addressee = "al.hlebka@gmail.com"
    And the user sets Email Subject = "<SUBJECT>"
    And the user sets Email Body
    """
    <BODY>
    """
    And the user sends the email
    Then confirmation message "Письмо отправлено." is displayed

    Examples:
      | SUBJECT              | BODY                 |
      | Test mail subject    | Test mail body       |
      | Тестовая тема письма | Тестовое тело письма |