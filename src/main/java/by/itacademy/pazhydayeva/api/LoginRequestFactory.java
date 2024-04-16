package by.itacademy.pazhydayeva.api;

import by.itacademy.pazhydayeva.user.User;

import static by.itacademy.pazhydayeva.api.Urls.BASE_URL;

public class LoginRequestFactory extends KvitkiCommonRequestFactory {

    public static final String LOGIN_URL = BASE_URL + "/customer/public/auth/login";

    public static String generateLoginBody(int centreId, User user) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"email\": \"%s\",\n" + "\"password\": \"%s\"\n" + "}", centreId, user.getEmail(), user.getPassword());
    }

    public static String generateLoginBody(int centreId, String email, String password) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"email\": \"%s\",\n" + "\"password\": \"%s\"\n" + "}", centreId, email, password);
    }

    public static String generateLoginBody(int centreId, String email) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"email\": \"%s\",\n" + "}", centreId, email);
    }

    public static String generateLoginBody(String centreId, String email, String password) {
        return String.format("{\n" + "\"centreId\": %s,\n" + "\"email\": \"%s\",\n" + "\"password\": \"%s\",\n" + "}", centreId, email, password);
    }
}
