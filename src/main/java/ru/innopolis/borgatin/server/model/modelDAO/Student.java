package ru.innopolis.borgatin.server.model.modelDAO;

import ru.innopolis.borgatin.server.model.api.IStudent;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс содержит описение сущность Студент
 */
@Entity
@Table(name = "students")
public class Student implements IStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Column
    private String lastname;
    @Column
    private String firstname;
    @Column
    private String gender;
    @Column
    private Date birthdate;

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Date getBirthdate() {
        return birthdate;
    }
    @Override
    public String getBirthdateStr() {
        return new SimpleDateFormat("dd.MM.yyyy").format(birthdate);
    }

    @Override
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public void setBirthdate(String birthday) throws ParseException {
        this.birthdate =new SimpleDateFormat("dd.MM.yyyy").parse(birthday);
    }
}
