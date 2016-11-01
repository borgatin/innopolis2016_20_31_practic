package ru.innopolis.borgatin.common;

import ru.innopolis.borgatin.server.model.Lesson;
import ru.innopolis.borgatin.server.model.Student;

import java.util.List;

/**
 * Created by avborg on 31.10.2016.
 */
public interface ILessonService {

    List<Lesson> getAllLessons();

    List<Lesson> getAllLessons(String sortType) ;

    List<Lesson> getAllLessonsFiltered(String filter) ;

    Lesson createLesson(Lesson lesson);

    Lesson getLessonById(int id);

    Lesson updateLesson(Lesson lesson);

    boolean deleteLessonById(int id);


    List<Student> getStudentsByLessonID(int id);

    List<Student> getFreeStudentsByLessonID(int id);

    void addStudentOnLesson(int id, int studentId);

    void deleteStudentFromLesson(int id, int studentId);
}
