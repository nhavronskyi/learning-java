CREATE TABLE IF NOT EXISTS users
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO users (name)
VALUES ('test'),
       ('test2'),
       ('test3')
