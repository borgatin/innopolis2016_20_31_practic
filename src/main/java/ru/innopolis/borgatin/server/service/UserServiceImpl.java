package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.borgatin.common.service.IUserService;
import ru.innopolis.borgatin.repositories.UserRepository;
import ru.innopolis.borgatin.server.mapping.UsersMapping;
import ru.innopolis.borgatin.server.model.UserModel;

/**
 * Класс реализует интерфейс IUserService,
 * предназначен для работы с сущностью User.
 */
@Service
public class UserServiceImpl implements IUserService {

/*
    private final UserDAO userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userRepository) {
        this.userRepository = userRepository;
    }
*/
    private final UserRepository userRepository;
    private final UsersMapping usersMapping;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UsersMapping usersMapping) {
        this.userRepository = userRepository;
        this.usersMapping = usersMapping;
    }


    @Override
    public UserModel getUser(String username) {
        return usersMapping.makeMapping(userRepository.findOne(username));
    }


}
