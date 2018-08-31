package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainee;

import java.util.List;

/**
 * Интерфейс, содержащий методы работы с таблицей trainee
 */
public interface TraineeDao {

    Long save(Trainee trainee);

    Trainee find(Long id);

    List<Trainee> findAll();

    void update(Trainee trainee);

    void delete(Trainee trainee);

    List<Trainee> findByTrainerName(String name);
}
