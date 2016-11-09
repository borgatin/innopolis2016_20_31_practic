package ru.innopolis.borgatin.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.service.ILessonService;
import ru.innopolis.borgatin.repositories.LessonRepository;
import ru.innopolis.borgatin.server.mapping.LessonsMapping;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.enums.SortType;

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
    private final LessonsMapping lessonsMapping;

    @Autowired
    public LessonService(LessonRepository lessonRepository, LessonsMapping lessonsMapping) {
        this.lessonRepository = lessonRepository;
        this.lessonsMapping = lessonsMapping;
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
        /*if (ASC == sortType ) {
            return lessonsMapping.makeMapping(lessonRepository.findAllOrderByTopicAsc());
        } else {
            return lessonsMapping.makeMapping(lessonRepository.findAllOrderByTopicDesc());
        }*/
        return null;
    }

    @Override
    public List<LessonModel> getAllLessonsFiltered(String filter) {
//        return lessonsMapping.makeMapping(lessonRepository.findAllByTopicContaining(filter));
    return null;
    }

    @Override
    public Set<StudentModel> getStudentsByLessonID(int id) {
/*        Set<StudentModel> students = lessonRepository.getStudentsIDByLessonID(id);
        return students;*/
return null;
    }

    @Override
    public Set<StudentModel> getFreeStudentsByLessonID(int id) {
/*        List<StudentModel> allStudents = studentDAO.getAllStudents();
        Set<StudentModel> studentsIdByLesson = lessonRepository.getStudentsIDByLessonID(id);
        Set<StudentModel> freeStudents = new HashSet<>();
        for (StudentModel student: allStudents){
            if (!studentsIdByLesson.contains(student)){
                freeStudents.add(student);
            }
        }
        return freeStudents;*/
return null;
    }

    @Override
    public void addStudentOnLesson(int id, int studentId) {
/*        StudentModel studentModel = studentDAO.getStudentById(studentId);

        lessonRepository.addStudentOnLesson(id,studentModel);*/
    }

    @Override
    public void deleteStudentFromLesson(int id, int studentId) {
      /*  StudentModel studentModel = studentDAO.getStudentById(studentId);

        lessonRepository.deleteStudentFromLesson(id,studentModel);*/
    }

}
