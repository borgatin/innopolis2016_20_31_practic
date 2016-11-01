package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.service.IStudentService;
import ru.innopolis.borgatin.server.DAO.StudentDAO;
import ru.innopolis.borgatin.server.model.Student;
import ru.innopolis.borgatin.server.model.StudentModel;

import java.util.ArrayList;
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
    public List<StudentModel> getAllStudents() {
        return transformStudentListToStudentModelList(studentDAO.getAllStudents());
    }

    private List<StudentModel> transformStudentListToStudentModelList(List<Student> students) {
        List<StudentModel> studentModels = new ArrayList<>();
        for (Student student: students){
            StudentModel studentModel =  transformStudentToStudentModel(student);
            studentModels.add(studentModel);
        }
        return studentModels;
    }

    private StudentModel transformStudentToStudentModel(Student student) {
        StudentModel studentModel = new StudentModel();
        studentModel.setId(student.getId());
        studentModel.setFirstname(student.getFirstname());
        studentModel.setLastname(student.getLastname());
        studentModel.setBirthdate(student.getBirthdate());
        studentModel.setGender(student.getGender());
        studentModel.setLessonsCount(studentDAO.getLessonsCount(student.getId()));
        return studentModel;
    }
    private Student transformStudentToStudentModel(StudentModel studentModel) {
        Student student = new Student();
        student.setId(studentModel.getId());
        student.setFirstname(studentModel.getFirstname());
        student.setLastname(studentModel.getLastname());
        student.setBirthdate(studentModel.getBirthdate());
        student.setGender(studentModel.getGender());
        return student;
    }

    @Override
    public StudentModel createStudent(StudentModel student) {
        return  transformStudentToStudentModel(studentDAO.create(transformStudentToStudentModel(student)));
    }

    @Override
    public StudentModel getStudentById(int id) {
        return  transformStudentToStudentModel(studentDAO.getStudentById(id));
    }

    @Override
    public StudentModel updateStudent(StudentModel student) {
        return  transformStudentToStudentModel(studentDAO.update(transformStudentToStudentModel(student)));
    }

    @Override
    public boolean deleteStudentById(int id) {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentModel> getAllStudents(String sortType) {
        if (SORT_TYPE_ASC.equals(sortType)) {
            return transformStudentListToStudentModelList(studentDAO.getAllStudentsSortByNameAsc());
        } else {
            return transformStudentListToStudentModelList(studentDAO.getAllStudentsSortByNameDesc());
        }
    }

    @Override
    public List<StudentModel> getAllStudentsFiltered(String filter) {
        return transformStudentListToStudentModelList(studentDAO.getAllStudentsFilter(filter));
    }


}
