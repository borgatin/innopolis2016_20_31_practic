package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.service.ILessonService;
import ru.innopolis.borgatin.server.DAO.LessonDAO;
import ru.innopolis.borgatin.server.DAO.StudentDAO;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;
import ru.innopolis.borgatin.server.model.modelDAO.Student;
import ru.innopolis.borgatin.server.model.enums.SortType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.innopolis.borgatin.server.model.enums.SortType.ASC;

/**
 * Created by avborg on 31.10.2016.
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LessonService implements ILessonService {

    @Autowired
    private LessonDAO lessonDAO;

    @Autowired
    private StudentDAO studentDAO;


    @Override
    public List<LessonModel> getAllLessons() {
        return lessonDAO.getAllLessons();
    }

    @Override
    public LessonModel createLesson(LessonModel lesson) {
        return lessonDAO.updateLesson(lesson);
    }

    @Override
    public LessonModel getLessonById(int id) {
        return lessonDAO.getLessonById(id);
    }

    @Override
    public LessonModel updateLesson(LessonModel lesson) {
        return lessonDAO.updateLesson(lesson);
    }

    @Override
    public void deleteLessonById(int id) {
         lessonDAO.deleteLesson(id);
        return;
    }

    @Override
    public Set<StudentModel> getStudentsByLessonID(int id) {
        Set<StudentModel> students = lessonDAO.getStudentsIDByLessonID(id);
        return students;
    }

    @Override
    public Set<StudentModel> getFreeStudentsByLessonID(int id) {
        List<StudentModel> allStudents = studentDAO.getAllStudents();
        Set<StudentModel> studentsIdByLesson = lessonDAO.getStudentsIDByLessonID(id);
        Set<StudentModel> freeStudents = new HashSet<>();
        for (StudentModel student: allStudents){
            if (!studentsIdByLesson.contains(student)){
                freeStudents.add(student);
            }
        }
        return freeStudents;
    }

    @Override
    public void addStudentOnLesson(int id, int studentId) {
        StudentModel studentModel = studentDAO.getStudentById(studentId);

        lessonDAO.addStudentOnLesson(id,studentModel);
    }

    @Override
    public void deleteStudentFromLesson(int id, int studentId) {
        StudentModel studentModel = studentDAO.getStudentById(studentId);

        lessonDAO.deleteStudentFromLesson(id,studentModel);
    }

    @Override
    public List<LessonModel> getAllLessons(SortType sortType) {
        if (ASC == sortType ) {
            return lessonDAO.getAllLessonsSortByNameAsc();
        } else {
            return lessonDAO.getAllLessonsSortByNameDesc();
        }
    }

    @Override
    public List<LessonModel> getAllLessonsFiltered(String filter) {
        return lessonDAO.getAllLessonsFilter(filter);
    }
}
