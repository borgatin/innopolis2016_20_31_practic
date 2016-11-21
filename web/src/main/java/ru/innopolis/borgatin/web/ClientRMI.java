package ru.innopolis.borgatin.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.innopolis.borgatin.common.model.LessonModel;
import ru.innopolis.borgatin.common.service.ILessonService;

import java.util.List;

/**
 * Created by avborg on 17.11.2016.
 */
public class ClientRMI {

    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("client-bean.xml");
        ILessonService lessonService = (ILessonService) factory.getBean("lessonService");
        List<LessonModel> lessonModels = lessonService.getAllLessons();
        /*
        UserDetailsService userDetailsService = (UserDetailsService) factory.getBean("userDetailsService");
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername("user");
            String username = userDetails.getUsername();
            String password = userDetails.getPassword();
        } catch (Throwable t){
            t.printStackTrace();
        }
*/
    }
}
