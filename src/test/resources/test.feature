Feature: Signup User

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given user wants to create an employee with the following attributes
      | username  | email | password |
      | testuser | testuser@yahoo.com    | Test1234 |

    When user saves the new employee 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'