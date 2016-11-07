package ru.innopolis.borgatin.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.Lesson;

import javax.sql.DataSource;

import static ru.innopolis.borgatin.common.MainConst.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by avborg on 01.11.2016.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LessonDAOImpl extends EntityDAO implements LessonDAO {

    private static final String QUERY_SELECT_ALL_ORDER_ID = "SELECT * FROM lesson order by id";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_DESC = "SELECT * FROM lesson order by topic desc";

    private static final String QUERY_SELECT_ALL_ORDER_NAME_ASC = "SELECT * FROM lesson order by topic";

    private static final String QUERY_SELECT_ALL_FILTER_MASK = "SELECT * FROM lesson where topic like ";

    private static final String QUERY_SELECT_LESSON_BY_ID = "SELECT * FROM lesson WHERE id=?";

    private static final String QUERY_UPDATE_LESSON = "UPDATE lesson set topic = ?, description = ?,lesson_date = ?, duration = ? WHERE id = ?";

    private static final String QUERY_DELETE_FROM_LESSON = "DELETE FROM lesson WHERE id = ?";

    private static final String QUERY_DELETE_FROM_STUDENTSLESSONS_BY_LESSON_ID = "DELETE FROM StudentsLesson WHERE lesson_id = ?";

    private static final String QUERY_INSERT_INTO_LESSONS = "Insert into lesson (topic,description,lesson_date, duration) values ( ?,?,?,? )";

    private static final String QUERY_SELECT_LESSON_BY_TOPIC = "SELECT id from lesson WHERE topic = ?";

    private static final String QUERY_SELECT_FROM_STUDENTSLESSONS_BY_LESSON_ID = "SELECT * FROM StudentsLesson WHERE lesson_id=?";

    private static final String QUERY_INSERT_STUDENTSLESSONS = "Insert into StudentsLesson (lesson_id, student_id) values ( ?,?)";

    private static final String QUERY_DELETE_FROM_STUDENTSLESSONS = "DELETE FROM StudentsLesson WHERE lesson_id = ? and student_id = ?";

    @Autowired
    LessonDAOImpl(DataSource dataSource) {
        super(dataSource);
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
                lesson.setId(resultSet.getInt(SQL_FIELD_ID));
                lesson.setTopic(resultSet.getString(SQL_FIELD_TOPIC));
                lesson.setDescription(resultSet.getString(SQL_FIELD_DESCRIPTION));
                lesson.setDuration(resultSet.getInt(SQL_FIELD_DURATION));
                lesson.setDate(resultSet.getDate(SQL_FIELD_LESSON_DATE));
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
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LESSON_BY_ID);
        ) {
            statement.setInt(CONST_ONE, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(resultSet.getInt(SQL_FIELD_ID));
                    lesson.setTopic(resultSet.getString(SQL_FIELD_TOPIC));
                    lesson.setDescription(resultSet.getString(SQL_FIELD_DESCRIPTION));
                    lesson.setDuration(resultSet.getInt(SQL_FIELD_DURATION));
                    lesson.setDate(resultSet.getDate(SQL_FIELD_LESSON_DATE));
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
                     connection.prepareStatement(QUERY_UPDATE_LESSON);
        ) {
            statement.setString(CONST_ONE, lesson.getTopic());
            statement.setString(CONST_TWO, lesson.getDescription());
            statement.setDate(CONST_THREE, new Date(lesson.getDate().getTime()));
            statement.setInt(CONST_FOUR, lesson.getDuration());
            statement.setInt(CONST_FIVE, lesson.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    @Override
    public boolean deleteLesson(int id) {
        try (Connection connection = getConnection()){
            return deleteEntity(connection, id, QUERY_DELETE_FROM_LESSON,new String[]{QUERY_DELETE_FROM_STUDENTSLESSONS_BY_LESSON_ID} );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_INTO_LESSONS);
        ) {
            statement.setString(CONST_ONE, lesson.getTopic());
            statement.setString(CONST_TWO, lesson.getDescription());
            statement.setDate(CONST_THREE, new Date(lesson.getDate().getTime()));
            statement.setInt(CONST_FOUR, lesson.getDuration());
            statement.executeUpdate();

            try (PreparedStatement statement1 = connection.prepareStatement(QUERY_SELECT_LESSON_BY_TOPIC)) {
                statement1.setString(CONST_ONE, lesson.getTopic());
                try (ResultSet resultSet = statement1.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(SQL_FIELD_ID);
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
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_FROM_STUDENTSLESSONS_BY_LESSON_ID);
        ) {
            statement.setInt(CONST_ONE, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                LinkedList<Integer> integerList = new LinkedList<>();
                while (resultSet.next()) {
                    integerList.addLast(resultSet.getInt(SQL_FIELD_STUDENT_ID));
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
        executeQueryWithTwoIntPar(id, studentId, QUERY_INSERT_STUDENTSLESSONS);
    }

    @Override
    public void deleteStudentFromLesson(int id, int studentId) {
        executeQueryWithTwoIntPar(id, studentId, QUERY_DELETE_FROM_STUDENTSLESSONS);

    }

    private void executeQueryWithTwoIntPar(int id, int secondId, String query){
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query);
        ) {
            statement.setInt(CONST_ONE, id);
            statement.setInt(CONST_TWO, secondId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
