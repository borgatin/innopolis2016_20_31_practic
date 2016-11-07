package ru.innopolis.borgatin.server.DAO;

import org.springframework.security.core.GrantedAuthority;
import ru.innopolis.borgatin.server.model.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * Класс необходим для получения информации о сущности User из базы данных
 */
public interface UserDAO {


    /**
     * Метод необходим для получения сущности пользователь
     * по имени пользователя
     * @param username имя пользователя типа данных String
     * @return объект User, соответствующий имени пользователя username
     */
    User getUser(String username) ;

    /**
     * Метод необходим для получения ролей (GrantedAuthority) пользователя
     * по его имени
     * @param username - имя пользователя
     * @return множество GrantedAuthority пользователя
     */
    Set<GrantedAuthority> getUserRoles(String username) ;
}
