package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.innopolis.borgatin.common.service.IUserService;
import ru.innopolis.borgatin.server.DAO.UserDAO;
import ru.innopolis.borgatin.server.model.modelDAO.User;

import java.util.Set;

/**
 * Класс реализует интерфейс IUserService,
 * предназначен для работы с сущностью User.
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    @Override
    public Set<GrantedAuthority> getUserRoles(String username) {
        return userDAO.getUserRoles(username);
    }
}
