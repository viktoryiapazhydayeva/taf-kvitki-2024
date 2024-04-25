package by.itacademy.pazhydayeva.ui.pages;

import by.itacademy.pazhydayeva.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationPage {

    private WebDriver driver;
    private static final String REGISTRATION_TAB_ID = "mat-tab-label-0-1";
    private static final String REGISTRATION_TAB_TITLE_XPATH = "//div[@id='mat-tab-label-0-1']//span[@class='mdc-tab__text-label']";
    private static final String FORENAME_FIELD_NAME = "firstName";
    private static final String SURNAME_FIELD_NAME = "lastName";
    private static final String EMAIL_FIELD_NAME = "email";
    private static final String PHONE_NUMBER_FIELD_NAME = "phoneNr";
    private static final String PASSWORD_FIELD_NAME = "newPassword";
    private static final String NEWS_MAIL_SUBSCRIPTION_CHECKBOX_ID = "checkbox-newsmail";
    private static final String AGREEMENT_RULES_CHECKBOX_ID = "agreement-checkbox";
    private static final String REGISTER_BTN_XPATH = "//button[@class='ng-tns-c28-9']";
    private static final String VALIDATION_ERROR_XPATH = "//div[@class='info-text']";
    private static final String PASSWORD_LENGTH_ERROR_XPATH = "//app-collapsible[@class='form-field-error ng-tns-c7-16 ng-tns-c6-17 ng-trigger ng-trigger-slideInOut ng-star-inserted']";
    private static final String FIELD_ERROR_SELECTOR = ".form-field-error";
    private static final String BACKGROUND_IMG_XPATH = "//div[@class='background-image']";
    public static final String REGISTRATION_ERROR_MESSAGE = "Введенное имя недействительно, если длина имени или фамилии составляет менее 2 символов.";
    public static final String PASSWORD_LENGTH_ERROR = "Пароль должен содержать как минимум 8 знаков!";
    public static final String PASSWORD_NO_DIGITS_ERROR = "Пароль должен содержать как минимум одно число";
    public static final String PASSWORD_NO_CAPITAL_LETTER_ERROR = "Пароль должен содержать как минимум одну заглавную букву";
    public static final String PASSWORD_NO_LOWERCASE_LETTER_ERROR = "Пароль должен содержать как минимум одну строчную букву";
    public static final List<String> ERRORS_TEXT_LIST = new ArrayList<>(Arrays.asList("Пожалуйста, заполните поле (Имя)", "Пожалуйста, заполните поле (Фамилия)",
            "Пожалуйста, заполните поле (Эл. почта)", "Пожалуйста, заполните поле (Пароль)"));
    public static final String EMPTY_FORENAME_ERROR = "Пожалуйста, заполните поле (Имя)";
    public static final String EMPTY_SURNAME_ERROR = "Пожалуйста, заполните поле (Фамилия)";
    public static final String EMPTY_EMAIL_ERROR = "Пожалуйста, заполните поле (Эл. почта)";
    public static final String EMPTY_PASSWORD_ERROR = "Пожалуйста, заполните поле (Пароль)";
    public static final String EMAIL_FORMAT_ERROR = "Пожалуйста, введите адрес электронной почты в правильном формате: name@example.com";
    public static final String PHONE_NUMBER_LENGTH_ERROR = "Номер телефона слишком короткий, пожалуйста, проверьте номер";
    public static final String PHONE_NUMBER_FORMAT_ERROR = "В поле для номера телефона разрешено использовать только цифры от 0 до 9!";
    public static final String INVALID_PHONE_NUMBER_ERROR = "Номер телефона указан неверно, пожалуйста, проверьте номер";

    public RegistrationPage() {
        this.driver = Driver.getDriver();
    }

    public void openRegistrationTab() {
        WebElement registrationTab = driver.findElement(By.id(REGISTRATION_TAB_ID));
        registrationTab.click();
    }

    public String getRegistrationTabTitleText() {
        openRegistrationTab();
        WebElement registrationTabTitle = driver.findElement(By.xpath(REGISTRATION_TAB_TITLE_XPATH));
        return registrationTabTitle.getText();
    }

    public void enterForename(String forename) {
        WebElement forenameField = driver.findElement(By.name(FORENAME_FIELD_NAME));
        forenameField.sendKeys(forename);
    }

    public void enterSurname(String surname) {
        WebElement surnameField = driver.findElement(By.name(SURNAME_FIELD_NAME));
        surnameField.sendKeys(surname);
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.name(EMAIL_FIELD_NAME));
        emailField.sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneNumberField = driver.findElement(By.name(PHONE_NUMBER_FIELD_NAME));
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.name(PASSWORD_FIELD_NAME));
        passwordField.sendKeys(password);
    }

    public void confirmNewsSubscription() {
        WebElement newsMailCheckbox = driver.findElement(By.id(NEWS_MAIL_SUBSCRIPTION_CHECKBOX_ID));
        newsMailCheckbox.click();
    }

    public void confirmAgreementRules() {
        WebElement agreementCheckbox = driver.findElement(By.id(AGREEMENT_RULES_CHECKBOX_ID));
        agreementCheckbox.click();
    }

    public void confirmRegistration() {
        WebElement registerBtn = driver.findElement(By.xpath(REGISTER_BTN_XPATH));
        registerBtn.click();
    }

    public String getValidationErrorText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VALIDATION_ERROR_XPATH)));

        WebElement validationError = driver.findElement(By.xpath(VALIDATION_ERROR_XPATH));
        return validationError.getText();
    }

    public String getPasswordLengthErrorText() {
        WebElement passwordLengthError = driver.findElement(By.xpath(PASSWORD_LENGTH_ERROR_XPATH));
        return passwordLengthError.getText();
    }

    public String getFieldErrorText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(FIELD_ERROR_SELECTOR)));

        WebElement passwordFormatError = driver.findElement(By.cssSelector(FIELD_ERROR_SELECTOR));
        return passwordFormatError.getText();
    }

    public void clickOnBackgroundImg() {
        WebElement backGroundImg = driver.findElement(By.xpath(BACKGROUND_IMG_XPATH));
        backGroundImg.click();
    }

    public List<WebElement> getValidationErrors() {
        return driver.findElements(By.cssSelector(FIELD_ERROR_SELECTOR));
    }

    public List<String> getValidationErrorsTextList() {
        List<String> errorsText = new ArrayList<>();
        for (WebElement error : getValidationErrors()) {
            errorsText.add(error.getText());
        }
        return errorsText;
    }
}
