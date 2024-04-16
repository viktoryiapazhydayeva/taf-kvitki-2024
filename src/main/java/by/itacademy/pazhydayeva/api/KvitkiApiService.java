package by.itacademy.pazhydayeva.api;

import by.itacademy.pazhydayeva.user.User;
import by.itacademy.pazhydayeva.user.UserFactory;

import static io.restassured.RestAssured.given;
import static by.itacademy.pazhydayeva.api.KvitkiCommonRequestFactory.CENTRE_ID;

public class KvitkiApiService {

    public static final User kvitkiUser = UserFactory.getRegisteredKvitkiUser();

    public static String getAccessToken() {
        String accessToken = given().
                headers(LoginRequestFactory.getRequestHeaders()).
                body(LoginRequestFactory.generateLoginBody(CENTRE_ID, kvitkiUser)).
                when().
                post(LoginRequestFactory.LOGIN_URL).
                then().extract().response().path("accessToken");
        return accessToken;
    }
}
