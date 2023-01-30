package ru.yandex.practicum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private static final int DURATION = 3;
    private final WebDriver driver;

    private final By INPUT_NAME = By.xpath(".//div/input[@placeholder = '* Имя']");
    private final By INPUT_SURNAME = By.xpath(".//div/input[@placeholder = '* Фамилия']");
    private final By INPUT_ADDRESS = By.xpath(".//div/input[@placeholder = '* Адрес: куда привезти заказ']");
    private final By INPUT_METRO = By.xpath(".//div/input[@placeholder = '* Станция метро']");
    private final By INPUT_PHONE_NUMBER = By.xpath(".//div/input[@placeholder = '* Телефон: на него позвонит курьер']");
    private final By BUTTON_NEXT = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button");
    private final By INPUT_DATE = By.xpath(".//div/input[@placeholder='* Когда привезти самокат']");
    private final By INPUT_RENT_PERIOD = By.className("Dropdown-placeholder");
    private final By INPUT_COLOR_SCOOTER = By.className("Order_Title__3EKne");
    private final By INPUT_COMMENT = By.xpath(".//div/input[@placeholder='Комментарий для курьера']");
    private final By BUTTON_ORDER = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    private final By BUTTON_CONFIRM = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Да']");
    private final By MESSAGE_ORDER_CONFIRM = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div[@class = 'Order_ModalHeader__3FDaJ']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadButtonNext() {
        new WebDriverWait(driver, DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(BUTTON_NEXT));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(BUTTON_NEXT));
    }

    public void waitForLoadButtonOrder() {
        new WebDriverWait(driver, DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(BUTTON_ORDER));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(BUTTON_ORDER));
    }

    public void waitForLoadButtonConfirm() {
        new WebDriverWait(driver, DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(BUTTON_CONFIRM));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(BUTTON_CONFIRM));
    }

    public void waitForLoadMassageOrderConfirm() {
        new WebDriverWait(driver, DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_ORDER_CONFIRM));
    }

    public void setName(String nameClient) {
        driver.findElement(INPUT_NAME).sendKeys(nameClient);
    }

    public void setSurname(String surnameClient) {
        driver.findElement(INPUT_SURNAME).sendKeys(surnameClient);
    }

    public void setAddress(String addressClient) {
        driver.findElement(INPUT_ADDRESS).sendKeys(addressClient);
    }

    public void setMetro(String metroClient) {
        driver.findElement(INPUT_METRO).click();
        By metroOption = By.xpath(".//li[@class = 'select-search__row']/button/div[contains(text(),'" + metroClient + "')]");
        driver.findElement(metroOption).click();
    }

    public void setPhoneNumber(String phoneNumberClient) {
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys(phoneNumberClient);
    }

    public void clickButtonNext() {
        driver.findElement(BUTTON_NEXT).click();
    }

    public void setDate(String dateOrder) {
        driver.findElement(INPUT_DATE).click();
        driver.findElement(INPUT_DATE).sendKeys(dateOrder);
        driver.findElement(INPUT_DATE).sendKeys(Keys.ENTER);
    }

    public void setRentPeriod(String rentPeriodClient) {
        driver.findElement(INPUT_RENT_PERIOD).click();
        By rentPeriodOption = By.xpath(".//div[@class = 'Dropdown-menu']/div[contains(text(),'" + rentPeriodClient + "')]");
        driver.findElement(rentPeriodOption).click();
    }

    public void setColorScooter() {
        driver.findElement(INPUT_COLOR_SCOOTER);
        driver.findElement(By.id("black")).click();
        driver.findElement(By.id("grey")).click();
    }

    public void setComment(String commentClient) {
        driver.findElement(INPUT_COMMENT).sendKeys(commentClient);
    }

    public void clickButtonOrder() {
        driver.findElement(BUTTON_ORDER).click();
    }

    public void clickConfirmButton() {
        driver.findElement(BUTTON_CONFIRM).click();
    }

    public String getMessageOrderConfirm() {
        return driver.findElement(MESSAGE_ORDER_CONFIRM).getText();
    }

    // Шаг заполнения заявки 1. Данные о клиенте.
    public void setOrderFormClient(String nameClient, String surnameClient,
                                   String addressClient, String metroClient, String phoneNumberClient) {
        setName(nameClient);
        setSurname(surnameClient);
        setAddress(addressClient);
        setMetro(metroClient);
        setPhoneNumber(phoneNumberClient);
    }

    // Шаг заполнения заявки 2. Данные о заказе.
    public void setOrderFormScooter(String dateOrder, String rentPeriodClient, String commentClient) {
        setDate(dateOrder);
        setRentPeriod(rentPeriodClient);
        setColorScooter();
        setComment(commentClient);
    }

}
