CREATE TABLE machine
(
    id UUID PRIMARY KEY,
    time_generator_from NUMERIC NOT NULL,
    time_generator_to   NUMERIC NOT NULL
);


CREATE TABLE simulation
(
    id                    UUID PRIMARY KEY,
    machine_id            UUID REFERENCES machine(id) ON DELETE CASCADE,
    status                TEXT NOT NULL
);

CREATE TABLE execution_events (
    simulation_id UUID REFERENCES simulation(id) ON DELETE CASCADE,
    type    TEXT NOT NULL,
    duration_in_ms BIGINT NOT NULL
);

CREATE TABLE execution
(
    simulation_id UUID PRIMARY KEY REFERENCES simulation(id) ON DELETE CASCADE,
    duration_in_ms BIGINT,
    duration_elapsed_in_ms BIGINT NOT NULL DEFAULT 0,
    pieces_pending        BIGINT,
    pieces_finished       BIGINT
);

