package ru.innopolis.borgatin.server.model.modelDAO;

import ru.innopolis.borgatin.server.model.api.IUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс предназначен для работы с сущностью Пользователь
 */
//@Entity
public class User implements IUser {

//    @Column
    private String username;

//    @Column
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
