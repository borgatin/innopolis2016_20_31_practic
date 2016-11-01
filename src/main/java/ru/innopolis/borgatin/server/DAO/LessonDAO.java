package ru.innopolis.borgatin.server.DAO;


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

public abstract class LessonDAO {
    private Connection connection;

    protected LessonDAO() throws SQLException {
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/app");
            connection = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public abstract List<Lesson> getAllLessons();

    public abstract List<Lesson> getAllLessonsFilter(String filter);

    public abstract List<Lesson> getAllLessonsSortByNameAsc();

    public abstract List<Lesson> getAllLessonsSortByNameDesc();

    public abstract Lesson getLessonById(int id);

    public abstract Lesson updateLesson(Lesson lesson);

    public abstract boolean deleteLesson(int id);

    public abstract Lesson createLesson(Lesson lesson);

    public Connection getConnection() {
        return connection;
    }

    // Возвращения экземпляра Connection в пул соединений
    public void returnConnectionInPool() throws SQLException {
        connection.close();
    }


    public abstract List<Integer> getStudentsIDByLessonID(int id);

    public abstract void addStudentOnLesson(int id, int studentId);

    public abstract void deleteStudentFromLesson(int id, int studentId);
}
