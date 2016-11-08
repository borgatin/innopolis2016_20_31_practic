package ru.innopolis.borgatin.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Главный контроллер - для переадресования на строницу входа
 * и на страницу приветствия
 */
@Controller
public class MainController {

    /**
     * Метод контроллера предназначен для
     * возврата управления view ввода пароля
     * @return view ввода пароля
     */
    @Secured(ROLE_ANONYMOUS)
    @RequestMapping(value = CONST_URL_LOGIN)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CONST_VIEW_LOGIN);

        return modelAndView ;
    }

    /**
     * Метод контроллера предназначен для
     * возврата управления view приветсвия пользователя
     * @return view ввода пароля
     */
    @Secured({ROLE_USER, ROLE_ADMIN})
    @RequestMapping(value = URL_PATTERN_ROOT)
    public ModelAndView start()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CONST_VIEW_WELCOME);
        return modelAndView ;

    }




}
