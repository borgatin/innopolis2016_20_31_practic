package ru.innopolis.borgatin.server.DAO;


import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Student;

import java.util.List;

/**
 * Класс предназначен для получения объектов Connection из пула,
 * описывает основную логику для работы с БД для конкретных сущностей.
 * От него необходимо наследоваться, чтобы реализовать работу с БД для сущности.
 */
@Component
public interface StudentDAO {

     List<StudentModel> getAllStudents();

    List<StudentModel> getAllStudentsFilter(String filter);

    List<StudentModel> getAllStudentsSortByNameAsc();

    List<StudentModel> getAllStudentsSortByNameDesc();

    StudentModel getStudentById(int id);

    StudentModel update(StudentModel student);

    void delete(int id);


    int getLessonsCount(int id) ;
}
