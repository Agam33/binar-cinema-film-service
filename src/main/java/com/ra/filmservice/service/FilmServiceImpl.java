package com.ra.filmservice.service;

import com.ra.filmservice.dto.mapper.FilmMapper;
import com.ra.filmservice.dto.model.FilmDTO;
import com.ra.filmservice.exception.ExceptionType;
import com.ra.filmservice.model.Film;
import com.ra.filmservice.model.FilmGenre;
import com.ra.filmservice.repository.FilmRepository;
import com.ra.filmservice.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.ra.filmservice.exception.BioskopException.throwException;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmDTO add(FilmDTO filmDTO) {
        if(filmRepository
                .findById(filmDTO.getFilmCode())
                .isEmpty()) {
            Film filmModel = new Film();
            filmModel.setFilmCode(filmDTO.getFilmCode());
            filmModel.setTitle(filmDTO.getTitle());
            filmModel.setCreatedAt(LocalDateTime.now());
            filmModel.setOverview(filmDTO.getOverview());
            filmModel.setRuntime(filmDTO.getRuntime());
            filmModel.setOnShow(filmDTO.isOnShow());
            filmModel.setReleaseDate(filmDTO.getReleaseDate());

            for(FilmGenre genre : filmDTO.getGenres()) {
                filmModel.getGenres().add(genre);
            }

            filmRepository.save(filmModel);
            return filmDTO;
        }
        throw throwException(ExceptionType.DUPLICATE_ENTITY, HttpStatus.CONFLICT, Constants.ALREADY_EXIST_MSG);
    }

    @Override
    public FilmDTO updateName(String filmId, String newName) {
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            Film filmModel = film.get();
            filmModel.setUpdatedAt(LocalDateTime.now());
            filmModel.setTitle(newName);
            filmRepository.save(filmModel);
            return FilmMapper.toDto(filmModel);
        }
        throw throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, Constants.NOT_FOUND_MSG);
    }

    @Override
    public FilmDTO delete(String filmCode) {
        Optional<Film> film = filmRepository.findById(filmCode);
        if(film.isPresent()) {
            FilmDTO filmDTO = FilmMapper.toDto(film.get());
            filmRepository.delete(film.get());
            return filmDTO;
        }
        throw throwException(ExceptionType.NOT_FOUND, HttpStatus.NO_CONTENT, Constants.NOT_FOUND_MSG);
    }

    @Override
    public List<FilmDTO> nowPlaying() {
        List<Film> films = filmRepository.findByOnShow(true);
        if(!films.isEmpty()) {
            return films.stream()
                    .filter(Film::isOnShow)
                    .map(FilmMapper::toDto)
                    .toList();
        }
        throw throwException(ExceptionType.NOT_FOUND, HttpStatus.NO_CONTENT, Constants.NOT_FOUND_MSG);
    }

    @Override
    public List<FilmDTO> addAll(List<Film> films) {
        return filmRepository.saveAll(films)
                .stream()
                .map(FilmMapper::toDto)
                .toList();
    }

    @Override
    public List<FilmDTO> getAllFilm() {
        List<Film> films = filmRepository.findAll();
        if(!films.isEmpty()) {
            return films.stream()
                    .map(FilmMapper::toDto)
                    .toList();
        }
        throw throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, Constants.NOT_FOUND_MSG);
    }

    @Override
    public FilmDTO detailFilm(String filmId) {
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            return FilmMapper.toDto(film.get());
        }
        throw throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, Constants.NOT_FOUND_MSG);
    }
}
