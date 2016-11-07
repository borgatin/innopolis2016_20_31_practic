package ru.innopolis.borgatin.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Created by avborg on 05.11.2016.
 */
@Component
public abstract class EntityDAO {

    private Connection connection;

/*    final
    DataSource dataSource;

    @Autowired
    EntityDAO(DataSource dataSource){
        try{
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.dataSource = dataSource;
    }*/
    private EntityManager entityManager ;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        EntityManagerFactory factory =  Persistence.createEntityManagerFactory("myPersistenceUnit");
        this.entityManager = factory.createEntityManager();

    }



    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Метод необходим для получения connection из пула БД
     * @return connection к БД
     */
    Connection getConnection() {
        return connection;
    }

    /**
     * Метод возвращает экземпляр Connection в пул соединений
     * @throws SQLException
     */
    public void returnConnectionInPool() throws SQLException {
        connection.close();
    }



    boolean deleteEntity(Connection connection, int id, String QueryDeleteEntity, String[] addQueries) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(QueryDeleteEntity);
        ) {
            for (String query : addQueries) {
                try (PreparedStatement statement2 = connection.prepareStatement(query)){
                statement2.setInt(CONST_ONE, id);
                statement2.executeUpdate();
                }
            }
            statement.setInt(CONST_ONE, id);
            int count = statement.executeUpdate();
            if (count > CONST_ZERO) {
                return true;
            }

        }
        return false;
    }



}
