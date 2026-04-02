CREATE TABLE manufacturing_line(
  id UUID PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE machine(
    id UUID PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE manufacturing_line_node(
    manufacturing_line_id UUID REFERENCES manufacturing_line(id) NOT NULL,
    machine_id UUID REFERENCES machine(id) NOT NULL,
    next_machine_id UUID REFERENCES machine(id)
);

ALTER TABLE manufacturing_line_node ADD CONSTRAINT manufacturing_line_node_pk PRIMARY KEY (manufacturing_line_id, machine_id);

CREATE TABLE production_event(
    id UUID PRIMARY KEY,
    machine_id UUID REFERENCES machine(id) NOT NULL,
    quality TEXT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP
);

CREATE TABLE simulation(
    id UUID PRIMARY KEY,
    manufacturing_line_id UUID REFERENCES manufacturing_line(id) NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP
);

CREATE TABLE simulation_event(
    id UUID PRIMARY KEY,
    simulation_id UUID REFERENCES simulation(id) NOT NULL,
    type TEXT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP
);
