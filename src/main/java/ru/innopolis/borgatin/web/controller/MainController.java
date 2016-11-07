package ru.innopolis.borgatin.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by avborg on 01.11.2016.
 */
@Controller
public class MainController {


    @Secured("ROLE_ANONYMOUS")
    @RequestMapping(/*method = RequestMethod.GET,*/ value = "/login")
    public String login(Model model){
        return "login";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(/*method = RequestMethod.GET,*/ value = "/")
    public String start(Model model){
        return "welcome";
    }




}