package ru.innopolis.borgatin.common.service;

import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.enums.SortType;

import java.util.List;

/**
 * Класс предназначен для работы с сущностью студент
 */
public interface IStudentService {

    /**
     * Метод предазначен для получения списка всех студентов
     * @return список всех студентов
     */
    List<StudentModel> getAllStudents();

    /**
     * Метод предназначен для получения отсортированного списка студентов
     * по полю lastname
     * @param sortType тип сортировки: ASC - по возрастанию, DESC - о убыванию
     * @return список студентов отсортированный в зависимости от параметра sortType
     */
    List<StudentModel> getAllStudents(SortType sortType) ;

    /**
     * Метод необходим для получения всех студентов в отфильтрованном виде.
     * Фильтр применяется к полю lastname в формате like %filter%
     * @param filter строка, по которой фильтруется поле lastname
     * @return отфильтрованный список студентов
     */
    List<StudentModel> getAllStudentsFiltered(String filter) ;

    /**
     * Метод предназначен для создания студента
     * @param student студент, который необходимо создать
     * @return созданный студент с заполненным полем id
     */
    StudentModel createStudent(StudentModel student);

    /**
     * Метод предназначен для получения студента
     * по его идентификатору
     * @param id идентификатор студента
     * @return студент, соответствуюший переданному идентификатору
     */
    StudentModel getStudentById(int id);

    /**
     * Метод предназначен для обнвления студента
     * @param student студент, который нужно обновить
     * @return обновленный студент
     */
    StudentModel updateStudent(StudentModel student);

    /**
     * Метод предназначен для удаления студента по его идентификатору
     * @param id идентификатор студента, который нужно удалить
     * @return true если удалось удалить студента, иначе false
     */
    boolean deleteStudentById(int id);





}
