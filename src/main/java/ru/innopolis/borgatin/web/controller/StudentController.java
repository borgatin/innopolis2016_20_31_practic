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
import ru.innopolis.borgatin.server.model.Student;
import ru.innopolis.borgatin.server.model.StudentModel;
import ru.innopolis.borgatin.server.model.enums.SortType;
import static ru.innopolis.borgatin.server.model.enums.SortType.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
@Component
@RequestMapping(value = "/students")
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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/all")
    public ModelAndView getAllStudents(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List list = studentService.getAllStudents();
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", ASC.name());
        modelAndView.setViewName("allStudents");
        return modelAndView;
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/filter")
    public ModelAndView getAllStudents(@RequestParam("filter") String filter) {
        ModelAndView modelAndView = new ModelAndView();
        List list = studentService.getAllStudentsFiltered(filter);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", DESC.name());

        modelAndView.setViewName("allStudents");
        return modelAndView;
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/view/{id}")
    public ModelAndView viewStudent(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        StudentModel studentModel = studentService.getStudentById(id);
        modelAndView.addObject("student", studentModel);

        modelAndView.setViewName("viewStudent");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-student")
    public ModelAndView addStudentView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", new Student());
        modelAndView.setViewName("addStudents");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editStudentView(@PathVariable("id") int id) {
        StudentModel student = studentService.getStudentById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("editStudents");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add")
    public ModelAndView addStudent(StudentModel student, @RequestParam("birthday") String birthday) {
        try {
            student.setBirthdate(birthday);
            student = studentService.createStudent(student);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("student", student);
            modelAndView.setViewName("viewStudent");
            return modelAndView;
        } catch (ParseException e) {
            return addStudentView();
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update")
    public ModelAndView updateStudent(StudentModel student) {
        student = studentService.updateStudent(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("viewStudent");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/del/{id}")
    public ModelAndView deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudentById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../all");
        return modelAndView;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/sort/{sortType}")
    public ModelAndView sortAllByName(@PathVariable("sortType") String sortType) {
        ModelAndView modelAndView = new ModelAndView();
        SortType sortTypeEnum = SortType.valueOf(sortType);
        List list = studentService.getAllStudents(sortTypeEnum);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", ASC == sortTypeEnum ? DESC.name() : ASC.name());

        modelAndView.setViewName("allStudents");
        return modelAndView;
    }
}
