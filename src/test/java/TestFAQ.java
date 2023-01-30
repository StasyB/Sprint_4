import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.model.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class TestFAQ {
    private WebDriver driver;
    private final String answer_first;
    private final String answer_second;
    private final String answer_third;
    private final String answer_fourth;
    private final String answer_fifth;
    private final String answer_sixth;
    private final String answer_seventh;
    private final String answer_eighth;


    public TestFAQ(String answerFirst, String answerSecond, String answerThird, String answerFourth, String answerFifth,
                   String answerSixth, String answerSeventh, String answerEighth) {
        answer_first = answerFirst;
        answer_second = answerSecond;
        answer_third = answerThird;
        answer_fourth = answerFourth;
        answer_fifth = answerFifth;
        answer_sixth = answerSixth;
        answer_seventh = answerSeventh;
        answer_eighth = answerEighth;
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
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. "
                                + "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. "
                                + "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — "
                                + "даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        "Да, пока самокат не привезли. Штрафа не будет, "
                                + "объяснительной записки тоже не попросим. Все же свои.",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. "
                                + "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. "
                                + "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — "
                                + "даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        "Да, пока самокат не привезли. Штрафа не будет, "
                                + "объяснительной записки тоже не попросим. Все же свои.",
                        null},
        };
    }

    @Test
    public void checkAnswerPositive() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadFAQ();
        objMainPage.clickConfirmCookie();

        String actual1 = objMainPage.getFirstAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_first, actual1);
        String actual2 = objMainPage.getSecondAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_second, actual2);
        String actual3 = objMainPage.getThirdAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_third, actual3);
        String actual4 = objMainPage.getFourthAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_fourth, actual4);
        String actual5 = objMainPage.getFifthAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_fifth, actual5);
        String actual6 = objMainPage.getSixthAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_sixth, actual6);
        String actual7 = objMainPage.getSeventhAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_seventh, actual7);
        String actual8 = objMainPage.getEighthAnswer();
        assertEquals("Текст ответа отличается от ожидаемого", answer_eighth, actual8);
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
