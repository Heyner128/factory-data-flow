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

CREATE TABLE simulation_state (
    simulation_id UUID PRIMARY KEY REFERENCES simulation(id) ON DELETE CASCADE,
    pieces_pending        BIGINT DEFAULT 0,
    pieces_finished       BIGINT DEFAULT 0
);

CREATE TABLE simulation_state_event (
    simulation_id UUID PRIMARY KEY REFERENCES simulation_state(simulation_id) ON DELETE CASCADE
);

CREATE TABLE simulation_execution_log
(
    simulation_id UUID PRIMARY KEY REFERENCES simulation(id) ON DELETE CASCADE,
    start_date    TIMESTAMP  NOT NULL,
    end_date      TIMESTAMP
);

