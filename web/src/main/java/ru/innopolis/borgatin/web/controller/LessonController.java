package ru.innopolis.borgatin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.borgatin.common.service.ILessonService;
import ru.innopolis.borgatin.web.DateCustomEditor;
import ru.innopolis.borgatin.common.model.LessonModel;
import ru.innopolis.borgatin.common.model.StudentModel;
import ru.innopolis.borgatin.common.enums.SortType;

import static ru.innopolis.borgatin.common.MainConst.*;
import static ru.innopolis.borgatin.common.enums.SortType.*;

import java.util.Date;
import java.util.List;

/**
 * Контролер для работы с уроками
 */

@Controller
@RequestMapping(value = CONST_URL_LESSONS)
public class LessonController {

    Logger logger = LoggerFactory.getLogger(LessonController.class);

    private final ILessonService lessonService;


    @Autowired
    public LessonController(ILessonService lessonService) {
        this.lessonService = lessonService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }

    /**
     * Метод контроллера предназначен для получения списка всех уроков из модели
     * и передачи его во view
     * @return view отображения всех уроков
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_ALL)
    public ModelAndView getAllLessons() {
        ModelAndView modelAndView = new ModelAndView();
        try {
           List<LessonModel> list = lessonService.getAllLessons();
            modelAndView.addObject(VIEW_VARIABLE_LIST, list);
            modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, DESC.name());
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при получении списка уроков: ")
                    .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }
        modelAndView.setViewName(CONST_VIEW_ALL_LESSONS);
        return modelAndView;
    }



    /**
     * Метод контроллера предназначен для получения из модели
     * отфильтрованного списка уроков по полю topic и передачи его во view
     * @param filter фильтр для поля topic
     * @return view отображения всех уроков
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_FILTER)
    public ModelAndView getAllLessons(@RequestParam(VIEW_VARIABLE_FILTER) String filter) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List list = lessonService.getAllLessonsFiltered(filter);
            modelAndView.addObject(VIEW_VARIABLE_LIST, list);
            modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, ASC.name());
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при получении отфильтрованного списка уроков: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }
        modelAndView.setViewName(CONST_VIEW_ALL_LESSONS);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения из модели
     * урока по его идентификатору и передачи его во view
     * для просмотра
     * @param id идентификатор урока
     * @return view отображения одного урока
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_VIEW_BY_ID)
    public ModelAndView viewLesson(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            LessonModel lesson = lessonService.getLessonById(id);
            modelAndView.addObject(CONST_LESSON, lesson);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при получении урока по его идентификатору: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }


        modelAndView.setViewName(CONST_VIEW_LESSON);
        return modelAndView;
    }


    /**
     * Метод контроллера предназначен для получения из модели
     * урока по его идентификатору и передачи его во view
     * для редактирования
     * @param id идентификатор урока
     * @return view редактирования урока
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_EDIT_BY_ID)
    public ModelAndView editLessonView(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            LessonModel lesson = lessonService.getLessonById(id);
            modelAndView.addObject(CONST_LESSON, lesson);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при получении урока по идентификатору: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }


        modelAndView.setViewName(CONST_VIEW_EDIT_LESSONS);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения
     * view добавления урока
     * @return view добавления урока
     */
    @Secured({ROLE_ADMIN})
    @RequestMapping(value = CONST_URL_ADD_LESSON)
    public ModelAndView addLessonView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, new LessonModel());
        modelAndView.setViewName(CONST_VIEW_ADD_LESSONS);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения
     * из view добавленного урока и передачи его в модель
     * @param lesson добавляемый урок
     * @return view отображения результата сохранения студента
     */
    @Secured({ROLE_ADMIN})
    @RequestMapping(value = CONST_URL_ADD)
    public ModelAndView addLesson(LessonModel lesson) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            lesson = lessonService.createLesson(lesson);
            modelAndView.addObject(CONST_LESSON, lesson);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при создании урока: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }
        modelAndView.setViewName(CONST_VIEW_LESSON);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения
     * из view отредактированного урока и передачи его в модель
     * @param lesson отредактированный урок
     * @return view отображения результата сохранения урока
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_UPDATE)
    public ModelAndView updateLesson(LessonModel lesson) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            lesson = lessonService.updateLesson(lesson);
            modelAndView.addObject(CONST_LESSON, lesson);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при обновлении урока: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }
        modelAndView.setViewName(CONST_VIEW_LESSON);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен передачи в модель
     * идентификатора урока для его удаления
     * @param id идентифкатор урока
     * @return view отображения списка уроков после удаления
     */
    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_DEL_BY_ID)
    public ModelAndView deleteLesson(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            lessonService.deleteLessonById(id);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при удалении урока: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }
        modelAndView.setViewName(CONST_URL_REDIRECT_ALL);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения из модели
     * отсортированного списка уроков по полю topic и передачи его во view
     * @param sortType тип сортировки (ASC,DESC)
     * @return view отображения всех уроков
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_SORT_BY_SORT_TYPE)
    public ModelAndView sortAllByName(@PathVariable(VIEW_VARIABLE_SORT_TYPE) String sortType) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            SortType sortTypeEnum = SortType.valueOf(sortType);
            List list = lessonService.getAllLessons( sortTypeEnum);
            modelAndView.addObject(VIEW_VARIABLE_LIST, list);
            modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, ASC == sortTypeEnum ? DESC.name(): ASC.name());
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при получении отсортированного списка уроков: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }
        modelAndView.setViewName(CONST_VIEW_ALL_LESSONS);
        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для получения списка
     * записанных на урок студентов по его идентификатору
     * для последуюшего вывода на view
     * @param id идентифкатор урока
     * @return view отображения списка студентов по выбранному уроку
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_EDIT_STUDENTS_BY_LESSON_ID)
    public ModelAndView editStudentsForLesson(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            LessonModel lesson = lessonService.getLessonById(id);
            List<StudentModel> students = lessonService.getStudentsByLessonID(lesson.getId());
            modelAndView.addObject(CONST_LESSON, lesson);
            modelAndView.addObject(VIEW_VARIABLE_LIST, students);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при получении списка записанных на урок студентов: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
        }

        modelAndView.setViewName(CONST_VIEW_EDIT_STUDENTS_FOR_LESSON);
        return modelAndView;
    }
    /**
     * Метод контроллера предназначен для получения из модели
     * списка не записанных на урок студентов по идентификатору урока
     * для вывода на view с целью записи студента на урок
     * @param id идентифкатор урока
     * @return view отображения списка студентов, которых можно записать на выбранный урок
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_EDIT_ADD_STUDENTS_BY_LESSONS)
    public ModelAndView addStudentsForLesson(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            LessonModel lesson = lessonService.getLessonById(id);
            modelAndView.addObject(CONST_LESSON, lesson);
            List<StudentModel> students = lessonService.getFreeStudentsByLessonID(id);
            if (students.size()>0) {
                modelAndView.addObject(VIEW_VARIABLE_LIST, students);
                modelAndView.setViewName(CONST_VIEW_ADD_STUDENTS_FOR_LESSON);
            } else {
                modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ALL_STUDENTS_RECORDED);
                students = lessonService.getStudentsByLessonID(id);
                modelAndView.addObject(VIEW_VARIABLE_LIST, students);
                modelAndView.setViewName(CONST_VIEW_EDIT_STUDENTS_FOR_LESSON);
            }
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при получении списка свободных от урока студентов: ")
                            .append(ex.getMessage()).toString());
            modelAndView.addObject(VIEW_VARIABLE_MSG_ERROR, VIEW_MSG_ERROR);
            modelAndView.setViewName(CONST_VIEW_EDIT_STUDENTS_FOR_LESSON);
        }

        return modelAndView;
    }

    /**
     * Метод контроллера предназначен для передачи в модель
     * идентификаторов студента и урока для записи студента на урок
     * и отображения списка записанных на урок студентов.
     * @param id идентифкатор урока
     * @param studentId идентификатор студента
     * @return view отображения списка студентов по выбранному уроку
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_EDIT_ADD_STUDENT_BY_LESSONS)
    public ModelAndView addStudentOnLesson(@PathVariable(CONST_ID) int id, @RequestParam(CONST_STUDENT) int studentId) {

        try {
            lessonService.addStudentOnLesson(id, studentId);
            return editStudentsForLesson(id);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при добавлении студента на урок: ")
                            .append(ex.getMessage()).toString());

            return editStudentsForLesson(id);
        }

    }

    /**
     * Метод контроллера предназначен для передачи в модель
     * идентификаторов студента и урока для удаления  записи студента на урок
     * и последующего отображения списка записанных на урок студентов.
     * @param id идентифкатор урока
     * @param studentId идентификатор студента
     * @return view отображения списка студентов по выбранному уроку
     */
    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_EDIT_DEL_STUDENT_BY_LESSONS)
    public ModelAndView deleteStudentFromLesson(@PathVariable(CONST_ID) int id,@PathVariable(VIEW_VARIABLE_STUDENT_ID) int studentId) {
        try {
            lessonService.deleteStudentFromLesson(id, studentId);
            return editStudentsForLesson(id);
        } catch (RuntimeException ex){
            logger.error(
                    new StringBuilder("Произошла ошибка при удалении студента с урока: ")
                            .append(ex.getMessage()).toString());

            return editStudentsForLesson(id);
        }

    }
}
