package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Trainer;
import com.borzdykooa.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Класс для тестирования методов сушности Trainer
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class TrainerServiceTest {

    @Autowired
    private TrainerService trainerService;

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
    public void testFindAll() {
        List<Trainer> trainers = trainerService.findAll();
        assertTrue(trainers.size() == 3);
    }

    @Test
    public void testFind() {
        Trainer trainer = sessionFactory.getCurrentSession().createQuery("select t from Trainer t ", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainer);
        String name = trainer.getName();

        Trainer theSameTrainer = trainerService.find(trainer.getId());
        String theSameName = theSameTrainer.getName();
        assertTrue(name.equals(theSameName));
    }

    @Test
    public void testSave() {
        Trainer nikolaiNikolaev = new Trainer("Nikolai Nikolaev", "C", 12);
        Long id = trainerService.save(nikolaiNikolaev);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testDelete() {
        Trainer trainer = sessionFactory.getCurrentSession().createQuery("select t from Trainer t where t.name like 'Ivan Ivanov'", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainer);
        trainerService.delete(trainer);

        Trainer theSameTrainer = sessionFactory.getCurrentSession().createQuery("select t from Trainer t where t.name like 'Ivan Ivanov'", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", theSameTrainer);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testUpdate() {
        Trainer trainer = sessionFactory.getCurrentSession().createQuery("select t from Trainer t where t.name like 'Ivan Ivanov' and t.language='C++'", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(trainer);

        trainer.setLanguage("Java");
        trainerService.update(trainer);

        Trainer updatedTrainer = sessionFactory.getCurrentSession().createQuery("select t from Trainer t where t.name like 'Ivan Ivanov' and t.language='Java'", Trainer.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedTrainer);
    }

    @Test
    public void testFindByLanguage() {
        List<Trainer> trainers = trainerService.findByLanguage("Java");
        assertTrue(trainers.size() == 1);
    }
}
