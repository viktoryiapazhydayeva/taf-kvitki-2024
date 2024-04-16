package by.itacademy.pazhydayeva.api;

import java.util.HashMap;
import java.util.Map;

public class KvitkiCommonRequestFactory {

    public static final int CENTRE_ID = 1873;

    public static Map<String, String> getLanguageQueryParams() {
        Map<String, String> params = new HashMap<>();
        params.put("language", "ru");
        return params;
    }

    public static Map<String, String> getRequestHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
