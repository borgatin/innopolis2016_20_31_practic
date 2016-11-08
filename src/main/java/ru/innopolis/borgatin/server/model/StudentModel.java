package ru.innopolis.borgatin.server.model;

import ru.innopolis.borgatin.server.model.api.IStudent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ru.innopolis.borgatin.common.MainConst.CONST_DATE_FORMAT;
import static ru.innopolis.borgatin.common.MainConst.CONST_DATE_SQL_FORMAT;

/**
 * Модель сущности студент. Используется в сервисных классах и во view
 * Переопределены методы hashCode и equals класса Object для коректной работы
 * в HashMap
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
    public void setBirthdate(String birthday) throws ParseException {
        this.birthdate =new SimpleDateFormat(CONST_DATE_SQL_FORMAT).parse(birthday);
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
        return new SimpleDateFormat(CONST_DATE_FORMAT).format(birthdate);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentModel that = (StudentModel) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
