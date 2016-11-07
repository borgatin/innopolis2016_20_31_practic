package ru.innopolis.borgatin.common.service;

import org.springframework.security.core.GrantedAuthority;
import ru.innopolis.borgatin.server.model.User;

import java.util.Set;

/**
 * Класс предназначен для работы с сущнотью пользователь
 */
public interface IUserService {

    /**
     * Метод предназначен для получения пользователя по его имени
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * Получение множества GrantedAuthority (ролей) пользователя
     * по его имени
     * @param username имя пользователя
     * @return множество GrantedAuthority
     */
    Set<GrantedAuthority> getUserRoles(String username);
}
