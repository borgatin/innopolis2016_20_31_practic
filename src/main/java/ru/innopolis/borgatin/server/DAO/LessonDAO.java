package ru.innopolis.borgatin.server.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.Lesson;
import ru.innopolis.borgatin.server.model.Student;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс предназначен для получения объектов Connection из пула,
 * описывает основную логику для работы с БД для конкретных сущностей.
 * От него необходимо наследоваться, чтобы реализовать работу с БД для сущности.
 */
@Component
public interface LessonDAO{



    List<Lesson> getAllLessons();

    List<Lesson> getAllLessonsFilter(String filter);

    List<Lesson> getAllLessonsSortByNameAsc();

    List<Lesson> getAllLessonsSortByNameDesc();

    Lesson getLessonById(int id);

    Lesson updateLesson(Lesson lesson);

    boolean deleteLesson(int id);

    Lesson createLesson(Lesson lesson);

    List<Integer> getStudentsIDByLessonID(int id);

    void addStudentOnLesson(int id, int studentId);

    void deleteStudentFromLesson(int id, int studentId);
}
