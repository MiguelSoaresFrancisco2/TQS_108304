Feature: Online Library Book Search

  Scenario: User searches for a book by title
    Given the user is on the online library homepage
    When the user searches for "Harry Potter"
    Then books containing "Harry Potter" should be displayed

  Scenario: Search books by author
    Given the user is on the online library homepage
    When the user searches for books by "George R.R. Martin"
    Then books by "George R.R. Martin" should be displayed


  Scenario: User searches for a book that does not exist
    Given the user is on the online library homepage
    When the user searches for "Nonexistent Book"
    Then a message "No results found" should be displayed
