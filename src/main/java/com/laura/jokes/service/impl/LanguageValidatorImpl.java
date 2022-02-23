package com.laura.jokes.service.impl;

import com.laura.jokes.entity.LanguageEntity;
import com.laura.jokes.entity.repository.LanguageRepository;
import com.laura.jokes.service.api.LanguageValidator;
import com.laura.jokes.service.model.LanguageModel;
import com.laura.jokes.exceptions.EntityAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LanguageValidatorImpl implements LanguageValidator {

    private final LanguageRepository repository;

    @Override
    public void validate(LanguageModel language) {
        Optional<LanguageEntity> languageEntity = repository.findByLocale(language.getLocale());
        if(languageEntity.isPresent()){
            throw new EntityAlreadyExistException(LanguageEntity.class);
        }
    }

    @Override
    public void validateUpdate(LanguageModel languageModel, Long languageId) {
        Optional<LanguageEntity> language = repository.findByLocaleAndIdNot(languageModel.getLocale(), languageId);
        if(language.isPresent()){
            throw new EntityAlreadyExistException(LanguageEntity.class);
        }
    }
}
