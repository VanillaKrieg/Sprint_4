import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.samokat.pageobject.MainPage;
import ru.yandex.praktikum.samokat.pageobject.OrderPage;

public class InvalidOrderIdTest {
    private WebDriver driver;

    @Test
    public void checkInvalidOrderId() {
        // Драйвер Chrome
        driver = new ChromeDriver();
        // Переход на Главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Создаем объект класса Главной страницы
        MainPage objMainPage = new MainPage(driver);

        String orderId = "dfhdcfbh";

        // Проверяем, что FAQ раскрывается
        objMainPage.fillAndClickStatus(orderId);

        // Создаем объект класса страницы Заказа
        OrderPage objOrderPage = new OrderPage(driver);

        // Проверяем, что после перехода на страницу заказа отображается сообщение Такого заказа нет
        objOrderPage.checkInvalidOrderIdMessage();
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
