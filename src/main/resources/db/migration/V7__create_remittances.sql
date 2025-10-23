CREATE TABLE remittances (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    city_id UUID NOT NULL REFERENCES cities(id) ON DELETE CASCADE,
    fund_id UUID NOT NULL REFERENCES funds(id) ON DELETE CASCADE,
    remittance_type_id UUID NOT NULL REFERENCES remittance_types(id) ON DELETE CASCADE,
    remittance_period_id UUID NOT NULL REFERENCES remittance_periods(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL CHECK (status IN ('ENVIADO','NAO_ENVIADO','PENDENCIA')),
    reason_pendency TEXT,
    date_shipping DATE,
    user_id UUID REFERENCES users(id),
    UNIQUE (city_id, fund_id, remittance_period_id)
);
