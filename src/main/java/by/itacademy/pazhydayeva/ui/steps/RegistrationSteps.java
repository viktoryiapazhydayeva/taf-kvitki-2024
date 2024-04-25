package by.itacademy.pazhydayeva.ui.steps;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import by.itacademy.pazhydayeva.ui.pages.RegistrationPage;
import by.itacademy.pazhydayeva.user.User;

public class RegistrationSteps {

    private HomePage homePage;
    private RegistrationPage registrationPage;

    public RegistrationSteps() {
        this.homePage = new HomePage();
        this.registrationPage = new RegistrationPage();
    }

    private void fillRequiredFields(String forename, String surname, String email, String password) {
        registrationPage.enterForename(forename);
        registrationPage.enterSurname(surname);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
    }

    public void registerWithOnlyRequiredFields(User user) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        fillRequiredFields(user.getForename(), user.getSurname(), user.getEmail(), user.getPassword());
        registrationPage.confirmAgreementRules();
        registrationPage.confirmRegistration();
    }
    public void registerWithOnlyRequiredFields(String forename, String surname, String email, String password) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        fillRequiredFields(forename, surname, email, password);
        registrationPage.confirmAgreementRules();
        registrationPage.confirmRegistration();
    }

    public void registerWithAllFieldsPopulated(User user, String phoneNumber) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        fillRequiredFields(user.getForename(), user.getSurname(), user.getEmail(), user.getPassword());
        registrationPage.enterPhoneNumber(phoneNumber);
        registrationPage.confirmNewsSubscription();
        registrationPage.confirmAgreementRules();
        registrationPage.confirmRegistration();
    }

    public void registerWithAllFieldsPopulated(String forename, String surname, String email, String phoneNumber, String password) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        fillRequiredFields(forename, surname, email, password);
        registrationPage.enterPhoneNumber(phoneNumber);
        registrationPage.confirmNewsSubscription();
        registrationPage.confirmAgreementRules();
        registrationPage.confirmRegistration();
    }

    public void fillRequiredFieldsForValidation(String forename, String surname, String email, String password) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        fillRequiredFields(forename, surname, email, password);
        registrationPage.clickOnBackgroundImg();
    }

    public void fillEmail(String email) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        registrationPage.enterEmail(email);
        registrationPage.clickOnBackgroundImg();
    }

    public void fillPhoneNumber(String phoneNumber) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        registrationPage.enterPhoneNumber(phoneNumber);
    }

    public void fillPassword(String password) {
        homePage.openLoginForm();
        registrationPage.openRegistrationTab();
        registrationPage.enterPassword(password);
    }
}
