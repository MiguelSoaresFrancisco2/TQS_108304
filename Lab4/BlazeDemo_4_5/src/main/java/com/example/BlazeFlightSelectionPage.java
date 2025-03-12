package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlazeFlightSelectionPage {
    private WebDriver driver;

    @FindBy(css = "input[type='submit']")
    private WebElement chooseFlightButton;

    public BlazeFlightSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void chooseFirstFlight() {
        chooseFlightButton.click();
    }
}
