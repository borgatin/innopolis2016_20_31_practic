package ru.innopolis.borgatin.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.mapping.StudentsMapping;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;


/**
 * Класс реализует абстрактный класс EntityDAO и интерфейс StudentDAO
 * Необходим для получения информации о сущности Student из базы данных.
 * Использует объект EntityManager, создающийся в родителе EntityDAO.
 * Все методы интерфейса UserDAO возвращают объект StudentModel.
 * Для маппинга используется экземпляр класса StudentsMapping, использующий библиотеку Orica.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class StudentDAOImpl extends EntityDAO implements StudentDAO{

    private static final String QUERY_SELECT_ALL_ORDER_ID = "SELECT s FROM Student s order by s.id";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_DESC = "SELECT s FROM Student s order by s.lastname desc";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_ASC = "SELECT s FROM Student s order by s.lastname";

    private static final String QUERY_SELECT_ALL_FILTER_MASK = "SELECT s FROM Student s where s.lastname like :filter";

    private final StudentsMapping studentsMapping;

    @Autowired
    public StudentDAOImpl(StudentsMapping studentsMapping) {
        this.studentsMapping = studentsMapping;
    }


    /**
     * Основной метод по получению списка студентов. Выводит студентов по переданному SQL-запросу
     * @param query строка String с SQL-запросом для получения списка студентов
     * @return List StudentModel
     */
    private List<StudentModel> getAllStudents(String query) {
        TypedQuery<Student> lessonTypedQuery  = getEntityManager().createQuery(query, Student.class);
        List<Student> students = lessonTypedQuery.getResultList();
        return studentsMapping.makeMapping(students);
    }


    @Override
    public List<StudentModel> getAllStudents() {
        return getAllStudents(QUERY_SELECT_ALL_ORDER_ID);
    }

    @Override
    public List<StudentModel> getAllStudentsFilter(String filter) {
        TypedQuery<Student>  lessonTypedQuery  = getEntityManager().createQuery(QUERY_SELECT_ALL_FILTER_MASK , Student.class);
        lessonTypedQuery.setParameter("filter",
                new StringBuilder("%")
                        .append(filter)
                        .append("%").toString());
        List<Student> students = lessonTypedQuery.getResultList();
        return studentsMapping.makeMapping(students);
    }

    public List<StudentModel> getAllStudentsSortByNameAsc(){
        return getAllStudents(QUERY_SELECT_ALL_ORDER_NAME_ASC);
    }
    public List<StudentModel> getAllStudentsSortByNameDesc(){
        return getAllStudents(QUERY_SELECT_ALL_ORDER_NAME_DESC);
    }




    @Override
    public StudentModel getStudentById(int id) {
        Student student = getEntityManager().find(Student.class, id);
        return studentsMapping.makeMapping(student);
    }

    @Override
    public StudentModel update(StudentModel studentModel) {
        EntityManager em = getEntityManager();
        Student student = studentsMapping.makeMapping(studentModel );
        em.getTransaction().begin();
        student= em.merge(student);
        em.getTransaction().commit();
        studentModel = studentsMapping.makeMapping(student );
        return studentModel;
    }

    @Override
    public void deleteStudent(int id) {
        EntityManager em = getEntityManager();
        Student student = em.find(Student.class, id);
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
    }

}
