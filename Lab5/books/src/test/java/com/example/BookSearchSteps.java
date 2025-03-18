package com.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @Given("a book with the title {string}, written by {string}, published in {int} {word} {int}")
public void addNewBook(final String title, final String author, final int day, final String month, final int year) throws ParseException {
    // Criar string formatada para a data
    String publishedDate = day + " " + month + " " + year;

    // Especificar o formato correto para parsing
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", java.util.Locale.ENGLISH);
    Date published = dateFormat.parse(publishedDate);

    // Criar e adicionar o livro à biblioteca
    Book book = new Book(title, author, published);
    library.addBook(book);
}



    @When("the customer searches for books published between {int} and {int}")
    public void setSearchParameters(final int fromYear, final int toYear) {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        try {
            Date from = yearFormat.parse(String.valueOf(fromYear));
            Date to = yearFormat.parse(String.valueOf(toYear));
            result = library.findBooks(from, to);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertThat("Number of books found does not match!", result.size(), equalTo(booksFound));
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        if (position <= 0 || position > result.size()) {
            throw new IndexOutOfBoundsException("Invalid book position: " + position);
        }
        assertThat("Book title does not match!", result.get(position - 1).getTitle(), equalTo(title));
    }
}

