package ru.innopolis.borgatin.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by avborg on 01.11.2016.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(/*method = RequestMethod.GET,*/ value = "/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping(/*method = RequestMethod.GET,*/ value = "/")
    public String start(Model model){
        return "welcome";
    }


}
