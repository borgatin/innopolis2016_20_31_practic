package ru.innopolis.borgatin.common.apistudent;

import java.util.Date;

/**
 * Интерфейс описывает сущность урок
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
