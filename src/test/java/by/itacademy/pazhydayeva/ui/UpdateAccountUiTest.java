package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.UpdateAccountPage;
import by.itacademy.pazhydayeva.ui.steps.LoginSteps;
import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static by.itacademy.pazhydayeva.ui.pages.UpdateAccountPage.*;
import static by.itacademy.pazhydayeva.user.UserFactory.getRegisteredKvitkiUser;

@Tag("UI")
@Tag("UpdateAccount")
public class UpdateAccountUiTest extends BaseTest {
    @Test
    @DisplayName("Update First Name: success")
    public void testFirstNameUpdate() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        UpdateAccountPage updateAccountPage = new UpdateAccountPage();
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeForenameValue();
        updateAccountPage.enterNewForename(Util.getRandomForename());
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }

    @Test
    @DisplayName("Remove existing First Name")
    public void testEmptyForenameFieldValidation() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        UpdateAccountPage updateAccountPage = new UpdateAccountPage();
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeForenameValue();
        updateAccountPage.clickOnSurnameField();
        Assertions.assertEquals(EMPTY_FORENAME_VALIDATION_ERROR, updateAccountPage.getEmptyFieldErrorText());
    }

    @Test
    @DisplayName("Update Last Name: success")
    public void testLastNameUpdate() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        UpdateAccountPage updateAccountPage = new UpdateAccountPage();
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeSurnameValue();
        updateAccountPage.enterNewSurname(Util.getRandomSurname());
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }

    @Test
    @DisplayName("Remove existing Last Name")
    public void testEmptySurnameFieldValidation() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        UpdateAccountPage updateAccountPage = new UpdateAccountPage();
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.removeSurnameValue();
        updateAccountPage.clickOnForenameField();
        Assertions.assertEquals(EMPTY_SURNAME_VALIDATION_ERROR, updateAccountPage.getEmptyFieldErrorText());
    }

    @Test
    @DisplayName("Update Country field")
    public void testCountryUpdate() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        UpdateAccountPage updateAccountPage = new UpdateAccountPage();
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.expandCountryDdl();
        updateAccountPage.selectNewCountry();
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }

    @Test
    @DisplayName("Update Country field")
    public void testCountryUpdateSec() {
        User kvitkiUser = getRegisteredKvitkiUser();
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.login(kvitkiUser.getEmail(), kvitkiUser.getPassword());
        UpdateAccountPage updateAccountPage = new UpdateAccountPage();
        updateAccountPage.clickUpdateContactDetailsBtn();
        updateAccountPage.expandCountryDdl();
        updateAccountPage.searchCountry("латвия");
        updateAccountPage.selectSearchedCountryItem();
        updateAccountPage.clickConfirmBtn();
        Assertions.assertEquals(SUCCESS_CONFIRMATION_MSG, updateAccountPage.getConfirmationMsgText());
    }
}

