package ru.innopolis.borgatin.common.service;

import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;
import ru.innopolis.borgatin.server.model.modelDAO.Student;
import ru.innopolis.borgatin.server.model.enums.SortType;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс преназначен для работы с сущность урок
 */
public interface ILessonService {
    /**
     * Метод предназначен для получения спсика уроков
     * @return возвращает список всех уроков
     */
    List<LessonModel> getAllLessons();

    /**
     * Метод предназначен для сортировки всех уроков
     * по убыванию или возрастанию
     * по полю topic
     * @param sortType может принимать значения ASC, DESC
     * @return отсортированный список
     */
    List<LessonModel> getAllLessons(SortType sortType) ;

    /**
     * Метод необходим для получения всех уроков в отфильтрованном виде.
     * Фильтр применяется к полю topic в формате like %filter%
     * @param filter строка, по которой фильтруется поле topic
     * @return отфильтрованный список уроков
     */
    List<LessonModel> getAllLessonsFiltered(String filter) ;

    /**
     * Метод предназначен для создания урока
     * @param lesson урок, который необходимо создать
     * @return созданный урок с заполненным полем id
     */
    LessonModel createLesson(LessonModel lesson);

    /**
     * Метод предназначен для получения урока
     * по его идентификатору
     * @param id идентификатор урока
     * @return урок, соответствуюший переданному идентификатору
     */
    LessonModel getLessonById(int id);

    /**
     * Метод предназначен для обнвления урока
     * @param lesson урок, который нужно обновить
     * @return обновленный урок
     */
    LessonModel updateLesson(LessonModel lesson);

    /**
     * Метод предназначен для удаления урока по его идентификатору
     * @param id идентификатор урока, который нужно удалить
     */
    void deleteLessonById(int id);

    /**
     * Метод предназначен для получения списка студентов,
     * записанных на урок
     * @param id идентификатор урока
     * @return список студентов, записанных на урок
     */
    List<StudentModel> getStudentsByLessonID(int id);

    /**
     * Метод предназначен для получения списка студентов,
     * еще не записавшихся на этот урок
     * @param id идентификатор урока
     * @return список студентов, не записавшихся на урок
     */
    List<StudentModel> getFreeStudentsByLessonID(int id);

    /**
     * Метод предназначен для записи студента на урок
     * @param id идентификатор урока
     * @param studentId идентификатор студента
     */
    void addStudentOnLesson(int id, int studentId);

    /**
     * Метод предназначен для удаления записи студента на урок
     * @param id идентификатор урока
     * @param studentId идентификатор студента
     */
    void deleteStudentFromLesson(int id, int studentId);
}
