import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.samokat.pageobject.CustomerPage;
import ru.yandex.praktikum.samokat.pageobject.MainPage;
import ru.yandex.praktikum.samokat.pageobject.RentPage;

import java.lang.reflect.InvocationTargetException;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    String orderButtonMethod;

    public OrderTest(String orderButtonMethod) {
        this.orderButtonMethod = orderButtonMethod;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"clickTopOrderButton"},
                {"clickBottomOrderButton"}
        };
    }


    @Test
    public void checkOrderCreation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Драйвер Chrome
        driver = new ChromeDriver();


        // Переход на Главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Создаем объект класса Главной страницы
        MainPage objMainPage = new MainPage(driver);

        // Кликаем кнопку Заказать на Главной странице с набором параметров (верхняя или нижняя)
        MainPage.class.getMethod(orderButtonMethod).invoke(objMainPage);

        // Создаем объект класса страницы Для кого самокат
        CustomerPage objCustomerPage = new CustomerPage(driver);

        // Заполняем поля на странице Для кого самокат
        objCustomerPage.fillCustomerForm("Станислав", "Михалкин", "Пхукет", "Лубянка", "+79261255666");

        // Кликаем по кнопке Далее на странице Для кого самокат
        objCustomerPage.clickOrderNext();

        // Создаем объект класса страницы Про аренду
        RentPage objRentPage = new RentPage(driver);

        // Заполняем поля на странице Про аренду
        objRentPage.fillRentForm("Тест");

        // Кликаем по кнопке Заказать на странице Про аренду
        objRentPage.clickOrderFinal();

        // Кликаем по кнопке Да во всплывающем окне подтверждения заказа
        objRentPage.clickOrderConfirm();

        // Проверяем, что открылось окно успешного создания заказа
        objRentPage.checkOrderConfirm();
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
