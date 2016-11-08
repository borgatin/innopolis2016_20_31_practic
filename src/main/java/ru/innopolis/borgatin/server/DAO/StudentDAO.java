package ru.innopolis.borgatin.server.DAO;


import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Student;

import java.util.List;

/**
 * Интерфейс описывает методы для получения сущности StudentModel из базы данных
 */
@Component
public interface StudentDAO {

    /**
     * Получить все объекты StudentModel
     * @return List объектов StudentModel
     */
    List<StudentModel> getAllStudents();
    /**
     * Получить все объекты StudentModel, отфильтрованные по полю
     * lastname
     * @param filter фильтр для поля lastname
     * @return List объектов StudentModel, удовлетворяющие фильтру
     */
    List<StudentModel> getAllStudentsFilter(String filter);

    /**
     * Получить все объекты StudentModel, отсортированные по возрастанию по полю
     * lastname
     * @return List отсортированных по возрастанию объектов StudentModel по полю lastname
     */
    List<StudentModel> getAllStudentsSortByNameAsc();

    /**
     * Получить все объекты StudentModel, отсортированные по убыванию по полю
     * lastname
     * @return List отсортированных по убыванию объектов StudentModel по полю lastname
     */
    List<StudentModel> getAllStudentsSortByNameDesc();

    /**
     * Получить объект StudentModel по его идентификатору
     * @param id идентификтор студента
     * @return объект StudentModel
     */
    StudentModel getStudentById(int id);

    /**
     * Обновить или создать стедента
     * @param studentModel студент, которого нужно обновить или создать
     * @return при создании объекта возвращает этот объект с заполненным идентификатором,
     * при изменении этот же объект.
     */
    StudentModel update(StudentModel studentModel);

    /**
     * Удалить студента по его идентификатору
     * @param id идентификатор удаляемого студента
     */
    void deleteStudent(int id);
}
