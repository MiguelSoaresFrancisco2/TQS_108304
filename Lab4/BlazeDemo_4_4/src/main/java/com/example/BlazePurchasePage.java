package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlazePurchasePage {
    private WebDriver driver;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardInput;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCardInput;

    @FindBy(id = "rememberMe")
    private WebElement rememberMeCheckbox;

    @FindBy(css = "input[type='submit']")
    private WebElement purchaseButton;

    public BlazePurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillPassengerDetails(String name, String address, String city, String creditCard, String nameOnCard) {
        nameInput.sendKeys(name);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        creditCardInput.sendKeys(creditCard);
        nameOnCardInput.sendKeys(nameOnCard);
        rememberMeCheckbox.click();
    }

    public void purchaseFlight() {
        purchaseButton.click();
    }
}
