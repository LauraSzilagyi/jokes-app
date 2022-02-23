package com.laura.jokes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddLanguageDTO {

    @NotEmpty
    @Size(min = 2, max = 2, message = "Locale code should contain only two character")
    private String locale;

    private String description;
}
