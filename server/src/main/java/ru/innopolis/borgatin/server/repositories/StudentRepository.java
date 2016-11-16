package ru.innopolis.borgatin.server.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.borgatin.server.entity.Student;

import java.util.List;

/**
 * Репозиторий для студентов, основан на Spring Data
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findAllByOrderByLastnameAsc();
    List<Student> findAllByOrderByLastnameDesc();
    List<Student> findAllByLastnameContaining(String filter);
}
