package com.laura.jokes.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateJokesModel {

    private long id;
    private String languageLocale;
    private String joke;
    private int likes;
    private int dislikes;
}
