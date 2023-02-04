import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.samokat.pageobject.MainPage;
import ru.yandex.praktikum.samokat.pageobject.OrderPage;

public class InvalidOrderIdTest {
    private WebDriver driver;

    String MAIN_PAGE_LINK = "https://qa-scooter.praktikum-services.ru/";

    String orderId = "dfhdcfbh";

    @Test
    public void checkInvalidOrderId() {
        // Драйвер Chrome
        driver = new ChromeDriver();

        // Переход на Главную страницу
        driver.get(MAIN_PAGE_LINK);

        // Создаем объект класса Главной страницы
        MainPage mainPage = new MainPage(driver);

        // Проверяем, что FAQ раскрывается
        mainPage.fillAndClickStatus(orderId);

        // Создаем объект класса страницы Заказа
        OrderPage orderPage = new OrderPage(driver);

        // Проверяем, что после перехода на страницу заказа отображается сообщение Такого заказа нет
        orderPage.checkInvalidOrderIdMessage();
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
