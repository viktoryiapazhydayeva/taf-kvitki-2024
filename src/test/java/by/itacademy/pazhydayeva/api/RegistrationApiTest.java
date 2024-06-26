package by.itacademy.pazhydayeva.api;

import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.user.UserFactory;
import by.itacademy.pazhydayeva.utils.Util;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static by.itacademy.pazhydayeva.api.KvitkiCommonRequestFactory.CENTRE_ID;

@Tag("API")
@Tag("Registration")
public class RegistrationApiTest {

    @Test
    @Disabled
    @DisplayName("Status 200: only Required fields are filled")
    public void testSuccessRegistration() {
        User newKvitkiUser = UserFactory.getNewKvitkiUser();
        ValidatableResponse validatableResponse = given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, newKvitkiUser)).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all();
        RegistrationResponseService.assertRegisteredUserDetails(validatableResponse, newKvitkiUser);
    }

    @Test
    @Disabled
    @DisplayName("Status 200: All fields are filled")
    public void testSuccessRegistrationFull() {
        User newKvitkiUser = UserFactory.getNewKvitkiUser();
        String phoneNumber = Util.getRandomPhoneNumber(9);
        ValidatableResponse validatableResponse = given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithAllFields(CENTRE_ID, newKvitkiUser, phoneNumber, true)).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_OK).
                body("customer.phoneNr", equalTo(phoneNumber));
        RegistrationResponseService.assertRegisteredUserDetails(validatableResponse, newKvitkiUser);
    }

    @Test
    @DisplayName("Status 400: Email in use- the same email")
    public void testRegistrationWithSameEmail() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomSurname(), kvitkiUser.getEmail(), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_customer_email_in_use"));
    }

    @Test
    @DisplayName("Status 400: First Name too short")
    public void testRegistrationWithInvalidForename() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomString(1), Util.getRandomSurname(), Util.getRandomSurname(), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_customer_name_invalid"));
    }

    @Test
    @DisplayName("Status 400: Last Name too short")
    public void testRegistrationWithInvalidSurname() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomString(1), Util.getRandomEmail(), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_customer_name_invalid"));
    }

    @Test
    @DisplayName("Status 400: Email- invalid format")
    public void testRegistrationWithInvalidEmail() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomString(7), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_customer_email_invalid"));
    }

    @Test
    @DisplayName("Status 422: weak Password")
    public void testRegistrationWithWeakPassword() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomEmail(), Util.getRandomString(8))).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY).
                body("message", equalTo("web_password_strength_does_not_meet_requirements"));
    }

    @Test
    @DisplayName("Status 422: short Password")
    public void testRegistrationWithShortPassword() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomEmail(), Util.getShortRandomPassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY).
                body("message", equalTo("web_password_strength_does_not_meet_requirements"));
    }

    @Test
    @DisplayName("Status 500: First Name too long")
    public void testRegistrationWithLongForename() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomString(600), Util.getRandomSurname(), Util.getRandomEmail(), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                body("message", equalTo("api_internal_server_error"));
    }

    @Test
    @DisplayName("Status 500: Last Name too long")
    public void testRegistrationWithLongSurname() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getLanguageQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomString(600), Util.getRandomEmail(), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                body("message", equalTo("api_internal_server_error"));
    }

    @Test
    @DisplayName("Status 400: Params are missed")
    public void testRegistrationWithoutLanguageParams() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomEmail(), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("Missing header 'language' for method parameter type [class java.lang.String]"));
    }

    @Test
    @DisplayName("Status 400: Unsupported language param.")
    public void testRegistrationWithWrongParams() {
        given().
                headers(RegistrationRequestFactory.getRequestHeaders()).
                queryParams(RegistrationRequestFactory.getInvalidQueryParams()).
                body(RegistrationRequestFactory.generateBodyWithRequiredFields(CENTRE_ID, Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomEmail(), Util.generatePassword())).
                filter(new AllureRestAssured()).
                when().
                post(RegistrationRequestFactory.REGISTRATION_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_language_not_supported"));
    }
}
