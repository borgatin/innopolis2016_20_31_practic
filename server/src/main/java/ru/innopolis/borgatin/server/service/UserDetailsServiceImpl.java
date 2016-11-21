package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.borgatin.common.model.UserModel;
import ru.innopolis.borgatin.common.service.IUserService;
//import ru.innopolis.borgatin.common.service.IUserService;
//import ru.innopolis.borgatin.common.model.UserModel;

import java.io.Serializable;
import java.util.Set;

/**
 * Класс отвечает за авторизацию пользователя.
 * Переопределяет метод loadUserByUsername(String username) для
 * аутентификации из БД
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService , Serializable {

    private final IUserService userService;

    @Autowired
    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userService.getUser(username);
        if (user==null){
            throw new UsernameNotFoundException("Неверное имя пользователя или пароль");
        }
        Set<GrantedAuthority> roles = user.getRoles();
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);

        return userDetails;
    }

}
