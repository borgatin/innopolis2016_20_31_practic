package ru.innopolis.borgatin.server.model.modelDAO;

import ru.innopolis.borgatin.server.model.api.IStudent;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Класс содержит описение сущность Студент. Используется в DAO.
 * Переопределены методы hashCode и equals класса Object для коректной работы
 * в HashMap
 */
@Entity
@Table(name = SQL_TABLE_STUDENT)
public class Student implements IStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Column
    private String lastname;
    @Column
    private String firstname;
    @Column
    private String gender;
    @Column
    private Date birthdate;


    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }


    @ManyToMany(targetEntity = Lesson.class, fetch = FetchType.LAZY)
    @JoinTable(name="student_lesson",
            joinColumns={@JoinColumn(name=SQL_FIELD_STUDENT_ID)},
            inverseJoinColumns={@JoinColumn(name= CONST_ID)})
    private Set<Lesson> lessons;

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
        this.birthdate =new SimpleDateFormat(CONST_DATE_FORMAT).parse(birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
