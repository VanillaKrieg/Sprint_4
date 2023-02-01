import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.samokat.pageobject.MainPage;

public class CheckFaqTest {
    private WebDriver driver;

    @Test
    public void checkFaqAnswerVisible() {
        // Драйвер Chrome
        driver = new ChromeDriver();
        // Переход на Главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Создаем объект класса Главной страницы
        MainPage objMainPage = new MainPage(driver);

        // Проверяем, что FAQ раскрывается
        objMainPage.unfoldAndCheckFaq();
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
