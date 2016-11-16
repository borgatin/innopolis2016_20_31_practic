package ru.innopolis.borgatin.server.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.model.StudentModel;
import ru.innopolis.borgatin.server.entity.Student;

import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Класс предназначен для маппинга из типа Student(не должен уходить дальше DAO)
 * в тип StudentModel(используется во view)
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

    /**
     * Переобразует объект Student в объект StudentModel
     * @param student объект Student, который нужно преобразовать
     * @return результат преобразования - объект StudentModel
     */
    public StudentModel makeMapping(Student student) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(student, StudentModel.class);
    }
    /**
     * Переобразует объект StudentModel в объект Student
     * @param studentModel объект StudentModel, который нужно преобразовать
     * @return результат преобразования - объект Student
     */
    public Student  makeMapping(StudentModel studentModel) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(studentModel, Student.class);
    }

    /**
     * Переобразует список объектов Student в список объектов StudentModel
     * @param students список объектов Student, который нужно преобразовать
     * @return результат преобразования - список объектов StudentModel
     */
    public List<StudentModel> makeMapping(Iterable<Student> students) {
        List<StudentModel> studentModels = new ArrayList<>();
        for (Student student: students){
            studentModels.add(makeMapping(student));
        }

        return studentModels;
    }
}
