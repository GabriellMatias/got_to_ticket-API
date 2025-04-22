CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE coupon(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    coe VARCHAR(100) NOT NULL,
    discount INTEGER NOT NULL,
    event_id UUID,
    valid TIMESTAMP NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE
);