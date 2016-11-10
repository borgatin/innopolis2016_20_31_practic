package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.service.ILessonService;
import ru.innopolis.borgatin.server.mapping.StudentsMapping;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;
import ru.innopolis.borgatin.server.model.modelDAO.Student;
import ru.innopolis.borgatin.server.repositories.LessonRepository;
import ru.innopolis.borgatin.server.mapping.LessonsMapping;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.enums.SortType;
import ru.innopolis.borgatin.server.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.innopolis.borgatin.server.model.enums.SortType.ASC;

/**
 * Класс реализует интерфейс ILessonService.
 * Предназначен дл работы с сущностью LessonModel.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LessonService implements ILessonService {

/*    @Autowired
    private LessonDAO lessonRepository;

    @Autowired
    private StudentDAO studentDAO;*/

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final LessonsMapping lessonsMapping;
    private final StudentsMapping studentsMapping;

    @Autowired
    public LessonService(LessonRepository lessonRepository, StudentRepository studentRepository, LessonsMapping lessonsMapping, StudentsMapping studentsMapping) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.lessonsMapping = lessonsMapping;
        this.studentsMapping = studentsMapping;
    }


    @Override
    public List<LessonModel> getAllLessons() {
        return lessonsMapping.makeMapping(lessonRepository.findAll());
    }

    @Override
    public LessonModel createLesson(LessonModel lesson) {
        return lessonsMapping.makeMapping(lessonRepository.save(lessonsMapping.makeMapping(lesson)));
    }

    @Override
    public LessonModel getLessonById(int id) {
        return lessonsMapping.makeMapping(lessonRepository.findOne(id));
    }

    @Override
    public LessonModel updateLesson(LessonModel lesson) {
        return lessonsMapping.makeMapping(lessonRepository.save(lessonsMapping.makeMapping(lesson)));
    }

    @Override
    public void deleteLessonById(int id) {
         lessonRepository.delete(id);
    }


    @Override
    public List<LessonModel> getAllLessons(SortType sortType) {
        if (ASC == sortType ) {
            return lessonsMapping.makeMapping(lessonRepository.findAllByOrderByTopicAsc());
        } else {
            return lessonsMapping.makeMapping(lessonRepository.findAllByOrderByTopicDesc());
        }
    }

    @Override
    public List<LessonModel> getAllLessonsFiltered(String filter) {
        return lessonsMapping.makeMapping(lessonRepository.findAllByTopicContaining(filter));
    }

    @Override
    public List<StudentModel> getStudentsByLessonID(int id) {
        Lesson lesson= lessonRepository.findOne(id);
        List<StudentModel> students =  studentsMapping.makeMapping(lesson.getStudents());
        return students;
    }

    @Override
    public List<StudentModel> getFreeStudentsByLessonID(int id) {
        List<StudentModel> allStudents = studentsMapping.makeMapping(studentRepository.findAll());
        Lesson lesson =lessonRepository.findOne(id);
        List<StudentModel> studentsIdByLesson = studentsMapping.makeMapping(lesson.getStudents());
        List<StudentModel> freeStudents = new ArrayList<>();
        for (StudentModel student: allStudents){
            if (!studentsIdByLesson.contains(student)){
                freeStudents.add(student);
            }
        }
        return freeStudents;
    }

    @Override
    public void addStudentOnLesson(int id, int studentId) {
        Lesson lesson = lessonRepository.findOne(id);
        Student student = studentRepository.findOne(studentId);
        lesson.getStudents().add(student);
        lessonRepository.save(lesson);
    }

    @Override
    public void deleteStudentFromLesson(int id, int studentId) {
        Lesson lesson = lessonRepository.findOne(id);
        Student student = studentRepository.findOne(studentId);
        lesson.getStudents().remove(student);
        lessonRepository.save(lesson);
    }

}
