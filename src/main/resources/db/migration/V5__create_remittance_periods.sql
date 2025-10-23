CREATE TABLE remittance_periods (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    remittance_type_id UUID NOT NULL REFERENCES remittance_types(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL
);

-- SIAP - 12 mensais
INSERT INTO remittance_periods (remittance_type_id, name)
SELECT id, n || 'º Mês' FROM remittance_types, generate_series(1,12) n WHERE name = 'SIAP';

-- SIOPE - 6 bimestres
INSERT INTO remittance_periods (remittance_type_id, name)
SELECT id, n || 'º Bim' FROM remittance_types, generate_series(1,6) n WHERE name = 'SIOPE';

-- SIOPS - 6 bimestres
INSERT INTO remittance_periods (remittance_type_id, name)
SELECT id, n || 'º Bim' FROM remittance_types, generate_series(1,6) n WHERE name = 'SIOPS';

-- SICONFI - 10 períodos (1º bim, 2º bim, 1º quad, 3º bim, 4º bim, 2º quad, 5º bim, 6º bim, 3º quad, DCA)
INSERT INTO remittance_periods (remittance_type_id, name)
SELECT id, periodo FROM remittance_types,
unnest(ARRAY['1º Bim','2º Bim','1º Quad','3º Bim','4º Bim','2º Quad','5º Bim','6º Bim','3º Quad','DCA'])
AS periodo WHERE name = 'SICONFI';

-- ORÇAMENTOS - 3 períodos
INSERT INTO remittance_periods (remittance_type_id, name)
SELECT id, periodo FROM remittance_types,
unnest(ARRAY['PPA - REVISÃO 2026/2026','LDO 2026','LOA 2026'])
AS periodo WHERE name = 'ORÇAMENTOS';