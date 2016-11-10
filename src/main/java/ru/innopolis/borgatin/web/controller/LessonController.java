package ru.innopolis.borgatin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.borgatin.common.service.ILessonService;
import ru.innopolis.borgatin.server.editor.DateCustomEditor;
import ru.innopolis.borgatin.server.model.LessonModel;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;
import ru.innopolis.borgatin.server.model.modelDAO.Student;
import ru.innopolis.borgatin.server.model.enums.SortType;

import static ru.innopolis.borgatin.common.MainConst.*;
import static ru.innopolis.borgatin.server.model.enums.SortType.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Контролер для работы с уроками
 */

@Controller
@Component
@RequestMapping(value = CONST_URL_LESSONS)
public class LessonController {

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
        List list = lessonService.getAllLessons();
        modelAndView.addObject(VIEW_VARIABLE_LIST, list);
        modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, DESC.name());
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
        List list = lessonService.getAllLessonsFiltered(filter);
        modelAndView.addObject(VIEW_VARIABLE_LIST, list);
        modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, ASC.name());

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
        LessonModel lesson = lessonService.getLessonById(id);
        modelAndView.addObject(CONST_LESSON, lesson);

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
        LessonModel lesson = lessonService.getLessonById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, lesson);
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
        lesson = lessonService.createLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, lesson);
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
        lesson = lessonService.updateLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, lesson);
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
        lessonService.deleteLessonById(id);

        ModelAndView modelAndView = new ModelAndView();
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
        SortType sortTypeEnum = SortType.valueOf(sortType);
        List list = lessonService.getAllLessons( sortTypeEnum);
        modelAndView.addObject(VIEW_VARIABLE_LIST, list);
        modelAndView.addObject(VIEW_VARIABLE_SORT_TYPE, ASC == sortTypeEnum ? DESC.name(): ASC.name());
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
        LessonModel lesson = lessonService.getLessonById(id);
        List<StudentModel> students = lessonService.getStudentsByLessonID(lesson.getId());
        modelAndView.addObject(CONST_LESSON, lesson);
        modelAndView.addObject(VIEW_VARIABLE_LIST, students);
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
        //добавляем студента на урок
        lessonService.addStudentOnLesson(id, studentId);
        return editStudentsForLesson(id);
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
        lessonService.deleteStudentFromLesson(id, studentId);
        return editStudentsForLesson(id);
    }
}
