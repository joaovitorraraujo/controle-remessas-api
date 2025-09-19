-- Cria função que gera remessas automáticas ao criar um novo município
CREATE OR REPLACE FUNCTION create_initial_remittances()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO remittances (city_id, fund_id, remittance_type_id, remittance_period_id, status)
  SELECT
    NEW.id AS city_id,
    f.id AS fund_id,
    rt.id AS remittance_type_id,
    rp.id AS remittance_period_id,
    'NAO_ENVIADO' AS status
  FROM funds f
  CROSS JOIN remittance_periods rp
  JOIN remittance_types rt ON rt.id = rp.remittance_type_id;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Cria trigger que executa a função após inserir um município
CREATE TRIGGER trg_create_remittances_on_city
AFTER INSERT ON cities
FOR EACH ROW
EXECUTE FUNCTION create_initial_remittances();
