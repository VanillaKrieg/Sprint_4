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
        driver.get("https://qa-scooter.praktikum-services.ru/order");

        // Создаем объект класса страницы Для кого самокат
        CustomerPage objCustomerPage = new CustomerPage(driver);

        // Заполняем поля на странице Для кого самокат параметризованными данными
        objCustomerPage.fillCustomerForm(firstName, lastName, address, metro, phone);

        //Проверяем, нужно ли кликнуть по кнопке Далее при негативном наборе данных, чтобы вызвать ошибку поля Метро
        if (expected) {
            objCustomerPage.clickOrderNext();
        }

        // Проверяем заполненные поля на наличие ошибки
        objCustomerPage.checkFilledCustomerForm(expected);
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
