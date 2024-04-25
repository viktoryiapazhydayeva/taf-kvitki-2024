package by.itacademy.pazhydayeva.api;

import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.user.UserFactory;
import by.itacademy.pazhydayeva.utils.Util;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static by.itacademy.pazhydayeva.api.KvitkiCommonRequestFactory.CENTRE_ID;

@Tag("API")
@Tag("UpdateAccount")
public class UpdateAccountApiTest {

    private static final String INTERNAT_FORENAME = "Arnbjörg-Ąžuolas";
    private static final String INTERNAT_SURNAME = "Jóhannsson-Guðjónsson";
    private static final String INVALID_FORENAME = "Name\b \" \t \\ \f \n";

    @Test
    @DisplayName("Status 200: update First Name")
    public void testFirstNameUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        String newFirstName = Util.getRandomForename();
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, newFirstName, "Amber", kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_OK).
                body("customer.firstName", equalTo(newFirstName));
    }

    @Test
    @DisplayName("Status 200: update Last Name")
    public void testLastNameUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        String newLastName = Util.getRandomForename();
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, "Ann", newLastName, kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_OK).
                body("customer.lastName", equalTo(newLastName));
    }

    @Test
    @DisplayName("Status 200: update names")
    public void testInternationalNamesUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, INTERNAT_FORENAME, INTERNAT_SURNAME, kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_OK).
                body("customer.firstName", equalTo(INTERNAT_FORENAME)).
                body("customer.lastName", equalTo(INTERNAT_SURNAME));
    }

    @Test
    @DisplayName("Status 400: new First Name too short")
    public void testShortFirstNameUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        String newFirstName = Util.getRandomString(1);
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, newFirstName, "Amber", kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_customer_name_invalid"));
    }

    @Test
    @DisplayName("Status 400: new Last Name is empty")
    public void testEmptyFirstNameUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, "Ann", "", kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("web_customer_name_invalid"));
    }

    @Test
    @DisplayName("Status 500: new First Name is too long")
    public void testLongFirstNameUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        String newFirstName = Util.getRandomString(513);
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, newFirstName, "Amber", kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                body("message", equalTo("api_internal_server_error"));
    }

    @Test
    @DisplayName("Status 500: new Last Name is too long")
    public void testLongLastNameUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        String newLastName = Util.getRandomString(513);
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, "Ann", newLastName, kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                body("message", equalTo("api_internal_server_error"));
    }

    @Test
    @DisplayName("Status 400: invalid First Name")
    public void testInvalidFirstNameUpdate() {
        User kvitkiUser = UserFactory.getRegisteredKvitkiUser();
        given().
                headers(UpdateAccountRequestFactory.getRequestHeaders()).
                auth().oauth2(KvitkiApiService.getAccessToken()).
                queryParams(UpdateAccountRequestFactory.getLanguageQueryParams()).
                body(UpdateAccountRequestFactory.generateUpdateAccountBody(CENTRE_ID, INVALID_FORENAME, "Amber", kvitkiUser.getEmail())).
                when().
                put(UpdateAccountRequestFactory.UPDATE_ACCOUNT_URL).
                then().
                log().all().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("message", equalTo("api_request_malformed_body"));
    }
}
