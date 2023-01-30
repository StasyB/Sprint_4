import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.model.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class TestFAQ {
    private WebDriver driver;
    private final By question;
    private final By answer;
    private final String expectedAnswer;

    public TestFAQ(By question, By answer, String expectedAnswer) {
        this.question = question;
        this.answer = answer;
        this.expectedAnswer = expectedAnswer;
    }

    @Before
    public void setUp() {
        //WebDriverManager.firefoxdriver().setup(); //для выполнения теста в Firefox, если выполняем в Chrome - закомитить строку
        driver = new ChromeDriver(); // для выполнения теста в Firefox, необходимо заменить new ChromeDriver() на new FirefoxDriver()
        String URL = "https://qa-scooter.praktikum-services.ru/";
        driver.get(URL);
    }

    @Parameterized.Parameters
    public static Object[][] getContentData() {
        return new Object[][]{
                {MainPage.QUESTION_FIRST, MainPage.ANSWER_FIRST, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {MainPage.QUESTION_SECOND, MainPage.ANSWER_SECOND, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {MainPage.QUESTION_THIRD, MainPage.ANSWER_THIRD, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. "
                        + "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. "
                        + "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {MainPage.QUESTION_FOURTH, MainPage.ANSWER_FOURTH, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {MainPage.QUESTION_FIFTH, MainPage.ANSWER_FIFTH, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {MainPage.QUESTION_SIXTH, MainPage.ANSWER_SIXTH, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — "
                        + "даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {MainPage.QUESTION_SEVENTH, MainPage.ANSWER_SEVENTH, "Да, пока самокат не привезли. Штрафа не будет, " + "объяснительной записки тоже не попросим. Все же свои."},
                {MainPage.QUESTION_EIGHTH, MainPage.ANSWER_EIGHTH, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkTextAnswer() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickConfirmCookie();
        objMainPage.waitForLoadFAQ();

        objMainPage.clickQuestion(question);
        String actual = objMainPage.getAnswer(answer);
        assertEquals("Текст ответа отличается от ожидаемого", expectedAnswer, actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
