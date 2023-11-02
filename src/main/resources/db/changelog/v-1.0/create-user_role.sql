CREATE TABLE IF NOT EXISTS task_3_1_2.user_role (
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (user_id) REFERENCES task_3_1_2.users(id),
    FOREIGN KEY (role_id) REFERENCES task_3_1_2.role(id)
);