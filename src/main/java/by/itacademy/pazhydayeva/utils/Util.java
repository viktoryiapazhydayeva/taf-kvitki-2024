package by.itacademy.pazhydayeva.utils;

import com.github.javafaker.Faker;
import org.apache.commons.text.RandomStringGenerator;

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

    public static String getRandomPassword(int passwordLength, boolean includeUppercase, boolean includeSpecial, boolean includeDigit) {
        Faker faker = new Faker();
        return faker.internet().password(passwordLength, passwordLength + 1, includeUppercase, includeSpecial, includeDigit);
    }

    public static String getShortRandomPassword() {
        return getRandomPassword(5, true, true, true);
    }

    public static String generatePassword(int length) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(Character::isLetterOrDigit)
                .build();
        return generator.generate(length);
    }

    public static String generatePassword() {
        return generatePassword(13);
    }

    public static String getRandomPhoneNumber(int phoneNumberLength) {
        Faker faker = new Faker();
        Random random = new Random();
        return MOBILE_CODES_BY.get(random.nextInt(MOBILE_CODES_BY.size())) + faker.phoneNumber().subscriberNumber(phoneNumberLength - 2);
    }
}
