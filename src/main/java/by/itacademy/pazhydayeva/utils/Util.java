package by.itacademy.pazhydayeva.utils;

import com.github.javafaker.Faker;

import java.util.*;

public class Util {

    private static final List<String> MOBILE_CODES_BY = new ArrayList<>(Arrays.asList("25", "29", "33", "44"));

    public static String getRandomString(int stringLength) {
        Faker faker = new Faker();
        return faker.lorem().characters(stringLength);
    }

    public static String getRandomForename() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String getRandomSurname() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String getRandomDomainName() {
        Faker faker = new Faker();
        return faker.internet().domainName();
    }

    public static String getRandomEmail() {
        String email = getRandomForename() + "@" + getRandomDomainName();
        return email.toLowerCase();
    }

    public static String getRandomPassword(int passwordLength, boolean includeUppercase, boolean includeSpecial, boolean includeDigit) {      // !!
        Faker faker = new Faker();
        return faker.internet().password(passwordLength, passwordLength + 1, includeUppercase, includeSpecial, includeDigit);
    }

    public static String getRandomPassword() {
        return getRandomPassword(8, true, true, true);
    }

    public static String getShortRandomPassword() {
        return getRandomPassword(5, true, true, true);
    }

    public static String getRandomPasswordRegex(int passwordLength) {                  //////////////////
        Faker faker = new Faker();
        return faker.regexify("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])");
    }

    public static String getRandomPhoneNumber(int phoneNumberLength) {
        Faker faker = new Faker();
        Random random = new Random();
        return MOBILE_CODES_BY.get(random.nextInt(MOBILE_CODES_BY.size())) + faker.phoneNumber().subscriberNumber(phoneNumberLength - 2);
    }

    public static String getPhoneNumberOne(List<String> codes, int phoneLength) {
        Faker faker = new Faker();
        String prefix = String.join("|", codes);
        String regex = String.format("(%s)[0-9]{%d}", prefix, phoneLength - 2);
        System.out.println(regex);
        return faker.regexify(regex);
    }
}
