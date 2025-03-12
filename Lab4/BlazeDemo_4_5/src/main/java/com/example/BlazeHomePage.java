package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlazeHomePage {
    private WebDriver driver;

    @FindBy(name = "fromPort")
    private WebElement departureDropdown;

    @FindBy(css = "input[type='submit']")
    private WebElement findFlightsButton;

    public BlazeHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectDepartureCity(String city) {
        departureDropdown.click();
        departureDropdown.findElement(org.openqa.selenium.By.xpath("//option[. = '" + city + "']")).click();
    }

    public void clickFindFlights() {
        findFlightsButton.click();
    }
}
