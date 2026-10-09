create database taskmanagerdb;

use taskmanagerdb;

CREATE TABLE tasks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    completed BIT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE app_users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO tasks (title, description, completed)
VALUES ('Learn Spring Boot basics', 'Go through the official Spring Boot getting started guide', 0);

INSERT INTO tasks (title, description, completed)
VALUES ('Set up MySQL database', 'Install MySQL and create the taskmanagerdb schema', 1);

INSERT INTO tasks (title, description, completed)
VALUES ('Implement Spring Security', 'Secure the REST endpoints using Spring Security', 0);

INSERT INTO tasks (title, description, completed)
VALUES ('Write JPA repositories', 'Create repository interfaces for Task and User entities', 0);

INSERT INTO tasks (title, description, completed)
VALUES ('Test endpoints with Postman', 'Verify all CRUD endpoints work as expected', 0);

-- Seed user: username "admin", password "password123" (already BCrypt encoded below)
INSERT INTO app_users (username, password, role)
VALUES ('admin', '$2b$10$JX5Ydt95H9qvrdTVCn/wGeNxejaU7X8xYs2dj9mmGMsTUs9TJ7WJq', 'ROLE_USER');

SELECT * FROM tasks;
SELECT * FROM app_users;
