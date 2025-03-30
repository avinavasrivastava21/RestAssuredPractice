Feature: Update User

  Background: Authorize user
    Given User is authorized

    @updateUser
  Scenario: Update User details
    Given Select created User
    Then Update User details
    And Validate that user is Updated