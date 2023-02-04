import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.samokat.pageobject.CustomerPage;

@RunWith(Parameterized.class)
public class FillOrderFormTest {
    private WebDriver driver;

    String ORDER_PAGE_LINK = "https://qa-scooter.praktikum-services.ru/order";

    String firstName;
    String lastName;
    String address;
    String metro;
    String phone;
    boolean expected;

    public FillOrderFormTest(String firstName, String lastName, String address, String metro, String phone, boolean expected) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Станислав", "Михалкин", "Пхукет", "Лубянка", "+79261255666", false},
                {"sdf", "tnbdf", "dfnbdcb", "kvhjv", "dfbdxfb", true}
        };
    }

    @Test
    public void fillCustomerForm() {
        // Драйвер Chrome
        driver = new ChromeDriver();
        // Переход на страницу Для кого Самокат
        driver.get(ORDER_PAGE_LINK);

        // Создаем объект класса страницы Для кого самокат
        CustomerPage customerPage = new CustomerPage(driver);

        // Заполняем поля на странице Для кого самокат параметризованными данными
        customerPage.fillCustomerForm(firstName, lastName, address, metro, phone);

        //Проверяем, нужно ли кликнуть по кнопке Далее при негативном наборе данных, чтобы вызвать ошибку поля Метро
        if (expected) {
            customerPage.clickOrderNext();
        }

        // Проверяем заполненные поля на наличие ошибки
        customerPage.checkFilledCustomerForm(expected);
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
