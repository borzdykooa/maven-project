CREATE DATABASE maven_database;
USE maven_database;

CREATE TABLE trainer (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(32) NOT NULL,
  language   VARCHAR(16) NOT NULL,
  experience INTEGER     NOT NULL
);

CREATE TABLE trainee (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(32) NOT NULL,
  trainer_id BIGINT REFERENCES maven_database.trainer (id)
);

INSERT INTO maven_database.trainer (name, language, experience) VALUES
  ('Andrei Reut', 'Java', 6),
  ('Ivan Ivanov', 'C++', 4),
  ('Petr Petrov', 'C#', 5);

INSERT INTO maven_database.trainee (name, trainer_id) VALUES
  ('Olga Borzdyko', (SELECT id
                     FROM maven_database.trainer
                     WHERE trainer.name = 'Andrei Reut')),
  ('Denis Byhovsky', (SELECT id
                      FROM maven_database.trainer
                      WHERE trainer.name = 'Andrei Reut')),
  ('Sergei Sergeev', (SELECT id
                      FROM maven_database.trainer
                      WHERE trainer.name = 'Andrei Reut'));

