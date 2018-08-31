package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Trainee;
import com.borzdykooa.entity.Trainer;
import com.borzdykooa.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Класс для тестирования методов сушности Trainee
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class TraineeServiceTest {

    @Autowired
    private TraineeService traineeService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TestDataImporter testDataImporter;

    @Before
    public void initDb() {
        testDataImporter.deleteTestData();
        testDataImporter.importTestData();
    }

    @Test
    public void testSave() {
        Trainer trainer = sessionFactory.getCurrentSession().createQuery("select t from Trainer t where t.name like 'Ivan Ivanov'", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainer);

        Trainee alexandrAlexandrov = new Trainee("Alexandr Alexandrov", trainer);
        Long id = traineeService.save(alexandrAlexandrov);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testDelete() {
        Trainee trainee = sessionFactory.getCurrentSession().createQuery("select t from Trainee t where t.name like 'Sergei Sergeev'", Trainee.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainee);
        traineeService.delete(trainee);
        Trainee theSameTrainee = sessionFactory.getCurrentSession().createQuery("select t from Trainee t where t.name like 'Sergei Sergeev'", Trainee.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", theSameTrainee);
    }

    @Test
    public void testUpdate() {
        Trainee trainee = sessionFactory.getCurrentSession().createQuery("select t from Trainee t where t.name like 'Sergei Sergeev' and t.trainer.name='Andrei Reut'", Trainee.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainee);
        Trainer trainer = sessionFactory.getCurrentSession().createQuery("select t from Trainer t where t.name like 'Ivan Ivanov'", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainer);

        trainee.setTrainer(trainer);
        traineeService.update(trainee);
        Trainee updatedTrainee = sessionFactory.getCurrentSession().createQuery("select t from Trainee t where t.name like 'Sergei Sergeev' and t.trainer.name='Ivan Ivanov'", Trainee.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedTrainee);
    }

    @Test
    public void testFindByTrainerName() {
        List<Trainee> trainees = traineeService.findByTrainerName("Andrei");
        assertTrue(trainees.size() == 3);
    }

    @Test
    public void testFind() {
        Trainee trainee = sessionFactory.getCurrentSession().createQuery("select t from Trainee t ", Trainee.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainee);
        String name = trainee.getName();

        Trainee theSameTrainee = traineeService.find(trainee.getId());
        String theSameName = theSameTrainee.getName();
        assertTrue(name.equals(theSameName));
    }

    @Test
    public void testFindAll() {
        List<Trainee> trainees = traineeService.findAll();
        assertTrue(trainees.size() == 3);
    }
}
