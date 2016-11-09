package ru.innopolis.borgatin.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.borgatin.server.model.modelDAO.User;

/**
 * Репозиторий для пользователей, основан на Spring Data
 */
public interface UserRepository extends CrudRepository<User,String> {


}
