package ru.innopolis.borgatin.server.mapping;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.model.LessonModel;
import ru.innopolis.borgatin.server.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.borgatin.common.MainConst.*;
import static ru.innopolis.borgatin.common.MainConst.SQL_FIELD_DURATION;

/**
 * Класс предназначен для маппинга из типа Lesson(не должен уходить дальше DAO)
 * в тип LessonModel(используется во view)
 */
@Component
public class LessonsMapping {

    private MapperFactory mapperFactory;

    public LessonsMapping() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Lesson.class, LessonModel.class)
                .field(CONST_ID, CONST_ID)
                .field(SQL_FIELD_TOPIC,SQL_FIELD_TOPIC)
                .field(SQL_FIELD_DESCRIPTION,SQL_FIELD_DESCRIPTION)
                .field(SQL_FIELD_DURATION,SQL_FIELD_DURATION)
                .field(CONST_DATE_FIELD,CONST_DATE_FIELD)
                .byDefault()
                .register();
        this.mapperFactory = mapperFactory ;
    }

    /**
     * Переобразует объект Lesson в объект LessonModel
     * @param lesson объект Lesson, который нужно преобразовать
     * @return результат преобразования - объект LessonModel
     */
    public LessonModel makeMapping(Lesson lesson) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(lesson, LessonModel.class);
    }
    /**
     * Переобразует объект LessonModel в объект Lesson
     * @param lessonModel объект LessonModel, который нужно преобразовать
     * @return результат преобразования - объект Lesson
     */
    public Lesson  makeMapping(LessonModel lessonModel) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(lessonModel, Lesson.class);
    }

    /**
     * Переобразует список объектов Lesson в список объектов LessonModel
     * @param lessons список объектов Lesson, который нужно преобразовать
     * @return результат преобразования - список объектов LessonModel
     */
    public List<LessonModel> makeMapping(Iterable<Lesson> lessons) {
        List<LessonModel> lessonModels = new ArrayList<>();
        for (Lesson lesson: lessons){
            lessonModels.add(makeMapping(lesson));
        }
        return lessonModels;
    }
}
