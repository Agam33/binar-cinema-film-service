package com.ra.filmservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FilmEntity {
//    @Id
//    @Column(name = "film_code")
    private String filmCode;

//    @Column(name = "title")
    private String title;

//    @Column(name = "runtime")
    private Integer runtime;

//    @Column(name = "on_show")
    private boolean onShow;

//    @Column(name = "release_date")
    private LocalDate releaseDate;

//    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

//    @Column(name = "popularity")
    private Integer popularity;

//    @JsonIgnore
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

//    @JsonIgnore
//    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;
}
