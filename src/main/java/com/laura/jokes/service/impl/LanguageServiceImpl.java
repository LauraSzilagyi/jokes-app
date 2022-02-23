package com.laura.jokes.service.impl;

import com.laura.jokes.entity.LanguageEntity;
import com.laura.jokes.entity.repository.LanguageRepository;
import com.laura.jokes.service.api.LanguageService;
import com.laura.jokes.service.api.LanguageValidator;
import com.laura.jokes.service.model.LanguageModel;
import com.laura.jokes.exceptions.LanguageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;
    private final ModelMapper modelMapper;
    private final LanguageValidator validator;

    @Override
    public List<LanguageModel> getAll() {
        List<LanguageEntity> languages = repository.findAll();
        return languages.stream()
                .map(language -> modelMapper.map(language, LanguageModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public LanguageModel getByLocale(String locale) {
        LanguageEntity language = repository.findByLocale(locale)
                .orElseThrow(() -> new LanguageNotFoundException(locale));
        return modelMapper.map(language, LanguageModel.class);
    }

    @Override
    public LanguageModel getById(long id) {
        LanguageEntity language = repository.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id));
        return modelMapper.map(language, LanguageModel.class);

    }

    @Override
    public LanguageModel save(LanguageModel language) {
        validator.validate(language);
        LanguageEntity entity = modelMapper.map(language, LanguageEntity.class);
        repository.save(entity);
        return modelMapper.map(entity, LanguageModel.class);
    }

    @Override
    public LanguageModel updateLanguage(LanguageModel languageModel, Long languageId) {
        validator.validateUpdate(languageModel, languageId);
        LanguageEntity entity = repository.findById(languageId)
                .orElseThrow(() -> new LanguageNotFoundException(languageId));

        entity.setLocale(languageModel.getLocale());
        entity.setDescription(languageModel.getDescription());
        repository.save(entity);

        return modelMapper.map(entity, LanguageModel.class);
    }

    @Override
    public void deleteById(Long languageId) {
        repository.deleteById(languageId);
    }
}
