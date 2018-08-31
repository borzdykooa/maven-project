package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainer;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Класс, содержащий реализацию методов работы с таблицей trainer
 */
@Repository
public class TrainerDaoImpl implements TrainerDao {

    private static final Logger LOG = Logger.getLogger(TrainerDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public TrainerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Trainer trainer) {
        LOG.info("Method save is called in TrainerDaoImpl");
        Serializable id = sessionFactory.getCurrentSession().save(trainer);
        if (id != null) {
            LOG.info(trainer.toString() + " has been saved successfully!");
        }
        return (Long) id;
    }

    public Trainer find(Long id) {
        LOG.info("Method find is called in TrainerDaoImpl");
        Trainer trainer = sessionFactory.getCurrentSession().find(Trainer.class, id);
        if (trainer != null) {
            LOG.info(trainer.toString() + " has been found successfully");
        }
        return trainer;
    }

    public List<Trainer> findAll() {
        LOG.info("Method findAll is called in TrainerDaoImpl");
        List<Trainer> list = sessionFactory.getCurrentSession().createQuery("select t from Trainer t", Trainer.class).list();
        if (list.size() > 0) {
            LOG.info("List of Trainers: " + list.toString());
        } else {
            LOG.info("List of Trainers is empty");
        }
        return list;
    }

    public void update(Trainer trainer) {
        LOG.info("Method update is called in TrainerDaoImpl for " + trainer.toString());
        sessionFactory.getCurrentSession().update(trainer);
        LOG.info(trainer.toString() + " has been updated successfully!");
    }

    public void delete(Trainer trainer) {
        LOG.info("Method delete is called in TrainerDaoImpl");
        sessionFactory.getCurrentSession().delete(trainer);
        LOG.info(trainer.toString() + " has been deleted successfully!");
    }

    public List<Trainer> findByLanguage(String language) {
        LOG.info("Method findByLanguage is called in TrainerDaoImpl");
        List<Trainer> list = sessionFactory.getCurrentSession().createQuery("select t from Trainer t where t.language=:language", Trainer.class)
                .setParameter("language", language)
                .list();
        if (list.size() > 0) {
            LOG.info("List of Trainers: " + list.toString());
        } else {
            LOG.info("List of Trainers is empty");
        }
        return list;
    }
}
