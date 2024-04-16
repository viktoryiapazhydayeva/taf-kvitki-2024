package by.itacademy.pazhydayeva.ui.services;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import by.itacademy.pazhydayeva.ui.pages.LoginPage;
import by.itacademy.pazhydayeva.user.User;

public class LoginService {
    private HomePage homePage;
    private LoginPage loginPage;

    public LoginService(HomePage homePage, LoginPage loginPage) {
        this.homePage = homePage;
        this.loginPage = loginPage;
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
