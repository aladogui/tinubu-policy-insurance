Feature: Insurance Policy

  Scenario: Create a new insurance policy
    Given A user want to create a new insurance policy
    When The user create a policy
    Then The policy is created
    And a unique id is generated
