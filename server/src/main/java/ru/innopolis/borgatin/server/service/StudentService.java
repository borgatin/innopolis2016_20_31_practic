package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
//import ru.innopolis.borgatin.common.service.IStudentService;
import ru.innopolis.borgatin.common.enums.SortType;
import ru.innopolis.borgatin.common.model.StudentModel;
import ru.innopolis.borgatin.common.service.IStudentService;
import ru.innopolis.borgatin.server.entity.Student;
import ru.innopolis.borgatin.server.mapping.StudentsMapping;
import ru.innopolis.borgatin.server.repositories.StudentRepository;

import java.io.Serializable;
import java.util.List;

import static ru.innopolis.borgatin.common.enums.SortType.ASC;

/**
 * Класс реализует интерфейс IStudentService.
 * Предназначен дл работы с сущностью StudentModel.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class StudentService implements IStudentService, Serializable {


    private StudentRepository studentRepository;
    private  StudentsMapping studentsMapping;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setStudentsMapping(StudentsMapping studentsMapping) {
        this.studentsMapping = studentsMapping;
    }

    @Override
    public List<StudentModel> getAllStudents() {
        return studentsMapping.makeMapping(studentRepository.findAll());
    }


    @Override
    public StudentModel createStudent(StudentModel studentModel) {
        Student student = studentsMapping.makeMapping(studentModel);
        student = studentRepository.save(student);
        return studentsMapping.makeMapping(student);
    }

    @Override
    public StudentModel getStudentById(int id) {
        return studentsMapping.makeMapping(studentRepository.findOne(id));
    }

    @Override
    public StudentModel updateStudent(StudentModel studentModel) {
        Student student = studentRepository.save(studentsMapping.makeMapping(studentModel));
        return studentsMapping.makeMapping(student);
    }

    @Override
    public void deleteStudentById(int id) {
        studentRepository.delete(id);
    }

    @Override
    public List<StudentModel> getAllStudents(SortType sortType) {
        if (ASC == sortType) {
            return studentsMapping.makeMapping(studentRepository.findAllByOrderByLastnameAsc());
        } else {
            return studentsMapping.makeMapping(studentRepository.findAllByOrderByLastnameDesc());
        }
    }

    @Override
    public List<StudentModel> getAllStudentsFiltered(String filter) {
        return (studentsMapping.makeMapping(studentRepository.findAllByLastnameContaining(filter)));
    }


}
