import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
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
import ru.yandex.practicum.model.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;


@RunWith(Parameterized.class)
public class TestScooterOrder {
    private WebDriver driver;
    private final By button;
    private final String nameClient;
    private final String surnameClient;
    private final String addressClient;
    private final String metroClient;
    private final String phoneNumberClient;
    private final String dateOrder;
    private final String rentPeriodClient;
    private final String commentClient;

    @Before
    public void setUp() {
        // WebDriverManager.firefoxdriver().setup(); //для выполнения теста в Firefox, если выполняем в Chrome - закомитить строку
        driver = new ChromeDriver(); // для выполнения теста в Firefox, необходимо заменить new ChromeDriver() на new FirefoxDriver()
    }

    public TestScooterOrder(By button, String nameClient, String surnameClient, String addressClient, String metroClient,
                            String phoneNumberClient, String dateOrder, String rentPeriodClient, String commentClient) {
        this.button = button;
        this.nameClient = nameClient;
        this.surnameClient = surnameClient;
        this.addressClient = addressClient;
        this.metroClient = metroClient;
        this.phoneNumberClient = phoneNumberClient;
        this.dateOrder = dateOrder;
        this.rentPeriodClient = rentPeriodClient;
        this.commentClient = commentClient;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {By.xpath(".//div/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']"), "Вениамин", "Одуванчиков", "г. Москва, ул.Правды, д. 8", "Белорусская", "+79999999999",
                        "30.01.23", "трое", "домофон 223456"},
                {By.className("Button_Button__ra12g"), "Маэстро", "Сапарин", "г. Санкт-Петербург, ул. Ленина, д.1б, кв. 1", "Сокольники", "+79269321451",
                        "01.02.23", "сутки", ""},
        };
    }


    @Test
    public void createScooterOrderTest() {
        assert driver != null;
        String URL = "https://qa-scooter.praktikum-services.ru/";
        driver.get(URL);

        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadOrderButtonUP();
        objMainPage.clickConfirmCookie();

        if (button.equals(objMainPage.getORDER_BUTTON_UP())) {
            objMainPage.clickOrderButtonUP();
        } else if (button.equals(objMainPage.getORDER_BUTTON_MIDDLE())) {
            objMainPage.waitForLoadOrderButtonMiddle();
            objMainPage.clickOrderButtonMiddle();
        }

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setOrderFormClient(nameClient, surnameClient, addressClient, metroClient, phoneNumberClient);
        objOrderPage.waitForLoadButtonNext();
        objOrderPage.clickButtonNext();
        objOrderPage.setOrderFormScooter(dateOrder, rentPeriodClient, commentClient);
        objOrderPage.waitForLoadButtonOrder();
        objOrderPage.clickButtonOrder();
        objOrderPage.waitForLoadButtonConfirm();
        objOrderPage.clickConfirmButton();
        objOrderPage.waitForLoadMassageOrderConfirm();

        MatcherAssert.assertThat(objOrderPage.getMessageOrderConfirm(), containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
