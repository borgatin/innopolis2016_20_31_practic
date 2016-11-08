package ru.innopolis.borgatin.server.DAO.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Student;

import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Created by avborg on 07.11.2016.
 */
@Component
public class StudentsMapping {

    private MapperFactory mapperFactory;

    public StudentsMapping()  {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Student.class, StudentModel.class)
                .field(CONST_ID, CONST_ID)
                .field(SQL_FIELD_FIRSTNAME,SQL_FIELD_FIRSTNAME)
                .field(SQL_FIELD_LASTNAME,SQL_FIELD_LASTNAME)
                .field(SQL_FIELD_BIRTHDATE,SQL_FIELD_BIRTHDATE)
                .field(SQL_FIELD_GENDER,SQL_FIELD_GENDER)
                .customize(new CustomMapper<Student, StudentModel>() {
            @Override
            public void mapAtoB(Student student, StudentModel studentModel, MappingContext context) {
                if (student.getLessons() != null) {
                    studentModel.setLessonsCount(student.getLessons().size());
                }
            }
        })
                .byDefault()
                .register();
        this.mapperFactory = mapperFactory ;
    }

    public StudentModel makeMapping(Student student) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(student, StudentModel.class);
    }
    public Student  makeMapping(StudentModel studentModel) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(studentModel, Student.class);
    }

    public List<StudentModel> makeMapping(List<Student> students) {
        List<StudentModel> lessonModels = new ArrayList<>();
        for (Student student: students){
            lessonModels.add(makeMapping(student));
        }
        return lessonModels;
    }
}
