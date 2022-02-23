package com.laura.jokes.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageModel {
    private long id;
    private String locale;
    private String description;

    public void setLocale(String locale) {
        this.locale = locale.toUpperCase();
    }
}
