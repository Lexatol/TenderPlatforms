CREATE TABLE IF NOT EXISTS roles
(
    role_id   SERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    user_id       UUID PRIMARY KEY,
    user_name     VARCHAR(30),
    user_password VARCHAR(80) NOT NULL
);


CREATE TABLE IF NOT EXISTS users_roles
(
    user_id UUID NOT NULL,
    role_id int  NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

INSERT INTO roles (role_name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPERADMIN'),
       ('ROLE_MODERATOR');

INSERT INTO users (user_id, user_name, user_password)
VALUES ('694836c0-b2d0-4bbe-83dc-98c5beef1d5a', 'Mutabor',
        '$2a$12$wZtFQJ/Yn7PsqbqJqRzYCeuZ4C8DmrVoQ9ABxq9MTi3SxqoyTLyfi');--password: 900

INSERT INTO users (user_id, user_name, user_password)
VALUES ('e4e839c5-4f9b-45d2-8e06-c3e598406cf0', 'Petr', '$2a$12$EVLGvmdlvNtahtIbh51syeb4mpygK4qcQf6ds8/4rNohefMi5JK8K');


INSERT INTO users_roles (user_id, role_id)
VALUES ('694836c0-b2d0-4bbe-83dc-98c5beef1d5a', 4),
       ('e4e839c5-4f9b-45d2-8e06-c3e598406cf0', 3);
