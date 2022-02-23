package com.laura.jokes.entity.repository;

import com.laura.jokes.entity.JokesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JokesRepository extends JpaRepository<JokesEntity, Long> {
    Optional<JokesEntity> findByJoke(String joke);

    @Query("SELECT j FROM JokesEntity j " +
            " WHERE (:locale IS NULL OR j.language.locale = :locale)")
    List<JokesEntity> findAll(String locale);

    List<JokesEntity> findByLanguageLocale(String locale);

}
