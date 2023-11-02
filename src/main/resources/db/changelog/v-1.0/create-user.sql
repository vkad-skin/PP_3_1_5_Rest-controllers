CREATE TABLE IF NOT EXISTS task_3_1_2.users (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    age int DEFAULT NULL,
    email varchar(25) DEFAULT NULL,
    PRIMARY KEY (id)
);