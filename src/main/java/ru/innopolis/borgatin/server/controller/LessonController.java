package ru.innopolis.borgatin.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.borgatin.common.ILessonService;
import ru.innopolis.borgatin.common.IStudentService;
import ru.innopolis.borgatin.server.editor.DateCustomEditor;
import ru.innopolis.borgatin.server.model.Lesson;
import ru.innopolis.borgatin.server.model.Student;

import java.util.ArrayList;
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

    private final String SORT_TYPE_ASC = "asc";

    private final String SORT_TYPE_DESC = "desc";

    @Autowired
    public LessonController(ILessonService lessonService) {
        this.lessonService = lessonService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }


    @RequestMapping(value = "/all")
    public ModelAndView getAllLessons() {
        ModelAndView modelAndView = new ModelAndView();
        List list = lessonService.getAllLessons();
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", SORT_TYPE_ASC);
        modelAndView.setViewName("allLessons");
        return modelAndView;
    }


    @RequestMapping(value = "/filter")
    public ModelAndView getAllLessons(@RequestParam("filter") String filter) {
        ModelAndView modelAndView = new ModelAndView();
        List list = lessonService.getAllLessonsFiltered(filter);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", SORT_TYPE_ASC);

        modelAndView.setViewName("allLessons");
        return modelAndView;
    }


    @RequestMapping(value = "/view/{id}")
    public ModelAndView viewLesson(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Lesson lesson = lessonService.getLessonById(id);
        modelAndView.addObject("lesson", lesson);

        modelAndView.setViewName("viewLesson");
        return modelAndView;
    }
    @RequestMapping(value = "/add-lesson")
    public ModelAndView addLessonView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", new Lesson());
        modelAndView.setViewName("addLessons");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLessonView(@PathVariable("id") int id) {
        Lesson lesson = lessonService.getLessonById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", lesson);
        modelAndView.setViewName("editLessons");
        return modelAndView;
    }

    @RequestMapping(value = "/add")
    public ModelAndView addLesson(Lesson lesson) {
        lesson = lessonService.createLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", lesson);
        modelAndView.setViewName("viewLesson");
        return modelAndView;
    }
    @RequestMapping(value = "/update")
    public ModelAndView updateLesson(Lesson lesson) {
        lesson = lessonService.updateLesson(lesson);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lesson", lesson);
        modelAndView.setViewName("viewLesson");
        return modelAndView;
    }

    @RequestMapping(value = "/del/{id}")
    public ModelAndView deleteLesson(@PathVariable("id") int id) {
        lessonService.deleteLessonById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../all");
        return modelAndView;
    }

    @RequestMapping(value = "/sort/{sortType}")
    public ModelAndView sortAllByName(@PathVariable("sortType") String sortType) {
        ModelAndView modelAndView = new ModelAndView();
        List list = lessonService.getAllLessons(sortType);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", SORT_TYPE_ASC.equals(sortType)?SORT_TYPE_DESC: SORT_TYPE_ASC);

        modelAndView.setViewName("allLessons");
        return modelAndView;
    }


    @RequestMapping(value = "/edit-students/{id}")
    public ModelAndView editStudentsForLesson(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Lesson lesson = lessonService.getLessonById(id);
        List<Student> students = lessonService.getStudentsByLessonID(lesson.getId());
        modelAndView.addObject("lesson", lesson);
        modelAndView.addObject("list", students);
        String btnAddStudents = "<tr><td></td><td></td><td></td><td></td><td></td><td><a href=\"/lessons/"+id+"/add-students\">Add</a></td></tr>";
        List<Student> list = lessonService.getFreeStudentsByLessonID(id);
        if (list.size()>0){
            modelAndView.addObject("btnAddStudents", btnAddStudents);
        }

        modelAndView.setViewName("editStudentsForLesson");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/add-students")
    public ModelAndView addStudentsForLesson(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Lesson lesson = lessonService.getLessonById(id);
        List<Student> students = lessonService.getFreeStudentsByLessonID(lesson.getId());
        modelAndView.addObject("lesson", lesson);


        modelAndView.addObject("list", students);

        modelAndView.setViewName("addStudentsForLesson");
        return modelAndView;
    }
    @RequestMapping(value = "/{id}/add-student")
    public ModelAndView addStudentOnLesson(@PathVariable("id") int id, @RequestParam("student") int studentId) {
        //добавляем студента на урок
        lessonService.addStudentOnLesson(id, studentId);
        return editStudentsForLesson(id);
    }

    @RequestMapping(value = "/{id}/del/{studentId}")
    public ModelAndView deleteStudentFromLesson(@PathVariable("id") int id,@PathVariable("studentId") int studentId) {
        lessonService.deleteStudentFromLesson(id, studentId);

        return editStudentsForLesson(id);
    }



}
