package com.laura.jokes.api;

import com.laura.jokes.dto.AddUpdateJokesDTO;
import com.laura.jokes.dto.JokesDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/jokes")
public interface JokesApi {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<JokesDTO>> getAll(@RequestParam(required = false) String locale);

    @GetMapping(path = "/{jokeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JokesDTO> getById(@PathVariable Long jokeId);

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JokesDTO> addJokes(@RequestBody @Valid AddUpdateJokesDTO joke);

    @GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JokesDTO> randomJoke(@RequestParam(required = false) String locale);

    @PatchMapping(path = "/{jokeId}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JokesDTO> updateJokes(@RequestBody @Valid AddUpdateJokesDTO jokes,
                                         @PathVariable Long jokeId);

    @DeleteMapping(path = "/{jokeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteJoke(@PathVariable Long jokeId);

    @PatchMapping(path = "/{jokeId}/like",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JokesDTO> likeJoke(@PathVariable Long jokeId);

    @PatchMapping(path = "/{jokeId}/dislike",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JokesDTO> dislikeJoke(@PathVariable Long jokeId);
}
