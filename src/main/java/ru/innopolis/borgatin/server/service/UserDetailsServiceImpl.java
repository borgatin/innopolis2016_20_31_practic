package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.borgatin.common.service.IUserService;
import ru.innopolis.borgatin.server.model.User;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;

    @Autowired
    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        Set<GrantedAuthority> roles = userService.getUserRoles(user.getUsername());

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);

        return userDetails;
    }

}
