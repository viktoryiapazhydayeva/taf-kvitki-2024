package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import by.itacademy.pazhydayeva.ui.pages.LoginPage;
import by.itacademy.pazhydayeva.ui.services.LoginService;
import by.itacademy.pazhydayeva.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import by.itacademy.pazhydayeva.utils.Util;

import static by.itacademy.pazhydayeva.user.UserFactory.getRegisteredKvitkiUser;

@Tag("UI")
@Tag("Login")
public class LoginUiTest extends BaseTest {

    private static final String WRONG_CREDENTIALS_ERROR_MSG = "Электронная почта или пароль недействительны. Система была обновлена, " +
            "и в связи с этим мы перешли на вход по электронной почте.";
    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private LoginService loginService = new LoginService(homePage, loginPage);
    private User kvitkiUser = getRegisteredKvitkiUser();

    @Test
    @DisplayName("Login tab rendering: check title")
    public void testLoginTabOpening() {
        homePage.openLoginForm();
        loginPage.getLoginTabTitleText();
        Assertions.assertEquals("Вход", loginPage.getLoginTabTitleText());
    }

    @Test
    @DisplayName("Success login")
    public void testLoginWithValidCredentials() {
        loginService.login(kvitkiUser);
        Assertions.assertEquals(kvitkiUser.getEmail(), loginPage.getLoggedUserEmailFromAccount());
    }

    @Test
    @DisplayName("Login with wrong password")
    public void testLoginWitIncorrectPassword() {
        loginService.login(kvitkiUser.getEmail(), Util.getRandomPassword());
        Assertions.assertEquals(WRONG_CREDENTIALS_ERROR_MSG, loginPage.getLoginErrorMsgText());
    }

    @Test
    @DisplayName("Login with wrong email")
    public void testLoginWithIncorrectEmail() {
        loginService.login(Util.getRandomEmail(), kvitkiUser.getPassword());
        Assertions.assertEquals(WRONG_CREDENTIALS_ERROR_MSG, loginPage.getLoginErrorMsgText());
    }
}

