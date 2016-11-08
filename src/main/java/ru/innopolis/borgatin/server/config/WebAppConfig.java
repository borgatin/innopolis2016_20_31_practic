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
 * Created by avborg on 03.11.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.innopolis.borgatin.*")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
    }


    @Bean
    InternalResourceViewResolver setupInternalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }


/*    @Bean(name = "basicDataSource")
    DataSource setupDriverManagerDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DRIVER_NAME_SQL);
        basicDataSource.setUrl(SQL_URL);
        basicDataSource.setUsername(SQL_DB_USER);
        basicDataSource.setPassword(SQL_DB_PASSWORD);
        return basicDataSource;

    }*/

    Persistence getPersistence(){
        Persistence persistence = new Persistence();
        return persistence;
    }



}
