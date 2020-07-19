CREATE TABLE IF NOT EXISTS log
(
    id      INTEGER NOT NULL,
    module  TEXT    NOT NULL,
    operate TEXT    NOT NULL,
    PRIMARY KEY (id)
)