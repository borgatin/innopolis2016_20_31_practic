package ru.innopolis.borgatin.server.model.modelDAO;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс содержит описание сущности Lesson
 */
@Entity
@Table(name = "lesson")
public class Lesson implements ru.innopolis.borgatin.server.model.api.ILesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private String topic;
    @Column
    private String description;
    @Column
    private int duration;
    @Column
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
}
