Feature: Insurance Policy

  Scenario: Create a new insurance policy
    Given A user want to create a new insurance policy
    When The user create a policy
    Then The policy is created
    And a unique id is generated

  Scenario: Update an existing insurance policy
    Given A user want to update an existing insurance policy
    When The user update a policy
    Then The policy is updated

  Scenario: Delete an existing insurance policy
    Given A user want to delete an existing insurance policy
    When The user delete a policy
    Then The policy is deleted

  Scenario: Get an existing insurance policy by id
    Given An insurance policy exists
    When The user get a policy by id
    Then The policy is retrieved

  Scenario: Get all existing insurance policies
    Given An insurance policy exists
    When The user get all policies
    Then The policies are retrieved