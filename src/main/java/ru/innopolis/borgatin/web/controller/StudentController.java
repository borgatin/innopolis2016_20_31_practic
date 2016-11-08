package ru.innopolis.borgatin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.borgatin.common.service.IStudentService;
import ru.innopolis.borgatin.server.editor.DateCustomEditor;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.enums.SortType;

import static ru.innopolis.borgatin.common.MainConst.*;
import static ru.innopolis.borgatin.server.model.enums.SortType.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Контроллер для работы со студентами
 */
@Controller
@Component
@RequestMapping(value = CONST_URL_STUDENTS)
public class StudentController {

    private final IStudentService studentService;


    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }


    /**
     * Метод контроллера предназначен для получения списка всех студентов из модели
     * и передачи его во view
     * @return view отображения всех студентов
     */
    @Secured({ROLE_USER, ROLE_ADMIN})
    @RequestMapping(value = CONST_URL_ALL)
    public ModelAndView getAllStudents() {
        ModelAndView modelAndView = new ModelAndView();
        List list = studentService.getAllStudents();
        modelAndView.addObject(VIEW_VARIABLE_LIST, list);
        modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, ASC.name());
        modelAndView.setViewName(CONST_VIEW_ALL_STUDENTS);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения из модели
     * отфильтрованного списка студентов по полю lastname и передачи его во view
     * @param filter фильтр для поля lastname
     * @return view отображения всех студентов
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_FILTER)
    public ModelAndView getAllStudents(@RequestParam(VIEW_VARIABLE_FILTER) String filter) {
        ModelAndView modelAndView = new ModelAndView();
        List list = studentService.getAllStudentsFiltered(filter);
        modelAndView.addObject(VIEW_VARIABLE_LIST, list);
        modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, DESC.name());
        modelAndView.setViewName(CONST_VIEW_ALL_STUDENTS);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения из модели
     * студента по его идентификатору и передачи его во view
     * для просмотра
     * @param id идентификатор студента
     * @return view отображения одного студента
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_VIEW_BY_ID)
    public ModelAndView viewStudent(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();
        StudentModel studentModel = studentService.getStudentById(id);
        modelAndView.addObject(CONST_STUDENT, studentModel);
        modelAndView.setViewName(CONST_VIEW_STUDENT);
        return modelAndView;
    }


    /**
     * Метод контроллера предназначен для получения из модели
     * студента по его идентификатору и передачи его во view
     * для редактирования
     * @param id идентификатор студента
     * @return view редактирования студента
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_EDIT_BY_ID)
    public ModelAndView editStudentView(@PathVariable(CONST_ID) int id) {
        StudentModel student = studentService.getStudentById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_STUDENT, student);
        modelAndView.setViewName(CONST_VIEW_EDIT_STUDENTS);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения
     * view добавления студента
     * @return view добавления студента
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_ADD_STUDENT)
    public ModelAndView addStudentView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_STUDENT, new StudentModel());
        modelAndView.setViewName(CONST_VIEW_ADD_STUDENTS);
        return modelAndView;
    }
    /**
     * Метод контроллера предназначен для получения
     * из view добавленного урока и передачи его в модель
     * @param student добавляемый урок
     * @return view отображения результата сохранения студента
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_ADD)
    public ModelAndView addStudent(StudentModel student, @RequestParam(VIEW_VARIABLE_BIRTHDAY) String birthday) {
        try {
            student.setBirthdate(birthday);
            student = studentService.createStudent(student);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(CONST_STUDENT, student);
            modelAndView.setViewName(CONST_VIEW_STUDENT);
            return modelAndView;
        } catch (ParseException e) {
            return addStudentView();
        }
    }
    /**
     * Метод контроллера предназначен для получения
     * из view отредактированного студент
     * @return view отображения результата сохранения студента
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_UPDATE)
    public ModelAndView updateStudent(StudentModel student) {
        student = studentService.updateStudent(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_STUDENT, student);
        modelAndView.setViewName(CONST_VIEW_STUDENT);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен передачи в модель
     * идентификатора студента для его удаления
     * @param id идентифкатор студента
     * @return view отображения списка студентов после удаления
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_DEL_BY_ID)
    public ModelAndView deleteStudent(@PathVariable(CONST_ID) int id) {
        studentService.deleteStudentById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CONST_URL_REDIRECT_ALL);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения из модели
     * отсортированного списка студентов по полю lastname и передачи его во view
     * @param sortType тип сортировки (ASC,DESC)
     * @return view отображения всех уроков
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_SORT_BY_SORT_TYPE)
    public ModelAndView sortAllByName(@PathVariable(VIEW_VARIABLE_SORT_TYPE) String sortType) {
        ModelAndView modelAndView = new ModelAndView();
        SortType sortTypeEnum = SortType.valueOf(sortType);
        List list = studentService.getAllStudents(sortTypeEnum);
        modelAndView.addObject(VIEW_VARIABLE_LIST, list);
        modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, ASC == sortTypeEnum ? DESC.name() : ASC.name());
        modelAndView.setViewName(CONST_VIEW_ALL_STUDENTS);
        return modelAndView;
    }
}
