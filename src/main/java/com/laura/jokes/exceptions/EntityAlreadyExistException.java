package com.laura.jokes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(Class<?> entity) {
        super(String.format("%s already exist!", entity.getSimpleName()));
    }
}
