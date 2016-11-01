package ru.innopolis.borgatin.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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

    private final String SORT_TYPE_ASC = "asc";

    private final String SORT_TYPE_DESC = "desc";

    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }


    @RequestMapping(value = "/all")
    public ModelAndView getAllStudents() {
        ModelAndView modelAndView = new ModelAndView();
        List list = studentService.getAllStudents();
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", SORT_TYPE_ASC);
        modelAndView.setViewName("allStudents");
        return modelAndView;
    }


    @RequestMapping(value = "/filter")
    public ModelAndView getAllStudents(@RequestParam("filter") String filter) {
        ModelAndView modelAndView = new ModelAndView();
        List list = studentService.getAllStudentsFiltered(filter);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", SORT_TYPE_ASC);

        modelAndView.setViewName("allStudents");
        return modelAndView;
    }


        @RequestMapping(value = "/view/{id}")
    public ModelAndView viewStudent(@PathVariable("id") int id) {
            ModelAndView modelAndView = new ModelAndView();
            StudentModel studentModel = studentService.getStudentById(id);
            modelAndView.addObject("student", studentModel);

            modelAndView.setViewName("viewStudent");
            return modelAndView;
        }
    @RequestMapping(value = "/add-student")
    public ModelAndView addStudentView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", new Student());
        modelAndView.setViewName("addStudents");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editStudentView(@PathVariable("id") int id) {
        StudentModel student = studentService.getStudentById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("editStudents");
        return modelAndView;
    }

    @RequestMapping(value = "/add")
    public ModelAndView addStudent(StudentModel student) {
        student = studentService.createStudent(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("viewStudent");
        return modelAndView;
    }
    @RequestMapping(value = "/update")
    public ModelAndView updateStudent(StudentModel student) {
        student = studentService.updateStudent(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("viewStudent");
        return modelAndView;
    }

    @RequestMapping(value = "/del/{id}")
    public ModelAndView deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudentById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../all");
        return modelAndView;
    }

    @RequestMapping(value = "/sort/{sortType}")
    public ModelAndView sortAllByName(@PathVariable("sortType") String sortType) {
        ModelAndView modelAndView = new ModelAndView();
        List list = studentService.getAllStudents(sortType);
        modelAndView.addObject("list", list);
        modelAndView.addObject("sortType", SORT_TYPE_ASC.equals(sortType)?SORT_TYPE_DESC: SORT_TYPE_ASC);

        modelAndView.setViewName("allStudents");
        return modelAndView;
    }
}
