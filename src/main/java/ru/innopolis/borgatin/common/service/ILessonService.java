package ru.innopolis.borgatin.common.service;

import ru.innopolis.borgatin.server.model.Lesson;
import ru.innopolis.borgatin.server.model.Student;
import ru.innopolis.borgatin.server.model.enums.SortType;

import java.util.List;

/**
 * Интерфейс преназначен для работы с сущность урок
 */
public interface ILessonService {
    /**
     * Метод возвращает список всех уроков
     * @return
     */
    List<Lesson> getAllLessons();

    /**
     * Метод предназначен для сортировки всех уроков
     * по убыванию или возрастанию
     * по полю topic
     * @param sortType может принимать значения ASC, DESC
     * @return отсортированный список
     */
    List<Lesson> getAllLessons(SortType sortType) ;

    /**
     * Метод необходим для получения всех уроков в отфильтрованном виде.
     * Фильтр применяется к полю topic в формате like %filter%
     * @param filter строка, по которой фильтруется поле topic
     * @return отфильтрованный список уроков
     */
    List<Lesson> getAllLessonsFiltered(String filter) ;

    /**
     * Метод предназначен для создания урока
     * @param lesson урок, который необходимо создать
     * @return созданный урок с заполненным полем id
     */
    Lesson createLesson(Lesson lesson);

    /**
     * Метод предназначен для получения урока
     * по его идентификатору
     * @param id идентификатор урока
     * @return урок, соответствуюший переданному идентификатору
     */
    Lesson getLessonById(int id);

    /**
     * Метод предназначен для обнвления урока
     * @param lesson урок, который нужно обновить
     * @return обновленный урок
     */
    Lesson updateLesson(Lesson lesson);

    /**
     * Метод предназначен для удаления урока по его идентификатору
     * @param id идентификатор урока, который нужно удалить
     * @return true если удалось удалить урок, иначе false
     */
    boolean deleteLessonById(int id);

    /**
     * Метод предназначен для получения списка студентов,
     * записанных на урок
     * @param id идентификатор урока
     * @return список студентов, записанных на урок
     */
    List<Student> getStudentsByLessonID(int id);

    /**
     * Метод предназначен для получения списка студентов,
     * еще не записавшихся на этот урок
     * @param id идентификатор урока
     * @return список студентов, не записавшихся на урок
     */
    List<Student> getFreeStudentsByLessonID(int id);

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
