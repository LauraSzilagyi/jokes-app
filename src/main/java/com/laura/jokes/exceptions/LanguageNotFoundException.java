package com.laura.jokes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LanguageNotFoundException extends RuntimeException{

    public LanguageNotFoundException(String locale) {
        super("Language not found with locale: " + locale);
    }

    public LanguageNotFoundException(Long id){
        super("Language not found with id: " + id);
    }
}
