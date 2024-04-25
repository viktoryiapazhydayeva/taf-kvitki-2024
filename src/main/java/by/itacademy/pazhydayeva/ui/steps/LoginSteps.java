package by.itacademy.pazhydayeva.ui.steps;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import by.itacademy.pazhydayeva.ui.pages.LoginPage;
import by.itacademy.pazhydayeva.user.User;

public class LoginSteps {
    private HomePage homePage;
    private LoginPage loginPage;

    public LoginSteps() {
        this.homePage = new HomePage();
        this.loginPage = new LoginPage();
    }

    public void login(User user) {
        homePage.openLoginForm();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.confirmLogin();
    }

    public void login(String email, String password) {
        homePage.openLoginForm();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.confirmLogin();
    }
}
