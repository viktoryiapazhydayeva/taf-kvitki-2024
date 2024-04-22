package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import by.itacademy.pazhydayeva.ui.pages.LoginPage;
import by.itacademy.pazhydayeva.ui.pages.UpdateAccountPage;
import by.itacademy.pazhydayeva.ui.services.LoginService;
import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static by.itacademy.pazhydayeva.user.UserFactory.getRegisteredKvitkiUser;

@Tag("UI")
@Tag("UpdateAccount")
public class UpdateAccountUiTest extends BaseTest {

    private static final String EMPTY_FORENAME_VALIDATION_ERROR = "Пожалуйста, заполните поле (Имя)";
    private static final String EMPTY_SURNAME_VALIDATION_ERROR = "Пожалуйста, заполните поле (Фамилия)";
    private static final String SUCCESS_CONFIRMATION_MSG = "Данные сохранены";
    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private UpdateAccountPage updateAccountPage = new UpdateAccountPage();
    private User kvitkiUser = getRegisteredKvitkiUser();
    private LoginService loginService = new LoginService(homePage, loginPage);

    @Test
    @DisplayName("Update First Name: success")
    public void testFirstNameUpdate() {
        loginService.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeForenameValue();
        updateAccountPage.enterNewForename(Util.getRandomForename());
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }

    @Test
    @DisplayName("Remove existing First Name")
    public void testEmptyForenameFieldValidation() {
        loginService.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeForenameValue();
        updateAccountPage.clickOnSurnameField();
        Assertions.assertEquals(EMPTY_FORENAME_VALIDATION_ERROR, updateAccountPage.getEmptyFieldErrorText());
    }

    @Test
    @DisplayName("Update Last Name: success")
    public void testLastNameUpdate() {
        loginService.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeSurnameValue();
        updateAccountPage.enterNewSurname(Util.getRandomSurname());
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }

    @Test
    @DisplayName("Remove existing Last Name")
    public void testEmptySurnameFieldValidation() {
        loginService.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeSurnameValue();
        updateAccountPage.clickOnForenameField();
        Assertions.assertEquals(EMPTY_SURNAME_VALIDATION_ERROR, updateAccountPage.getEmptyFieldErrorText());
    }

    @Test
    @DisplayName("Update Country field")
    public void testCountryUpdateOne() {
        loginService.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.expandCountryDdl();
        updateAccountPage.selectNewCountry();
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }

    @Test
    @DisplayName("Update Country field")
    public void testCountryUpdate() {
        loginService.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.expandCountryDdl();
        updateAccountPage.searchCountry("латвия");
        updateAccountPage.selectSearchedCountryItem();
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }
}

