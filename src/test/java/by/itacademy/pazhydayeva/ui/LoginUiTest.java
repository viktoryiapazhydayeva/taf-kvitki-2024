package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.LoginPage;
import by.itacademy.pazhydayeva.ui.steps.LoginSteps;
import by.itacademy.pazhydayeva.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import by.itacademy.pazhydayeva.utils.Util;

import static by.itacademy.pazhydayeva.ui.pages.LoginPage.WRONG_CREDENTIALS_ERROR_MSG;
import static by.itacademy.pazhydayeva.user.UserFactory.getRegisteredKvitkiUser;

@Tag("UI")
@Tag("Login")
public class LoginUiTest extends BaseTest {

    @Test
    @DisplayName("Login tab rendering: check title")
    public void testLoginTabOpening() {
        homePage.openLoginForm();
        LoginPage loginPage = new LoginPage();
        loginPage.getLoginTabTitleText();
        Assertions.assertEquals("Вход", loginPage.getLoginTabTitleText());
    }

    @Test
    @DisplayName("Success login")
    public void testLoginWithValidCredentials() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser);
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals(kvitkiUser.getEmail(), loginPage.getLoggedUserEmailFromAccount());
    }

    @Test
    @DisplayName("Login with wrong password")
    public void testLoginWitIncorrectPassword() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser.getEmail(), Util.generatePassword());
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals(WRONG_CREDENTIALS_ERROR_MSG, loginPage.getLoginErrorMsgText());
    }

    @Test
    @DisplayName("Login with wrong email")
    public void testLoginWithIncorrectEmail() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(Util.getRandomEmail(), kvitkiUser.getPassword());
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals(WRONG_CREDENTIALS_ERROR_MSG, loginPage.getLoginErrorMsgText());
    }
}

