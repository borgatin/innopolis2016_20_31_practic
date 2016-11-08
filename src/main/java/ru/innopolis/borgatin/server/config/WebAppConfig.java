package ru.innopolis.borgatin.server.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.innopolis.borgatin.server.service.UserDetailsServiceImpl;

import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Класс конфигурации приложения
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.innopolis.borgatin.*")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/")
                .addResourceLocations("/views/");
    }


    @Bean
    InternalResourceViewResolver setupInternalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }



}
