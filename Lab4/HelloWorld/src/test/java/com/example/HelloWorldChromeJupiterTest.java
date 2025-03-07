package com.example;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class) // Enables Selenium-Jupiter
class HelloWorldChromeJupiterTest {

    @Test
    @Timeout(20) // Timeout de 20 segundos para o teste
    void testBasicNavigation(WebDriver driver) throws InterruptedException {
        // Open the website
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Thread.sleep(2000); // Espera 2 segundos para visualizar melhor
        assertThat(driver.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");

        // Click on the "Web form" link
        WebElement webFormLink = driver.findElement(By.linkText("Web form"));
        Thread.sleep(2000); // Espera antes de clicar
        webFormLink.click();

        // Aguarda 2 segundos após a navegação
        Thread.sleep(2000);

        // Encontrar e clicar no botão "Submit"
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Thread.sleep(2000); // Espera antes de clicar
        submitButton.click();

        // Espera a página ser submetida
        Thread.sleep(2000);

        // Verificar se o formulário foi submetido corretamente
        assertThat(driver.getCurrentUrl()).contains("submitted-form");
    }
}
