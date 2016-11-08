package ru.innopolis.borgatin.server.model.modelDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Класс содержит описание сущности Lesson. Используется в DAO.
 * Переопределены методы hashCode и equals класса Object для коректной работы
 * в HashMap
 */
@Entity
@Table(name = SQL_TABLE_LESSON)
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
    @Column(name = SQL_FIELD_LESSON_DATE)
    private Date date;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @ManyToMany(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinTable(name="student_lesson",
            joinColumns={@JoinColumn(name= CONST_ID)},
            inverseJoinColumns={@JoinColumn(name=SQL_FIELD_STUDENT_ID)})
    private Set<Student> students;


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

        Lesson lesson = (Lesson) o;

        return id == lesson.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
