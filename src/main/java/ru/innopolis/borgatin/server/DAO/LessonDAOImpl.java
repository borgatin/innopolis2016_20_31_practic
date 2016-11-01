package ru.innopolis.borgatin.server.DAO;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.Lesson;
import ru.innopolis.borgatin.server.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by avborg on 01.11.2016.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LessonDAOImpl extends LessonDAO {

    private final static String QUERY_SELECT_ALL_ORDER_ID = "SELECT * FROM lesson order by id";

    private final static String QUERY_SELECT_ALL_ORDER_NAME_DESC = "SELECT * FROM lesson order by topic desc";

    private final static String QUERY_SELECT_ALL_ORDER_NAME_ASC = "SELECT * FROM lesson order by topic";

    private final static String QUERY_SELECT_ALL_FILTER_MASK = "SELECT * FROM lesson where topic like ";



    protected LessonDAOImpl() throws SQLException {
    }

    @Override
    public List<Lesson> getAllLessons() {
        return getAllLessons(QUERY_SELECT_ALL_ORDER_ID);
    }

    private List<Lesson> getAllLessons(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(query)
        ) {
            List<Lesson> list = new ArrayList<>();
            int i = 0;
            while (resultSet.next()) {
                Lesson lesson= new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setTopic(resultSet.getString("topic"));
                lesson.setDescription(resultSet.getString("description"));
                lesson.setDuration(resultSet.getInt("duration"));
                lesson.setDate(resultSet.getDate("lesson_date"));
                list.add(i++, lesson);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

        @Override
    public List<Lesson> getAllLessonsFilter(String filter) {
            return getAllLessons(QUERY_SELECT_ALL_FILTER_MASK+"\'%"+filter+"%\'");
    }

    @Override
    public List<Lesson> getAllLessonsSortByNameAsc() {
        return getAllLessons(QUERY_SELECT_ALL_ORDER_NAME_ASC);
    }

    @Override
    public List<Lesson> getAllLessonsSortByNameDesc() {
        return getAllLessons(QUERY_SELECT_ALL_ORDER_NAME_DESC);
    }

    @Override
    public Lesson getLessonById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM lesson WHERE id=?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(resultSet.getInt("id"));
                    lesson.setTopic(resultSet.getString("topic"));
                    lesson.setDescription(resultSet.getString("description"));
                    lesson.setDuration(resultSet.getInt("duration"));
                    lesson.setDate(resultSet.getDate("lesson_date"));
                    return lesson;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("UPDATE lesson set topic = ?, description = ?,lesson_date = ?, duration = ? WHERE id = ?");
        ) {
            statement.setString(1, lesson.getTopic());
            statement.setString(2, lesson.getDescription());
            statement.setDate(3, new Date(lesson.getDate().getTime()));
            statement.setInt(4, lesson.getDuration());
            statement.setInt(5, lesson.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    @Override
    public boolean deleteLesson(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM lesson WHERE id = ?");
             PreparedStatement statement2 = connection.prepareStatement("DELETE FROM StudentsLesson WHERE lesson_id = ?");
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
    public Lesson createLesson(Lesson lesson) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("Insert into lesson (topic,description,lesson_date, duration) values ( ?,?,?,? )");
        ) {
            statement.setString(1, lesson.getTopic());
            statement.setString(2, lesson.getDescription());
            statement.setDate(3, new Date(lesson.getDate().getTime()));
            statement.setInt(4, lesson.getDuration());
            statement.executeUpdate();

            try (PreparedStatement statement1 = connection.prepareStatement("SELECT id from lesson WHERE topic = ?")) {
                statement1.setString(1, lesson.getTopic());
                try (ResultSet resultSet = statement1.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        lesson.setId(id);
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesson;
    }

    @Override
    public List<Integer> getStudentsIDByLessonID(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM StudentsLesson WHERE lesson_id=?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                LinkedList<Integer> integerList = new LinkedList<>();
                while (resultSet.next()) {
                    integerList.addLast(resultSet.getInt("student_id"));
                }
                return integerList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addStudentOnLesson(int id, int studentId) {
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("Insert into StudentsLesson (lesson_id, student_id) values ( ?,?)");
        ) {
            statement.setInt(1, id);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudentFromLesson(int id, int studentId) {
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM StudentsLesson WHERE lesson_id = ? and student_id = ?");
        ) {
            statement.setInt(1, id);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
