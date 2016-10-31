package ru.innopolis.borgatin.server.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by avborg on 31.10.2016.
 */
public class Student {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String lastname;
    private String firstname;
    private String gender;
    private Date birthdate;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }
    public String getBirthdateStr() {
        return new SimpleDateFormat("dd.MM.yyyy").format(birthdate);
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
