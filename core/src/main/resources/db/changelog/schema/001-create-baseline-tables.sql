CREATE TABLE machine
(
    id UUID PRIMARY KEY
);


CREATE TABLE simulation
(
    id                    UUID PRIMARY KEY,
    machine_id            UUID REFERENCES machine(id) ON DELETE CASCADE,
    status                TEXT NOT NULL,
    start_date            TIMESTAMP,
    pieces_pending        BIGINT,
    pieces_finished       BIGINT
);

CREATE TABLE simulation_execution_log
(
    id            UUID PRIMARY KEY,
    simulation_id UUID REFERENCES simulation(id) ON DELETE CASCADE,
    start_date    TIMESTAMP  NOT NULL,
    end_date      TIMESTAMP
);

