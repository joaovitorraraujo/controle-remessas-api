CREATE TABLE remittances (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type_remittance VARCHAR(50) NOT NULL,
    period TEXT[] NOT NULL,
    status VARCHAR(50) NOT NULL,
    reason_pendency VARCHAR(1000),
    date_shipping DATE,
    city_id UUID NOT NULL,
    user_id UUID,
    CONSTRAINT fk_city_remittance FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_remittance FOREIGN KEY (user_id) REFERENCES users(id)
);
