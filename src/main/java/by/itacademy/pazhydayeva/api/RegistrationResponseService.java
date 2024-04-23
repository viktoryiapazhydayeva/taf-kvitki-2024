package by.itacademy.pazhydayeva.api;

import by.itacademy.pazhydayeva.user.User;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RegistrationResponseService {

    public static void assertRegisteredUserDetails(ValidatableResponse validatableResponse, User user) {
        validatableResponse.
                statusCode(HttpStatus.SC_OK).
                body("customer.email", equalTo(user.getEmail())).
                body("customer.firstName", equalTo(user.getForename())).
                body("customer.lastName", equalTo(user.getSurname())).
                body("tokens.accessToken", notNullValue()).
                body("tokens.refreshToken", notNullValue());
    }
}
