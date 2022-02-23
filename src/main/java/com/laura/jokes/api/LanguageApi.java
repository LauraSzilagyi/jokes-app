package com.laura.jokes.api;

import com.laura.jokes.dto.AddLanguageDTO;
import com.laura.jokes.dto.LanguageDTO;
import com.laura.jokes.dto.UpdateLanguageDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/language")

public interface LanguageApi {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LanguageDTO>> getAll();

    @GetMapping(path = "/{languageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LanguageDTO> getById(@PathVariable Long languageId);

    @GetMapping(path = "/locale/{localeCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LanguageDTO> getByLocale(@PathVariable String localeCode);

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LanguageDTO> addLanguage(@RequestBody @Valid AddLanguageDTO language);

    @PatchMapping(path = "/{languageId}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LanguageDTO> updateLanguage(@RequestBody @Valid UpdateLanguageDTO language, @PathVariable Long languageId);

    @DeleteMapping(path = "/{languageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteLanguage(@PathVariable Long languageId);
}
