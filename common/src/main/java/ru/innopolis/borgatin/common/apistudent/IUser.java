package ru.innopolis.borgatin.common.apistudent;

/**
 * Интерфейс описывает сущность пользователь
 */
public interface IUser {
    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);
}
