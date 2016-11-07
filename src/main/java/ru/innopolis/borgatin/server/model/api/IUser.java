package ru.innopolis.borgatin.server.model.api;

/**
 * Created by avborg on 07.11.2016.
 */
public interface IUser {
    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);
}
