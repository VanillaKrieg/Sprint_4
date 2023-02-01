package ru.yandex.praktikum.samokat.pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class MainPage {
    private final WebDriver driver;

    // Верхняя кнопка Заказать
    private final By topOrderButton = By.xpath(".//*[contains(@class, 'Header_Nav')]/button[text()='Заказать']");

    // Нижняя кнопка Заказать
    private final By bottomOrderButton = By.xpath(".//*[contains(@class, 'Home_Finish')]/button[text()='Заказать']");

    // Кнопка Статус заказа
    private final By orderStatusButton = By.xpath(".//button[contains(@class, 'Header_Link') and text()='Статус заказа']");

    // Поле ввода Номера заказа
    private final By orderIdInput = By.xpath(".//input[@placeholder='Введите номер заказа']");

    // Кнопка Go!
    private final By orderGoButton = By.xpath(".//button[contains(@class, 'Button') and text()='Go!']");

    // Кнопка Яндекс лого
    private final By yandexLogo = By.xpath(".//*[contains(@class, 'Header_LogoYandex')]");

    // Вопрос FAQ
    private final By faqQuestionAccordion = By.xpath(".//*[contains(@class, 'accordion__button') and text()='Сколько это стоит? И как оплатить?']");

    // Ответ FAQ
    private final By faqAnswerAccordion = By.xpath(".//*[contains(@class, 'accordion__panel')]/*[text()='Сутки — 400 рублей. Оплата курьеру — наличными или картой.']");

    // Кнопка Самокат лого
    private final By samokatLogo = By.xpath(".//*[contains(@class, 'Header_LogoScooter')]");


    public MainPage(WebDriver driver){
        this.driver = driver;
    }


    // Метод проверки FAQ
    public void unfoldAndCheckFaq() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(faqQuestionAccordion));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(faqQuestionAccordion));
        driver.findElement(faqQuestionAccordion).click();
        driver.findElement(faqAnswerAccordion).isDisplayed();
    }

    // Метод клика по верхней кнопке Заказать
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    // Метод клика по нижней кнопке Заказать
    public void clickBottomOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(bottomOrderButton));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(bottomOrderButton));
        driver.findElement(bottomOrderButton).click();
    }

    // Метод клика на лого Яндекса
    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }

    // Метод проверки, что после клика лого открывается Главная страница Яндекса
    public void checkYandexMainPage() {
        Assert.assertEquals("https://dzen.ru/?yredirect=true", driver.getCurrentUrl());
    }

    // Метод клика на лого Самоката
    public void clickSamokatLogo() {
        driver.findElement(samokatLogo).click();
    }

    // Метод проверки, что после клика лого открывается Главная страница Самоката
    public void checkSamokatMainPage() {
        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    // Метод клика по кнопке Статус заказа
    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    // Метод заполнения поля Номер заказа
    public void fillOrderIdInput(String orderId) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(orderIdInput));
        driver.findElement(orderIdInput).sendKeys(orderId);
    }

    // Метод клика по кнопке Go!
    public void clickOrderStatusGoButton() {
        driver.findElement(orderGoButton).click();
    }

    //
    public void fillAndClickStatus(String orderId) {
        clickOrderStatusButton();
        fillOrderIdInput(orderId);
        clickOrderStatusGoButton();
    }
}
