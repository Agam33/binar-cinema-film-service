package com.ra.filmservice.dto.request;

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
//    @Schema(description = "input: yyyy-MM-dd", type = "string", example = "2022-10-10")
    private String releaseDate;
    private boolean onShow;
    private Integer genre;
}