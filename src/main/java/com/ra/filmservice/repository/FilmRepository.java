package com.ra.filmservice.repository;

import com.ra.filmservice.model.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends MongoRepository<Film, String> {
    @Query(value = "{onShow: ?0}")
    List<Film> findByOnShow(boolean onShow);
}
