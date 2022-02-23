package com.laura.jokes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateJokesDTO {

    @NotEmpty
    @Size(min = 2, max = 2, message = "Locale should contains only 2 characters!")
    public String languageLocale;
    @NotEmpty
    public String joke;
}
