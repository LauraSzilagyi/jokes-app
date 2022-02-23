package com.laura.jokes.service.api;

import com.laura.jokes.service.model.AddUpdateJokesModel;

public interface JokesValidator {

    void validate(AddUpdateJokesModel jokes);
}
