package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.borgatin.common.service.IUserService;
import ru.innopolis.borgatin.server.model.UserModel;
import ru.innopolis.borgatin.server.model.modelDAO.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс отвечает за авторизацию пользователя.
 * Переопределяет метод loadUserByUsername(String username) для
 * аутентификации из БД
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;

    @Autowired
    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userService.getUser(username);
        Set<GrantedAuthority> roles = user.getRoles();
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);

        return userDetails;
    }

}
