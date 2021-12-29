@wip
  @api
  @db
Feature: As a tester I should be able to create a book with the API

  Scenario: A book is created with POJO from the API and verify its
  creation on the opposing ends (User Interface and Database)

    Given The tester is authenticated
    Then A new book is added using POJO and the add_book endpoint
    When The tester logs in as a student the book should exist
    When The tester queries the database for the book it should exist
    Then Assert all fields match