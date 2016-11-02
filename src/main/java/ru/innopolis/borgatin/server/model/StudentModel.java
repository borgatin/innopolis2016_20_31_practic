package ru.innopolis.borgatin.server.model;

import ru.innopolis.borgatin.server.model.api.IStudent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by avborg on 01.11.2016.
 */
public class StudentModel implements IStudent {

    private int id;
    private String lastname;
    private String firstname;
    private String gender;
    private Date birthdate;
    private int lessonsCount;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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

    public int getLessonsCount() {
        return lessonsCount;
    }

    public void setLessonsCount(int lessonsCount) {
        this.lessonsCount = lessonsCount;
    }


}