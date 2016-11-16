package ru.innopolis.borgatin.server.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.borgatin.server.entity.Lesson;

import java.util.List;

/**
 * Репозиторий для уроков, основан на Spring Data
 */

public interface LessonRepository extends CrudRepository<Lesson, Integer> {


    List<Lesson> findAllByOrderByTopicAsc();
    List<Lesson> findAllByOrderByTopicDesc();
    List<Lesson> findAllByTopicContaining(String filter);




}
