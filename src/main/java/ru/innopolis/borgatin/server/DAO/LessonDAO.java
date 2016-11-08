package ru.innopolis.borgatin.server.DAO;


import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;

import java.util.List;
import java.util.Set;

/**
 * Класс предназначен для получения объектов Connection из пула,
 * описывает основную логику для работы с БД для конкретных сущностей.
 * От него необходимо наследоваться, чтобы реализовать работу с БД для сущности.
 */
@Component
public interface LessonDAO{



    List<LessonModel> getAllLessons();

    List<LessonModel> getAllLessonsFilter(String filter);

    List<LessonModel> getAllLessonsSortByNameAsc();

    List<LessonModel> getAllLessonsSortByNameDesc();

    LessonModel getLessonById(int id);

    LessonModel updateLesson(LessonModel lesson);

    void deleteLesson(int id);

    Set<StudentModel> getStudentsIDByLessonID(int id);

    void addStudentOnLesson(int id, StudentModel studentModel);

    void deleteStudentFromLesson(int id, StudentModel studentModel);
}
