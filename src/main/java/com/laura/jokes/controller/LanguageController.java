package com.laura.jokes.controller;

import com.laura.jokes.service.api.LanguageService;
import com.laura.jokes.service.model.LanguageModel;
import com.laura.jokes.api.LanguageApi;
import com.laura.jokes.dto.AddLanguageDTO;
import com.laura.jokes.dto.LanguageDTO;
import com.laura.jokes.dto.UpdateLanguageDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LanguageController implements LanguageApi {

    private final LanguageService service;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<List<LanguageDTO>> getAll() {
        List<LanguageModel> languages = service.getAll();
        List<LanguageDTO> response = languages.stream()
                .map(language -> modelMapper.map(language, LanguageDTO.class))
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<LanguageDTO> getById(Long languageId) {
        LanguageModel language = service.getById(languageId);
        return getLanguageDTOResponseEntity(language);
    }

    @Override
    public ResponseEntity<LanguageDTO> getByLocale(String localeCode) {
        LanguageModel language = service.getByLocale(localeCode);
        return getLanguageDTOResponseEntity(language);
    }

    private ResponseEntity<LanguageDTO> getLanguageDTOResponseEntity(LanguageModel language) {
        LanguageDTO response = modelMapper.map(language, LanguageDTO.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<LanguageDTO> addLanguage(AddLanguageDTO language) {
        LanguageModel languageModel = modelMapper.map(language, LanguageModel.class);
        LanguageModel entity = service.save(languageModel);
        LanguageDTO response = modelMapper.map(entity, LanguageDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<LanguageDTO> updateLanguage(UpdateLanguageDTO language, Long languageId) {
        LanguageModel languageModel = modelMapper.map(language, LanguageModel.class);
        LanguageModel entity = service.updateLanguage(languageModel, languageId);
        LanguageDTO response = modelMapper.map(entity, LanguageDTO.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteLanguage(Long languageId) {
        service.deleteById(languageId);
        return ResponseEntity.ok(null);
    }
}
