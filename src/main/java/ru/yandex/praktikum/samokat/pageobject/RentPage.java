package ru.yandex.praktikum.samokat.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RentPage {
    private final WebDriver driver;

    // Поле даты
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Календарь выбора даты
    private final By pickDate = By.xpath(".//div[contains(@class, 'react-datepicker__day--001')]");

    // Скрытый список длительности аренды
    private final By termInput = By.xpath(".//div[(contains(@class, 'Dropdown-control') and div[text()='* Срок аренды'])]");

    // Раскрытый список длительности аренды
    private final By termDropdown = By.xpath(".//div[contains(@class, 'Dropdown-option') and text()='сутки']");

    // Чекбокс выбора цвета
    private final By colorCheckbox = By.xpath(".//input[@id='grey' and @type='checkbox']");

    // Поле ввода комментария для курьера
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка Заказать
    private final By orderFinalButton = By.xpath(".//*[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");

    // Кнопка Да во всплывающем окне подтверждения заказа
    private final By orderConfirmButton = By.xpath(".//*[contains(@class, 'Order_Buttons')]/button[text()='Да']");

    // Сообщение об успешном оформлении заказа
    private final By orderConfirmMessage = By.xpath(".//*[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");


    public RentPage(WebDriver driver){
        this.driver = driver;
    }


    // Метод заполнения поля Когда привезти самокат
    public void fillDate() {
        driver.findElement(dateInput).click();
        driver.findElement(pickDate).click();
    }

    // Метод заполнения поля Срок аренды
    public void fillTerm() {
        driver.findElement(termInput).click();
        driver.findElement(termDropdown).click();
    }

    // Метод заполнения поля Цвет самоката
    public void fillColor() {
        driver.findElement(colorCheckbox).click();
    }

    // Метод заполнения поля Комментарий для курьера
    public void fillComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    // Метод заполнения полей
    public void fillRentForm(String comment) {
        fillDate();
        fillTerm();
        fillColor();
        fillComment(comment);
    }

    // Метод клика по кнопке Заказать
    public void clickOrderFinal() {
        driver.findElement(orderFinalButton).click();
    }

    // Метод клика по кнопке Да во всплывающем окне подтверждения заказа
    public void clickOrderConfirm() {
        driver.findElement(orderConfirmButton).click();
    }

    // Метод проверки, что открылось окно успешного создания заказа
    public void checkOrderConfirm() {
        driver.findElement(orderConfirmMessage).isDisplayed();
    }
}
