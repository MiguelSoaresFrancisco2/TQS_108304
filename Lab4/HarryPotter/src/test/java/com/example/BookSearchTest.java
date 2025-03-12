package com.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class BookSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
    
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSearchHarryPotter() {
        driver.get("https://cover-bookstore.onrender.com");
        System.out.println("Página carregada: " + driver.getTitle()); 

        WebElement searchBox;
        try {
            searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchBox")));
        } catch (TimeoutException e) {
            searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
        }
        
        searchBox.sendKeys("Harry Potter");
        searchBox.sendKeys(Keys.ENTER);

        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='book-search-item']")));

        Assertions.assertFalse(results.isEmpty(), "Nenhum resultado encontrado!");
        Assertions.assertTrue(results.get(0).getText().contains("Harry Potter"), "O primeiro resultado não contém 'Harry Potter'");
    }
}
