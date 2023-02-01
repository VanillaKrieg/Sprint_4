import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.samokat.pageobject.MainPage;

@RunWith(Parameterized.class)
public class LogoTest {
    private WebDriver driver;

    String startingPage;

    public LogoTest(String startingPage) {
        this.startingPage = startingPage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"https://qa-scooter.praktikum-services.ru/"},
                {"https://qa-scooter.praktikum-services.ru/order"},
                {"https://qa-scooter.praktikum-services.ru/track"}
        };
    }

    @Test
    public void checkYandexLogo() {
        // Драйвер Chrome
        driver = new ChromeDriver();
        // Переход на страницу с набором параметров (Главная, Заказать, Статус заказа)
        driver.get(startingPage);

        // Создаем объект класса Главной страницы
        MainPage objMainPage = new MainPage(driver);

        // Кликаем на лого Яндекса
        objMainPage.clickYandexLogo();

        // Проверяем, что после клика лого открывается Главная страница Яндекса
        objMainPage.checkYandexMainPage();
    }

    @Test
    public void checkSamokatLogo() {
        // Драйвер Chrome
        driver = new ChromeDriver();
        // Переход на страницу с набором параметров (Главная, Заказать, Статус заказа)
        driver.get(startingPage);

        // Создаем объект класса Главной страницы
        MainPage objMainPage = new MainPage(driver);

        // Кликаем на лого Самоката
        objMainPage.clickSamokatLogo();

        // Проверяем, что после клика лого открывается Главная страница Самоката
        objMainPage.checkSamokatMainPage();
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
