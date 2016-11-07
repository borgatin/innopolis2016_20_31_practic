package ru.innopolis.borgatin.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Класс реализует абстрактный класс UserDAO.
 * Необходим для получения информации о сущности User из базы данных.
 * Connection получаем через метод getConnection
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class UserDAOImpl extends EntityDAO implements UserDAO{


    private static final String QUERY_SELECT_USER = "SELECT * FROM users WHERE username =?";
    private static final String QUERY_SELECT_USER_ROLES = "select DISTINCT username, user_role from user_roles where username = ?";

    @Autowired
    UserDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public User getUser(String username) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_USER);
        ) {
            statement.setString(CONST_ONE, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUsername(resultSet.getString(SQL_FIELD_USERNAME));
                    user.setPassword(resultSet.getString(SQL_FIELD_PASSWORD));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Set<GrantedAuthority> getUserRoles(String username) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_USER_ROLES);
        ) {
            statement.setString(CONST_ONE, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                while (resultSet.next()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(resultSet.getString(SQL_FIELD_USER_ROLE)) );
                }
                return grantedAuthorities;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
