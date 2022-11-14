package com.ra.filmservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("film")
public class Film {
    @Id private String filmCode;
    @TextIndexed private String title;
    private Integer runtime;
    private boolean onShow;
    private LocalDate releaseDate;
    private String overview;
    private Double popularity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<FilmGenre> genres = new ArrayList<>();
}
