package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import by.itacademy.pazhydayeva.ui.pages.LoginPage;
import by.itacademy.pazhydayeva.ui.pages.RegistrationPage;
import by.itacademy.pazhydayeva.ui.services.RegistrationService;
import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.user.UserFactory;
import by.itacademy.pazhydayeva.utils.Util;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag("UI")
@Tag("Registration")
public class RegistrationUiTest extends BaseTest {

    private static final String REGISTRATION_ERROR_MESSAGE = "Введенное имя недействительно, если длина имени или фамилии составляет менее 2 символов.";
    private static final String PASSWORD_LENGTH_ERROR = "Пароль должен содержать как минимум 8 знаков!";
    private static final String PASSWORD_NO_DIGITS_ERROR = "Пароль должен содержать как минимум одно число";
    private static final String PASSWORD_NO_CAPITAL_LETTER_ERROR = "Пароль должен содержать как минимум одну заглавную букву";
    private static final String PASSWORD_NO_LOWERCASE_LETTER_ERROR = "Пароль должен содержать как минимум одну строчную букву";
    private static final List<String> ERRORS_TEXT_LIST = new ArrayList<>(Arrays.asList("Пожалуйста, заполните поле (Имя)", "Пожалуйста, заполните поле (Фамилия)",
            "Пожалуйста, заполните поле (Эл. почта)", "Пожалуйста, заполните поле (Пароль)"));
    private static final String EMPTY_FORENAME_ERROR = "Пожалуйста, заполните поле (Имя)";
    private static final String EMPTY_SURNAME_ERROR = "Пожалуйста, заполните поле (Фамилия)";
    private static final String EMPTY_EMAIL_ERROR = "Пожалуйста, заполните поле (Эл. почта)";
    private static final String EMPTY_PASSWORD_ERROR = "Пожалуйста, заполните поле (Пароль)";
    private static final String EMAIL_FORMAT_ERROR = "Пожалуйста, введите адрес электронной почты в правильном формате: name@example.com";
    private static final String PHONE_NUMBER_LENGTH_ERROR = "Номер телефона слишком короткий, пожалуйста, проверьте номер";
    private static final String PHONE_NUMBER_FORMAT_ERROR = "В поле для номера телефона разрешено использовать только цифры от 0 до 9!";
    private static final String INVALID_PHONE_NUMBER_ERROR = "Номер телефона указан неверно, пожалуйста, проверьте номер";

    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private RegistrationPage registrationPage = new RegistrationPage();
    private RegistrationService registrationService = new RegistrationService(homePage, registrationPage);

    public static final User newKvitkiUser = UserFactory.getNewKvitkiUser();

    @Test
    @DisplayName("Registration tab rendering: check title")
    public void testRegistrationTabOpening() {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        Assertions.assertEquals("Регистрация", registrationPage.getRegistrationTabTitleText());
    }

    @Test
    @Disabled
    @DisplayName("Success registration: required fields populated")
    public void testRegistration() {
        registrationService.registerWithOnlyRequiredFields(newKvitkiUser);
        Assertions.assertEquals(newKvitkiUser.getEmail(), loginPage.getLoggedUserEmailFromAccount());
    }

    @Test
    @Disabled
    @DisplayName("Success registration: all fields populated")
    public void testRegistrationFull() {
        registrationService.registerWithAllFieldsPopulated(newKvitkiUser, Util.getRandomPhoneNumber(9));
        Assertions.assertEquals(newKvitkiUser.getEmail(), loginPage.getLoggedUserEmailFromAccount());
    }

    @Test
    @DisplayName("Fist Name is too short")
    public void testRegistrationWithTooShortForename() {
        registrationService.registerWithOnlyRequiredFields(Util.getRandomString(1), Util.getRandomSurname(), Util.getRandomEmail(), Util.generatePassword());
        Assertions.assertEquals(REGISTRATION_ERROR_MESSAGE, registrationPage.getValidationErrorText());
    }

    @Test
    @DisplayName("Last Name is too short")
    public void testRegistrationWithTooShortSurname() {
        registrationService.registerWithAllFieldsPopulated(Util.getRandomForename(), Util.getRandomString(1), Util.getRandomEmail(), Util.getRandomPhoneNumber(9), Util.generatePassword());
        Assertions.assertEquals(REGISTRATION_ERROR_MESSAGE, registrationPage.getValidationErrorText());
    }

    @Test
    @DisplayName("Password is too short")
    public void testPasswordLengthValidation() {
        registrationService.fillPassword(Util.getShortRandomPassword());
        Assertions.assertEquals(PASSWORD_LENGTH_ERROR, registrationPage.getPasswordLengthErrorText());
    }

    @Test
    @DisplayName("Password with no digits")
    public void testPasswordWithoutDigitsValidation() {
        registrationService.fillPassword(Util.getRandomPassword(12, true, true, false));
        Assertions.assertEquals(PASSWORD_NO_DIGITS_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Password with no capital letter")
    public void testPasswordWithoutCapitalLetterValidation() {
        registrationService.fillPassword(Util.generatePassword().toLowerCase());
        Assertions.assertEquals(PASSWORD_NO_CAPITAL_LETTER_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Password with no lowercase letter")
    public void testPasswordWithoutLowercaseLetterValidation() {
        registrationService.fillPassword(Util.generatePassword().toUpperCase());
        Assertions.assertEquals(PASSWORD_NO_LOWERCASE_LETTER_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Empty fields validation")
    public void testEmptyFieldsValidations() {
        registrationService.fillRequiredFieldsForValidation("", "", "", "");
        Assertions.assertEquals(ERRORS_TEXT_LIST, registrationPage.getValidationErrorsTextList());
    }

    @Test
    @DisplayName("First Name is empty")
    public void testFistNameFieldValidation() {
        registrationService.fillRequiredFieldsForValidation("", Util.getRandomSurname(), Util.getRandomEmail(), Util.generatePassword());
        Assertions.assertEquals(EMPTY_FORENAME_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Last Name is empty")
    public void testLastNameFieldValidation() {
        registrationService.fillRequiredFieldsForValidation(Util.getRandomForename(), "", Util.getRandomEmail(), Util.generatePassword());
        Assertions.assertEquals(EMPTY_SURNAME_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Email is empty")
    public void testEmailFieldValidation() {
        registrationService.fillRequiredFieldsForValidation(Util.getRandomForename(), Util.getRandomSurname(), "", Util.generatePassword());
        Assertions.assertEquals(EMPTY_EMAIL_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Password is empty")
    public void testPasswordFieldValidation() {
        registrationService.fillRequiredFieldsForValidation(Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomEmail(), "");
        Assertions.assertEquals(EMPTY_PASSWORD_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Email of incorrect format")
    public void testEmailFormatValidation() {
        registrationService.fillEmail(Util.getRandomString(7));
        Assertions.assertEquals(EMAIL_FORMAT_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Phone number is too short")
    public void testPhoneNumberLengthValidation() {
        registrationService.fillPhoneNumber(Util.getRandomPhoneNumber(2));
        Assertions.assertEquals(PHONE_NUMBER_LENGTH_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Phone number with letters")
    public void testPhoneNumberFormatValidation() {
        registrationService.fillPhoneNumber(Util.getRandomString(9));
        Assertions.assertEquals(PHONE_NUMBER_FORMAT_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Phone number is too long")
    public void testInvalidPhoneNumberValidation() {
        registrationService.fillPhoneNumber(Util.getRandomPhoneNumber(12));
        Assertions.assertEquals(INVALID_PHONE_NUMBER_ERROR, registrationPage.getFieldErrorText());
    }
}
