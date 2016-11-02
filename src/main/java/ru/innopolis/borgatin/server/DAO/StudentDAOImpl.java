package ru.innopolis.borgatin.server.DAO;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avborg on 31.10.2016.
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class StudentDAOImpl extends StudentDAO {

    private final static String QUERY_SELECT_ALL_ORDER_ID = "SELECT * FROM students order by id";

    private final static String QUERY_SELECT_ALL_ORDER_NAME_DESC = "SELECT * FROM students order by lastname desc";

    private final static String QUERY_SELECT_ALL_ORDER_NAME_ASC = "SELECT * FROM students order by lastname";

    private final static String QUERY_SELECT_ALL_FILTER_MASK = "SELECT * FROM students where lastname like ";


    public StudentDAOImpl() throws SQLException {
        super();
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
            int i = 0;
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstname(resultSet.getString("firstname"));
                student.setLastname(resultSet.getString("lastname"));
                student.setGender(resultSet.getString("gender"));
                student.setBirthdate(resultSet.getDate("birthdate"));
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
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE id=?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setFirstname(resultSet.getString("firstname"));
                    student.setLastname(resultSet.getString("lastname"));
                    student.setGender(resultSet.getString("gender"));
                    student.setBirthdate(resultSet.getDate("birthdate"));
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
             PreparedStatement statement = connection.prepareStatement("UPDATE students set lastname = ?, firstname = ?,birthdate = ?, gender = ? WHERE id = ?");
        ) {
            statement.setString(1, student.getLastname());
            statement.setString(2, student.getFirstname());
            statement.setDate(3, new Date(student.getBirthdate().getTime()));
            statement.setString(4, student.getGender());
            statement.setInt(5, student.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
             PreparedStatement statement2 = connection.prepareStatement("DELETE FROM StudentsLesson WHERE student_id = ?");
        ) {
            statement2.setInt(1, id);
            statement2.executeUpdate();
            statement.setInt(1, id);
            int count = statement.executeUpdate();
            if (count > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Student create(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("Insert into students (lastname,firstname,birthdate, gender) values ( ?,?,?,? )");
        ) {
            statement.setString(1, student.getLastname());
            statement.setString(2, student.getFirstname());
            if (student.getBirthdate()!= null) {
                statement.setDate(3, new Date(student.getBirthdate().getTime()));
            }
            statement.setString(4, student.getGender());
            statement.executeUpdate();

            try (PreparedStatement statement1 = connection.prepareStatement("SELECT id from students WHERE lastname = ?")) {
                statement1.setString(1, student.getLastname());
                try (ResultSet resultSet = statement1.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
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
             PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT count(lesson_id) lessons_count FROM StudentsLesson WHERE student_id=?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("lessons_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
