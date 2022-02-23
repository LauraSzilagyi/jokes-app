package com.laura.jokes.service.impl;

import com.laura.jokes.dto.AddUpdateJokesDTO;
import com.laura.jokes.entity.JokesEntity;
import com.laura.jokes.entity.LanguageEntity;
import com.laura.jokes.entity.repository.JokesRepository;
import com.laura.jokes.entity.repository.LanguageRepository;
import com.laura.jokes.exceptions.LanguageNotFoundException;
import com.laura.jokes.exceptions.NoJokesException;
import com.laura.jokes.service.api.JokesService;
import com.laura.jokes.service.api.JokesValidator;
import com.laura.jokes.service.model.AddUpdateJokesModel;
import com.laura.jokes.service.model.JokesModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JokesServiceImpl implements JokesService {

    private final JokesRepository repository;
    private final ModelMapper modelMapper;
    private final JokesValidator validator;
    private final LanguageRepository languageRepository;

    @Override
    public List<JokesModel> getAll(String locale) {
        List<JokesEntity> entities = repository.findAll(locale);
        return entities.stream()
                .map(entity -> modelMapper.map(entity, JokesModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JokesModel getById(Long jokeId) {
        JokesEntity entity = this.getJokesEntityById(jokeId);
        return modelMapper.map(entity, JokesModel.class);
    }

    @Override
    public List<JokesModel> getByLocale(String locale) {
        List<JokesEntity> entities = repository.findByLanguageLocale(locale);
        return entities.stream()
                .map(entity -> modelMapper.map(entity, JokesModel.class)).toList();
    }

    @Override
    public JokesModel add(AddUpdateJokesModel jokesModel) {
        validator.validate(jokesModel);
        LanguageEntity languageEntity = getLanguageEntityByLocale(jokesModel.getLanguageLocale());
        JokesEntity entity = new JokesEntity();
        entity.setJoke(jokesModel.getJoke());
        entity.setLanguage(languageEntity);
        repository.save(entity);
        return modelMapper.map(entity, JokesModel.class);
    }

    @Override
    public JokesModel getRandomJoke(String locale) {
        List<JokesModel> byLocale = getByLocale(locale);
        if (byLocale.isEmpty()) {
            throw new NoJokesException();
        }
        int randomNumber = getRandomNumber(byLocale.size());
        return byLocale.get(randomNumber);
    }

    private int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    @Override
    public JokesModel update(AddUpdateJokesDTO jokes, Long jokeId) {
        JokesEntity entity = getJokesEntityById(jokeId);
        LanguageEntity languageEntity = getLanguageEntityByLocale(jokes.getLanguageLocale());
        entity.setJoke(jokes.getJoke());
        entity.setLanguage(languageEntity);
        repository.save(entity);
        return modelMapper.map(entity, JokesModel.class);
    }

    @Override
    public void deleteById(Long jokeId) {
        JokesEntity entity = getJokesEntityById(jokeId);
        repository.delete(entity);
    }

    @Override
    public JokesModel likeJoke(Long jokeId) {
        JokesEntity entity = getJokesEntityById(jokeId);
        entity.incrementLikeByOne();
        repository.save(entity);
        return modelMapper.map(entity, JokesModel.class);
    }

    @Override
    public JokesModel dislikeJoke(Long jokeId) {
        JokesEntity entity = getJokesEntityById(jokeId);
        entity.incrementDislikeByOne();
        repository.save(entity);
        return modelMapper.map(entity, JokesModel.class);
    }

    private JokesEntity getJokesEntityById(Long jokeId) {
        return repository.findById(jokeId)
                .orElseThrow(() -> new EntityNotFoundException("Entity with this Id doesn't exist!"));
    }

    private LanguageEntity getLanguageEntityByLocale(String languageLocale) {
        return languageRepository.findByLocale(languageLocale)
                .orElseThrow(() -> new LanguageNotFoundException(languageLocale));
    }
}
