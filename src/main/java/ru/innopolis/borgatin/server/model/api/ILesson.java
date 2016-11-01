package ru.innopolis.borgatin.server.model.api;

import java.util.Date;

/**
 * Created by avborg on 01.11.2016.
 */
public interface ILesson {
    int getId();

    void setId(int id);

    String getTopic();

    void setTopic(String topic);

    String getDescription();

    void setDescription(String description);

    int getDuration();

    void setDuration(int duration);

    Date getDate();

    void setDate(Date date);
}
