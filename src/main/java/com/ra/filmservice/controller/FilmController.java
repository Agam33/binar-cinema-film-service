package com.ra.filmservice.controller;

import com.ra.filmservice.dto.mapper.FilmMapper;
import com.ra.filmservice.dto.model.FilmDTO;
import com.ra.filmservice.dto.reponse.Response;
import com.ra.filmservice.dto.reponse.ResponseError;
import com.ra.filmservice.dto.request.FilmRequest;
import com.ra.filmservice.exception.BioskopException;
import com.ra.filmservice.model.Film;
import com.ra.filmservice.service.FilmService;
import com.ra.filmservice.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(Constants.FILM_V1_ENDPOINT)
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<?> getAllFilm() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG,
                    filmService.getAllFilm()));
        } catch (BioskopException.EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetailFilm(@RequestParam(value = "id") String id) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG,
                    filmService.detailFilm(id)));
        } catch (BioskopException.EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    @PostMapping("/addAll")
    public ResponseEntity<?> addAllFilm(@RequestBody List<FilmRequest> filmRequests) {
        try {

            List<Film> filmModels = filmRequests.stream()
                    .map(this::filmRequestToDto)
                    .map(FilmMapper::toModel)
                    .toList();
            filmService.addAll(filmModels);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG,
                    null));
        } catch (BioskopException.DuplicateEntityException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    @GetMapping("/nowPlaying")
    public ResponseEntity<?> nowPlaying() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG, filmService.nowPlaying()));
        } catch (BioskopException.EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()),
                    e.getStatusCode());
        }
    }

    private FilmDTO filmRequestToDto(FilmRequest filmRequest) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmCode(getFilmCode(filmRequest.getTitle()));
        filmDTO.setTitle(filmRequest.getTitle());
        filmDTO.setOverview(filmRequest.getOverview());
        filmDTO.setRuntime(filmRequest.getRuntime());
        filmDTO.setOnShow(filmRequest.isOnShow());
        filmDTO.setReleaseDate(LocalDate.parse(filmRequest.getReleaseDate()));
        filmDTO.getGenres().add(filmRequest.getGenre());
        return filmDTO;
    }


    private String getFilmCode(String filmTitle) {
        String[] codes = Constants.randomIdentifier(filmTitle);
        return "film" +
                "-" +
                codes[1];
    }
}
