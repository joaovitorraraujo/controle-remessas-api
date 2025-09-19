CREATE TABLE remittance_types (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL
);

INSERT INTO remittance_types (name) VALUES
('SIAP'),
('SIOPE'),
('SIOPS'),
('SICONFI'),
('ORÇAMENTOS');