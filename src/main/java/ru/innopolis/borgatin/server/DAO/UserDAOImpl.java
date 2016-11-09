package ru.innopolis.borgatin.server.DAO;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.server.model.UserModel;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;
import ru.innopolis.borgatin.server.model.modelDAO.User;
import ru.innopolis.borgatin.server.model.modelDAO.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.innopolis.borgatin.common.MainConst.*;

/**
 * Класс реализует абстрактный класс EntityDAO и интерфейс UserDAO
 * Необходим для получения информации о сущности User из базы данных.
 * Использует объект EntityManager, создающийся в родителе EntityDAO.
 * Единственный метод интерфейса UserDAO возвращает объект UserModel.
 * Для маппинга используется библиотека Orica.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class UserDAOImpl extends EntityDAO implements UserDAO{


    @Override
    public UserModel getUser(String username) {
        try {
            User user = getEntityManager().find(User.class, username);
            UserModel userModel = getUserModelByUser(user);

            return userModel ;
        }catch (Throwable t){
            t.printStackTrace();
        }
        return null;
    }

    private UserModel getUserModelByUser(User user) {
        UserModel userModel = new UserModel();

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserModel.class)
                .field("username","username")
                .field("password","password")
                /*.customize(new CustomMapper<User, UserModel>() {
                    @Override
                    public void mapAtoB(User user, UserModel userModel, MappingContext context) {
                        Set<UserRole> userRoles = user.getUserRole();
                        Set<GrantedAuthority> ga = new HashSet<GrantedAuthority>();
                        for (UserRole userRole: userRoles){
                            ga.add(new SimpleGrantedAuthority(userRole.getName()));
                        }
                        userModel.setRoles(ga);
                    }
                })*/

                .byDefault()
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();


        return mapper.map(user, UserModel.class);
    }

}
