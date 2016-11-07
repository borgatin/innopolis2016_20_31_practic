package ru.innopolis.borgatin.server.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Класс необходим для инициализации приложения
 */

public class Initializer implements WebApplicationInitializer {



    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        ctx.register(SecurityConfig.class);
        ctx.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(ctx));



        //Добавим Диспатчер сервлет
        Dynamic dispatcherServlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        dispatcherServlet.addMapping(URL_PATTERN_ROOT);
        dispatcherServlet.setLoadOnStartup(CONST_ONE);

        //Фильтр Spring Security
        servletContext.addFilter(SPRING_SECURITY_FILTER_NAME, new DelegatingFilterProxy(SPRING_SECURITY_FILTER_NAME))
                .addMappingForUrlPatterns(null, false, URL_PATTERN_ALL);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(SPRING_ENCODING_FILTER_NAME, org.springframework.web.filter.CharacterEncodingFilter.class);
        encodingFilter.setInitParameter(ENCODING_PARAM_NAME, ENCODING_UTF8_PARAM_VALUE);
        encodingFilter.setInitParameter(FORCE_ENCODING_PARAM_NAME, CONST_TRUE);
        encodingFilter.addMappingForUrlPatterns(null, true, URL_PATTERN_ALL);



    }
}
