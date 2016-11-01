package ru.innopolis.borgatin.common.service;

import ru.innopolis.borgatin.server.model.Student;
import ru.innopolis.borgatin.server.model.StudentModel;

import java.util.List;

/**
 * Created by avborg on 31.10.2016.
 */
public interface IStudentService {

    List<StudentModel> getAllStudents();

    List<StudentModel> getAllStudents(String sortType) ;

    List<StudentModel> getAllStudentsFiltered(String filter) ;

    StudentModel createStudent(StudentModel student);

    StudentModel getStudentById(int id);

    StudentModel updateStudent(StudentModel student);

    boolean deleteStudentById(int id);





}
