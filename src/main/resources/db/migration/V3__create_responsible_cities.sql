CREATE TABLE responsible_cities (
    city_id UUID NOT NULL,
    user_id UUID NOT NULL,
    PRIMARY KEY (city_id, user_id),
    CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
