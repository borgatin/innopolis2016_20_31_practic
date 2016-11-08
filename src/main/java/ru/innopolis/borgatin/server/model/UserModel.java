package ru.innopolis.borgatin.server.model;

import org.springframework.security.core.GrantedAuthority;
import ru.innopolis.borgatin.server.model.api.IUser;

import javax.persistence.Column;
import java.util.Set;

/**
 * Модель пользователя. Используется в сервисных классах.
 */
public class UserModel implements IUser {

    private String username;

    private String password;

    private Set<GrantedAuthority> roles;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<GrantedAuthority> roles) {
        this.roles = roles;
    }
}
