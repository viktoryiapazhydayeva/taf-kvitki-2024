package by.itacademy.pazhydayeva.api;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HomePageApiTest {

    @Test
    @DisplayName("Status 200: open Home Page")
    public void testHomePage(){
        given().
        when().
                get("https://www.kvitki.by").
        then().
                statusCode(HttpStatus.SC_OK);
    }
}
