package com.laura.jokes.service.api;

import com.laura.jokes.service.model.LanguageModel;

public interface LanguageValidator {
    void validate(LanguageModel language);

    void validateUpdate(LanguageModel languageModel, Long languageId);
}
