package by.itacademy.pazhydayeva.api;

import by.itacademy.pazhydayeva.user.User;

import java.util.HashMap;
import java.util.Map;

import static by.itacademy.pazhydayeva.api.Urls.BASE_URL;

public class RegistrationRequestFactory extends KvitkiCommonRequestFactory {

    public static final String REGISTRATION_URL = BASE_URL + "/customer/public/customer";

    public static Map<String, String> getInvalidQueryParams() {
        Map<String, String> params = new HashMap<>();
        params.put("language", "kaz");
        return params;
    }

    public static String generateBodyWithRequiredFields(int centreId, User user) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"firstName\": \"%s\",\n" + "\"lastName\": \"%s\",\n" + "\"email\": \"%s\",\n" + "\"password\": \"%s\"\n" + "}", centreId, user.getForename(), user.getSurname(), user.getEmail(), user.getPassword());
    }

    public static String generateBodyWithRequiredFields(int centreId, String firstName, String lastName, String email, String password) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"firstName\": \"%s\",\n" + "\"lastName\": \"%s\",\n" + "\"email\": \"%s\",\n" + "\"password\": \"%s\"\n" + "}", centreId, firstName, lastName, email, password);
    }

    public static String generateBodyWithAllFields(int centreId, User user, String phoneNr, boolean newsmail) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"firstName\": \"%s\",\n" + "\"lastName\": \"%s\",\n" + "\"email\": \"%s\",\n" + "\"password\": \"%s\",\n" + "\"phoneNr\": \"%s\",\n" + "\"newsmail\": \"%b\"\n" + "}", centreId, user.getForename(), user.getSurname(), user.getEmail(), user.getPassword(), phoneNr, newsmail);
    }

    public static String generateBodyWithAllFields(int centreId, String firstName, String lastName, String email, String password, String phoneNr, boolean newsmail) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"firstName\": \"%s\",\n" + "\"lastName\": \"%s\",\n" + "\"email\": \"%s\",\n" + "\"password\": \"%s\",\n" + "\"phoneNr\": \"%s\",\n" + "\"newsmail\": \"%b\"\n" + "}", centreId, firstName, lastName, email, password, phoneNr, newsmail);
    }
}
