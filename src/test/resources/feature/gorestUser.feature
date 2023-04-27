Feature: Gorest users

  @getAllUsers
  Scenario: Get all users
    When all users are requested
    Then a status code 200 is returned
    And 10 users are returned