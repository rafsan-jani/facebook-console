CREATE TABLE IF NOT EXISTS post(
    post_id       INTEGER PRIMARY KEY UNIQUE AUTO_INCREMENT,
    user_id       INTEGER NOT NULL,
    post_content  LONGTEXT,
    post_time     DATE
);
