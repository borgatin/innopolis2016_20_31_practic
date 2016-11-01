package ru.innopolis.borgatin.server.model.api;

import java.util.Date;

/**
 * Created by avborg on 01.11.2016.
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
}
