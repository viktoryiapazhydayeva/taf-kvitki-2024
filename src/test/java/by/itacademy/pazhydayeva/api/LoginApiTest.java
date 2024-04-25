package by.itacademy.pazhydayeva.api;

import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.user.UserFactory;
import by.itacademy.pazhydayeva.utils.Util;
import io.qameta.allure.restassured.AllureRestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static by.itacademy.pazhydayeva.api.KvitkiCommonRequestFactory.CENTRE_ID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Tag("API")
@Tag("Login")
public class LoginApiTest {

    @Test
    @DisplayName("Status 200: Success login")
    public void testLogin() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, kvitkiUser)).
                filter(new AllureRestAssured()).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_OK).
                body("accessToken", notNullValue()).
                body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Status 404: Wrong URL")
    public void testLoginWrongUrl() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, kvitkiUser)).
                when().
                post("https://store.piletilevi.ee/web-api/customer/public/auth/loggin").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND).
                body("message", equalTo("api_page_not_found"));
    }

    @Test
    @DisplayName("Status 401: Unauthorized- wrong password")
    public void testLoginWithWrongPassword() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, kvitkiUser.getEmail(), Util.generatePassword())).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_UNAUTHORIZED).
                body("message", equalTo("web_login_email_or_password_invalid"));
    }

    @Test
    @DisplayName("Status 401: Unauthorized- wrong email")
    public void testLoginWithWrongEmail() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, Util.getRandomEmail(), kvitkiUser.getEmail())).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_UNAUTHORIZED).
                body("message", equalTo("web_login_email_or_password_invalid"));
    }

    @Test
    @DisplayName("Status 400: Wrong CentreID")
    public void testLoginWithWrongCentreId() {
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(1020, Util.getRandomEmail(), Util.generatePassword())).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_centre_id_invalid"));
    }

    @Test
    @DisplayName("Status 400: Empty Email")
    public void testLoginWithInvalidEmail() {
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, "", Util.generatePassword())).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_login_email_missing"));
    }

    @Test
    @DisplayName("Status 400: Empty Password")
    public void testLoginWithInvalidPassword() {
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, Util.getRandomEmail(), "")).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_login_password_missing"));
    }

    @Test
    @DisplayName("Status 400: Malformed Body- centreId String")
    public void testLoginWithInvalidCentreId() {
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody("1873", Util.getRandomEmail(), Util.generatePassword())).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("api_request_malformed_body"));
    }

    @Test
    @DisplayName("Status 400: Malformed Body- no password in body")
    public void testLoginWithoutPassword() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, kvitkiUser.getEmail())).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("api_request_malformed_body"));
    }
}
