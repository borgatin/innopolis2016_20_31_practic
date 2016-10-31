package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.IStudentService;
import ru.innopolis.borgatin.server.DAO.StudentDAO;
import ru.innopolis.borgatin.server.model.Student;

import java.util.List;

/**
 * Created by avborg on 31.10.2016.
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class StudentService implements IStudentService {

    @Autowired
    StudentDAO studentDAO;

    private final String SORT_TYPE_ASC = "asc";

    private final String SORT_TYPE_DESC = "desc";

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public Student createStudent(Student student) {
        return studentDAO.create(student);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public boolean deleteStudentById(int id) {
        return studentDAO.delete(id);
    }

    @Override
    public List<Student> getAllStudents(String sortType) {
        if (SORT_TYPE_ASC.equals(sortType)) {
            return studentDAO.getAllStudentsSortByNameAsc();
        } else {
            return studentDAO.getAllStudentsSortByNameDesc();
        }
    }

    @Override
    public List<Student> getAllStudentsFiltered(String filter) {
        return studentDAO.getAllStudentsFilter(filter);
    }


}
