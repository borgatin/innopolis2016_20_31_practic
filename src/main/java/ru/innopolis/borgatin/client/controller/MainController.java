package ru.innopolis.borgatin.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by avborg on 01.11.2016.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {


    @RequestMapping(value = "/login")
    public ModelAndView goHome(@RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "logout", required = false) String logout,
                         Model model
    ) {
        ModelAndView modelAndView = new ModelAndView();

        if (error != null) {
            modelAndView.addObject("error", "Неправильный логин или пароль!");
        }
        if (logout != null) {
            modelAndView.addObject("msg", "Вы вышли");
        }

        modelAndView.setViewName("login");
        return modelAndView;
    }



}
