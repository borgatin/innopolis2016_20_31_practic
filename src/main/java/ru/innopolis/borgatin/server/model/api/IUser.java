package ru.innopolis.borgatin.server.model.api;

/**
 * Интерфейс описывает сущность пользователь
 */
public interface IUser {
    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);
}
