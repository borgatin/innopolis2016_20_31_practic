package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.ILessonService;
import ru.innopolis.borgatin.common.IStudentService;
import ru.innopolis.borgatin.server.DAO.LessonDAO;
import ru.innopolis.borgatin.server.DAO.StudentDAO;
import ru.innopolis.borgatin.server.model.Lesson;
import ru.innopolis.borgatin.server.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avborg on 31.10.2016.
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LessonService implements ILessonService {

    @Autowired
    LessonDAO lessonDAO;

    @Autowired
    StudentDAO studentDAO;

    private final String SORT_TYPE_ASC = "asc";

    private final String SORT_TYPE_DESC = "desc";

    @Override
    public List<Lesson> getAllLessons() {
        return lessonDAO.getAllLessons();
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonDAO.createLesson(lesson);
    }

    @Override
    public Lesson getLessonById(int id) {
        return lessonDAO.getLessonById(id);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        return lessonDAO.updateLesson(lesson);
    }

    @Override
    public boolean deleteLessonById(int id) {
        return lessonDAO.deleteLesson(id);
    }

    @Override
    public List<Student> getStudentsByLessonID(int id) {
        List<Integer> studentsId = lessonDAO.getStudentsIDByLessonID(id);
        List<Student> students = new ArrayList<>();
        int i = 0;
        for(int studentId: studentsId){
            students.add(i++, studentDAO.getStudentById(studentId));
        }
        return students;
    }

    @Override
    public List<Student> getFreeStudentsByLessonID(int id) {
        List<Student> allStudents = studentDAO.getAllStudents();
        List<Integer> studentsIdByLesson = lessonDAO.getStudentsIDByLessonID(id);
        List<Student> freeStudents = new ArrayList<>();
        int i = 0;
        for (Student student: allStudents){
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
    public List<Lesson> getAllLessons(String sortType) {
        if (SORT_TYPE_ASC.equals(sortType)) {
            return lessonDAO.getAllLessonsSortByNameAsc();
        } else {
            return lessonDAO.getAllLessonsSortByNameDesc();
        }
    }

    @Override
    public List<Lesson> getAllLessonsFiltered(String filter) {
        return lessonDAO.getAllLessonsFilter(filter);
    }


}
