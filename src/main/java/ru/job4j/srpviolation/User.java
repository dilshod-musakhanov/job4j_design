package ru.job4j.srpviolation;

import java.util.Calendar;
import java.util.Objects;

/**
 * Нарушение SRP:
 * 1.добавление логики проверки логина и пароля в isValidLoginPassword()
 * 2.создание объекта внутри класса в createUser()
 */

public class User {

    private String login;
    private String password;
    private Calendar time = Calendar.getInstance();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Calendar time) {
        this.login = login;
        this.password = password;
        this.time = time;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public boolean isValidLoginPassword() {
        return true;
    }

    public User createUser(String login, String password, Calendar time) {
        return new User(login, password, time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }
}
