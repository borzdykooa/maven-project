package com.borzdykooa.util;

import com.borzdykooa.entity.Trainee;
import com.borzdykooa.entity.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс для импортирования тестовых данных
 */
@Component
public class TestDataImporter {

    private final SessionFactory sessionFactory;

    @Autowired
    public TestDataImporter(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void importTestData() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Trainer andreiReut = new Trainer("Andrei Reut", "Java", 6);
            Trainer ivanIvanov = new Trainer("Ivan Ivanov", "C++", 4);
            Trainer petrPetrov = new Trainer("Petr Petrov", "C#", 5);
            session.save(andreiReut);
            session.save(ivanIvanov);
            session.save(petrPetrov);

            Trainee olgaBorzdyko = new Trainee("Olga Borzdyko", andreiReut);
            Trainee denisByhovsky = new Trainee("Denis Byhovsky", andreiReut);
            Trainee sergeiSergeev = new Trainee("Sergei Sergeev", andreiReut);
            session.save(olgaBorzdyko);
            session.save(denisByhovsky);
            session.save(sergeiSergeev);

            session.getTransaction().commit();
        }
    }

    public void deleteTestData() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Trainee ").executeUpdate();
            session.createQuery("delete from Trainer ").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
