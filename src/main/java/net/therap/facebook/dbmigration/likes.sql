CREATE TABLE IF NOT EXISTS likes(
    post_id       INTEGER,
    user_id       INTEGER,
    PRIMARY KEY(post_id,user_id)
);