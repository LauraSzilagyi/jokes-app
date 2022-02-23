package com.laura.jokes.controller;

import com.laura.jokes.api.JokesApi;
import com.laura.jokes.dto.AddUpdateJokesDTO;
import com.laura.jokes.dto.JokesDTO;
import com.laura.jokes.service.api.JokesService;
import com.laura.jokes.service.model.AddUpdateJokesModel;
import com.laura.jokes.service.model.JokesModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JokesController implements JokesApi {

    private final JokesService service;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<JokesDTO>> getAll(String locale) {
        List<JokesModel> models = service.getAll(locale);;
        List<JokesDTO> response = models.stream()
                .map(model -> modelMapper.map(model, JokesDTO.class))
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<JokesDTO> getById(Long jokeId) {
        JokesModel model = service.getById(jokeId);
        JokesDTO response = modelMapper.map(model, JokesDTO.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<JokesDTO> addJokes(AddUpdateJokesDTO joke) {
        AddUpdateJokesModel jokesModel = modelMapper.map(joke, AddUpdateJokesModel.class);
        JokesModel model = service.add(jokesModel);
        JokesDTO response = modelMapper.map(model, JokesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<JokesDTO> randomJoke(String locale) {
        JokesModel model = service.getRandomJoke(locale);
        JokesDTO response = modelMapper.map(model, JokesDTO.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<JokesDTO> updateJokes(AddUpdateJokesDTO jokes, Long jokeId) {
        JokesModel model = service.update(jokes, jokeId);
        JokesDTO response = modelMapper.map(model,JokesDTO.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteJoke(Long jokeId) {
        service.deleteById(jokeId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<JokesDTO> likeJoke(Long jokeId) {
        JokesModel model = service.likeJoke(jokeId);
        JokesDTO response = modelMapper.map(model, JokesDTO.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<JokesDTO> dislikeJoke(Long jokeId) {
        JokesModel model = service.dislikeJoke(jokeId);
        JokesDTO response = modelMapper.map(model, JokesDTO.class);
        return ResponseEntity.ok(response);
    }
}
