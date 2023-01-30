package ru.yandex.practicum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private static final int DURATION = 3;
    private final WebDriver driver;
    private final By ORDER_BUTTON_UP = By.className("Button_Button__ra12g"); // кнопка "Заказать" вверху страницы
    private final By ORDER_BUTTON_MIDDLE = By.xpath(".//div/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']"); // кнопка "Заказать" вверху страницы
    private final By CONFIRM_COOKIE_BUTTON = By.id("rcc-confirm-button"); //кнопка Принять куки
    private final By FAQ_ACCORDION = By.className("Home_FourPart__1uthg"); //секция с вопросами
    public static final By QUESTION_FIRST = By.id("accordion__heading-0"); // открыть 1й вопрос
    public static final By ANSWER_FIRST = By.xpath(".//div[@aria-labelledby = 'accordion__heading-0']/p"); //ответ на 1й вопрос
    public static final By QUESTION_SECOND = By.id("accordion__heading-1"); // открыть 2й вопрос
    public static final By ANSWER_SECOND = By.xpath(".//div[@aria-labelledby = 'accordion__heading-1']/p"); //ответ на 2й вопрос
    public static final By QUESTION_THIRD = By.id("accordion__heading-2"); // открыть 3й вопрос
    public static final By ANSWER_THIRD = By.xpath(".//div[@aria-labelledby = 'accordion__heading-2']/p"); //ответ на 3й вопрос
    public static final By QUESTION_FOURTH = By.id("accordion__heading-3"); // открыть 4й вопрос
    public static final By ANSWER_FOURTH = By.xpath(".//div[@aria-labelledby = 'accordion__heading-3']/p"); //ответ на 4й вопрос
    public static final By QUESTION_FIFTH = By.id("accordion__heading-4"); // открыть 5й вопрос
    public static final By ANSWER_FIFTH = By.xpath(".//div[@aria-labelledby = 'accordion__heading-4']/p"); //ответ на 5й вопрос
    public static final By QUESTION_SIXTH = By.id("accordion__heading-5"); // открыть 6й вопрос
    public static final By ANSWER_SIXTH = By.xpath(".//div[@aria-labelledby = 'accordion__heading-5']/p"); //ответ на 6й вопрос
    public static final By QUESTION_SEVENTH = By.id("accordion__heading-6"); // открыть 7й вопрос
    public static final By ANSWER_SEVENTH = By.xpath(".//div[@aria-labelledby = 'accordion__heading-6']/p"); //ответ на 7й вопрос
    public static final By QUESTION_EIGHTH = By.id("accordion__heading-7"); // открыть 8й вопрос
    public static final By ANSWER_EIGHTH = By.xpath(".//div[@aria-labelledby = 'accordion__heading-7']/p"); //ответ на 8й вопрос

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getORDER_BUTTON_UP() {
        return ORDER_BUTTON_UP;
    }

    public By getORDER_BUTTON_MIDDLE() {
        return ORDER_BUTTON_MIDDLE;
    }

    public void waitForLoadOrderButtonUP() {
        new WebDriverWait(driver, DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_BUTTON_UP));
    }

    public void waitForLoadOrderButtonMiddle() {
        new WebDriverWait(driver, DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_BUTTON_MIDDLE));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(ORDER_BUTTON_MIDDLE));

    }

    public void waitForLoadFAQ() {
        new WebDriverWait(driver, DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(FAQ_ACCORDION));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(FAQ_ACCORDION));
    }

    public void clickConfirmCookie() {
        driver.findElement(CONFIRM_COOKIE_BUTTON).click();
    }

    public void clickOrderButtonUP() {
        driver.findElement(ORDER_BUTTON_UP).click();
    }

    public void clickOrderButtonMiddle() {
        driver.findElement(ORDER_BUTTON_MIDDLE).click();
    }

    public void clickQuestion(By question) {
        driver.findElement(question).click();
    }

    public String getAnswer(By answer) {
        return driver.findElement(answer).getText();
    }

}
