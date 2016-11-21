package ru.innopolis.borgatin.server.mapping;


import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.innopolis.borgatin.common.model.UserModel;
import ru.innopolis.borgatin.server.entity.User;
import ru.innopolis.borgatin.server.entity.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.innopolis.borgatin.common.MainConst.*;


/**
 * Класс предназначен для маппинга из типа User(не должен уходить дальше DAO)
 * в тип UserModel(используется во view)
 */
@Component
public class UsersMapping {

    private MapperFactory mapperFactory;

    public UsersMapping()  {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();


        mapperFactory.classMap(User.class, UserModel.class)
                .field(SQL_FIELD_USERNAME,SQL_FIELD_USERNAME)
                .field(CONST_PASSWORD_FIELD,CONST_PASSWORD_FIELD)
                .field(CONST_VERSION_FIELD,CONST_VERSION_FIELD)
                .customize(new CustomMapper<User, UserModel>() {
                    @Override
                    public void mapAtoB(User user, UserModel userModel, MappingContext context) {
                        Set<UserRole> userRoles = user.getUserRole();
                        Set<GrantedAuthority> ga = new HashSet<GrantedAuthority>();
                        for (UserRole userRole: userRoles){
                            ga.add(new SimpleGrantedAuthority(userRole.getName()));
                        }
                        userModel.setRoles(ga);
                    }
                })

                .byDefault()
                .register();


        this.mapperFactory = mapperFactory ;
    }

    /**
     * Переобразует объект User в объект UserModel
     * @param user объект User, который нужно преобразовать
     * @return результат преобразования - объект UserModel
     */
    public UserModel makeMapping(User user) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(user, UserModel.class);
    }
    /**
     * Переобразует объект UserModel в объект User
     * @param UserModel объект UserModel, который нужно преобразовать
     * @return результат преобразования - объект User
     */
    public User  makeMapping(UserModel UserModel) {
        MapperFacade mapper = mapperFactory.getMapperFacade();

        return mapper.map(UserModel, User.class);
    }

    /**
     * Переобразует список объектов User в список объектов UserModel
     * @param users список объектов User, который нужно преобразовать
     * @return результат преобразования - список объектов UserModel
     */
    public List<UserModel> makeMapping(List<User> users) {
        List<UserModel> userModels = new ArrayList<>();
        for (User user: users){
            userModels.add(makeMapping(user));
        }
        return userModels;
    }
}
