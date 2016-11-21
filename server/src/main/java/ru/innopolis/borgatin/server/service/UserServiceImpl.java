package ru.innopolis.borgatin.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import ru.innopolis.borgatin.common.service.IUserService;
import ru.innopolis.borgatin.common.model.UserModel;
import ru.innopolis.borgatin.common.service.IUserService;
import ru.innopolis.borgatin.server.entity.User;
import ru.innopolis.borgatin.server.mapping.UsersMapping;
import ru.innopolis.borgatin.server.repositories.UserRepository;

import java.io.Serializable;
//import ru.innopolis.borgatin.common.model.UserModel;

/**
 * Класс реализует интерфейс IUserService,
 * предназначен для работы с сущностью User.
 */
@Service
public class UserServiceImpl implements IUserService, Serializable {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersMapping usersMapping;

    public UserServiceImpl() {
    }


    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUsersMapping(UsersMapping usersMapping) {
        this.usersMapping = usersMapping;
    }


    @Override
    public UserModel getUser(String username) {
        logger.debug("get user");
        User user = userRepository.findOne(username);
        logger.debug("after get user");

        return usersMapping.makeMapping(user);
    }

}
