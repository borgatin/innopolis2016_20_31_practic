package ru.innopolis.borgatin.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
