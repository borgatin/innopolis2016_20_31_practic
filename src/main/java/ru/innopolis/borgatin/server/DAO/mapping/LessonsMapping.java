package ru.innopolis.borgatin.server.DAO.mapping;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;

import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.borgatin.common.MainConst.*;
import static ru.innopolis.borgatin.common.MainConst.SQL_FIELD_DURATION;

/**
 * Created by avborg on 07.11.2016.
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
                .field("date","date")
                .byDefault()
                .register();
        this.mapperFactory = mapperFactory ;
    }

    public LessonModel makeMapping(Lesson lesson) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(lesson, LessonModel.class);
    }
    public Lesson  makeMapping(LessonModel lessonModel) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(lessonModel, Lesson.class);
    }

    public List<LessonModel> makeMapping(List<Lesson> lessons) {
        List<LessonModel> lessonModels = new ArrayList<>();
        for (Lesson lesson: lessons){
            lessonModels.add(makeMapping(lesson));
        }
        return lessonModels;
    }
}
