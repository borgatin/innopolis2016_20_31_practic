package ru.innopolis.borgatin.server.model.api;

import java.text.ParseException;
import java.util.Date;

/**
 * Интерфейс описывает сущность студент
 */
public interface IStudent {
    int getId();

    void setId(int id);

    String getGender();

    void setGender(String gender);

    Date getBirthdate();

    String getBirthdateStr();

    void setBirthdate(Date birthdate);

    String getLastname();

    void setLastname(String lastname);

    String getFirstname();

    void setFirstname(String firstname);

    void setBirthdate(String birthday) throws ParseException;
}
