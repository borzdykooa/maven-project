package com.borzdykooa.service;

import com.borzdykooa.dao.TraineeDao;
import com.borzdykooa.entity.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс, содержащий методы для работы с сущностью Trainee
 */
@Service
@Transactional
public class TraineeService {

    private final TraineeDao traineeDao;

    @Autowired
    public TraineeService(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    public Long save(Trainee trainee) {
        return traineeDao.save(trainee);
    }

    public Trainee find(Long id) {
        return traineeDao.find(id);
    }

    public List<Trainee> findAll() {
        return traineeDao.findAll();
    }

    public void update(Trainee trainee) {
        traineeDao.update(trainee);
    }

    public void delete(Trainee trainee) {
        traineeDao.delete(trainee);
    }

    public List<Trainee> findByTrainerName(String name) {
        return traineeDao.findByTrainerName(name);
    }
}
