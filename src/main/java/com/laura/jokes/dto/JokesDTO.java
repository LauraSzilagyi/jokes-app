package com.laura.jokes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JokesDTO {

    private long id;
    private LanguageDTO language;
    private String joke;
    private int likes;
    private int dislikes;
}
