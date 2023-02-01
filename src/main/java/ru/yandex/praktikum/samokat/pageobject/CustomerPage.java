package ru.yandex.praktikum.samokat.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class CustomerPage {
    private final WebDriver driver;

    // Поле ввода имени
    private final By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");

    // Поле ввода фамилии
    private final By lastNameInput = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле ввода адреса
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Скрытый список станций метро
    private final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");

    // Отфильтрованная станция метро
    private final By metroOption = By.xpath(".//li[contains(@class, 'select-search__row')]");

    // Поле ввода телефона
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка Далее
    private final By orderNextButton = By.xpath(".//*[contains(@class, 'Order_NextButton')]/button");

    // Ошибка поля Имя
    private final By firstNameError = By.xpath(".//div[contains(@class, 'Input_ErrorMessage') and text()='Введите корректное имя']");

    // Ошибка поля Фамилия
    private final By lastNameError = By.xpath(".//div[contains(@class, 'Input_ErrorMessage') and text()='Введите корректную фамилию']");

    // Ошибка поля Адрес
    private final By addressError = By.xpath(".//div[contains(@class, 'Input_ErrorMessage') and text()='Введите корректный адрес']");

    // Ошибка поля Метро
    private final By metroError = By.xpath(".//div[contains(@class, 'Order_MetroError') and text()='Выберите станцию']");

    // Ошибка поля Телефон
    private final By phoneError = By.xpath(".//div[contains(@class, 'Input_ErrorMessage') and text()='Введите корректный номер']");


    public CustomerPage(WebDriver driver){
        this.driver = driver;
    }


    // Метод заполнения поля Имя
    public void fillFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    // Метод заполнения поля Фамилия
    public void fillLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    // Метод заполнения поля Адрес
    public void fillAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    // Метод заполнения поля Метро
    public void fillMetro(String metro) {
        driver.findElement(metroInput).click();
        // Проверяем, что успел подгрузиться список всех станций
        new WebDriverWait(driver, 3).until(driver -> (driver.findElements(metroOption).size() > 2));
        driver.findElement(metroInput).sendKeys(metro);
        // Проверяем, что успел отфильтроваться список станций
        new WebDriverWait(driver, 3).until(driver -> (driver.findElements(metroOption).size() < 2));
        if (driver.findElements(metroOption).size() == 1) {
            driver.findElement(metroOption).click();
        }
    }

    // Метод заполнения поля Телефон
    public void fillPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    // Метод заполнения всей формы
    public void fillCustomerForm(String firstName, String lastName, String address, String metro, String phone) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillAddress(address);
        fillMetro(metro);
        fillPhone(phone);
    }

    // Метод проверки на наличие ошибок заполненного поля Имя
    public void checkFilledFirstName(boolean expected) {
        assertEquals(driver.findElement(firstNameError).isDisplayed(), expected);
    }

    // Метод проверки на наличие ошибок заполненного поля Фамилия
    public void checkFilledLastName(boolean expected) {
        assertEquals(driver.findElement(lastNameError).isDisplayed(), expected);
    }

    // Метод проверки на наличие ошибок заполненного поля Адрес
    public void checkFilledAddress(boolean expected) {
        assertEquals(driver.findElement(addressError).isDisplayed(), expected);
    }

    // Метод проверки на наличие ошибок заполненного поля Телефон
    public void checkFilledPhone(boolean expected) {
        assertEquals(driver.findElement(phoneError).isDisplayed(), expected);
    }

    // Метод проверки на наличие ошибок НЕзаполненного поля Метро
    public void checkFilledMetro(boolean expected) {
        assertEquals(driver.findElements(metroError).size() != 0, expected);
    }

    // Метод проверки на наличие ошибок всей заполненной формы
    public void checkFilledCustomerForm(boolean expected) {
        checkFilledFirstName(expected);
        checkFilledLastName(expected);
        checkFilledAddress(expected);
        checkFilledPhone(expected);
        checkFilledMetro(expected);
    }

    // Метод клика по кнопке Далее
    public void clickOrderNext() {
        driver.findElement(orderNextButton).click();
    }
}
