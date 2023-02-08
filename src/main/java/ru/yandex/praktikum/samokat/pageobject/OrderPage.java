package ru.yandex.praktikum.samokat.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;

    // Cообщение Такого заказа нет
    private final By invalidOrderIdMessage = By.xpath(".//img[@alt='Not found']");


    public OrderPage(WebDriver driver){
        this.driver = driver;
    }


    public void checkInvalidOrderIdMessage() {
        driver.findElement(invalidOrderIdMessage).isDisplayed();
    }
}
