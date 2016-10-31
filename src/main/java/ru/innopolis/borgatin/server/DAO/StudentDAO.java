package ru.innopolis.borgatin.server.DAO;


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

public abstract class StudentDAO {
    private Connection connection;

    protected StudentDAO() throws SQLException {
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/app");
            connection = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public abstract List<Student> getAllStudents();

    public abstract List<Student> getAllStudentsFilter(String filter);

    public abstract List<Student> getAllStudentsSortByNameAsc();

    public abstract List<Student> getAllStudentsSortByNameDesc();

    public abstract Student getStudentById(int id);

    public abstract Student update(Student entity);

    public abstract boolean delete(int id);

    public abstract Student create(Student student);

    public Connection getConnection() {
        return connection;
    }

    // Возвращения экземпляра Connection в пул соединений
    public void returnConnectionInPool() throws SQLException {
        connection.close();
    }


}
