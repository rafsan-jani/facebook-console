CREATE TABLE IF NOT EXISTS account(
    user_id       INTEGER PRIMARY KEY UNIQUE AUTO_INCREMENT,
    email         VARCHAR(100) NOT NULL,
    password      VARCHAR(100) NOT NULL
);