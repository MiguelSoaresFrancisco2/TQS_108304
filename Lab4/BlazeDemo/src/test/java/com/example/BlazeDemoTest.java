package com.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class BlazeDemoTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
public void testBookingAndTitle() {
    driver.get("https://blazedemo.com/");

    driver.findElement(By.name("fromPort")).click();
    driver.findElement(By.xpath("//option[. = 'Philadelphia']")).click();

    driver.findElement(By.cssSelector("input[type='submit']")).click();

    driver.findElement(By.cssSelector("input[type='submit']")).click();

    driver.findElement(By.id("inputName")).sendKeys("d");
    driver.findElement(By.id("address")).sendKeys("s");
    driver.findElement(By.id("city")).sendKeys("s");
    driver.findElement(By.id("creditCardNumber")).sendKeys("12131232313");
    driver.findElement(By.id("nameOnCard")).sendKeys("John");
    driver.findElement(By.id("rememberMe")).click();

    driver.findElement(By.cssSelector("input[type='submit']")).click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));

    String pageTitle = driver.getTitle();
    System.out.println("Título atual da página: " + pageTitle);
    assertEquals("BlazeDemo Confirmation", pageTitle);
}

}