package com.laura.jokes.service.api;

import com.laura.jokes.service.model.AddUpdateJokesModel;
import com.laura.jokes.service.model.JokesModel;
import com.laura.jokes.dto.AddUpdateJokesDTO;

import java.util.List;

public interface JokesService {
    List<JokesModel> getAll(String locale);

    JokesModel getById(Long jokeId);

    List<JokesModel> getByLocale(String locale);

    JokesModel add(AddUpdateJokesModel jokesModel);

    JokesModel getRandomJoke(String locale);

    JokesModel update(AddUpdateJokesDTO jokes, Long jokeId);

    void deleteById(Long jokeId);

    JokesModel likeJoke(Long jokeId);

    JokesModel dislikeJoke(Long jokeId);
}
