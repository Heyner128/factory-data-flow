CREATE TABLE IF NOT EXISTS test_persistable_entity
(
    id UUID PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS test_with_duration_entity
(
    id       UUID PRIMARY KEY,
    duration_in_ms BIGINT NOT NULL
);