package ru.innopolis.borgatin.server.repositories;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;
import ru.innopolis.borgatin.server.entity.User;

/**
 * Репозиторий для пользователей, основан на Spring Data
 */

public interface UserRepository extends CrudRepository<User,String> {


}

