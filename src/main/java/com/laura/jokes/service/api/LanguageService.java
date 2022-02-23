package com.laura.jokes.service.api;

import com.laura.jokes.service.model.LanguageModel;

import java.util.List;

public interface LanguageService {
    List<LanguageModel> getAll();
    LanguageModel getByLocale(String locale);
    LanguageModel getById(long id);
    LanguageModel save(LanguageModel language);

    LanguageModel updateLanguage(LanguageModel languageModel, Long languageId);

    void deleteById(Long languageId);
}
