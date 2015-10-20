CREATE TABLE IF NOT EXISTS comment(
    comment_id      INTEGER UNIQUE PRIMARY KEY AUTO_INCREMENT,
    post_id         INTEGER NOT NULL,
    user_id         INTEGER NOT NULL,
    content         LONGTEXT NOT NULL,
    comment_time    DATE NOT NULL
);