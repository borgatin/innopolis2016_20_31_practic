package ru.innopolis.borgatin.server.model;

/**
 * Класс предназначен для работы с сущностью Пользователь
 */
public class User {


    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
