package ru.innopolis.borgatin.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.Student;

import javax.sql.DataSource;

import static ru.innopolis.borgatin.common.MainConst.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс предназначен для получения и записи в БД
 * данных о сущности студент
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class StudentDAOImpl extends EntityDAO implements StudentDAO{

    private static final String QUERY_SELECT_ALL_ORDER_ID = "SELECT * FROM students order by id";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_DESC = "SELECT * FROM students order by lastname desc";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_ASC = "SELECT * FROM students order by lastname";

    private static final String QUERY_SELECT_ALL_FILTER_MASK = "SELECT * FROM students where lastname like ";

    private static final String QUERY_SELECT_STUDENT_BY_ID = "SELECT * FROM students WHERE id=?";
    
    private static final String QUERY_UPDATE_STUDENT = "UPDATE students set lastname = ?, firstname = ?,birthdate = ?, gender = ? WHERE id = ?";

    private static final String QUERY_DELETE_FROM_STUDENT = "DELETE FROM students WHERE id = ?";
    
    private static final String QUERY_DELETE_FROM_STUDENTLESSONS = "DELETE FROM StudentsLesson WHERE student_id = ?";

    private static final String QUERY_INSERT_STUDENT = "Insert into students (lastname,firstname,birthdate, gender) values ( ?,?,?,? )";

    private static final String QUERY_SELECT_STUDENT_ID_BY_LASTNAME = "SELECT id from students WHERE lastname = ?";

    private static final String QUERY_SELECT_COUNT_STUDENT_LESSONS_BY_STUDENT_ID = "SELECT DISTINCT count(lesson_id) lessons_count FROM StudentsLesson WHERE student_id=?";

    private static final String FIELD_CALC_LESSONS_COUNT = "lessons_count";

    @Autowired
    StudentDAOImpl(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public List<Student> getAllStudents() {
        return getAllStudents(QUERY_SELECT_ALL_ORDER_ID);
    }

    @Override
    public List<Student> getAllStudentsFilter(String filter) {
        return getAllStudents(QUERY_SELECT_ALL_FILTER_MASK+"\'%"+filter+"%\'");
    }

    public List<Student> getAllStudentsSortByNameAsc(){
        return getAllStudents(QUERY_SELECT_ALL_ORDER_NAME_ASC);
    }
    public List<Student> getAllStudentsSortByNameDesc(){
        return getAllStudents(QUERY_SELECT_ALL_ORDER_NAME_DESC);
    }

    private List<Student> getAllStudents(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(query)
        ) {
            List<Student> list = new ArrayList<>();
            int i = CONST_ZERO;
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(SQL_FIELD_ID));
                student.setFirstname(resultSet.getString(SQL_FIELD_FIRSTNAME));
                student.setLastname(resultSet.getString(SQL_FIELD_LASTNAME));
                student.setGender(resultSet.getString(SQL_FIELD_GENDER));
                student.setBirthdate(resultSet.getDate(SQL_FIELD_BIRTHDATE));
                list.add(i++, student);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Student getStudentById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_STUDENT_BY_ID);
        ) {
            statement.setInt(CONST_ONE, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt(SQL_FIELD_ID));
                    student.setFirstname(resultSet.getString(SQL_FIELD_FIRSTNAME));
                    student.setLastname(resultSet.getString(SQL_FIELD_LASTNAME));
                    student.setGender(resultSet.getString(SQL_FIELD_GENDER));
                    student.setBirthdate(resultSet.getDate(SQL_FIELD_BIRTHDATE));
                    return student;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student update(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_STUDENT);
        ) {
            statement.setString(CONST_ONE, student.getLastname());
            statement.setString(CONST_TWO, student.getFirstname());
            statement.setDate(CONST_THREE, new Date(student.getBirthdate().getTime()));
            statement.setString(CONST_FOUR, student.getGender());
            statement.setInt(CONST_FIVE, student.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = getConnection()){
            return deleteEntity(connection, id, QUERY_DELETE_FROM_STUDENT,new String[]{QUERY_DELETE_FROM_STUDENTLESSONS} );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student create(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_STUDENT);
        ) {
            statement.setString(CONST_ONE, student.getLastname());
            statement.setString(CONST_TWO, student.getFirstname());
            if (student.getBirthdate()!= null) {
                statement.setDate(CONST_THREE, new Date(student.getBirthdate().getTime()));
            }
            statement.setString(CONST_FOUR, student.getGender());
            statement.executeUpdate();

            try (PreparedStatement statement1 = connection.prepareStatement(QUERY_SELECT_STUDENT_ID_BY_LASTNAME)) {
                statement1.setString(CONST_ONE, student.getLastname());
                try (ResultSet resultSet = statement1.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(SQL_FIELD_ID);
                        student.setId(id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public int getLessonsCount(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_COUNT_STUDENT_LESSONS_BY_STUDENT_ID);
        ) {
            statement.setInt(CONST_ONE, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(FIELD_CALC_LESSONS_COUNT);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CONST_ZERO;
    }


}
