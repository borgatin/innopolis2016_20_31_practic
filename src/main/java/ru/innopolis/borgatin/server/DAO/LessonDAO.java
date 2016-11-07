package ru.innopolis.borgatin.server.DAO;


import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;

import java.util.List;

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

    boolean deleteLesson(int id);

    LessonModel createLesson(LessonModel lesson);

    List<Integer> getStudentsIDByLessonID(int id);

    void addStudentOnLesson(int id, int studentId);

    void deleteStudentFromLesson(int id, int studentId);
}
