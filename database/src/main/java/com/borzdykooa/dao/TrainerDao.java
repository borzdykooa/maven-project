package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainer;

import java.util.List;

/**
 * Интерфейс, содержащий методы работы с таблицей trainer
 */
public interface TrainerDao {

    Long save(Trainer trainer);

    Trainer find(Long id);

    List<Trainer> findAll();

    void update(Trainer trainer);

    void delete(Trainer trainer);

    List<Trainer> findByLanguage(String language);
}
