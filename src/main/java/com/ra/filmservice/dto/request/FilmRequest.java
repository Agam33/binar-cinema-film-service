package com.ra.filmservice.dto.request;

import com.ra.filmservice.model.FilmGenre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class FilmRequest {

    @NotNull
    private String title;
    @NotNull
    private String overview;
    @NotNull
    private Integer runtime;
    @NotNull(message = "yyyy-MM-dd")
    private String releaseDate;
    private boolean onShow;
    private FilmGenre genre;
}