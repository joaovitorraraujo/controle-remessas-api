CREATE TABLE funds (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL
);

INSERT INTO funds (name) VALUES
('Prefeitura'),
('Assistência Social'),
('Saúde'),
('Educação'),
('Câmara');