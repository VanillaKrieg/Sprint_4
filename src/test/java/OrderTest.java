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

    String MAIN_PAGE_LINK = "https://qa-scooter.praktikum-services.ru/";

    String orderButtonMethod;

    String firstName = "Станислав";
    String lastName = "Михалкин";
    String address = "Пхукет";
    String metro = "Лубянка";
    String phone = "+79261255666";
    String comment = "Тест";

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
        driver.get(MAIN_PAGE_LINK);

        // Создаем объект класса Главной страницы
        MainPage mainPage = new MainPage(driver);

        // Кликаем кнопку Заказать на Главной странице с набором параметров (верхняя или нижняя)
        MainPage.class.getMethod(orderButtonMethod).invoke(mainPage);

        // Создаем объект класса страницы Для кого самокат
        CustomerPage customerPage = new CustomerPage(driver);

        // Заполняем поля на странице Для кого самокат
        customerPage.fillCustomerForm(firstName, lastName, address, metro, phone);

        // Кликаем по кнопке Далее на странице Для кого самокат
        customerPage.clickOrderNext();

        // Создаем объект класса страницы Про аренду
        RentPage rentPage = new RentPage(driver);

        // Заполняем поля на странице Про аренду
        rentPage.fillRentForm(comment);

        // Кликаем по кнопке Заказать на странице Про аренду
        rentPage.clickOrderFinal();

        // Кликаем по кнопке Да во всплывающем окне подтверждения заказа
        rentPage.clickOrderConfirm();

        // Проверяем, что открылось окно успешного создания заказа
        rentPage.checkOrderConfirm();
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
