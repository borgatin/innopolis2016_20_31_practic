package ru.innopolis.borgatin.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.mapping.LessonsMapping;
import ru.innopolis.borgatin.server.mapping.StudentsMapping;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;
import ru.innopolis.borgatin.server.model.modelDAO.Student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.*;

/**
 * Класс реализует абстрактный класс EntityDAO и интерфейс LessonDAO
 * Необходим для получения информации о сущности Lesson из базы данных,
 * а также связанных с ней объектов Student.
 * Использует объект EntityManager, создающийся в родителе EntityDAO.
 * Все методы интерфейса UserDAO возвращают объект (или список объектов) LessonModel, или StudentModel.
 * Для маппинга используется экземпляры классов StudentsMapping и LessonsMapping, использующие библиотеку Orica.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LessonDAOImpl extends EntityDAO implements LessonDAO {

    private static final String QUERY_SELECT_ALL_ORDER_ID = "SELECT l from Lesson l ORDER BY l.id";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_DESC = "SELECT l from Lesson l ORDER BY l.topic DESC";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_ASC = "SELECT l from Lesson l ORDER BY l.topic";

    private static final String QUERY_SELECT_ALL_FILTER_MASK = "SELECT l from Lesson l WHERE l.topic like :filter ORDER BY l.topic";



    private final LessonsMapping lessonsMapping;

    private final StudentsMapping studentsMapping;



    @Autowired
    public LessonDAOImpl(LessonsMapping lessonsMapping, StudentsMapping studentsMapping) {
        this.lessonsMapping = lessonsMapping;
        this.studentsMapping = studentsMapping;
    }

    @Override
    public List<LessonModel> getAllLessons() {
        return getAllLessons(QUERY_SELECT_ALL_ORDER_ID);
    }

    private List<LessonModel> getAllLessons(String query) {
        TypedQuery<Lesson>  lessonTypedQuery  = getEntityManager().createQuery(query, Lesson.class);
        List<Lesson> lessons = lessonTypedQuery.getResultList();
        return lessonsMapping.makeMapping(lessons);
    }

        @Override
    public List<LessonModel> getAllLessonsFilter(String filter) {


            TypedQuery<Lesson>  lessonTypedQuery  = getEntityManager().createQuery(QUERY_SELECT_ALL_FILTER_MASK , Lesson.class);
            lessonTypedQuery.setParameter("filter",
                    new StringBuilder("%")
                    .append(filter)
                    .append("%").toString());
            List<Lesson> lessons = lessonTypedQuery.getResultList();
            return lessonsMapping.makeMapping(lessons);

    }

    @Override
    public List<LessonModel> getAllLessonsSortByNameAsc() {
        return getAllLessons(QUERY_SELECT_ALL_ORDER_NAME_ASC);
    }

    @Override
    public List<LessonModel> getAllLessonsSortByNameDesc() {
        return getAllLessons(QUERY_SELECT_ALL_ORDER_NAME_DESC);
    }

    @Override
    public LessonModel getLessonById(int id) {
        Lesson lesson = getEntityManager().find(Lesson.class, id);
        return lessonsMapping.makeMapping(lesson);
    }



    @Override
    public LessonModel updateLesson(LessonModel lessonModel) {

        EntityManager em = getEntityManager();
        Lesson lesson = lessonsMapping.makeMapping(lessonModel );
        em.getTransaction().begin();
        lesson = em.merge(lesson);
        em.getTransaction().commit();
        lessonModel = lessonsMapping.makeMapping(lesson );


        return lessonModel;
    }

    @Override
    public void deleteLesson(int id) {
        EntityManager em = getEntityManager();
        Lesson lesson = em.find(Lesson.class, id);
        em.getTransaction().begin();
        em.remove(lesson);
        em.getTransaction().commit();
    }






    @Override
    public Set<StudentModel> getStudentsIDByLessonID(int id) {
        Lesson lesson = getEntityManager().find(Lesson.class, id);
        Set<Student> students = lesson.getStudents();
        Set<StudentModel> studentModels = new HashSet<>();
        for (Student student : students){
            studentModels.add(studentsMapping.makeMapping(student));
        }
        return studentModels;
    }

    @Override
    public void addStudentOnLesson(int id, StudentModel studentModel) {
        Lesson lesson = getEntityManager().find(Lesson.class, id);
        lesson.getStudents().add(studentsMapping.makeMapping(studentModel));

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(lesson);
        em.getTransaction().commit();
    }

    @Override
    public void deleteStudentFromLesson(int id, StudentModel studentModel) {
        Lesson lesson = getEntityManager().find(Lesson.class, id);
        Student student = studentsMapping.makeMapping(studentModel);

        Set<Student> students = lesson.getStudents();
        students.remove(student);
        lesson.setStudents(students);
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(lesson);
        em.getTransaction().commit();



    }



}
