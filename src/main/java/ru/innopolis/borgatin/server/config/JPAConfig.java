package ru.innopolis.borgatin.server.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Конфигурация Hibernate
 */
@Configuration
@EnableTransactionManagement
@Component
public class JPAConfig {

    @Bean
    @Autowired
    EntityManagerFactory getLocalContainerEntityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(setupHibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPersistenceProviderClass(org.hibernate.ejb.HibernatePersistence.class);

        entityManagerFactoryBean.setPackagesToScan("ru.innopolis.borgatin.server");
        entityManagerFactoryBean.setPersistenceUnitName("myPersistenceUnit");

//        Map properites = new HashMap();
/*        properites.put("hibernate.connection.datasource",DRIVER_NAME_SQL );
        properites.put("hibernate.connection.url",SQL_URL);
        properites.put("hibernate.connection.username",SQL_DB_USER);
        properites.put("hibernate.connection.password",SQL_DB_PASSWORD);*/
//        properites.put("hibernate.connection.datasource","");
//        properites.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
//        properites.put("hibernate.hbm2ddl.auto","update");
        PersistenceProvider persistenceProvider =  entityManagerFactoryBean.getPersistenceProvider();
        PersistenceUnitInfo info = entityManagerFactoryBean.getPersistenceUnitInfo();
        Map properites  = entityManagerFactoryBean.getJpaPropertyMap();
//        persistenceProvider.
        properites.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
        properites.put("hibernate.hbm2ddl.auto","update");
        properites.put("hibernate.connection.datasource",dataSource);
        try {
            EntityManagerFactory factory =  persistenceProvider.createEntityManagerFactory("myPersistenceUnit", properites);
            return factory;

        } catch (Throwable t){
            t.printStackTrace();
        }
        return null;
//        return  entityManagerFactoryBean;
    }





    @Bean(name = "basicDataSource")
    DataSource setupDriverManagerDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DRIVER_NAME_SQL);
        basicDataSource.setUrl(SQL_URL);
        basicDataSource.setUsername(SQL_DB_USER);
        basicDataSource.setPassword(SQL_DB_PASSWORD);
        return basicDataSource;

    }

    @Bean
    JpaVendorAdapter setupHibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        return hibernateJpaVendorAdapter;
    }



}
