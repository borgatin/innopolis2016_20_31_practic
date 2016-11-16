package ru.innopolis.borgatin.server.entity;

import ru.innopolis.borgatin.common.apistudent.IUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Класс содержит описение сущности Пользователь. Используется в DAO.
 */
@Entity
@Table(name = "Users")
public class User extends AbstractEntityVersion implements IUser, Serializable {

    @Id
    @Column
    private String username;

    @Column(name = "pass")
    private String password;

    public Set<UserRole> getUserRole() {
        return userRoles;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRoles = userRole;
    }
    @ManyToMany(targetEntity = UserRole.class, fetch = FetchType.EAGER)
    @JoinTable(name="users_userRoles",
            joinColumns={@JoinColumn(name="username")},
            inverseJoinColumns={@JoinColumn(name="id_role")})
    private Set<UserRole> userRoles;

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


}
