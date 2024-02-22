CREATE SCHEMA IF NOT EXISTS task_3_1_2;

CREATE TABLE IF NOT EXISTS task_3_1_2.users (
    id       int         NOT NULL AUTO_INCREMENT,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    password varchar(255) NOT NULL,
    age      int         DEFAULT NULL,
    email    varchar(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS task_3_1_2.role (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS task_3_1_2.user_role (
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (user_id) REFERENCES task_3_1_2.users(id),
    FOREIGN KEY (role_id) REFERENCES task_3_1_2.role(id)
);

INSERT IGNORE INTO task_3_1_2.role (id, name) VALUES (1, 'ROLE_USER');
INSERT IGNORE INTO task_3_1_2.role (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT IGNORE INTO task_3_1_2.users
    (id, first_name, last_name, password, age, email) values (1, 'user', 'user'
                                            , '$2a$12$QuEk.GqqVcWrh8Y6r4b3nuPsK6rQanYkcV.1QY7KUpu0Z/iGpJUPK'
                                            , '20', 'user@gmail.com');

INSERT IGNORE INTO task_3_1_2.users
    (id, first_name, last_name, password, age, email) values (2 ,'admin', 'admin'
                                             , '$2a$12$4VW4E0WohXfWDO.fZ7bThOnmMDw3dNUFEYX4d556YGmOIem5Bhd3m'
                                             , '25', 'admin@gmail.com');

INSERT IGNORE INTO task_3_1_2.user_role (user_id, role_id) values ('1', '1');
INSERT IGNORE INTO task_3_1_2.user_role (user_id, role_id) values ('2', '1');
INSERT IGNORE INTO task_3_1_2.user_role (user_id, role_id) values ('2', '2');