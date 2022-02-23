package com.laura.jokes.entity.repository;

import com.laura.jokes.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {
    Optional<LanguageEntity> findByLocale(String code);

    Optional<LanguageEntity> findByLocaleAndIdNot(String locale, Long languageId);

}
