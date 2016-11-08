package ru.innopolis.borgatin.server.DAO;


import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс описывает методы для получения сущности LessonModel из базы данных
 */
@Component
public interface LessonDAO{


    /**
     * Получить все объекты LessonModel
     * @return List объектов LessonModel
     */
    List<LessonModel> getAllLessons();

    /**
     * Получить все объекты LessonModel, отфильтрованные по полю
     * topic
     * @param filter фильтр для поля topic
     * @return List объектов LessonModel, удовлетворяющие фильтру
     */
    List<LessonModel> getAllLessonsFilter(String filter);

    /**
     * Получить все объекты LessonModel, отсортированные по возрастанию по полю
     * topic
     * @return List отсортированных по возрастанию объектов LessonModel по полю topic
     */
    List<LessonModel> getAllLessonsSortByNameAsc();

    /**
     * Получить все объекты LessonModel, отсортированные по убыванию по полю
     * topic
     * @return List отсортированных по убыванию объектов LessonModel по полю topic
     */
    List<LessonModel> getAllLessonsSortByNameDesc();

    /**
     * Получить объект LessonModel по его идентификатору
     * @param id идентификтор урока
     * @return объект LessonModel
     */
    LessonModel getLessonById(int id);

    /**
     * Обновить или создать урок
     * @param lessonModel урок, который нужно обновить или создать
     * @return при создании объекта возвращает этот объект с заполненным идентификатором,
     * при изменении этот же объект.
     */
    LessonModel updateLesson(LessonModel lessonModel);

    /**
     * Удалить урок по его идентификатору
     * @param id идентификатор удаляемого урока
     */
    void deleteLesson(int id);

    /**
     * Получить список студентов, записанных на урок
     * @param id идентификатор урока
     * @return список студентов, записанных на урок
     */
    Set<StudentModel> getStudentsIDByLessonID(int id);

    /**
     * Записать студента на урок
     * @param id идентификтор урока
     * @param studentModel объект StudentModel
     */
    void addStudentOnLesson(int id, StudentModel studentModel);


    /**
     * Удалить запись студента на урок
     * @param id идентификтор урока
     * @param studentModel объект StudentModel
     */
    void deleteStudentFromLesson(int id, StudentModel studentModel);
}
