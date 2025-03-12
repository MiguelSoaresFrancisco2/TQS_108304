package com.example;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class) 
class HelloWorldChromeJupiterTest {

    @Test
    @Timeout(20)
    void testBasicNavigation(WebDriver driver) throws InterruptedException {

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Thread.sleep(2000); 
        assertThat(driver.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");

        WebElement webFormLink = driver.findElement(By.linkText("Web form"));
        Thread.sleep(2000);       webFormLink.click();

        Thread.sleep(2000);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Thread.sleep(2000);
        submitButton.click();

        Thread.sleep(2000);

        assertThat(driver.getCurrentUrl()).contains("submitted-form");
    }
}
