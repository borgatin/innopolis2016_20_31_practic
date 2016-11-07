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
import ru.innopolis.borgatin.server.model.Lesson;
import ru.innopolis.borgatin.server.model.Student;
import ru.innopolis.borgatin.server.model.enums.SortType;
import static ru.innopolis.borgatin.server.model.enums.SortType.*;

import java.util.Date;
import java.util.List;

/**
 * Created by avborg on 01.11.2016.
 */

@Controller
@Component
@RequestMapping(value = "/lessons")
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


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/all")
    public ModelAndView getAllLessons() {
        ModelAndView modelAndView = new ModelAndView();
        List list = lessonService.getAllLessons();
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", DESC.name());
        modelAndView.setViewName("allLessons");
        return modelAndView;
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/filter")
    public ModelAndView getAllLessons(@RequestParam("filter") String filter) {
        ModelAndView modelAndView = new ModelAndView();
        List list = lessonService.getAllLessonsFiltered(filter);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", ASC.name());

        modelAndView.setViewName("allLessons");
        return modelAndView;
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/view/{id}")
    public ModelAndView viewLesson(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Lesson lesson = lessonService.getLessonById(id);
        modelAndView.addObject("lesson", lesson);

        modelAndView.setViewName("viewLesson");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-lesson")
    public ModelAndView addLessonView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", new Lesson());
        modelAndView.setViewName("addLessons");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLessonView(@PathVariable("id") int id) {
        Lesson lesson = lessonService.getLessonById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", lesson);
        modelAndView.setViewName("editLessons");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add")
    public ModelAndView addLesson(Lesson lesson) {
        lesson = lessonService.createLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", lesson);
        modelAndView.setViewName("viewLesson");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update")
    public ModelAndView updateLesson(Lesson lesson) {
        lesson = lessonService.updateLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", lesson);
        modelAndView.setViewName("viewLesson");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/del/{id}")
    public ModelAndView deleteLesson(@PathVariable("id") int id) {
        lessonService.deleteLessonById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../all");
        return modelAndView;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/sort/{sortType}")
    public ModelAndView sortAllByName(@PathVariable("sortType") String sortType) {
        ModelAndView modelAndView = new ModelAndView();
        SortType sortTypeEnum = SortType.valueOf(sortType);
        List list = lessonService.getAllLessons( sortTypeEnum);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", ASC == sortTypeEnum ? DESC.name(): ASC.name());
        modelAndView.setViewName("allLessons");
        return modelAndView;
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/edit-students/{id}")
    public ModelAndView editStudentsForLesson(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Lesson lesson = lessonService.getLessonById(id);
        List<Student> students = lessonService.getStudentsByLessonID(lesson.getId());
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("list", students);
        modelAndView.setViewName("editStudentsForLesson");
        return modelAndView;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/{id}/add-students")
    public ModelAndView addStudentsForLesson(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Lesson lesson = lessonService.getLessonById(id);
        modelAndView.addObject("lesson", lesson);
        List<Student> students = lessonService.getFreeStudentsByLessonID(id);
        if (students.size()>0) {
            modelAndView.addObject("list", students);
            modelAndView.setViewName("addStudentsForLesson");
        } else {
            modelAndView.addObject("msgError", "Все студенты записаны на занятие");
            students = lessonService.getStudentsByLessonID(id);
            modelAndView.addObject("list", students);
            modelAndView.setViewName("editStudentsForLesson");
        }
        return modelAndView;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/{id}/add-student")
    public ModelAndView addStudentOnLesson(@PathVariable("id") int id, @RequestParam("student") int studentId) {
        //добавляем студента на урок
        lessonService.addStudentOnLesson(id, studentId);
        return editStudentsForLesson(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/{id}/del/{studentId}")
    public ModelAndView deleteStudentFromLesson(@PathVariable("id") int id,@PathVariable("studentId") int studentId) {
        lessonService.deleteStudentFromLesson(id, studentId);
        return editStudentsForLesson(id);
    }
}
