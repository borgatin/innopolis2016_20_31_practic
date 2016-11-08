package ru.innopolis.borgatin.server.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Конфигурация Hibernate
 */
@Configuration
@EnableTransactionManagement
public class JPAConfig {

    @Bean
    EntityManagerFactory getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(setupDriverManagerDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(setupHibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        entityManagerFactoryBean.setPackagesToScan("ru.innopolis.borgatin.server");
        entityManagerFactoryBean.setPersistenceUnitName("myPersistenceUnit");

        Map properites = new HashMap();
        properites.put("hibernate.connection.driver_class",DRIVER_NAME_SQL );
        properites.put("hibernate.connection.url",SQL_URL);
        properites.put("hibernate.connection.username",SQL_DB_USER);
        properites.put("hibernate.connection.password",SQL_DB_PASSWORD);
        properites.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
        properites.put("hibernate.hbm2ddl.auto","update");
        PersistenceProvider persistenceProvider =  entityManagerFactoryBean.getPersistenceProvider();
        EntityManagerFactory factory =  persistenceProvider.createEntityManagerFactory("myPersistenceUnit", properites);
        return factory;
//        return  entityManagerFactoryBean;
    }


/*    @Bean
    EntityManagerFactory getEntityManagerFactory(){
        PersistenceProvider persistenceProvider =  getLocalContainerEntityManagerFactoryBean().getPersistenceProvider();
        Map properites = new HashMap();
        properites.put("hibernate.connection.driver_class",DRIVER_NAME_SQL );
        properites.put("hibernate.connection.url",SQL_URL);
        properites.put("hibernate.connection.username",SQL_DB_USER);
        properites.put("hibernate.connection.password",SQL_DB_PASSWORD);
        properites.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
        properites.put("hibernate.hbm2ddl.auto","update");

        EntityManagerFactory factory = persistenceProvider.createEntityManagerFactory("myPersistenceUnit",properites);
        return factory;
    }*/

    @Bean
    JpaTransactionManager setupJpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(getEntityManagerFactory());
//        jpaTransactionManager.
        return jpaTransactionManager;
    }

/*    @Bean EntityManager getEntityManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){

    }*/

    @Bean
    JpaVendorAdapter setupHibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        return hibernateJpaVendorAdapter;
    }



}
