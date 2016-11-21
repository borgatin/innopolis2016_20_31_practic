package ru.innopolis.borgatin.common.model;

import org.springframework.security.core.GrantedAuthority;
import ru.innopolis.borgatin.common.apistudent.IUser;

import java.io.Serializable;
import java.util.Set;

/**
 * Модель пользователя. Используется в сервисных классах.
 */
public class UserModel implements IUser,Serializable {

    private String username;

    private String password;

    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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
