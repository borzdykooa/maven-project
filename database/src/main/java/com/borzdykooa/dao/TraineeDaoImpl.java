package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainee;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Класс, содержащий реализацию методов работы с таблицей trainee
 */
@Repository
public class TraineeDaoImpl implements TraineeDao {

    private static final Logger LOG = Logger.getLogger(TraineeDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public TraineeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Trainee trainee) {
        LOG.info("Method save is called in TraineeDao");
        Serializable id = sessionFactory.getCurrentSession().save(trainee);
        if (id != null) {
            LOG.info(trainee.toString() + " has been saved successfully!");
        }
        return (Long) id;
    }

    public Trainee find(Long id) {
        LOG.info("Method find is called in TraineeDao");
        Trainee trainee = sessionFactory.getCurrentSession().find(Trainee.class, id);
        if (trainee != null) {
            LOG.info(trainee.toString() + " has been found successfully!");
        }
        return trainee;
    }

    public List<Trainee> findAll() {
        LOG.info("Method findAll is called in TraineeDao");
        List<Trainee> list = sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM trainee", Trainee.class).list();
        if (list.size() > 0) {
            LOG.info("List of Trainees: " + list.toString());
        } else {
            LOG.info("List of Trainees is empty");
        }
        return list;
    }

    public void update(Trainee trainee) {
        LOG.info("Method update is called in TraineeDao for " + trainee.toString());
        sessionFactory.getCurrentSession().update(trainee);
        LOG.info(trainee.toString() + "' has been updated successfully!");
    }

    public void delete(Trainee trainee) {
        LOG.info("Method delete is called in TraineeDao");
        sessionFactory.getCurrentSession().delete(trainee);
        LOG.info(trainee.toString() + "' has been deleted successfully!");
    }

    public List<Trainee> findByTrainerName(String name) {
        LOG.info("Method findByTrainerName is called in TraineeDao");
        List<Trainee> list = sessionFactory.getCurrentSession().createQuery("select t from Trainee t join fetch t.trainer tr where tr.name like :name", Trainee.class)
                .setParameter("name", "%" + name + "%")
                .list();
        if (list.size() > 0) {
            LOG.info("List of Trainees: " + list.toString());
        }
        return list;
    }
}
