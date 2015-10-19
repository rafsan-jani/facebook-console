CREATE TABLE IF NOT EXISTS message(
    message_id        INTEGER PRIMARY KEY UNIQUE AUTO_INCREMENT,
    sender_id         INTEGER NOT NULL,
    receiver_id       INTEGER NOT NULL,
    content  LONGTEXT
);