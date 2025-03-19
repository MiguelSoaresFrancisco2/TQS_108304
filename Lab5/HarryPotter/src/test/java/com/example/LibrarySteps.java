package com.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LibrarySteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout de 10 segundos
    }

    @Given("the user is on the online library homepage")
    public void theUserIsOnTheHomepage() {
        driver.get("https://cover-bookstore.onrender.com");
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String searchQuery) {
        WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='book-search-input']")));
        searchBox.sendKeys(searchQuery);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("books containing {string} should be displayed")
    public void booksContainingShouldBeDisplayed(String expectedTitle) {
        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("[data-testid='book-search-item']")));
        
        Assertions.assertFalse(results.isEmpty(), "Nenhum resultado encontrado!");
        Assertions.assertTrue(results.get(0).getText().contains(expectedTitle),
                "O primeiro resultado não contém '" + expectedTitle + "'");
    }

    @When("the user searches for books by {string}")
    public void theUserSearchesForBooksByAuthor(String author) {
        WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='book-search-input']")));
        searchBox.sendKeys(author);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("books by {string} should be displayed")
    public void booksByAuthorShouldBeDisplayed(String expectedAuthor) {
        List<WebElement> authors;

        try {
            // Seletor atualizado para encontrar os autores corretamente
            authors = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector(".SearchList_bookAuthor__3giPc")));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Nenhum livro com autor encontrado! Verifique se os resultados realmente carregaram.");
        }

        // Depuração: Listar os autores encontrados
        System.out.println("Autores encontrados na pesquisa:");
        for (WebElement authorElement : authors) {
            System.out.println("- " + authorElement.getText());
        }

        boolean found = authors.stream().anyMatch(element -> element.getText().equalsIgnoreCase(expectedAuthor));
        Assertions.assertTrue(found, "Nenhum livro encontrado para o autor: " + expectedAuthor);
    }

    @Then("a message {string} should be displayed")
    public void aMessageShouldBeDisplayed(String expectedMessage) {
        WebElement message;
        
        try {
            // Verifica a existência da imagem de "Nenhum resultado encontrado"
            message = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".SearchList_emptySearchImage__3R6v8")));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("A mensagem de erro não foi encontrada!");
        }

        Assertions.assertNotNull(message, "A mensagem de erro deveria estar presente, mas não foi encontrada.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
