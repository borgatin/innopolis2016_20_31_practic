package ru.innopolis.borgatin.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.borgatin.server.model.modelDAO.Lesson;
import ru.innopolis.borgatin.server.model.modelDAO.Student;

import java.util.List;

/**
 * Репозиторий для уроков, основан на Spring Data
 */

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

/*
    List<Lesson> findAllOrderByTopicAsc();
    List<Lesson> findAllOrderByTopicDesc();
    List<Lesson> findAllByTopicContaining(String filter);*/



}
