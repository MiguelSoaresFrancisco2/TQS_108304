package com.example;

import com.example.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlazeDemoTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private BlazeHomePage homePage;
    private BlazeFlightSelectionPage flightSelectionPage;
    private BlazePurchasePage purchasePage;
    private BlazeConfirmationPage confirmationPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new BlazeHomePage(driver);
        flightSelectionPage = new BlazeFlightSelectionPage(driver);
        purchasePage = new BlazePurchasePage(driver);
        confirmationPage = new BlazeConfirmationPage(driver);

        driver.get("https://blazedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBookingAndTitle() {
        homePage.selectDepartureCity("Philadelphia");
        homePage.clickFindFlights();

        flightSelectionPage.chooseFirstFlight();

        purchasePage.fillPassengerDetails("John Doe", "123 Street", "New York", "1234567812345678", "John Doe");
        purchasePage.purchaseFlight();

        String pageTitle = confirmationPage.getPageTitle();
        System.out.println("Título atual da página: " + pageTitle);
        assertEquals("BlazeDemo Confirmation", pageTitle);
    }
}
