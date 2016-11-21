package ru.innopolis.borgatin.common.apistudent;

import java.io.Serializable;

/**
 * Интерфейс описывает сущность пользователь
 */
public interface IUser extends Serializable {
    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);
}
