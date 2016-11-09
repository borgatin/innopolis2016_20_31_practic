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
 * Абстрактный класс для получения Entity.
 * От него наследуются все DAO-классы
 */
@Component
public abstract class EntityDAO {

    private EntityManager entityManager ;

    /**
     * В конструкторе создается объект EntityManager, затем используется в наследниках.
     */
    public EntityDAO() {
        try{
            this.entityManager = Persistence.createEntityManagerFactory("TEST").createEntityManager();
        } catch (Throwable t){
            t.printStackTrace();
        }

    }


    public EntityManager getEntityManager() {
        return entityManager;
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
