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
import java.util.List;

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
        //TODO: мапить с лессон на лессон модел
        return lessonDAO.getAllLessons();
    }

    @Override
    public LessonModel createLesson(LessonModel lesson) {
        return lessonDAO.createLesson(lesson);
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
    public boolean deleteLessonById(int id) {
        return lessonDAO.deleteLesson(id);
    }

    @Override
    public List<StudentModel> getStudentsByLessonID(int id) {
        List<Integer> studentsId = lessonDAO.getStudentsIDByLessonID(id);
        List<StudentModel> students = new ArrayList<>();
        int i = 0;
        for(int studentId: studentsId){
            students.add(i++, studentDAO.getStudentById(studentId));
        }
        return students;
    }

    @Override
    public List<StudentModel> getFreeStudentsByLessonID(int id) {
        List<StudentModel> allStudents = studentDAO.getAllStudents();
        List<Integer> studentsIdByLesson = lessonDAO.getStudentsIDByLessonID(id);
        List<StudentModel> freeStudents = new ArrayList<>();
        int i = 0;
        for (StudentModel student: allStudents){
            if (!studentsIdByLesson.contains(student.getId())){
                freeStudents.add(i++, student);
            }
        }

        return freeStudents;
    }

    @Override
    public void addStudentOnLesson(int id, int studentId) {
        lessonDAO.addStudentOnLesson(id, studentId);
    }

    @Override
    public void deleteStudentFromLesson(int id, int studentId) {
        lessonDAO.deleteStudentFromLesson(id,studentId);
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
