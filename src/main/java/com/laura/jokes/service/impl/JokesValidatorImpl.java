package com.laura.jokes.service.impl;

import com.laura.jokes.entity.JokesEntity;
import com.laura.jokes.entity.repository.JokesRepository;
import com.laura.jokes.service.api.JokesValidator;
import com.laura.jokes.service.model.AddUpdateJokesModel;
import com.laura.jokes.exceptions.EntityAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JokesValidatorImpl implements JokesValidator {

    private final JokesRepository repository;

    @Override
    public void validate(AddUpdateJokesModel jokes) {

        Optional<JokesEntity> entity = repository.findByJoke(jokes.getJoke());
        if(entity.isPresent()){
            throw new EntityAlreadyExistException(JokesEntity.class);
        }
    }
}
