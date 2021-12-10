@wip
Feature: As a Librarian I should be able to add a book

  Scenario: As a Librarian when I add a book the DB should have the entry

  Given the librarian is at the "Books" page
    When the librarian adds a book
    Then the DB name author year and isbn should match