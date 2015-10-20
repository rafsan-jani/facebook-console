CREATE TABLE IF NOT EXISTS personal_info(
    user_id         INTEGER PRIMARY KEY UNIQUE,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    sex             VARCHAR(100) NOT NULL,
    date_of_birth   DATE,
    age             INTEGER
);
