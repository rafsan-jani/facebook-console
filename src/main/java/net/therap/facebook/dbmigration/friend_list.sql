CREATE TABLE IF NOT EXISTS friend_list(
    user_id       INTEGER NOT NULL,
    friend_id     INTEGER NOT NULL,
    friendship    INTEGER,
    PRIMARY KEY(user_id,friend_id)
);
