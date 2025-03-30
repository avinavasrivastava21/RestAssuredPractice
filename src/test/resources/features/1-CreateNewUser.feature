Feature:Create new User


  Background: Authentication
  Given User is authorized

  @create
  Scenario: Create a new User
  Given Generate User details
  Then create User with the details
  And Validate that user is created