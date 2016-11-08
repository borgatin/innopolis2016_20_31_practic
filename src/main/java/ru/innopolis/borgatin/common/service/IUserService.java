package ru.innopolis.borgatin.common.service;

import org.springframework.security.core.GrantedAuthority;
import ru.innopolis.borgatin.server.model.UserModel;
import ru.innopolis.borgatin.server.model.modelDAO.User;

import java.util.Set;

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
