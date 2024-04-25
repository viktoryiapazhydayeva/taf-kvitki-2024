package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.LoginPage;
import by.itacademy.pazhydayeva.ui.pages.RegistrationPage;
import by.itacademy.pazhydayeva.ui.steps.RegistrationSteps;
import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.user.UserFactory;
import by.itacademy.pazhydayeva.utils.Util;
import org.junit.jupiter.api.*;

import static by.itacademy.pazhydayeva.ui.pages.RegistrationPage.*;

@Tag("UI")
@Tag("Registration")
public class RegistrationUiTest extends BaseTest {

    @Test
    @DisplayName("Registration tab rendering: check title")
    public void testRegistrationTabOpening() {
        homePage.openLoginForm();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openRegistrationTab();
        Assertions.assertEquals("Регистрация", registrationPage.getRegistrationTabTitleText());
    }

    @Test
    @Disabled
    @DisplayName("Success registration: required fields populated")
    public void testRegistration() {
        User newKvitkiUser = UserFactory.getNewKvitkiUser();
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.registerWithOnlyRequiredFields(newKvitkiUser);
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals(newKvitkiUser.getEmail(), loginPage.getLoggedUserEmailFromAccount());
    }

    @Test
    @Disabled
    @DisplayName("Success registration: all fields populated")
    public void testRegistrationFull() {
        User newKvitkiUser = UserFactory.getNewKvitkiUser();
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.registerWithAllFieldsPopulated(newKvitkiUser, Util.getRandomPhoneNumber(9));
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals(newKvitkiUser.getEmail(), loginPage.getLoggedUserEmailFromAccount());
    }

    @Test
    @DisplayName("Fist Name is too short")
    public void testRegistrationWithTooShortForename() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.registerWithOnlyRequiredFields(Util.getRandomString(1), Util.getRandomSurname(), Util.getRandomEmail(), Util.generatePassword());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(REGISTRATION_ERROR_MESSAGE, registrationPage.getValidationErrorText());
    }

    @Test
    @DisplayName("Last Name is too short")
    public void testRegistrationWithTooShortSurname() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.registerWithAllFieldsPopulated(Util.getRandomForename(), Util.getRandomString(1), Util.getRandomEmail(), Util.getRandomPhoneNumber(9), Util.generatePassword());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(REGISTRATION_ERROR_MESSAGE, registrationPage.getValidationErrorText());
    }

    @Test
    @DisplayName("Password is too short")
    public void testPasswordLengthValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillPassword(Util.getShortRandomPassword());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(PASSWORD_LENGTH_ERROR, registrationPage.getPasswordLengthErrorText());
    }

    @Test
    @DisplayName("Password with no digits")
    public void testPasswordWithoutDigitsValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillPassword(Util.getRandomPassword(12, true, true, false));
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(PASSWORD_NO_DIGITS_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Password with no capital letter")
    public void testPasswordWithoutCapitalLetterValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillPassword(Util.generatePassword().toLowerCase());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(PASSWORD_NO_CAPITAL_LETTER_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Password with no lowercase letter")
    public void testPasswordWithoutLowercaseLetterValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillPassword(Util.generatePassword().toUpperCase());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(PASSWORD_NO_LOWERCASE_LETTER_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Empty fields validation")
    public void testEmptyFieldsValidations() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillRequiredFieldsForValidation("", "", "", "");
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(ERRORS_TEXT_LIST, registrationPage.getValidationErrorsTextList());
    }

    @Test
    @DisplayName("First Name is empty")
    public void testFistNameFieldValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillRequiredFieldsForValidation("", Util.getRandomSurname(), Util.getRandomEmail(), Util.generatePassword());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(EMPTY_FORENAME_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Last Name is empty")
    public void testLastNameFieldValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillRequiredFieldsForValidation(Util.getRandomForename(), "", Util.getRandomEmail(), Util.generatePassword());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(EMPTY_SURNAME_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Email is empty")
    public void testEmailFieldValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillRequiredFieldsForValidation(Util.getRandomForename(), Util.getRandomSurname(), "", Util.generatePassword());
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(EMPTY_EMAIL_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Password is empty")
    public void testPasswordFieldValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillRequiredFieldsForValidation(Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomEmail(), "");
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(EMPTY_PASSWORD_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Email of incorrect format")
    public void testEmailFormatValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillEmail(Util.getRandomString(7));
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(EMAIL_FORMAT_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Phone number is too short")
    public void testPhoneNumberLengthValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillPhoneNumber(Util.getRandomPhoneNumber(2));
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(PHONE_NUMBER_LENGTH_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Phone number with letters")
    public void testPhoneNumberFormatValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillPhoneNumber(Util.getRandomString(9));
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(PHONE_NUMBER_FORMAT_ERROR, registrationPage.getFieldErrorText());
    }

    @Test
    @DisplayName("Phone number is too long")
    public void testInvalidPhoneNumberValidation() {
        RegistrationSteps registrationSteps = new RegistrationSteps();
        registrationSteps.fillPhoneNumber(Util.getRandomPhoneNumber(12));
        RegistrationPage registrationPage = new RegistrationPage();
        Assertions.assertEquals(INVALID_PHONE_NUMBER_ERROR, registrationPage.getFieldErrorText());
    }
}
