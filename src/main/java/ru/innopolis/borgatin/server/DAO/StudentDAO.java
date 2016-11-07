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

public interface StudentDAO {

     List<Student> getAllStudents();

    List<Student> getAllStudentsFilter(String filter);

    List<Student> getAllStudentsSortByNameAsc();

    List<Student> getAllStudentsSortByNameDesc();

    Student getStudentById(int id);

    Student update(Student entity);

    boolean delete(int id);

    Student create(Student student);

    int getLessonsCount(int id) ;
}
