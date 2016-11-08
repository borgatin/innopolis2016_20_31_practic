package ru.innopolis.borgatin.server.DAO;

import org.springframework.security.core.GrantedAuthority;
import ru.innopolis.borgatin.server.model.UserModel;
import ru.innopolis.borgatin.server.model.modelDAO.User;

import java.util.Set;

/**
 * Интерфейс описывает методы для получения сущности UserModel из базы данных
 */
public interface UserDAO {


    /**
     * Метод необходим для получения сущности пользователь
     * по имени пользователя
     * @param username имя пользователя типа данных String
     * @return объект User, соответствующий имени пользователя username
     */
    UserModel getUser(String username);

}
