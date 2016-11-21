package ru.innopolis.borgatin.common.model;

import ru.innopolis.borgatin.common.apistudent.ILesson;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Модель сущности урок. Используется в сервисных классах и во view.
 * Переопределены методы hashCode и equals класса Object для коректной работы
 * в HashMap
 */
public class LessonModel implements ILesson,Serializable {
    private int id;
    private String topic;
    private String description;
    private int duration;
    private Date date;
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonModel that = (LessonModel) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }


}
