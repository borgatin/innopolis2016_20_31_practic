package ru.innopolis.borgatin.server.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by avborg on 01.11.2016.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {


    @RequestMapping(value = "/")
    public ModelAndView goHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        return modelAndView;
    }
}
