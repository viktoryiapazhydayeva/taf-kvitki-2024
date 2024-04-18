package by.itacademy.pazhydayeva.user;

public class User {

    private String email;
    private String password;
    private String forename;
    private String surname;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String forename, String surname, String email, String password) {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
