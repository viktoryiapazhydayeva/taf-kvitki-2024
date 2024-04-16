package by.itacademy.pazhydayeva.utils;

public class TestChecksToBeDeleted {

    public static void main(String[] args) {

        System.out.println(Util.getRandomForename());
        System.out.println(Util.getRandomString(5));
        System.out.println();

        System.out.println(Util.getRandomSurname());
        System.out.println(Util.getRandomString(4));
        System.out.println();

        System.out.println(Util.getRandomEmail());
        System.out.println();


        System.out.println("MY PASSWORD METHOD");
        System.out.println(Util.getRandomPassword(8, true, true, true));
        System.out.println(Util.getRandomPassword(8, false, true, true));
        System.out.println(Util.getRandomPassword(5, true, false, true));
        System.out.println(Util.getRandomPassword(3, true, true, false));
        System.out.println(Util.getRandomPassword());
        System.out.println(Util.getRandomPassword().toUpperCase());
        System.out.println();

        System.out.println(Util.getRandomPhoneNumber(9));
        System.out.println();

        System.out.println(Util.getRandomPasswordRegex(8));





    }
}
