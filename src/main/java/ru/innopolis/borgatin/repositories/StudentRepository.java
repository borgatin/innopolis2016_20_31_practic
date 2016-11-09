package ru.innopolis.borgatin.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.borgatin.server.model.modelDAO.Student;

import java.util.List;

/**
 * Репозиторий для студентов, основан на Spring Data
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {

/*
    List<Student> findAllOrderByLastnameAsc();
    List<Student> findAllOrderByLastnameDesc();
    List<Student> findAllByLastnameContaining(String filter);*/
}
