package ru.innopolis.borgatin.common.service;

import org.springframework.security.core.GrantedAuthority;
import ru.innopolis.borgatin.common.model.UserModel;

/**
 * Класс предназначен для работы с сущнотью пользователь
 */
public interface IUserService {

    /**
     * Метод предназначен для получения пользователя по его имени
     * @param username имя пользователя
     * @return сущность UserModel, соответствующая имени пользователя
     */
    UserModel getUser(String username);

}
