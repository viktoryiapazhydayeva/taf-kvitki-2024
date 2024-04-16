package by.itacademy.pazhydayeva.api;

import static by.itacademy.pazhydayeva.api.Urls.BASE_URL;

public class UpdateAccountRequestFactory extends KvitkiCommonRequestFactory {

    public static final String UPDATE_ACCOUNT_URL = BASE_URL + "/customer/customer";

    public static String generateUpdateAccountBody(int centreId, String firstName, String lastName, String email) {
        return String.format("{\n" + "\"centreId\": %d,\n" + "\"firstName\": \"%s\",\n" + "\"lastName\": \"%s\",\n" + "\"email\": \"%s\"\n" + "}", centreId, firstName, lastName, email);
    }
}
