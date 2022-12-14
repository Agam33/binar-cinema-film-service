package com.ra.filmservice.service;


import com.ra.filmservice.dto.model.FilmDTO;
import com.ra.filmservice.model.Film;

import java.util.List;

public interface FilmService {
    FilmDTO add(FilmDTO filmDTO);
    FilmDTO updateName(String filmId, String newName);
    FilmDTO delete(String filmCode);
    List<FilmDTO> nowPlaying();
    List<FilmDTO> addAll(List<Film> films);
    List<FilmDTO> getAllFilm();
    FilmDTO detailFilm(String filmId);
}
