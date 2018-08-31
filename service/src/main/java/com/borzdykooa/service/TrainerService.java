package com.borzdykooa.service;

import com.borzdykooa.dao.TrainerDao;
import com.borzdykooa.entity.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс, содержащий методы для работы с сущностью Trainer
 */
@Service
@Transactional
public class TrainerService {

    private final TrainerDao trainerDao;

    @Autowired
    public TrainerService(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public Long save(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    public Trainer find(Long id) {
        return trainerDao.find(id);
    }

    public List<Trainer> findAll() {
        return trainerDao.findAll();
    }

    public void update(Trainer trainer) {
        trainerDao.update(trainer);
    }

    public void delete(Trainer trainer) {
        trainerDao.delete(trainer);
    }

    public List<Trainer> findByLanguage(String language) {
        return trainerDao.findByLanguage(language);
    }
}
