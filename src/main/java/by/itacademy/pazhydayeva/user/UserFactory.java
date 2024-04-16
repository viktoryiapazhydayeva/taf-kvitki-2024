package by.itacademy.pazhydayeva.user;

import by.itacademy.pazhydayeva.utils.Util;

public class UserFactory {
    public static User getRegisteredKvitkiUser(){
        return new User("ann@user.kvtk", "Pp2345678");
    }

    public static User getNewKvitkiUser(){
        return new User(Util.getRandomForename(), Util.getRandomSurname(), Util.getRandomEmail(), Util.getRandomPassword());
    }
}
