package com.ra.filmservice.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class FilmUpdateRequest {

    @NotNull
    private String filmCode;

    @NotNull
    private String newName;
}
