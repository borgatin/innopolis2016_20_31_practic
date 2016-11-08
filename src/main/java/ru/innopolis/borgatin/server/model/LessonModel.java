package ru.innopolis.borgatin.server.model;

import ru.innopolis.borgatin.server.model.api.ILesson;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by avborg on 07.11.2016.
 */
public class LessonModel implements ILesson {
    private int id;
    private String topic;
    private String description;
    private int duration;
    private Date date;

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
