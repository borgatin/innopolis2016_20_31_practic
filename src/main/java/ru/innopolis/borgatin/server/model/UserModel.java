package ru.innopolis.borgatin.server.model;

import ru.innopolis.borgatin.server.model.api.IUser;

import javax.persistence.Column;

/**
 * Created by avborg on 07.11.2016.
 */
public class UserModel implements IUser {

    private String username;

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
