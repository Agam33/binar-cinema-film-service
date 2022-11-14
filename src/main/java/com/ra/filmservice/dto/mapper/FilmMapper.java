package com.ra.filmservice.dto.mapper;


import com.ra.filmservice.dto.model.FilmDTO;
import com.ra.filmservice.model.Film;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FilmMapper {

    public FilmMapper() {}

    public static FilmDTO toDto(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmCode(film.getFilmCode());
        filmDTO.setTitle(film.getTitle());
        filmDTO.setOverview(film.getOverview());
        filmDTO.setRuntime(film.getRuntime());
        filmDTO.setOnShow(film.isOnShow());
        filmDTO.setGenres(film.getGenres());
        filmDTO.setCreatedAt(film.getCreatedAt());
        filmDTO.setUpdatedAt(film.getUpdatedAt());
        filmDTO.setReleaseDate(film.getReleaseDate());
        filmDTO.setPopularity(film.getPopularity());
        return filmDTO;
    }

    public static Film toModel(FilmDTO filmDTO) {
        Film filmModel = new Film();
        filmModel.setFilmCode(filmDTO.getFilmCode());
        filmModel.setUpdatedAt(LocalDateTime.now());
        filmModel.setCreatedAt(LocalDateTime.now());
        filmModel.setOverview(filmDTO.getOverview());
        filmModel.setReleaseDate(filmDTO.getReleaseDate());
        filmModel.setOnShow(filmDTO.isOnShow());
        filmModel.setGenres(filmDTO.getGenres());
        filmModel.setTitle(filmDTO.getTitle());
        filmModel.setRuntime(filmDTO.getRuntime());
        return filmModel;
    }
}
