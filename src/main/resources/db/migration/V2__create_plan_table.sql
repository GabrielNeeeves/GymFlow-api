CREATE TABLE plan (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    duration INTEGER NOT NULL,
    duration_unit VARCHAR(10) NOT NULL,
    description VARCHAR(500),
    active BOOLEAN NOT NULL
);