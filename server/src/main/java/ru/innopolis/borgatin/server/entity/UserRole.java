package ru.innopolis.borgatin.server.entity;

//import javax.persistence.*;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс содержит описение сущности Роль пользователь. Используется в DAO и
 * модели для определения прав пользователя на осуществление действия.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private int id;
    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
