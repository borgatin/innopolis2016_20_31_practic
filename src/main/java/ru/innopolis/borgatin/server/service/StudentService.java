package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.service.IStudentService;
import ru.innopolis.borgatin.server.DAO.StudentDAO;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.enums.SortType;

import static ru.innopolis.borgatin.server.model.enums.SortType.*;

import java.util.List;

/**
 * Класс реализует интерфейс IStudentService.
 * Предназначен дл работы с сущностью StudentModel.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class StudentService implements IStudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    @Override
    public List<StudentModel> getAllStudents() {
        return studentDAO.getAllStudents();
    }


    @Override
    public StudentModel createStudent(StudentModel student) {
        return  (studentDAO.update(student));
    }

    @Override
    public StudentModel getStudentById(int id) {
        return  (studentDAO.getStudentById(id));
    }

    @Override
    public StudentModel updateStudent(StudentModel student) {
        return  (studentDAO.update((student)));
    }

    @Override
    public void deleteStudentById(int id) {
        studentDAO.deleteStudent(id);
    }

    @Override
    public List<StudentModel> getAllStudents(SortType sortType) {
        if (ASC == sortType) {
            return (studentDAO.getAllStudentsSortByNameAsc());
        } else {
            return (studentDAO.getAllStudentsSortByNameDesc());
        }
    }

    @Override
    public List<StudentModel> getAllStudentsFiltered(String filter) {
        return (studentDAO.getAllStudentsFilter(filter));
    }


}
