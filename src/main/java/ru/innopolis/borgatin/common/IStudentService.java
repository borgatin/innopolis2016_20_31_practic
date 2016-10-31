package ru.innopolis.borgatin.common;

import ru.innopolis.borgatin.server.model.Student;

import java.util.List;

/**
 * Created by avborg on 31.10.2016.
 */
public interface IStudentService {

    List<Student> getAllStudents();

    List<Student> getAllStudents(String sortType) ;

    List<Student> getAllStudentsFiltered(String filter) ;

    Student createStudent(Student student);

    Student getStudentById(int id);

    Student updateStudent(Student student);

    boolean deleteStudentById(int id);





}
