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


    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_VIEW_BY_ID)
    public ModelAndView viewLesson(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();
        LessonModel lesson = lessonService.getLessonById(id);
        modelAndView.addObject(CONST_LESSON, lesson);

        modelAndView.setViewName(CONST_VIEW_LESSON);
        return modelAndView;
    }




    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_EDIT_BY_ID)
    public ModelAndView editLessonView(@PathVariable(CONST_ID) int id) {
        LessonModel lesson = lessonService.getLessonById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, lesson);
        modelAndView.setViewName(CONST_VIEW_EDIT_LESSONS);
        return modelAndView;
    }
    @Secured({ROLE_ADMIN})
    @RequestMapping(value = CONST_URL_ADD_LESSON)
    public ModelAndView addLessonView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, new LessonModel());
        modelAndView.setViewName(CONST_VIEW_ADD_LESSONS);
        return modelAndView;
    }


    @Secured({ROLE_ADMIN})
    @RequestMapping(value = CONST_URL_ADD)
    public ModelAndView addLesson(LessonModel lesson) {
        lesson = lessonService.createLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, lesson);
        modelAndView.setViewName(CONST_VIEW_LESSON);
        return modelAndView;
    }

    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_UPDATE)
    public ModelAndView updateLesson(LessonModel lesson) {
        lesson = lessonService.updateLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CONST_LESSON, lesson);
        modelAndView.setViewName(CONST_VIEW_LESSON);
        return modelAndView;
    }

    @Secured(ROLE_ADMIN)
    @RequestMapping(value = CONST_URL_DEL_BY_ID)
    public ModelAndView deleteLesson(@PathVariable(CONST_ID) int id) {
        lessonService.deleteLessonById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CONST_URL_REDIRECT_ALL);
        return modelAndView;
    }

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


    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_EDIT_STUDENTS_BY_LESSON_ID)
    public ModelAndView editStudentsForLesson(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();
        LessonModel lesson = lessonService.getLessonById(id);
        Set<StudentModel> students = lessonService.getStudentsByLessonID(lesson.getId());
        modelAndView.addObject(CONST_LESSON, lesson);
        modelAndView.addObject(VIEW_VARIABLE_LIST, students);
        modelAndView.setViewName(CONST_VIEW_EDIT_STUDENTS_FOR_LESSON);
        return modelAndView;
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = CONST_URL_EDIT_ADD_STUDENTS_BY_LESSONS)
    public ModelAndView addStudentsForLesson(@PathVariable(CONST_ID) int id) {
        ModelAndView modelAndView = new ModelAndView();
        LessonModel lesson = lessonService.getLessonById(id);
        modelAndView.addObject(CONST_LESSON, lesson);
        Set<StudentModel> students = lessonService.getFreeStudentsByLessonID(id);
        if (students.size()>0) {
            modelAndView.addObject(VIEW_VARIABLE_LIST, students);
            modelAndView.setViewName("addStudentsForLesson");
        } else {
            modelAndView.addObject("msgError", "Все студенты записаны на занятие");
            students = lessonService.getStudentsByLessonID(id);
            modelAndView.addObject(VIEW_VARIABLE_LIST, students);
            modelAndView.setViewName("editStudentsForLesson");
        }
        return modelAndView;
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "/{id}/add-student")
    public ModelAndView addStudentOnLesson(@PathVariable(CONST_ID) int id, @RequestParam("student") int studentId) {
        //добавляем студента на урок
        lessonService.addStudentOnLesson(id, studentId);
        return editStudentsForLesson(id);
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "/{id}/del/{studentId}")
    public ModelAndView deleteStudentFromLesson(@PathVariable(CONST_ID) int id,@PathVariable("studentId") int studentId) {
        lessonService.deleteStudentFromLesson(id, studentId);
        return editStudentsForLesson(id);
    }
}
